package test;

import java.util.*;

public class GiveUp {
    public static void main(String[] args) {

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


    //22. 括号生成(放弃)
    public List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        StringBuilder path=new StringBuilder();
        int[] time={n*2,n*2};
        backGenerateParenthesis(res,path,time,n*2);
        return res;
    }
    public void backGenerateParenthesis(List<String> res,StringBuilder path,int[] time,int n){
        if (path.length()==n){
            res.add(path.toString());
            return;
        }
        for (int i=0;i<=n;i++){
            char c=i%2==1?'(':')';
            int index=c=='('?0:1;
            int count=time[index];
            if (count<=1) continue;
            path.append(c);
            time[index]--;
            backGenerateParenthesis(res, path, time, n);
            int delI=path.charAt(path.length()-1)=='('?0:1;
            path.deleteCharAt(path.length()-1);
            time[delI]++;
        }
    }
}
