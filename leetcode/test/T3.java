package src.leetcode.test;

public class T3 {
    public static void main(String[] args) {
        Solution3 solution=new Solution3();
        int a=1534236469;
    }
}
class Solution3 {

    //7. 整数反转
    public int reverse(int x) {
        boolean f=false;
        if (x<0){
            x=x*-1;
            f=true;
        }
        String num=Integer.toString(x);
        int length=num.length();
        int result=0;
        for (int i=length-1;i>=0;i--){
            if (result>=214748365)
                return 0;
            result=result*10+(num.charAt(i)-'0');
        }
        return !f ? result:result*-1;
    }
}
