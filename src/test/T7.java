package test;

import util.ArrayStringUtils;

import java.util.*;

public class T7 {
    public static void main() {
        T7 t7 = new T7();
        int[] nums1 = {2,8};
        int[] nums2 = {4,1};
        int[] nums3 = {6};
        int[] nums4 = {3};
        String strGrid = "[[9,1,8,9,2,9,1,8,9,2],[10,2,7,8,9,10,2,7,8,9],[7,6,6,9,5,7,6,6,9,5]]";
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
        //System.out.println(t7.minCost(nums1,nums2,nums3,nums4));
        //System.out.println(t7.solveQueries(nums1, nums2));
        //System.out.println(t7.minMirrorPairDistance(new int[]{120,21}));
        //System.out.println(Arrays.deepToString(t7.rotateTheBox(charArray2)));
        System.out.println(t7.earliestFinishTime(nums1, nums2, nums3, nums4));
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

    //2515. 到目标字符串的最短距离
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length, res = -1;
        for (int i = startIndex, count = 0; count < n; i++, count++) {
            i %= n;
            if (words[i].equals(target)) {
                res = count;
                break;
            }
        }
        for (int i = startIndex, count = 0; count < n; i--, count++) {
            i += i < 0 ? n : 0;
            if (words[i].equals(target)) {
                res = Math.min(res, count);
                break;
            }
        }
        return res;
    }

