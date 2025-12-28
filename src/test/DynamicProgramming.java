package test;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {
    public static void main(String[] args) {
        DynamicProgramming dp=new DynamicProgramming();
        int[] nums={2,3,1,1,4};
        //int[][] dnums={{0,0,0,0},{0,1,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,0}};
        int[][] dnums={{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        String strs="aaabaaaabaaabaaaabaaaabaaaabaaaaba";
        String strs2="aaaba";
        //System.out.println(dp.fib(1));
        //System.out.println(dp.minCostClimbingStairs(nums));
        //System.out.println(dp.uniquePaths(5,5));
        //System.out.println(dp.uniquePathsWithObstacles(dnums));
        //System.out.println(dp.generate(5));
        //System.out.println(dp.getRow(5));
        //System.out.println(dp.maxProfit(nums));
        //System.out.println(dp.maxRepeating(strs,strs2));
        //System.out.println(dp.canJump(nums));
        //System.out.println(dp.maxSubArray(nums));
        //System.out.println(dp.jump(nums));
        System.out.println(dp.minPathSum(dnums));
    }


    //509. 斐波那契数
    public int fib(int n) {
        if (n==0) return 0;
        int[] fb=new int[2];
        fb[0]=0;
        fb[1]=1;
        for (int i=2;i<=n;i++){
            int sum=fb[0]+fb[1];
            fb[0]=fb[1];
            fb[1]=sum;
        }
        return fb[1];
    }

    //70. 爬楼梯
    public int climbStairs(int n) {
        if (n==1) return 1;
        int[] fb=new int[2];
        fb[0]=1;
        fb[1]=2;
        for (int i=3;i<=n;i++){
            int sum=fb[0]+fb[1];
            fb[0]=fb[1];
            fb[1]=sum;
        }
        return fb[1];
    }

    //746. 使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        int[] dp=new int[cost.length+1];
        for (int i=2;i<dp.length;i++){
            dp[i]=Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }

    //62. 不同路径
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[Math.max(m,n)+1][Math.max(m,n)+1];
        for (int i=0;i<=Math.max(m,n);i++){
            dp[1][i]=1;
            dp[i][1]=1;
        }
        for (int i=2;i<=m;i++){
            for (int j=2;j<=n;j++){
                dp[i][j]=dp[i][j-1]+dp[i-1][j];
            }
        }
        return dp[m][n];
    }

    //63. 不同路径II 太艰难了 12次提交
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length-1,n=obstacleGrid[0].length-1;
        if (obstacleGrid[m][n]==1||obstacleGrid[0][0]==1) return 0;
        int[][] dp=new int[m+1][n+1];
        for (int i=0;i<=m;i++){
            if (obstacleGrid[i][0]==1) {
                dp[i][0] = 0;
                break;
            }
            else dp[i][0]=1;
        }
        for (int i=0;i<=n;i++){
            if (obstacleGrid[0][i]==1) {
                dp[0][i] = 0;
                break;
            }
            else dp[0][i]=1;
        }

        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (obstacleGrid[i][j]==1) dp[i][j]=0;
                else dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m][n];
    }


    //118. 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result=new ArrayList<>();
        if (numRows>=1)
            result.add(new ArrayList<>(List.of(1)));
        if (numRows>=2)
            result.add(new ArrayList<>(List.of(1,1)));
        for (int i=1;i<=numRows-2;i++){
            List<Integer> list=new ArrayList<>();
            list.add(1);
            List<Integer> temp= result.get(i);
            for (int j=1;j<=i;j++){
                list.add(temp.get(j-1)+temp.get(j));
            }
            list.add(1);
            result.add(list);
        }
        return result;
    }

    //119. 杨辉三角II
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        if (rowIndex==0) return list;
        list.add(1);
        for (int i=1;i<=rowIndex-1;i++){
            List<Integer> temp=new ArrayList<>();
            temp.add(1);
            for (int j=1;j<=i;j++){
                temp.add(list.get(j-1)+list.get(j));
            }
            temp.add(1);
            list=temp;
        }
        return list;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int[] dp=new int[prices.length];
        dp[0]=prices[0];
        int min=prices[0],max=0;
        for (int i=1;i<prices.length;i++){
            min=Math.min(min,prices[i]);
            int x=prices[i] - min;
            dp[i]= Math.max(x, prices[i] - prices[i-1]);
            max=Math.max(dp[i],max);
        }
        return max;
    }


    //1137. 第 N 个泰波那契数
    public int tribonacci(int n) {
        if (n==0) return 0;
        else if (n==1||n==2) return 1;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=1;
        for (int i=3;i<=n;i++){
            dp[i]=dp[i-3]+dp[i-2]+dp[i-1];
        }
        return dp[n];
    }


    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        for (int i=1;i<nums.length;i++){
            if (dp[0]==0) return false;
            dp[i]=Math.max(nums[i],dp[i-1]-1);
            if (dp[i]==0&&i!=nums.length-1) return false;
        }
        return true;
    }


    //53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int[] dp=new int[nums.length];
        int max=nums[0];
        dp[0]=nums[0];
        for (int i=1;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            max=Math.max(dp[i],max);
        }
        return max;
    }

    //233. 数字 1 的个数 超时
    public int countDigitOne(int n) {
        if (n==0) return 0;
        int[] dp=new int[2];
        dp[0]=1;
        for (int i=2;i<=n;i++){
            String str=String.valueOf(i);
            int count=0;
            for (int j=0;j<str.length();j++){
                if (str.charAt(j)=='1'){
                    count++;
                }
            }
            dp[0]=dp[1];
            dp[1]=dp[0]+count;
        }
        return dp[1];
    }

    //45. 跳跃游戏II
    public int jump(int[] nums) {
        int length=nums.length;
        int i=length-1;
        int count=0;
        while (i>0){
            int index=i;
            for (int j=i-1;j>=0;j--){
                int k=i-j;
                if (nums[j]>=k){
                    index=j;
                }
            }
            count++;
            i=index;
        }
        return count;
    }

    //64. 最小路径和
    public int minPathSum(int[][] grid) {
        int m=grid[0].length,n=grid.length;
        int[][] dp=new int[n][m];
        dp[0][0]=grid[0][0];
        for (int i=1;i<m;i++){
            dp[0][i]=grid[0][i]+dp[0][i-1];
        }
        for (int i=1;i<n;i++){
            dp[i][0]=grid[i][0]+dp[i-1][0];
        }
        for (int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                dp[i][j]=grid[i][j]+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[n-1][m-1];
    }



}
