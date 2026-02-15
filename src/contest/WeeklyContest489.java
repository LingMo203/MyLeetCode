package contest;

import util.ArrayStringUtils;

import java.util.*;

//第 489 场周赛
public class WeeklyContest489 {
    public static void main(String[] args) {
        WeeklyContest489 t = new WeeklyContest489();
        int[] nums = {2, 2, 3, 1, 4, 5, 1, 1, 2};
        String str1 = "fusion";
        String str2 = "layout";
        String[] strs = {"query 0 2", "update 1 b", "query 0 2"};
        String strGrid = "[[0,1],[1,2]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        //System.out.println(t.firstUniqueFreq(new int[]{1, 2, 2}));
        System.out.println(t.almostPalindromic("aabc"));
    }

    //3842. 灯泡开关
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : bulbs) {
            if (hashSet.contains(num)) {
                hashSet.remove(num);
            } else {
                hashSet.add(num);
            }
        }
        List<Integer> res = new ArrayList<>(hashSet.stream().toList());
        Collections.sort(res);
        return res;
    }

    //3843. 频率不同的第一个元素
    public int firstUniqueFreq(int[] nums) {
        if (nums.length == 0) return -1;
        else if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int val : hashMap.values()) {
            hashMap2.put(val, hashMap2.getOrDefault(val, 0) + 1);
        }
        for (int num : nums) {
            int freq = hashMap.get(num);
            if (hashMap2.get(freq) == 1) {
                return num;
            }
        }
        return -1;
    }

    //3844. 最长的准回文子字符串(超时)
    public int almostPalindromic(String s) {
        int res = -1, n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String str = s.substring(i, j + 1);
                res = getRes(res, str);
            }
        }
        return res;
    }
    public int getRes(int res, String str) {
        for (int j = 0; j < str.length(); j++) {
            StringBuilder sb = new StringBuilder(str);
            sb.deleteCharAt(j);
            if (sb.isEmpty()) continue;
            if (isPalindrome(sb.toString())) {
                res = Math.max(res, str.length());
                break;
            }
        }
        return res;
    }
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (a != b) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


}





















