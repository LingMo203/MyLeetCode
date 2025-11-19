package src.leetcode.test;

public class Recursion {
    public static void main(String[] args) {
        int a=5;
        Recursion recursion=new Recursion();
        //System.out.println(recursion.fact(a));
        System.out.println(recursion.myPow(2,1000000));
    }

    //阶乘
    public int fact(int a){
        if (a==1)
            return a;
        return a*fact(a-1);
    }

    //50.Pow(x,n) 看不明白答案 放弃
    public double myPow(double x, int n) {
        if (n==1)
            return x;
        else if (n==0)
            return 1;
        return x*myPow(x,(Math.abs(n)-1)*(Math.abs(n)/n));
    }

    //326. 3 的幂
    public boolean isPowerOfThree(int n) {
        return false;
    }
}
