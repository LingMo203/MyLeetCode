package test;


public class Recursion {
    public static void main(String[] args) {
        int a=5;
        Recursion recursion=new Recursion();
        //System.out.println(recursion.fact(a));
        //System.out.println(recursion.myPow(2,1000000));
        //System.out.println(recursion.isPowerOfFour(4));
        System.out.println(recursion.gcd(12,32));
        System.out.println(12*32/recursion.gcd(12,32));
    }

    //阶乘
    public int fact(int a){
        if (a==1)
            return a;
        return a*fact(a-1);
    }

    //最大公约数
//    public int gcd(int a,int b){
//        if (b==0) return a;
//        return gcd(b,a%b);
//    }



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

    //509. 斐波那契数
    public int fib(int n) {
        if (n==1) return 1;
        if (n==0) return 0;
        return fib(n-1)+fib(n-2);
    }

    public int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public double myPow(double x, int n) {
        //return n >= 0 ? quickPow(x, n) : 1 / quickPow(x, -n);
        return n >= 0 ? qPow(x, n) : 1 / qPow(x, -(long) n);
    }
    private double quickPow(double x, int n) {
        if (n == 1) return x;
        else if (n == 0) return 1;
        double num = quickPow(x, n / 2);
        return n % 2 == 0 ? num * num : num * num * x;
    }
    private double qPow(double x, long n) {
        double res = 1;
        while (n != 0) {
            if (n % 2 == 1) res *= x;
            x *= x;
            n /= 2;
        }
        return res;
    }





}
