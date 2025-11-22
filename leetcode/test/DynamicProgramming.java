package src.leetcode.test;

public class DynamicProgramming {
    public static void main(String[] args) {
        DynamicProgramming dp=new DynamicProgramming();
        int[] nums={0,2,2,1};
        //System.out.println(dp.fib(1));
        System.out.println(dp.minCostClimbingStairs(nums));
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










}
