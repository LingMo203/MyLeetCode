package test;

import util.ArrayStringUtils;

import java.util.Collections;
import java.util.PriorityQueue;

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


}

