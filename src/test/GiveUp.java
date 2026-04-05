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

    //57. 插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length, left = newInterval[0], right = newInterval[1], i = 0;
        if (n == 0) return new int[][]{{newInterval[0], newInterval[1]}};
        ArrayList<int[]> list = new ArrayList<>();
        for (; i < n; i++) {
            int[] nums = intervals[i];
            if (nums[0] < left) {
                list.add(nums);
            }else {
                left = Math.min(left, intervals[i][0]);
                break;
            }
        }
        for (; i < n; i++) {
            int[] nums = intervals[i];
            if (nums[0] > right) {
                i--;
                break;
            }
        }
        right = Math.max(right, intervals[i][1]);
        list.add(new int[]{left, right});
        for (++i; i < n; i++) {
            list.add(intervals[i]);
        }
        int[][] res = new int[list.size()][2];
        for (int j = 0; j < list.size(); j++) {
            res[j] = list.get(j);
        }
        return res;
    }


    //System.out.println(bs.findMedianSortedArrays(new int[]{1,3,5,14},new int[]{2,4,8,9,11}));
    //4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = 0, right = m,
                mid = (right - left) / 2 + left, second = (m + n) / 2 - mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            second = (m + n) / 2 - mid;
        }
        return -1;
    }


    //3548. 等和矩阵分割 II
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[] row = new long[m], col = new long[n];
        long sum = 0;
        HashMap<Long, Integer> right = new HashMap<>();
        HashMap<Long, Integer> botton = new HashMap<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                long num = grid[i][j];
                sum += num;
                right.put(num, right.getOrDefault(num, 0) + 1);
                botton.put(num, botton.getOrDefault(num, 0) + 1);
            }
            col[j] = sum;
        }
        sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
            }
            row[i] = sum;
        }
        sum = col[n - 1];
        HashSet<Long> left = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long num = grid[i][j];
                left.add(num);
                int time = right.get(num);
                if (time <= 1) right.remove(num);
                else right.put(num, time - 1);
            }
            long leftSum = row[i], rightSum = sum - leftSum, diff = Math.abs(rightSum - leftSum);
            if (leftSum == rightSum) return true;

            if (m <= 2) continue;

            if (leftSum > rightSum && left.contains(diff) && i != 0) return true;
            else if (leftSum < rightSum && right.containsKey(diff) && i != m - 1) return true;
        }
        HashSet<Long> top = new HashSet<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                long num = grid[i][j];
                top.add(num);
                int time = botton.get(num);
                if (time <= 1) botton.remove(num);
                else botton.put(num, time - 1);
            }
            long topSum = col[j], bottonSum = sum - topSum, diff = Math.abs(topSum - bottonSum);
            if (topSum == bottonSum) return true;
            if (n <= 2) continue;
            if (topSum > bottonSum && top.contains(diff) && j != 0) {
                return true;
            }
            else if (topSum < bottonSum && botton.containsKey(diff) && j != n - 1) return true;
        }
        return false;
    }
}
