package src.leetcode.test;

import java.util.Arrays;

public class T3 {
    public static void main(String[] args) {
        Solution3 solution=new Solution3();
        int a=1534236469;
        int[] nums={2,2};
        String s="-2147483648";
        String h="-2147483647";
//        String b= String.valueOf(Integer.MAX_VALUE);
//        System.out.println(solution.myAtoi(h));
//        int n=(Integer.MAX_VALUE-6)/10;
        //214748364
        //System.out.println(n);
        //-214748364<=
//        int m=((Integer.MIN_VALUE+7)/10);
//        System.out.println(m);
        System.out.println(Arrays.toString(solution.searchRange(nums, 2)));
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

    //34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0) return new int[]{-1,-1};
        int left=0,right=nums.length-1,mid;
        int[] result={-1,-1};
        while (left<=right){
            mid=(right-left)/2+left;
            if (nums[mid]==target){
                int le=mid,ri=mid;
                boolean leb=false,rib=false;
                while (nums[le]==target){
                    if (le==0) {
                        leb=false;
                        break;
                    }
                    le--;
                    leb=true;
                }
                while (nums[ri]==target){
                    if (ri==nums.length-1) {
                        rib=false;
                        break;
                    }
                    ri++;
                    rib=true;
                }
                result[0]=leb? le+1:le;
                result[1]=rib? ri-1:ri;
                break;
            }else if (nums[mid]>target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return result;
    }
}
