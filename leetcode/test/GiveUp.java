package src.leetcode.test;

import java.util.*;

public class GiveUp {
    public static void main(String[] args) {

    }


    //17.电话号码的字母组合 放弃
    public List<String> letterCombinations(String digits) {
        String[] numbers={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> temp=new ArrayList<>();
        for (int i=0;i<digits.length();i++){
            int num=digits.charAt(i)-'0';
            String numS=numbers[num];
            temp.add(numS);
        }
        for (int i=0;i<temp.size();i++){
            String tepS=temp.get(i);
            System.out.println(tepS);
        }
        System.out.println(temp);
        return null;
    }
    //17. 电话号码的字母组合 放弃
    public List<String> letterCombinations2(String digits) {
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

    //997. 找到小镇的法官 放弃
    public int findJudge(int n, int[][] trust) {
        HashSet<Integer> hashSet=new HashSet<>();
        HashSet<Integer> reSet=new HashSet<>();
        for (int i=0 ;i<trust.length; i++) {
            int[] a=trust[i];
            for (int betrust:a){
                if (i==0){
                    hashSet.add(betrust);
                }else {
                    if (hashSet.contains(betrust)){
                        reSet.add(betrust);
                    }
                }
            }
            reSet.remove(trust[i][0]);
        }
        for (int f:reSet){
            return f;
        }
        return -1;
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

    //239. 滑动窗口最大值 超时 放弃
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result=new int[nums.length-k+1];
        ArrayList<Integer> list=new ArrayList<>();
        int max=0,i=0;
        for (int j=0;j<nums.length;j++){
            if (j<k){
                list.add(nums[j]);
                max=nums[max]>nums[j]?max:j;
                if (j==k-1) result[i]=nums[max];
            }else {
                i++;
                list.remove(0);
                list.add(nums[j]);
                max=nums[max]>nums[j]?max:j;
                if (max<i){
                    max++;
                    for (int l=0;l<list.size();l++){
                        max=nums[max]>list.get(l)?max:i+l;
                    }
                }
                result[i]=nums[max];
            }
        }
        return result;
    }
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] result=new int[nums.length-k+1];
        int max=0,i=0,second=1;
        for (int j=0;j<nums.length;j++){
            if (j<k){
                if (nums[j]>=nums[max]){
                    max=j;
                    second = max + 1;
                }else {
                    if (nums[j]>=nums[second]) second=j;
                }
                if (j==k-1) result[i]=nums[max];
            }else {
                i++;
                if (max<i){
                    if (nums[j] >= nums[second]) second = j;
                    max = second;
                    for (int t=max+1;t<=j;t++){
                        if (nums[t] >= nums[second]) {
                            second = t;
                            break;
                        }
                    }
                }else {
                    if (nums[j] >= nums[max]) {
                        max = j;
                        second = max + 1;
                    } else {
                        if (nums[j] >= nums[second]) second = j;
                    }
                }
                result[i]=nums[max];
            }
        }
        return result;
    }
    public int[] maxSlidingWindow(int[] nums, int k){
        Deque<Integer> deque=new ArrayDeque<>();
        int[] result=new int[nums.length-k-1];
        for (int num:nums){
            if (!deque.isEmpty()&&num==deque.getFirst()){
                deque.pollFirst();
            }
        }
        return result;
    }

    //50.Pow(x,n) 看不明白答案 放弃
    public double myPow(double x, int n) {
        if (n==1)
            return x;
        else if (n==0)
            return 1;
        return x*myPow(x,(Math.abs(n)-1)*(Math.abs(n)/n));
    }

    //1668. 最大重复子字符串 放弃
    public int maxRepeating(String sequence, String word) {
        char[] csequence=sequence.toCharArray();
        char[] cword=word.toCharArray();
        int j=0,count=0;
        for (char c : csequence) {
            char t=cword[j];
            if (c == cword[j]) {
                j++;
                if (j == cword.length) {
                    count++;
                    j = 0;
                }
            } else {
                j = 0;
            }
        }
        return count;
    }

    //322. 零钱兑换 放弃
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count=0;
        for (int i=coins.length-1;i>=0;i--){
            int num=coins[i];
            if (num<=amount){
                count+=amount/num;
                amount=amount%num;
            }
        }
        return amount==0?count:-1;
    }

    //287. 寻找重复数 放弃
    public int findDuplicate(int[] nums) {
        int slow=0,fast=1,length=nums.length;
        while (nums[slow]!=nums[fast]){
            slow++;
            fast+=2;
            if (slow==fast) fast++;
            if (slow==length-1) slow-=length-1;
            if (fast>length-1) fast-=length-1;
        }
        return nums[slow];
    }

}
