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
        String[] strs = {"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"};
        String[] strs2 = {"0:start:0", "1:start:2", "1:end:5", "0:end:6"};
        char[] chars = {'c', 'f', 'j'};
        //System.out.println(Arrays.toString(t6.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        //System.out.println(Arrays.toString(t6.exclusiveTime(2, List.of(strs2))));
        //System.out.println(t6.validMountainArray(new int[]{9,8,7,6,5,4,3,2,1,0}));
        //System.out.println(t6.detectCapitalUse("FlaG"));
        //System.out.println(t6.licenseKeyFormatting("5F3Z-2e-9-w",4));
        //System.out.println(t6.repeatedSubstringPattern("aba"));
        System.out.println(t6.longestBalanced("zzabccy"));
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

}




























