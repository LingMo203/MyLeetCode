package src.leetcode.test;

public class Recursion {
    public static void main(String[] args) {
        int a=5;
        Recursion recursion=new Recursion();
        //System.out.println(recursion.fact(a));
        //System.out.println(recursion.myPow(2,1000000));
        //System.out.println(recursion.isPowerOfFour(4));
        System.out.println(recursion.gcd(12,32));
    }

    //阶乘
    public int fact(int a){
        if (a==1)
            return a;
        return a*fact(a-1);
    }

    //最大公约数
    public int gcd(int a,int b){
        if (b==0) return a;
        return gcd(b,a%b);
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
        return n==1?true:pot(3,n)==1;
    }
    public int pot(long n,int x){
        if (n==x) return 1;
        else if (n>x) return 0;
        return pot(n*3,x);
    }

    //342. 4 的幂
    public boolean isPowerOfFour(int n) {
        return n==1?true:pof(4,n)==1;
    }
    public int pof(long n,int x){
        if (n==x) return 1;
        else if (n>x) return 0;
        return pof(n*4,x);
    }

    //




}
