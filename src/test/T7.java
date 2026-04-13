package test;

import util.ArrayStringUtils;

import java.util.*;

public class T7 {
    public static void main() {
        T7 t7 = new T7();
        int[] nums1 = {5,5};
        int[] nums2 = {5,2};
        int[] nums3 = {7,1,3,3,5,3,22,10,23};
        int[] nums4 = {5,5,6,2,0,16};
        String strGrid = "[[9,1,8,9,2,9,1,8,9,2],[10,2,7,8,9,10,2,7,8,9],[7,6,6,9,5,7,6,6,9,5]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        String strGrid2 = "[[1,0],[0,0]]";
        int[][] grid2 = ArrayStringUtils.parse2DIntArray(strGrid2);
        String str = "AB";
        String str2 = "cae";
        String[] strs = {"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"};
        String[] strs2 = {"0:start:0", "1:start:2", "1:end:5", "0:end:6"};
        char[] chars = {'c', 'f', 'j'};
        System.out.println(t7.minCost(nums1,nums2,nums3,nums4));
    }


    //2840. 判断通过操作能否让字符串相等 II
    public boolean checkStrings(String s1, String s2) {
        int[] odd1 = new int[26], odd2 = new int[26], even1 = new int[26], even2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            int index = s1.charAt(i) - 'a';
            if (i % 2 == 0) {
                even1[index]++;
            } else {
                odd1[index]++;
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            int index = s2.charAt(i) - 'a';
            if (i % 2 == 0) {
                even2[index]++;
            } else {
                odd2[index]++;
            }
        }
        if (!Arrays.equals(odd1, odd2)) return false;
        if (!Arrays.equals(even1, even2)) return false;
        return true;
    }

    //2087. 网格图中机器人回家的最小代价
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int res = 0;
        if (startPos[1] < homePos[1]) {
            for (int i = startPos[1] + 1; i <= homePos[1]; i++) {
                res += colCosts[i];
            }
        } else {
            for (int i = startPos[1] - 1; i >= homePos[1]; i--) {
                res += colCosts[i];
            }
        }
        if (startPos[0] < homePos[0]) {
            for (int i = startPos[0] + 1; i <= homePos[0]; i++) {
                res += rowCosts[i];
            }
        } else {
            for (int i = startPos[0] - 1; i >= homePos[0]; i--) {
                res += rowCosts[i];
            }
        }
        return res;
    }

    //657. 机器人能否返回原点
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
            }
        }
        return x == 0 && y == 0;
    }

    //874. 模拟行走机器人
    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0, dir = 0, res = 0;  // 北 : 0;// 东 : 1;// 南 : 2;// 西 : 3;
        record Coordinate(int x, int y) {};
        HashSet<Coordinate> hashSet = new HashSet<>();
        for (int[] nums : obstacles) {
            hashSet.add(new Coordinate(nums[0], nums[1]));
        }
        for (int num : commands) {
            if (num < 0) {
                dir += num == -1 ? 1 : -1;
                if (dir == 4) dir = 0;
                else if (dir == -1) dir = 3;
            } else {
                int step = dir == 0 || dir == 1 ? 1 : -1;
                if (dir == 1 || dir == 3) {
                    for (int i = 0; i < num; i++) {
                        if (hashSet.contains(new Coordinate(x + step, y))) break;
                        x += step;
                    }
                } else if (dir == 0 || dir == 2) {
                    for (int i = 0; i < num; i++) {
                        if (hashSet.contains(new Coordinate(x, y + step))) break;
                        y += step;
                    }
                }
                res = Math.max(res, x * x + y * y);
            }
        }
        return res;
    }

    //1705. 吃苹果的最大数目
    public int eatenApples(int[] apples, int[] days) {
        int res = 0, i = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (; i < apples.length; i++) {
            int apple = apples[i], day = days[i];
            if (apple > 0) {
                pq.add(new int[]{apple, day + i});
            }
            res = getRes(res, i, pq);
        }
        for (; !pq.isEmpty(); i++) {
            res = getRes(res, i, pq);
        }
        return res;
    }
    public int getRes(int res, int i, PriorityQueue<int[]> pq) {
        while (!pq.isEmpty() && pq.peek()[1] <= i) {
            pq.remove();
        }
        if (!pq.isEmpty()) {
            int last = pq.peek()[0];
            if (last <= 1) {
                pq.remove();
            } else {
                pq.peek()[0] = last - 1;
            }
            res++;
        }
        return res;
    }

    //3653. 区间乘法查询后的异或 I
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int MOD = 1000000007;
        for (int[] quer : queries) {
            int idx = quer[0], r = quer[1], k = quer[2], v = quer[3];
            while (idx <= r) {
                long x = (long) (nums[idx] % MOD) * (v % MOD) % MOD;
                nums[idx] = (int) x;
                idx += k;
            }
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    //3862. 找出最小平衡下标
    public int smallestBalancedIndex(int[] nums) {
        int n = nums.length;
        long sum = 0, are = 1;
        for (int num : nums) {
            sum += num;
        }
        int res = -1;
        for (int i = n - 1; i >= 0; i--) {
            sum -= nums[i];
            if (sum == are) res = i;
            if (sum / nums[i] < are) break;
            are *= nums[i];
        }
        return res;
    }

    //3740. 三个相等元素之间的最小距离 I 3741. 三个相等元素之间的最小距离 II
    public int minimumDistance(int[] nums) {
        int res = Integer.MAX_VALUE;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!hashMap.containsKey(nums[i])) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                hashMap.put(nums[i], list);
            } else {
                hashMap.get(nums[i]).add(i);
            }
        }
        for(Map.Entry<Integer, ArrayList<Integer>> entry : hashMap.entrySet()) {
            ArrayList<Integer> list = entry.getValue();
            if (list.size() < 3) {
                continue;
            }
            for(int ii = 0, jj = 1, kk = 2; kk < list.size() ;ii++, jj++, kk++) {
                int i = list.get(ii), j = list.get(jj), k = list.get(kk);
                res = Math.min(res, Math.abs(i - j) + Math.abs(j - k) + Math.abs(k - i));
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //1207. 独一无二的出现次数
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : arr) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for(Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (hashSet.contains(entry.getValue())) {
                return false;
            }
            hashSet.add(entry.getValue());
        }
        return true;
    }

    //1848. 到目标元素的最小距离
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length, res = Integer.MAX_VALUE;
        for (int i = start; i >= 0; i--) {
            if (nums[i] == target) {
                res = start - i;
                break;
            }
        }
        for (int i = start; i < n; i++) {
            if (nums[i] == target) {
                res = Math.min(res, i - start);
                break;
            }
        }
        return res;
    }

}



