    //3488. 距离最小相等元素查询
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        List<Integer> res = new ArrayList<>(m);
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], new ArrayList<>());
            }
            hashMap.get(nums[i]).add(i);
        }
        for (int num : queries) {
            ArrayList<Integer> list = hashMap.get(nums[num]);
            if (list.size() == 1) {
                res.add(-1);
                continue;
            }
            int listIndex = Collections.binarySearch(list, num), numIndex = list.get(listIndex), size = list.size();
            int listLeft = listIndex - 1 < 0 ? size - 1 : listIndex - 1, numLeft = list.get(listLeft);
            int lightRight = listIndex + 1 >= size ? 0 : listIndex + 1, numRight = list.get(lightRight);
            res.add(Math.min(
                    listIndex - 1 < 0 ? numIndex + n - numLeft : numIndex - numLeft,
                    listIndex + 1 >= size ? n - numIndex + numRight : numRight - numIndex
            ));
        }
        return res;
    }

    //3783. 整数的镜像距离
    public int mirrorDistance(int n) {
        return Math.abs(n - reverseInt(n));
    }
    private int reverseInt(int num) {
        int res = 0;
        while (num != 0) {
            res = res * 10 + (num % 10);
            num /= 10;
        }
        return res;
    }

    //3761. 镜像对之间最小绝对距离
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length, res = Integer.MAX_VALUE;
        HashMap<Integer, Deque<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], new ArrayDeque<>());
            }
            hashMap.get(nums[i]).addLast(i);
        }
        for (int i = 0; i < n; i++) {
            int reverse = reverseInt(nums[i]);
            Deque<Integer> deque = hashMap.get(reverse);
            if (deque == null) continue;
            while (!deque.isEmpty() && i >= deque.getFirst()) {
                deque.removeFirst();
            }
            if (!deque.isEmpty()) {
                int j = deque.getFirst();
                res = Math.min(res, j - i);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //2033. 获取单值网格的最小操作数
    public int minOperations(int[][] grid, int x) {
        if (grid.length == 1 && grid[0].length == 1) return 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] nums : grid) {
            for (int num : nums) {
                list.add(num);
            }
        }
        Collections.sort(list);
        int mid = list.get(list.size() / 2);
        int res = 0;
        for (int num : list) {
            int temp = Math.abs(mid - num);
            if (temp % x != 0) return -1;
            res += (Math.abs(mid - num) / x);
        }
        return res;
    }

    //1861. 旋转盒子
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length, n = boxGrid[0].length;
        char[][] res = new char[n][m];
        int i = n - 1, j = 0;//结果索引
        for (int ii = m - 1; ii >= 0; ii--) {
            i = n - 1;
            for (int jj = n - 1; jj >= 0; jj--) {
                char c = boxGrid[ii][jj];
                if (c == '#') {
                    res[i][j] = '#';
                    i--;
                } else if (c == '*') {
                    for (; jj < i; i--) {
                        res[i][j] = '.';
                    }
                    res[i][j] = '*';
                    i--;
                }
            }
            for (; i >= 0; i--) {
                res[i][j] = '.';
            }
            j++;
        }
        return res;
    }

    //2784. 检查数组是否是好的
    public boolean isGood(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != i + 1) return false;
        }
        return nums[n - 1] == nums[n - 2];
    }

    //2540. 最小公共值
    public int getCommon(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        for (int i = 0, j = 0; i < n && j < m; ) {
            int num1 = nums1[i], num2 = nums2[j];
            if (num1 == num2) return num1;
            else if (num1 < num2) i++;
            else j++;
        }
        return -1;
    }

    //2657. 找到两个数组的前缀公共数组
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        boolean[] bA = new boolean[n];
        boolean[] bB = new boolean[n];
        for (int i = 0, time = 0; i < n; i++) {
            int m1 = A[i], m2 = B[i];
            bA[m1 - 1] = true;
            bB[m2 - 1] = true;
            if (m1 == m2) {
                time++;
                res[i] = time;
                continue;
            }
            if (bA[m2 - 1]) time++;
            if (bB[m1 - 1]) time++;
            res[i] = time;
        }
        return res;
    }

    //3043. 最长公共前缀的长度
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int res = 0;
        Trie trie1 = new Trie();
        Trie trie2 = new Trie();
        for (int num : arr1) trie1.addNumber(num);
        for (int num : arr2) trie2.addNumber(num);
        for (int num : arr1) res = Math.max(res, trie2.findPrefix(num));
        for (int num : arr2) res = Math.max(res, trie1.findPrefix(num));
        return res;
    }

    //1752. 检查数组是否经排序和轮转得到
    public boolean check(int[] nums) {
        int n = nums.length, minI = 0;
        if (n == 1) return true;
        for (int i = 0, min = nums[0]; i < n; i++) {
            if (nums[i] < min) {
                minI = i;
                min = nums[i];
            }
        }
        if (minI == 0 && nums[0] == nums[n - 1]) {
            for (minI = n - 1; nums[0] == nums[minI] && minI > 0; minI--) {
            }
            minI++;
        }
        for (int k = 0, last = nums[minI]; k < n; k++, minI++) {
            if (minI >= n) minI = 0;
            if (last > nums[minI]) return false;
            last = nums[minI];
        }
        return true;
    }

    //1871. 跳跃游戏 VII
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length(), last = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        while (!deque.isEmpty()) {
            int remove = deque.removeFirst();
            if (remove == n - 1) return true;
            for (int i = Math.max(last + 1, remove + minJump); i <= Math.min(remove + maxJump, n - 1); i++) {
                if (i >= n) break;
                if (s.charAt(i) == '0') {
                    deque.addLast(i);
                }
            }
            last = Math.min(remove + maxJump, n - 1);
        }
        return false;
    }

    //3120. 统计特殊字母的数量 I
    public int numberOfSpecialChars(String word) {
        HashSet<Character> hashSet = new HashSet<>();
        boolean[] letters = new boolean[26];
        for (char c : word.toCharArray()) {
            char revers = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
            if (hashSet.contains(revers)) {
                letters[Character.toLowerCase(c) - 'a'] = true;
            }
            hashSet.add(c);
        }
        int res = 0;
        for (boolean f : letters) if (f) res++;
        return res;
    }

    //3121. 统计特殊字母的数量 II
    public int numberOfSpecialChars2(String word) {
        int n = word.length();
        int[] lower = new int[26];
        Arrays.fill(lower, -1);
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) continue;
            lower[c - 'a'] = i;
        }
        boolean[] isUse = new boolean[26];
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) continue;
            if (lower[c - 'A'] == -1) continue;
            if (isUse[c - 'A']) continue;
            isUse[c - 'A'] = true;
            if (lower[c - 'A'] < i) res++;
        }
        return res;
    }

    //3093. 最长公共后缀查询
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieStringIndices trie = new TrieStringIndices();
        for (int i = 0; i < wordsContainer.length; i++) {
            trie.insert(wordsContainer[i], i);
        }
        int[] res = new int[wordsQuery.length];
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < wordsQuery.length; i++) {
            if (hashMap.containsKey(wordsQuery[i])) {
                res[i] = hashMap.get(wordsQuery[i]);
            } else {
                res[i] = trie.query(wordsQuery[i]);
                hashMap.put(wordsQuery[i], res[i]);
            }
        }
        return res;
    }

    //3300. 替换为数位和以后的最小元素
    public int minElement(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            int sum = 0;
            while (num > 0) {
                sum += (num % 10);
                num /= 10;
            }
            res = Math.min(res, sum);
        }
        return res;
    }

    //2126. 摧毁小行星
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        long ma = mass;
        Arrays.sort(asteroids);
        for (int num : asteroids) {
            if (ma < num) return false;
            ma += num;
        }
        return true;
    }

    //2144. 打折购买糖果的最小开销
    public int minimumCost(int[] cost) {
        int res = 0, i = cost.length - 1;
        Arrays.sort(cost);
        for (; i >= 2; i -= 3) {
            res += cost[i] + cost[i - 1];
        }
        for (; i >= 0; i--) res += cost[i];
        return res;
    }

    //3633. 最早完成陆地和水上游乐设施的时间 I
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landDuration.length, m = waterDuration.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int endTime = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) {
                if (waterStartTime[j] >= endTime) {
                    res = Math.min(res, waterStartTime[j] + waterDuration[j]);
                } else {
                    res = Math.min(res, endTime + waterDuration[j]);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int endTime = waterStartTime[i] + waterDuration[i];
            for (int j = 0; j < n; j++) {
                if (landStartTime[j] >= endTime) {
                    res = Math.min(res, landStartTime[j] + landDuration[j]);
                } else {
                    res = Math.min(res, endTime + landDuration[j]);
                }
            }
        }
        return res;
    }
    //3635. 最早完成陆地和水上游乐设施的时间 II
    public int earliestFinishTime2(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(earliestFinishTime2impl(landStartTime, landDuration, waterStartTime, waterDuration),
                earliestFinishTime2impl(waterStartTime, waterDuration, landStartTime, landDuration));
    }
    public int earliestFinishTime2impl(int[] num1, int[] num2, int[] num3, int[] num4) {
        int n = num1.length, m = num3.length, res = Integer.MAX_VALUE, endTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            endTime = Math.min(endTime, num1[i] + num2[i]);
        }
        for (int i = 0; i < m; i++) {
            if (num3[i] > endTime) {
                res = Math.min(res, num3[i] + num4[i]);
            } else {
                res = Math.min(res, endTime + num4[i]);
            }
        }
        return res;
    }

    //3751. 范围内总波动值 I
    public int totalWaviness(int num1, int num2) {
        int res = 0;
        for (int i = num1; i <= num2; i++) {
            if (i < 100) continue;
            String str = String.valueOf(i);
            for (int j = 1; j < str.length() - 1; j++) {
                int left = str.charAt(j - 1) - '0', mid = str.charAt(j) - '0', right = str.charAt(j + 1) - '0';
                if ((left < mid && mid > right) || (left > mid && mid < right)) res++;
            }
        }
        return res;
    }

    //2574. 左右元素和的差值
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n], rightSum = new int[n], res = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            leftSum[i] = sum;
            sum += nums[i];
        }
        for (int i = n - 1, sum = 0; i >= 0; i--) {
            rightSum[i] = sum;
            sum += nums[i];
        }
        for (int i = 0; i < n; i++) {
            res[i] = Math.abs(leftSum[i] - rightSum[i]);
        }
        return res;
    }

    //2161. 根据给定数字划分数组
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length, same = 0;
        int[] res = new int[n];
        ArrayList<Integer> min = new ArrayList<>();
        ArrayList<Integer> max = new ArrayList<>();
        for (int num : nums) {
            if (pivot > num) min.add(num);
            else if (pivot < num) max.add(num);
            else same++;
        }
        int index = 0;
        for (int num : min) {
            res[index] = num;
            index++;
        }
        for (int i = 0; i < same; i++, index++) {
            res[index] = pivot;
        }
        for (int num : max) {
            res[index] = num;
            index++;
        }
        return res;
    }

    //3612. 用特殊操作处理字符串 I
    public String processStr(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(c);
            } else if (c == '*' && !res.isEmpty()) {
                res.deleteCharAt(res.length() - 1);
            } else if (c == '#') {
                res.append(res);
            } else if (c == '%') {
                res.reverse();
            }
        }
        return res.toString();
    }

    //1344. 时钟指针的夹角
    public double angleClock(int hour, int minutes) {
        double m = minutes * 6;
        double h = hour * 30 + minutes * 0.5;
        return Math.min(Math.abs(m - h), 360 - Math.abs(m - h));
    }

    //1833. 雪糕的最大数量
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int res = 0;
        for (int num : costs) {
            coins -= num;
            if (coins < 0) return res;
            res++;
            if (coins == 0) return res;
        }
        return res;
    }

    //1189. “气球” 的最大数量
    public int maxNumberOfBalloons(String text) {
        //balloon  b a l o n
        int[] counts = new int[5];
        int res =Integer.MAX_VALUE;
        for (char c : text.toCharArray()) {
            switch (c) {
                case 'b' : counts[0]++; break;
                case 'a' : counts[1]++; break;
                case 'l' : counts[2]++; break;
                case 'o' : counts[3]++; break;
                case 'n' : counts[4]++; break;
            }
        }
        counts[2] /= 2;
        counts[3] /= 2;
        for (int count : counts) res = Math.min(res, count);
        return res;
    }

}

