package test;

import util.ArrayStringUtils;

import java.util.*;

public class Heap {
    public static void main(String[] args) {
        Heap hp = new Heap();
        int[] nums1 = {4, 2, 1, 3};
        int[] nums2 = {1, -2, 3, -4};
        int[] nums3 = {5, 4, 3, 4, 2};
        String strGrid = "[[0,1],[0,2],[0,3]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        String str = "AB";
        String str2 = "cae";
        String[] strs = {"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"};
        String[] strs2 = {"0:start:0", "1:start:2", "1:end:5", "0:end:6"};
        char[] chars = {'c', 'f', 'j'};
    }


    //1046. 最后一块石头的重量
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : stones) {
            pq.add(num);
        }
        while (pq.size() > 1) {
            int max = pq.remove();
            int min = pq.remove();
            if (max != min) pq.add(max - min);
        }
        return pq.size() == 1 ? pq.peek() : 0;
    }

    //373. 查找和最小的 K 对数字
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>(k);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Math.toIntExact(b[2] - a[2]));
        for (int i = 0; i <= k && i < nums1.length; i++) {
            int num1 = nums1[i];
            if (pq.peek() != null && pq.size() >= k && pq.peek()[2] < num1 + nums2[0]) break;
            for (int j = 0; j <= k && j < nums2.length; j++) {
                int num2 = nums2[j];
                int size = pq.size();
                if (pq.peek() != null && size >= k && pq.peek()[2] < num1 + num2) break;
                pq.add(new long[]{num1, num2, (long) num1 + num2});
                if (size + 1 > k) pq.remove();
            }
        }
        while (!pq.isEmpty()) {
            long[] remove = pq.remove();
            List<Integer> list = new ArrayList<>();
            list.add((int) remove[0]);
            list.add((int) remove[1]);
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }


}

