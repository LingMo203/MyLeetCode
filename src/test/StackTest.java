package test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class StackTest {
    public static void main(String[] args) {
        StackTest st=new StackTest();
        int num=123;
        int[] nums={2,1,5,6,2,3};
        int[] nums2={2,1,2};
        String str="3[a]2[bc]";
        String str2="0";
        //System.out.println(Arrays.toString(st.maxSlidingWindow(nums, 3)));
        //System.out.println(st.maxResult(nums,2));
        //System.out.println(st.decodeString(str));
        //System.out.println(st.largestRectangleArea(nums));
        System.out.println(Arrays.toString(st.nextGreaterElements(new int[]{1,2,3,4,3})));
    }


    //150. 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque=new LinkedList<>();
        for (String str:tokens){
            switch (str) {
                case "+" -> {
                    int a = deque.removeLast();
                    int b = deque.removeLast();
                    deque.addLast(b + a);
                }
                case "-" -> {
                    int a = deque.removeLast();
                    int b = deque.removeLast();
                    deque.addLast(b - a);
                }
                case "*" -> {
                    int a = deque.removeLast();
                    int b = deque.removeLast();
                    deque.addLast(b * a);
                }
                case "/" -> {
                    int a = deque.removeLast();
                    int b = deque.removeLast();
                    deque.addLast(b / a);
                }
                default -> deque.addLast(Integer.valueOf(str));
            }
        }
        return deque.removeLast();
    }

    //239. 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result=new int[nums.length-k+1];
        Deque<Integer> deque=new ArrayDeque<>();
        for (int i=0;i<k;i++){
            while (!deque.isEmpty()&&deque.getLast()<nums[i]){
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        result[0]=deque.getFirst();
        int index=1,leftIndex=0;
        for (int i=k;i<nums.length;i++){
            int num=nums[i];
            int left=nums[leftIndex];
            if (!deque.isEmpty()&&deque.getFirst()==left){
                deque.removeFirst();
            }
            while (!deque.isEmpty()&&deque.getLast()<num){
                deque.removeLast();
            }
            deque.addLast(num);
            leftIndex++;
            result[index++]=deque.getFirst();
        }
        return result;
    }


    //1696. 跳跃游戏 VI 看了题解
    public int maxResult2(int[] nums, int k) {
        int position=0,i=0,point=0;
        while (position<nums.length-1){
            point+=nums[position];
            Deque<Integer> deque=new ArrayDeque<>();
            boolean f=false;
            for (i=position+1;i<=k+position;i++){
                if (i>nums.length-1) break;
                if (nums[i]>=0) {
                    f=true;
                    break;
                }
                while (!deque.isEmpty()&&nums[deque.getLast()]<=nums[i]){
                    deque.removeLast();
                }
                deque.addLast(i);
            }
            position=f?i:deque.removeFirst();
        }
        point+=nums[nums.length-1];
        return point;
    }
    public int maxResult(int[] nums, int k){
        int length=nums.length;
        int[] dp=new int[length];
        Deque<Integer> deque=new ArrayDeque<>();
        dp[0]=nums[0];
        deque.addLast(0);
        for (int i=1;i<length;i++){
            while (deque.getFirst()<i-k){
                deque.removeFirst();
            }
            dp[i]=nums[i]+dp[deque.getFirst()];
            while (!deque.isEmpty()&&dp[i]>=dp[deque.getLast()]){
                deque.removeLast();
            }
            deque.addLast(i);
        }
        return dp[length-1];
    }

    //394. 字符串解码
    public String decodeString(String s) {
        Deque<String> deque=new ArrayDeque<>();
        int num=0;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (c==']'){
                StringBuilder str=new StringBuilder();
                while (!deque.getFirst().equals("[")){
                    String temp = deque.removeFirst();
                    str.insert(0,temp);
                }
                deque.removeFirst();
                String st=str.toString();
                int n = Integer.parseInt(deque.removeFirst());
                str.append(st.repeat(Math.max(0, n-1)));
                deque.addFirst(str.toString());
            }else {
                if (c=='[') {
                    deque.addFirst(String.valueOf(num));
                    deque.addFirst(c+"");
                    num=0;
                }
                else if (Character.isDigit(c)) num=num*10+(c-'0');
                else deque.addFirst(c+"");
            }
        }
        StringBuilder result=new StringBuilder();
        while (!deque.isEmpty()){
            String str=deque.removeFirst();
            result.insert(0,str);
        }
        return result.toString();
    }

    //84. 柱状图中最大的矩形 太艰难了!!!!!!!!!!!!!!!!!
    public int largestRectangleArea(int[] heights) {
        int res=0;
        Deque<Integer> deque=new ArrayDeque<>();
        for (int i=0;i<heights.length;i++){
            int num=heights[i];
            while (!deque.isEmpty()&&heights[deque.getFirst()]>num){
                int j=deque.removeFirst(),s,left=0;
                if (!deque.isEmpty()) {
                    left = deque.getFirst()+1;
                }
                s=(i-left)*heights[j];
                res=Math.max(res,s);
            }
            deque.addFirst(i);
        }
        while (!deque.isEmpty()){
            int j=deque.removeFirst(),s,left=0,right=heights.length-1;
            if (!deque.isEmpty()) {
                left = deque.getFirst()+1;
            }
            s=(right-left+1)*heights[j];
            res=Math.max(res,s);
        }
        return res;
    }
    //85. 最大矩形 感谢灵神的思路
    public int maximalRectangle(char[][] matrix) {
        int rows=matrix.length,cols=matrix[0].length,res = 0;
        int[][] matrixNums=new int[rows][cols];
        for (int i=0;i<cols;i++){
            matrixNums[0][i]=matrix[0][i]-'0';
        }
        res=largestRectangleArea(matrixNums[0]);
        for (int i=1;i<rows;i++){
            for (int j=0;j<cols;j++){
                int num=matrix[i][j]=='0'?0:(matrix[i][j]-'0')+matrixNums[i-1][j];
                matrixNums[i][j]=num;
            }
            res=Math.max(res,largestRectangleArea(matrixNums[i]));
        }
        return res;
    }


    //503. 下一个更大元素 II
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, count = 0, max = Integer.MIN_VALUE;
        int[] res = new int[n];
        for (int num : nums) max = Math.max(max, num);
        boolean[] vis = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; count < n; i++) {
            if (i >= n) i = 0;
            int num = nums[i];
            while (!deque.isEmpty() && nums[deque.getLast()] < num) {
                int remove = deque.removeLast();
                if (vis[remove]) continue;
                res[remove] = num;
                vis[remove] = true;
                count++;
            }
            if (num == max) {
                if (vis[i]) continue;
                res[i] = -1;
                count++;
                vis[i] = true;
                continue;
            }
            deque.addLast(i);
        }
        return res;
    }















}
