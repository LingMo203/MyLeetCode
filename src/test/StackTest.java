package test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class StackTest {
    public static void main(String[] args) {
        StackTest st=new StackTest();
        int num=123;
        int[] nums={1,3,-1,-3,5,3,6,7};
        String str="(1+(4+5+2)-3)+(6+8)";
        String str2="0";
        System.out.println(Arrays.toString(st.maxSlidingWindow(nums, 3)));
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









}
