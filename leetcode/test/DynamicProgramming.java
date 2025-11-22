package src.leetcode.test;

public class DynamicProgramming {
    public static void main(String[] args) {
        DynamicProgramming dp=new DynamicProgramming();
        System.out.println(dp.fib(1));
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










}
