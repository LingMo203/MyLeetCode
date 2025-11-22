package src.leetcode.test;

public class T3 {
    public static void main(String[] args) {
        Solution3 solution=new Solution3();
        int a=1534236469;
        String s="-2147483648";
        String h="-2147483647";
        String b= String.valueOf(Integer.MAX_VALUE);
        System.out.println(solution.myAtoi(h));
        int n=(Integer.MAX_VALUE-6)/10;
        //214748364
        //System.out.println(n);
        //-214748364<=
        int m=((Integer.MIN_VALUE+7)/10);
        System.out.println(m);
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

    //8. 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        int result=0;
        boolean fu=false,w=false,g=true,k=true;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (c==' '&&k) continue;
            else if(c==' ') break;
            else if (c=='-'){
                if (w) break;
                fu=true;
                w=true;
                k=false;
                continue;
            }else if (c=='+'){
                if (w) break;
                k=false;
                w=true;
            } else if (c=='0'&&g) {
                w=true;
                k=false;
                continue;
            }
            else if (!Character.isDigit(c)||(!Character.isDigit(c)&&w)) break;
            else if (Character.isDigit(c)){
                int a=c-'0';
                if (result*-1<((Integer.MIN_VALUE+a)/10)&&fu)
                    return Integer.MIN_VALUE;
                else if (result>(Integer.MAX_VALUE-a)/10&&!fu)
                    return Integer.MAX_VALUE;
                result=result*10+a;
                w=true;
                g=false;
                k=false;
            }
        }
        return fu ? result*-1:result;
    }

    //3190. 使所有元素都可以被 3 整除的最少操作数
    public int minimumOperations(int[] nums) {
        int time=0;
        for (int num:nums){
            if (num%3==0) continue;
            time++;
        }
        return time;
    }
}
