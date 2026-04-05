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

}


























