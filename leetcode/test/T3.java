package src.leetcode.test;

import java.util.*;

public class T3 {
    public static void main(String[] args) {
        Solution3 solution=new Solution3();
        int a=1534236469;
        int[] nums={2,2,3,1};
        int[][] intervals={{2,3},{4,5},{6,7},{8,9},{1,10}};
        String s="cbahbacl";
        String h="abc";
        String[] strs={"eat","tea","tan","ate","nat","bat"};
//        String b= String.valueOf(Integer.MAX_VALUE);
//        System.out.println(solution.myAtoi(h));
//        int n=(Integer.MAX_VALUE-6)/10;
        //214748364
        //System.out.println(n);
        //-214748364<=
//        int m=((Integer.MIN_VALUE+7)/10);
//        System.out.println(m);
        //System.out.println(Arrays.toString(solution.searchRange(nums, 2)));
        //System.out.println(solution.letterCombinations(s));
        //System.out.println(Arrays.deepToString(solution.merge(intervals)));
//        long fh=Integer.MAX_VALUE;
//        System.out.println(fh+1);
//        System.out.println(solution.thirdMax(nums));
        //System.out.println(solution.groupAnagrams(strs));
        System.out.println(solution.findAnagrams(s,h));
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

    //11. 盛最多水的容器
    public int maxArea(int[] height) {
        int max=0,left=0,right=height.length-1;
        while (left<=right){
            int length=right-left,width=Math.min(height[left],height[right]);
            max=Math.max(max,length*width);
            if (height[left]<height[right]) left++;
            else right--;
        }
        return max;
    }

    //17. 电话号码的字母组合 放弃
    public List<String> letterCombinations(String digits) {
        List<String> result=new ArrayList<>();
        String[] s=new String[10];
        s[2]="abc";s[3]="def";s[4]="ghi";s[5]="jkl";
        s[6]="mno";s[7]="pqrs";s[8]="tuv";s[9]="wxyz";
        List<char[]> each=new ArrayList<>();
        for (int i=0;i<digits.length();i++){
            int d=digits.charAt(i)-'0';
            each.add(s[d].toCharArray());
        }
        lc(result,each,0,"",0);
        return result;
    }
    public void lc(List<String> result,List<char[]> each,int time,String temp,int ltime){
        if (time==each.size()){
            result.add(temp);
            time--;
            return;
        }
        temp=temp+each.get(time)[ltime];
        time++;
        ltime++;
        lc(result,each,time,temp,ltime);
    }

    //56. 合并区间 放弃
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> list=new ArrayList<>();
        list.add(intervals[0]);
        for (int i=1;i<intervals.length;i++){
            int left=intervals[i][0];
            int right=intervals[i][1];
            for (int[] ints : list) {
                int kleft = ints[0];
                int kright = ints[1];
                if (left-1>kright||right+1<kleft){
                    list.add(new int[]{left,right});
                    break;
                }else if (left>=kleft&&right<=kright){
                    break;
                }else {
                    list.remove(ints);
                    list.add(new int[]{Math.min(left,kleft),Math.max(right,kright)});
                    break;
                }
            }
        }
        int[][] result=new int[list.size()][2];
        for (int i=0;i<result.length;i++){
            result[i]=list.get(i);
        }
        return result;
    }

    //414. 第三大的数
    public int thirdMax(int[] nums) {
        long first=Integer.MIN_VALUE,second=Integer.MIN_VALUE,third=Integer.MIN_VALUE;
        first--;second--;third--;
        for (long num:nums){
            if (num>first){
                third=second;
                second=first;
                first=num;
            }else if (num>second&&num!=first){
                third=second;
                second=num;
            }else if (num>third&&num!=first&&num!=second){
                third=num;
            }
        }
        return third<Integer.MIN_VALUE? (int) first :(int)third;
    }

    //412. Fizz Buzz
    public List<String> fizzBuzz(int n) {
        List<String> result=new ArrayList<>();
        for (int i=1;i<=n;i++){
            if (i%3==0&&i%5==0){
                result.add("FizzBuzz");
            }else if (i%3==0){
                result.add("Fizz");
            }else if (i%5==0){
                result.add("Buzz");
            }else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    //485. 最大连续 1 的个数
    public int findMaxConsecutiveOnes(int[] nums) {
        int result=0,count=0;
        for (int num:nums){
            if (num==1){
                count++;
            }else {
                result=Math.max(count,result);
                count=0;
            }
        }
        return Math.max(count,result);
    }

    //49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result=new ArrayList<>();
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (String str : strs) {
            int[] temp = new int[26];
            for (int i = 0; i < str.length(); i++) {
                int b = str.charAt(i) - 'a';
                temp[b]++;
            }
            String tostr = Arrays.toString(temp);
            if (!hashMap.containsKey(tostr)) {
                hashMap.put(tostr, hashMap.size());
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
            } else {
                result.get(hashMap.get(tostr)).add(str);
            }
        }
        return result;
    }

    //438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        int[] sstrs=new int[26];
        int[] pstrs=new int[26];
        for (int i=0;i<p.length();i++){
            pstrs[p.charAt(i)-'a']++;
        }
        List<Integer> result=new ArrayList<>();
        int length=p.length(),j = 0;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (i>=length){
                sstrs[s.charAt(j)-'a']--;
                j++;
            }
            sstrs[c-'a']++;
            if (Arrays.equals(sstrs,pstrs)){
                result.add(i-length+1);
            }
        }
        return result;
    }























}
