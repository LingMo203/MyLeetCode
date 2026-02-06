package test;

import util.ArrayStringUtils;

import java.util.*;

public class T6 {
    private int n;

    public static void main(String[] args) {
        T6 t6 = new T6();
        int[] nums1 = {4, 2, 1, 3};
        int[] nums2 = {1, -2, 3, -4};
        int[] nums3 = {5, 4, 3, 4, 2};
        String strGrid = "[[0,1],[0,2],[0,3]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        String str = "AB";
        String str2 = "cae";
        String[] strs = {"flower", "flow", "flight"};
        char[] chars = {'c', 'f', 'j'};
        System.out.println(Arrays.toString(t6.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
    }

    //56. 合并区间
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            int max = list.get(list.size() - 1)[1];
            if (left <= max) {
                if (right > max) list.get(list.size() - 1)[1] = right;
            } else list.add(new int[]{left, right});
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    //1653. 使字符串平衡的最少删除次数
    public int minimumDeletions(String s) {
        int n = s.length(), res = Integer.MAX_VALUE, aCount = 0, bCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') aCount++;
        }
        for (int i = 0; i < n; i++) {
            int del = 0;
            if (s.charAt(i) == 'a') {
                aCount--;
                del += aCount + bCount;
            } else {
                del += aCount + bCount;
                bCount++;
            }
            res = Math.min(res, del);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    //1929. 数组串联
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[2 * n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i];
            res[i + n] = nums[i];
        }
        return res;
    }

    //1470. 重新排列数组
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[2 * n];
        for (int i = 0, j = n, k = 0; i < n && j < 2 * n; i++, j++, k++) {
            res[k++] = nums[i];
            res[k] = nums[j];
        }
        return res;
    }

    //1365. 有多少小于当前数字的数字
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[][] di = new int[n][2];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            di[i][0] = nums[i];
            di[i][1] = i;
        }
        Arrays.sort(di, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < n; i++) {
            int[] nm = di[i];
            int num = nm[0];
            if (num == di[i - 1][0]) {
                res[nm[1]] = res[di[i - 1][1]];
                continue;
            }
            res[nm[1]] = i;
        }
        return res;
    }

}



























