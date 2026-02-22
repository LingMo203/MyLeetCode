package contest;

import util.ArrayStringUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

//第 490 场周赛
public class WeeklyContest490 {
    public static void main(String[] args) {
        WeeklyContest490 t = new WeeklyContest490();
        int[] nums = {2, 2, 3, 1, 4, 5, 1, 1, 2};
        String str1 = "fusion";
        String str2 = "layout";
        String[] strs = {"query 0 2", "update 1 b", "query 0 2"};
        String strGrid = "[[0,1],[1,2]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        //System.out.println(t.isDigitorialPermutation(40558));
        System.out.println(t.countSequences(new int[]{4,6,3}, 2));
    }

    //3847. 计算比赛分数差
    public int scoreDifference(int[] nums) {
        int first = 0, second = 0, six = 5;
        boolean nowFirst = true;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num % 2 == 1) nowFirst = !nowFirst;
            if (i == six) {
                nowFirst = !nowFirst;
                six += 6;
            }
            if (nowFirst) {
                first += num;
            } else {
                second += num;
            }
        }
        return first - second;
    }

    //3848. 阶数数字排列
    public boolean isDigitorialPermutation(int n) {
        final int[] fac = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        int sum = 0;
        int[] first = new int[10];
        while (n != 0) {
            int num = n % 10;
            first[num]++;
            n /= 10;
            sum += fac[num];
        }
        int[] second = new int[10];
        while (sum != 0) {
            int num = sum % 10;
            second[num]++;
            sum /= 10;
        }
        return Arrays.equals(first, second);
    }

    //3849. 重新排列后的最大按位异或值
    public String maximumXor(String s, String t) {
        int[] rest = new int[2];
        for (char c : t.toCharArray()) {
            if (c == '0') rest[0]++;
            else rest[1]++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '0') {
                if (rest[1] > 0) {
                    sb.append('1');
                    rest[1]--;
                } else {
                    sb.append('0');
                    rest[0]--;
                }
            } else {
                if (rest[0] > 0) {
                    sb.append('1');
                    rest[0]--;
                } else {
                    sb.append('0');
                    rest[1]--;
                }
            }
        }
        return sb.toString();
    }

    //3850. 统计结果等于 K 的序列数目 (超时)
    public int countSequences(int[] nums, long k) {
        Deque<Fraction> deque = new ArrayDeque<>();
        deque.addLast(new Fraction(1, 1));
        for (int num : nums) {
            int size = deque.size();
            while (size-- > 0) {
                Fraction remove = deque.removeFirst();
                deque.addLast(new Fraction(remove.a * num, remove.b));
                deque.addLast(new Fraction(remove.a, remove.b * num));
                deque.addLast(new Fraction(remove.a, remove.b));
            }
        }
        int ans = 0;
        while (!deque.isEmpty()) {
            Fraction remove = deque.removeFirst();
            if (remove.a == k && remove.b == 1) ans++;
        }
        return ans;
    }

    class Fraction {
        long a; //分子
        long b; //分母

        public Fraction(long a, long b) {
            this.b = b;
            this.a = a;
            reduce();
        }

        void reduce() {
            long gcd = gcd(Math.abs(a), Math.abs(b));
            a /= gcd;
            b /= gcd;
        }

        long gcd(long aa, long bb) {
            return bb == 0 ? aa : gcd(bb, aa % bb);
        }

    }
}






















