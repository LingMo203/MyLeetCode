package test;

import util.ArrayStringUtils;



public class T8 {
    public static void main() {
        T8 t8 = new T8();
        int[] nums1 = {2,10,7,5,4,1,8,6};
        int[] nums2 = {4, 1};
        int[] nums3 = {6};
        int[] nums4 = {3};
        String strGrid = "[[1,2]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        String strGrid2 = "[[1,0],[0,0]]";
        int[][] grid2 = ArrayStringUtils.parse2DIntArray(strGrid2);
        String str = "AB";
        String str2 = "cae";
        String[] strs = {"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"};
        String[] strs2 = {"0:start:0", "1:start:2", "1:end:5", "0:end:6"};
        char[] chars = {'c', 'f', 'j'};
        String charInput2 = "[[\"#\",\".\",\"*\",\".\"],[\"#\",\"#\",\"*\",\".\"]]";
        char[][] charArray2 = ArrayStringUtils.parse2DCharArraySmart(charInput2);
        System.out.println(t8.minimumDeletions(nums1));
    }

    //2091. 从数组中移除最大值和最小值
    public int minimumDeletions(int[] nums) {
        int maxI = 0, minI = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, res = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num > max) {
                maxI = i;
                max = num;
            }
            if (num < min) {
                minI = i;
                min = num;
            }
        }
        int left = Math.min(minI, maxI), right = Math.max(maxI, minI);
        res += left + 1;
        res += Math.min(right - left, n - right);
        int temp = 0;
        temp += n - right;
        temp += Math.min(left + 1, right - left);
        return Math.min(res, temp);
    }













}
