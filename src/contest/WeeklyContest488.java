package contest;

import util.ArrayStringUtils;

import java.util.*;

//第 488 场周赛
public class WeeklyContest488 {
    public static void main(String[] args) {
        WeeklyContest488 t = new WeeklyContest488();
        int[] nums1 = {1,3,2};
        int[] nums2 = {-3,-2};
        int[] nums3 = {1,2};
        String strGrid = "[[0,1],[0,2],[0,3]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        String str = "AB";
        String str2 = "cae";
        String[] strs = {"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"};
        String[] strs2 = {"0:start:0", "1:start:2", "1:end:5", "0:end:6"};
        char[] chars = {'c', 'f', 'j'};
        //System.out.println(t.dominantIndices(new int[]{58, 89}));
        //System.out.println(t.mergeAdjacent(new int[]{2,1,1,2}));
        //System.out.println(t.countSubarrays(nums1, 4));
        System.out.println(t.maxScore(nums2,nums3,2));
    }

    //100985. 统计主导元素下标数
    public int dominantIndices(int[] nums) {
        int sum = 0, res = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            sum -= nums[i];
            n--;
            double num = nums[i];
            if (num > (double) sum / n) res++;
        }
        return res;
    }

    //100984. 合并相邻且相等的元素
    public List<Long> mergeAdjacent(int[] nums) {
        List<Long> res = new ArrayList<>();
        res.add((long) nums[0]);
        for (int i = 1; i < nums.length; i++) {
            long last = res.get(res.size() - 1);
            long num = nums[i];
            if (last == num) {
                res.remove(res.size() - 1);
                num += num;
                while (!res.isEmpty() && res.get(res.size() - 1) == num) {
                    num += num;
                    res.remove(res.size() - 1);
                }
                res.add(num);
            } else {
                res.add(num);
            }
        }
        return res;
    }

    //100982. 开销小于等于 K 的子数组数目
    public long countSubarrays(int[] nums, long k) {
        long res = 0;
        int n = nums.length;
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();
        for (int r = 0, l = 0; r < n; r++) {
            int right = nums[r];
            while (!maxDq.isEmpty() && nums[maxDq.getLast()] < right) {
                maxDq.removeLast();
            }
            maxDq.addLast(r);
            while (!minDq.isEmpty() && nums[minDq.getLast()] > right) {
                minDq.removeLast();
            }
            minDq.addLast(r);
            while (l <= r) {
                long max = nums[maxDq.getFirst()];
                long min = nums[minDq.getFirst()];
                long cost = (max - min) * (r - l + 1);
                if (cost > k) {
                    if (l == maxDq.getFirst()) maxDq.removeFirst();
                    if (l == minDq.getFirst()) minDq.removeFirst();
                    l++;
                } else break;
            }
            res += r - l + 1;
        }
        return res;
    }

    //Q4. 恰好 K 个下标对的最大得分 (没做出)
    public long maxScore(int[] nums1, int[] nums2, int k) {
        boolean[] v2 = new boolean[nums2.length];
        long res = 0;
        long[] r=new long[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            long n1 = nums1[i], max = Long.MIN_VALUE;
            int maxId = 0;
            for (int j = 0; j < nums2.length; j++) {
                if (v2[j]) continue;
                long n2 = nums2[j];
                if (n1 * n2 > max) {
                    max = n1 * n2;
                    maxId = j;
                }
            }
            v2[maxId] = true;
            r[i]=max;
        }
        //Arrays.sort(r, (a, b) -> b - a);
        for (int i = r.length - 1; i < k; i++) {

        }
        return res;
    }











}



















