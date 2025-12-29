package test;

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


    //862. 和至少为 K 的最短子数组 放弃
    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> deque=new ArrayDeque<>();
        int[] dp=new int[nums.length];
        int sum=0,size=Integer.MAX_VALUE;
        for (int i=0;i<nums.length;i++){
            int num=nums[i];
            deque.addLast(num);
            sum+=num;
            while (!deque.isEmpty()&&deque.getFirst()<=0){
                int m=deque.removeFirst();
                sum-=m;
            }
            while (!deque.isEmpty()&&sum>=k){
                size = Math.min(size, deque.size());
                int m=deque.removeFirst();
                sum-=m;
            }
        }
        return size==Integer.MAX_VALUE?-1:size;
    }

    //2402. 会议室 III 放弃
    public int mostBooked(int n, int[][] meetings) {
        int[] counts=new int[n];
        int[] using=new int[n];
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int i=0,time=0,k = 0;
        while (i<meetings.length){
            if (time>=meetings[i][0]) {
                while (i<meetings.length&&time >= using[k]&&time>=meetings[i][0]) {
                    int[] nums = meetings[i];
                    using[k] = Math.max(time, nums[0]) + (nums[1] - nums[0]);
                    counts[k]++;
                    i++;
                    int min = Integer.MAX_VALUE ;
                    k=0;
                    for (int j = 0; j < using.length; j++) {
                        if (using[j] == 0) {
                            k = j;
                            break;
                        }
                        if (using[j] < min) {
                            min = using[j];
                            k = j;
                        }
                    }
                }
            }
            time++;
        }
        int max=Integer.MIN_VALUE,index=0;
        for (int j=0;j<counts.length;j++){
            if (counts[j]>max){
                max=counts[j];
                index=j;
            }
        }
        return index;
    }
}
