package src.leetcode.test;

import java.util.ArrayList;
import java.util.HashSet;

public class DynamicProgramming {
    public static void main(String[] args) {
        DynamicProgramming dp=new DynamicProgramming();
        int[] nums={0,2,2,1};
        //int[][] dnums={{0,0,0,0},{0,1,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,0}};
        int[][] dnums={{0,0},{1,1},{0,0}};
        //System.out.println(dp.fib(1));
        //System.out.println(dp.minCostClimbingStairs(nums));
        //System.out.println(dp.uniquePaths(5,5));
        System.out.println(dp.uniquePathsWithObstacles(dnums));

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










}