//3043. 最长公共前缀的长度
class Trie {
    private final Trie[] trieNode;

    public Trie() {
        trieNode = new Trie[10];
    }

    public void addNumber(int num) {
        Trie cur = this;
        for (char c : String.valueOf(num).toCharArray()) {
            int n = c - '0';
            if (cur.trieNode[n] == null) {
                cur.trieNode[n] = new Trie();
            }
            cur = cur.trieNode[n];
        }
    }

    public int findPrefix(int num) {
        Trie cur = this;
        int length = 0;
        for (char c : String.valueOf(num).toCharArray()) {
            int n = c - '0';
            if (cur.trieNode[n] == null) {
                return length;
            }
            cur = cur.trieNode[n];
            length++;
        }
        return length;
    }
}

//3093. 最长公共后缀查询
class TrieStringIndices {
    private final TrieStringIndices[] trie;
    private int endIndex;

    public TrieStringIndices() {
        trie = new TrieStringIndices[26];
        endIndex = -1;
    }

    public void insert(String str, int index) {
        TrieStringIndices cur = this;
        for (int i = str.length() - 1; i >= 0; i--) {
            int id = str.charAt(i) - 'a';
            if (cur.trie[id] == null) cur.trie[id] = new TrieStringIndices();
            cur = cur.trie[id];
        }
        if (cur.endIndex == -1) cur.endIndex = index;
    }

    public int query(String str) {
        TrieStringIndices cur = this;
        int i = str.length() - 1;
        for (; i >= 0; i--) {
            int id = str.charAt(i) - 'a';
            if (cur.trie[id] == null) break;
            cur = cur.trie[id];
        }
        Deque<TrieStringIndices> deque = new ArrayDeque<>();
        deque.addLast(cur);
        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TrieStringIndices remove = deque.removeFirst();
                if (remove.endIndex > -1) {
                    list.add(remove.endIndex);
                    continue;
                }
                for (int j = 0; j < 26; j++) {
                    if (remove.trie[j] != null) deque.addLast(remove.trie[j]);
                }
            }
            if (!list.isEmpty()) {
                Collections.sort(list);
                return list.getFirst();
            }
        }
        return -1;
    }
}






























