package test;

import util.ArrayStringUtils;

import java.util.HashSet;


public class T8 {
    public static void main() {
        T8 t8 = new T8();
        int[] nums1 = {6,1,4};
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
        //System.out.println(t8.minimumDeletions(nums1));
        System.out.println(t8.firstStableIndex(nums1, 5));
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

    //3875. 构造奇偶一致的数组 I
    public boolean uniformArray(int[] nums1) {
        return true;
    }

    //3876. 构造奇偶一致的数组 II
    public boolean uniformArray2(int[] nums1) {
        boolean haveOdd = false, haveEven = false;
        int minOdd = Integer.MAX_VALUE;
        for (int num : nums1) {
            if (num % 2 == 0) haveEven = true;
            else {
                haveOdd = true;
                minOdd = Math.min(minOdd, num);
            }
        }
        if (haveOdd && haveEven) {
            for (int num : nums1) {
                if (num % 2 == 0 && num <= minOdd) return false;
            }
        }
        return true;
    }

    //3903. 最小稳定下标 I   3904. 最小稳定下标 II
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] maxs = new int[n], mins = new int[n];
        maxs[0] = nums[0];
        mins[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            maxs[i] = Math.max(maxs[i - 1], nums[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            mins[i] = Math.min(mins[i + 1], nums[i]);
        }
        for (int i = 0; i < n; i++) {
            int num = maxs[i] - mins[i];
            if (num <= k) return i;
        }
        return -1;
    }

    //3870. 统计范围内的逗号
    public int countCommas(int n) {
        return n <= 999 ? 0 : n - 999;
    }

    //3871. 统计范围内的逗号 II
    public long countCommas2(long n) {
        if (n <= 999) return 0;
        long res = 0;
        if (n <= 999999) res += n - 999;
        else {
            res += 999000;
            if (n <= 999999999) res += ((n - 999999) * 2);
            else {
                res += 999000000 * 2;
                if (n <= 999999999999L) res += ((n - 999999999) * 3);
                else {
                    res += 999000000000L * 3;
                    if (n <= 999999999999999L) res += ((n - 999999999999L) * 4);
                    else {
                        res += 999000000000000L * 4;
                        if (n == 1000000000000000L) res += 5;
                    }
                }
            }
        }
        return res;
    }

    //3483. 不同三位偶数的数目
    public int totalNumbers(int[] digits) {
        HashSet<Integer> hashSet = new HashSet<>();
        int n = digits.length;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j || digits[k] % 2 == 1) continue;
                    hashSet.add(digits[i] * 100 + digits[j] * 10 + digits[k]);
                }
            }
        }
        return hashSet.size();
    }

}




















