package test;

import java.util.*;

public class GiveUp {
    public static void main(String[] args) {

    }

    //1631. 最小体力消耗路径 超时放弃
    public int minimumEffortPath(int[][] heights) {
        if (heights.length==1&&heights[0].length==1) return 0;
        int[] res={Integer.MAX_VALUE};
        ArrayList<int[]> path=new ArrayList<>();
        boolean[][] visit=new boolean[heights.length][heights[0].length];
        visit[0][0]=true;
        dfsMinimumEffortPath(res,path,heights,0,0,visit);
        return res[0];
    }
    public void dfsMinimumEffortPath(int[] res,ArrayList<int[]> path,int[][] heights,int x,int y,boolean[][] visit){
        if (x==heights.length-1&&y==heights[0].length-1){
            int max=-1,last=heights[0][0];
            for (int[] dir:path){
                int num=heights[dir[0]][dir[1]];
                max=Math.max(max,Math.abs(last-num));
                last=num;
            }
            res[0]=Math.min(res[0],max);
            return;
        }
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//四个方向 左 上 右 下
        for (int[] dir:direction){
            int nextX=x+dir[0],nextY=y+dir[1];
            if (nextX<0||nextX>=heights.length||nextY<0||nextY>=heights[0].length||visit[nextX][nextY]) continue;
            visit[nextX][nextY]=true;
            path.add(new int[]{nextX,nextY});
            dfsMinimumEffortPath(res, path, heights, nextX, nextY, visit);
            int[] remove=path.remove(path.size()-1);
            visit[remove[0]][remove[1]]=false;
        }
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

    //480. 滑动窗口中位数 (超时放弃)
    public double[] medianSlidingWindow(int[] nums, int k) {
        MedianFinder medianFinder=new MedianFinder();
        double[] res=new double[nums.length-k+1];
        for (int i = 0; i < k; i++) {
            medianFinder.addNum(nums[i]);
        }
        res[0] = medianFinder.findMedian();
        for (int i = k, j = 0; i <nums.length; i++, j++) {
            medianFinder.remove(nums[j]);
            medianFinder.addNum(nums[i]);
            res[j+1]=medianFinder.findMedian();
        }
        return res;
    }
    class MedianFinder {

        PriorityQueue<Integer> maxNum;
        PriorityQueue<Integer> minNum;

        public MedianFinder() {
            maxNum = new PriorityQueue<>();
            minNum = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            if (minNum.isEmpty() || num <= minNum.peek()) {
                minNum.offer(num);
                if (minNum.size() == maxNum.size() + 2) {
                    maxNum.offer(minNum.poll());
                }
            } else {
                maxNum.offer(num);
                if (maxNum.size() == minNum.size() + 2) {
                    minNum.offer(maxNum.poll());
                }
            }
        }

        public double findMedian() {
            if (minNum.size() > maxNum.size()) return minNum.peek();
            long a = (long) maxNum.peek() + (long) minNum.peek();
            return (double) a / 2;
        }

        public void remove(int num) {
            if (num <= minNum.peek()) {
                minNum.remove(num);
                if (minNum.size() < maxNum.size()) {
                    minNum.add(maxNum.remove());
                }
            } else {
                maxNum.remove(num);
                if (minNum.size() - 1 > maxNum.size()) {
                    maxNum.add(minNum.remove());
                }
            }
        }
    }
}
