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
        String strGrid = "[[9,1,8,9,2,9,1,8,9,2],[10,2,7,8,9,10,2,7,8,9],[7,6,6,9,5,7,6,6,9,5]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        String strGrid2 = "[[1,0],[0,0]]";
        int[][] grid2 = ArrayStringUtils.parse2DIntArray(strGrid2);
        String str = "AB";
        String str2 = "cae";
        String[] strs = {"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"};
        String[] strs2 = {"0:start:0", "1:start:2", "1:end:5", "0:end:6"};
        char[] chars = {'c', 'f', 'j'};
        //System.out.println(Arrays.toString(t6.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        //System.out.println(Arrays.toString(t6.exclusiveTime(2, List.of(strs2))));
        //System.out.println(t6.validMountainArray(new int[]{9,8,7,6,5,4,3,2,1,0}));
        //System.out.println(t6.detectCapitalUse("FlaG"));
        //System.out.println(t6.licenseKeyFormatting("5F3Z-2e-9-w",4));
        //System.out.println(t6.repeatedSubstringPattern("aba"));
        //System.out.println(t6.longestBalanced("zzabccy"));
        //System.out.println(t6.addBinary("101111", "10"));
        //System.out.println(t6.reverseBits(43261596));
        //System.out.println(t6.countPrimes(1000000));
        //System.out.println(t6.countPrimeSetBits(10, 15));
        //System.out.println(t6.hasAllCodes("00110",2));
        //System.out.println(t6.numSteps("1101"));
        //System.out.println(t6.minSwaps(grid));
        //System.out.println(t6.findKthBit(4,11));
        //System.out.println(t6.minOperations("01010"));
        //System.out.println(t6.checkOnesSegment("1001"));
        //System.out.println(Arrays.deepToString(t6.reverseSubmatrix(grid, 1, 0, 3)));
        //System.out.println(t6.findRotation(grid,grid2));
        //System.out.println(Arrays.deepToString(t6.constructProductMatrix(grid)));
        //System.out.println(t6.canPartitionGrid(grid));
        System.out.println(t6.areSimilar(grid, 5));
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

    //1441. 用栈操作构建数组
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1, j = 0; i <= n && j < target.length; i++) {
            deque.addLast(i);
            res.add("Push");
            if (deque.getLast() != target[j]) {
                deque.removeLast();
                res.add("Pop");
            } else {
                j++;
            }
        }
        return res;
    }

    //636. 函数的独占时间
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0, lastTime = 0; i < logs.size(); i++) {
            String[] strs = logs.get(i).split(":");
            int id = Integer.parseInt(strs[0]);
            int time = Integer.parseInt(strs[2]);
            if (Objects.equals(strs[1], "start")) {
                if (!deque.isEmpty()) res[deque.getFirst()] += time - lastTime;
                deque.addFirst(id);
                lastTime = time;
            } else {
                res[deque.removeFirst()] += time - lastTime + 1;
                lastTime = time + 1;
            }
        }
        return res;
    }

    //941. 有效的山脉数组
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        int i = 1, last = arr[0];
        for (; i < n; i++) {
            if (last >= arr[i]) break;
            last = arr[i];
        }
        if (i >= n || i == 1) return false;
        for (; i < n; i++) {
            if (last <= arr[i]) break;
            last = arr[i];
        }
        return i >= n;
    }

    //1700. 无法吃午餐的学生数量
    public int countStudents(int[] students, int[] sandwiches) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : students) {
            deque.addLast(num);
        }
        for (int sandwich : sandwiches) {
            int size = deque.size(), time = 0;
            while (!deque.isEmpty() && deque.getFirst() != sandwich && time < size) {
                deque.addLast(deque.removeFirst());
                time++;
            }
            if (time >= size) return deque.size();
            deque.removeFirst();
        }
        return deque.size();
    }

    //2073. 买票需要的时间
    public int timeRequiredToBuy(int[] tickets, int k) {
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < tickets.length; i++) {
            deque.addLast(new int[]{i, tickets[i]});
        }
        for (int i = 0; !deque.isEmpty(); i++) {
            int[] remove = deque.removeFirst();
            int id = remove[0], ticket = remove[1];
            if (id == k && ticket == 1) return i + 1;
            if (ticket == 1) continue;
            deque.addLast(new int[]{id, ticket - 1});
        }
        return -1;
    }

    //520. 检测大写字母
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) return true;
        boolean f = Character.isUpperCase(word.charAt(0));
        boolean second = Character.isUpperCase(word.charAt(1));
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!f) {
                if (Character.isUpperCase(c)) return false;
            } else {
                if (second) {
                    if (Character.isLowerCase(c)) return false;
                } else {
                    if (i == 1) continue;
                    if (Character.isUpperCase(c)) return false;
                }
            }
        }
        return true;
    }

    //482. 密钥格式化
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') continue;
            else {
                c = Character.toUpperCase(c);
                sb.append(c);
            }
        }
        if (sb.isEmpty()) return "";
        StringBuilder res = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            temp.append(c);
            if (temp.length() == k) {
                res.append(temp);
                res.append("-");
                temp = new StringBuilder();
            }
        }
        if (temp.isEmpty()) res.deleteCharAt(res.length() - 1);
        else res.append(temp);
        return res.reverse().toString();
    }

    //831. 隐藏个人信息
    public String maskPII(String s) {
        StringBuilder res = new StringBuilder();
        if (s.contains("@")) {
            res.append(Character.toLowerCase(s.charAt(0)));
            res.append("*****");
            int i = 1;
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '@') break;
            }
            res.append(s.substring(i - 1).toLowerCase());
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) sb.append(c);
            }
            int length = sb.length();
            switch (length) {
                case 10:
                    res.append("***-***-");
                    break;
                case 11:
                    res.append("+*-***-***-");
                    break;
                case 12:
                    res.append("+**-***-***-");
                    break;
                case 13:
                    res.append("+***-***-***-");
                    break;
            }
            res.append(sb.substring(length - 4));
        }
        return res.toString();
    }

    //3719. 最长平衡子数组 I
    public int longestBalanced(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> one = new HashSet<>();
            HashSet<Integer> two = new HashSet<>();
            for (int j = i; j < n; j++) {
                int num = nums[j];
                if (num % 2 == 0) two.add(num);
                else one.add(num);
                if (one.size() == two.size()) res = Math.max(res, (j - i + 1));
            }
        }
        return res;
    }

    //459. 重复的子字符串
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            String minStr = s.substring(0, i);
            int j = i, min = minStr.length();
            boolean f = true;
            for (; j + min <= n; j += min) {
                String str = s.substring(j, j + min);
                if (!minStr.equals(str)) {
                    f = false;
                    break;
                }
            }
            if (f && j == n) return true;
        }
        return false;
    }

    //796. 旋转字符串
    public boolean rotateString(String s, String goal) {
        if (s.equals(goal)) return true;
        int n = s.length();
        if (n != goal.length()) return false;
        for (int i = 1; i < n; i++) {
            String turn = s.substring(i, n) + s.substring(0, i);
            if (turn.equals(goal)) return true;
        }
        return false;
    }
    //这思路太牛逼了
    public boolean rotateString2(String s, String goal) {
        return s.length() == goal.length() && (goal + goal).contains(s);
    }

    //686. 重复叠加字符串匹配
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int res = 0;
        while (sb.length() < b.length()) {
            sb.append(a);
            res++;
        }
        if (sb.indexOf(b) != -1) return res;
        sb.append(a);
        res++;
        if (sb.indexOf(b) != -1) return res;
        return -1;
    }

    //1732. 找到最高海拔
    public int largestAltitude(int[] gain) {
        int sum = 0, res = 0;
        for (int num : gain) {
            sum += num;
            res = Math.max(res, sum);
        }
        return res;
    }

    //3713. 最长的平衡子串 I
    public int longestBalanced(String s) {
        int res = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
                if (new HashSet<Integer>(hashMap.values()).size() == 1) res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }

    //67. 二进制求和
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        boolean add = false;
        int i = a.length() - 1, j = b.length() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            int aa = a.charAt(i) - '0', bb = b.charAt(j) - '0', c = aa + bb;
            if (add) c++;
            if (c >= 2) {
                add = true;
                c -= 2;
            } else add = false;
            res.append(c);
        }
        boolean t;
        if (i < 0) {
            t = lastAddBinary(b, res, add, j);
        } else {
            t = lastAddBinary(a, res, add, i);
        }
        if (t) res.append(1);
        res.reverse();
        return res.toString();
    }
    public boolean lastAddBinary(String b, StringBuilder res, boolean add, int j) {
        for (; j >= 0; j--) {
            int c = b.charAt(j) - '0';
            if (add) c++;
            if (c >= 2) {
                add = true;
                c -= 2;
            } else add = false;
            res.append(c);
        }
        return add;
    }

    //190. 颠倒二进制位 (惭愧调库)
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    //401. 二进制手表
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn){
                    res.add(i + ":" + (j < 10 ? "0" + j : j));
                }
            }
        }
        return res;
    }

    //693. 交替位二进制数
    public boolean hasAlternatingBits(int n) {
        String str = Integer.toBinaryString(n);
        char last = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (last == str.charAt(i)) return false;
            last = str.charAt(i);
        }
        return true;
    }

    //204. 计数质数 (埃氏筛)
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        else if (n == 3) return 1;
        int count = 1;
        boolean[] is = new boolean[n];
        for (int i = 3; i < n; i += 2) {
            if (!is[i]) {
                count++;
                for (int j = i; j < n; j += i) {
                    is[j] = true;
                }
            }
        }
        return count;
    }

    //50. Pow(x, n) (快速幂)
    public double myPow(double x, int n) {
        return n >= 0 ? quickPow(x, n) : 1 / quickPow(x, -n);
    }
    public double quickPow(double x, int n) {
        if (n == 0) return 1;
        else if (n == 1) return x;
        double num = quickPow(x, n / 2);
        return n % 2 == 0 ? num * num : num * num * x;
    }

    //696. 计数二进制子串
    public int countBinarySubstrings(String s) {
        int res = 0, n = s.length();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0, count = 0; i < n; i++) {
            char c = s.charAt(i);
            for (; i < n && s.charAt(i) == c; i++, count++) {
            }
            i--;
            list.add(count);
            count = 0;
        }
        int last = 0;
        for (int num : list) {
            res += Math.min(last, num);
            last = num;
        }
        return res;
    }

    //762. 二进制表示中质数个计算置位
    public int countPrimeSetBits(int left, int right) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        int res = 0;
        for (; left <= right; left++) {
            if (Arrays.binarySearch(primes, Integer.bitCount(left)) > -1) res++;
        }
        return res;
    }

    //868. 二进制间距
    public int binaryGap(int n) {
        int res = 0, last = 0;
        String str = Integer.toBinaryString(n);
        int m = str.length(), i = 0;
        for (; i < m; i++) {
            if (str.charAt(i) == '1') {
                last = i;
                break;
            }
        }
        for (; i < m; i++) {
            if (str.charAt(i) == '1') {
                res = Math.max(res, i - last);
                last = i;
            }
        }
        return res;
    }

    //1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
    public boolean hasAllCodes(String s, int k) {
        HashSet<String> hashSet = new HashSet<>();
        for (int left = 0, right = k; right <= s.length(); right++, left++) {
            hashSet.add(s.substring(left, right));
        }
        StringBuilder path = new StringBuilder();
        return backHasAllCodes(path, hashSet, k);
    }
    public boolean backHasAllCodes(StringBuilder path, HashSet<String> hashSet, int k) {
        if (path.length() == k) {
            return hashSet.contains(path.toString());
        }
        for (int i = 0; i < 2; i++) {
            char c = i == 0 ? '0' : '1';
            path.append(c);
            if (!backHasAllCodes(path, hashSet, k)) return false;
            path.deleteCharAt(path.length() - 1);
        }
        return true;
    }

    //1356. 根据数字二进制下 1 的数目排序
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        ArrayList<int[]> list = new ArrayList<>();
        for (int num : arr) {
            list.add(new int[]{num, Integer.bitCount(num)});
        }
        list.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i)[0];
        }
        return ans;
    }

    //1404. 将二进制表示减到 1 的步骤数
    public int numSteps(String s) {
        int res = 0;
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 1) {
            int n = sb.length() - 1;
            if (sb.charAt(n) == '0') {
                sb.deleteCharAt(n);
            } else {
                int i = n;
                for (; i > 0 && sb.charAt(i) == '1'; i--) {
                }
                sb.delete(i, n + 1);
                sb.append('1');
                if (i == 0) n++;
                sb.append("0".repeat(Math.max(0, n - i)));
            }
            res++;
        }
        if (sb.charAt(0) == '0') res++;
        return res;
    }

    //1689. 十-二进制数的最少数目
    public int minPartitions(String n) {
        int res = 0;
        for (int i = 0; i < n.length(); i++) {
            res = Math.max(res, n.charAt(i) - '0');
        }
        return res;
    }

    //1536. 排布二进制网格的最少交换次数
    public int minSwaps(int[][] grid) {
        int n = grid.length, res = 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) break;
                count++;
            }
            nums[i] = count;
        }
        for (int i = 0; i < n; i++) {
            int j = i;
            for (; j < n; j++) {
                if (nums[j] >= n - i - 1) {
                    break;
                }
            }
            if (j >= n) return -1;
            for (; j - 1 >= i; j--) {
                int temp = nums[j - 1];
                nums[j - 1] = nums[j];
                nums[j] = temp;
                res++;
            }
        }
        return res;
    }

    //1545. 找出第 N 个二进制字符串中的第 K 位
    public char findKthBit(int n, int k) {
        String last = "0";
        for (int i = 2; i <= n; i++) {
            last = last + "1" + reverseInvert(last);
        }
        return last.charAt(k - 1);
    }
    private String reverseInvert(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '1') {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
        return sb.toString();
    }

    //1582. 二进制矩阵中的特殊位置
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length, res = 0;
        int[] row = new int[m], col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row[i] != 1) continue;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    if (col[j] != 1) continue;
                    res++;
                }
            }
        }
        return res;
    }

    //461. 汉明距离
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    //1758. 生成交替二进制字符串的最少操作数
    public int minOperations(String s) {
        int res = 0;
        boolean f = true;
        for (char c : s.toCharArray()) {
            if (f) {
                if (c == '0') {
                    res++;
                }
                f = false;
            } else {
                if (c == '1') {
                    res++;
                }
                f = true;
            }
        }
        int count = 0;
        f = true;
        for (char c : s.toCharArray()) {
            if (f) {
                if (c == '1') {
                    count++;
                }
                f = false;
            } else {
                if (c == '0') {
                    count++;
                }
                f = true;
            }
        }
        return Math.min(res, count);
    }

    //1784. 检查二进制字符串字段
    public boolean checkOnesSegment(String s) {
        boolean f = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                if (f) return false;
                for (; i < s.length(); i++) {
                    c = s.charAt(i);
                    if (c == '0') break;
                }
                i--;
                f = true;
            }
        }
        return true;
    }

    //1980. 找出不同的二进制字符串
    public String findDifferentBinaryString(String[] nums) {
        int n = nums[0].length();
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(nums));
        StringBuilder path = new StringBuilder();
        String[] res = new String[1];
        backFindDifferentBinaryString(res, path, n, hashSet);
        return res[0];
    }
    public boolean backFindDifferentBinaryString(String[] res, StringBuilder path, int n, HashSet<String> hashSet) {
        if (path.length() == n) {
            if (!hashSet.contains(path.toString())) {
                res[0] = path.toString();
                return true;
            }
            return false;
        }
        for (int i = 0; i < 2; i++) {
            path.append(i);
            if (backFindDifferentBinaryString(res, path, n, hashSet)) return true;
            path.deleteCharAt(path.length() - 1);
        }
        return false;
    }

    //1009. 十进制整数的反码 476. 数字的补数
    public int bitwiseComplement(int n) {
        String str = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(c == '1' ? '0' : '1');
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    //3643. 垂直翻转子矩阵
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int left = y, right = y + k, top = x, botton = x + k - 1;
        while (top < botton) {
            for (int i = left; i < right; i++) {
                int temp = grid[top][i];
                grid[top][i] = grid[botton][i];
                grid[botton][i] = temp;
            }
            top++;botton--;
        }
        return grid;
    }

    //1886. 判断矩阵经轮转后是否一致
    public boolean findRotation(int[][] mat, int[][] target) {
        if (Arrays.deepEquals(mat, target)) return true;
        int[][] mat2 = turnFindRotation(mat);
        if (Arrays.deepEquals(mat2, target)) return true;
        int[][] mat3 = turnFindRotation(mat2);
        if (Arrays.deepEquals(mat3, target)) return true;
        if (Arrays.deepEquals(turnFindRotation(mat3), target)) return true;
        return false;
    }
    private int[][] turnFindRotation(int[][] grid) {
        int n = grid.length, k = 0;
        int[][] res = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                res[k][j] = grid[j][i];
            }
            k++;
        }
        return res;
    }

    //2906. 构造乘积矩阵
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int n = grid.length, m = grid[0].length;
        int[][] res = new int[n][m];
        int[] left = new int[n * m];
        int[] right = new int[n * m];
        for (int i = 0, aro = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = i * m + j;
                if (i == 0 && j == 0) {
                    left[0] = 1;
                    continue;
                }
                left[index] = ((aro % MOD) * (grid[(index - 1) / m][(index - 1) % m] % MOD)) % MOD;
                aro = left[index];
            }
        }
        for (int i = n - 1, aro = 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int index = i * m + j;
                if (i == n - 1 && j == m - 1) {
                    right[n * m - 1] = 1;
                    continue;
                }
                right[index] = ((aro % MOD) * (grid[(index + 1) / m][(index + 1) % m] % MOD)) % MOD;
                aro = right[index];
            }
        }
        for (int i = 0; i < m * n; i++) {
            res[i / m][i % m] = ((left[i] % MOD) * right[i] % MOD) % MOD;
        }
        return res;
    }

    //3546. 等和矩阵分割 I
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[] row = new long[m], col = new long[n];
        long sum = 0;
        for (int j = 0; j < n; j++) {
            for (int[] ints : grid) {
                sum += ints[j];
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
        for (long num : row) {
            if (num == col[n - 1] - num) return true;
        }
        for (long num : col) {
            if (num == col[n - 1] - num) return true;
        }
        return false;
    }

    //2946. 循环移位后的矩阵相似检查
    public boolean areSimilar(int[][] mat, int k) {
        int n = mat.length, m = mat[0].length;
        k %= m;
        if (k == 0) return true;
        for (int i = 0; i < n; i++) {
            int[] nums = new int[m];
            if (i % 2 == 0) {
                for (int j = 0, y = k; j < m; j++, y++) {
                    y %= m;
                    nums[j] = mat[i][y];
                }
            } else {
                int y = -k;
                if (y < 0) y += m;
                for (int j = 0; j < m; j++, y++) {
                    y %= m;
                    nums[j] = mat[i][y];
                }
            }
            if (!Arrays.equals(nums, mat[i])) return false;
        }
        return true;
    }

}




























