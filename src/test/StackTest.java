package test;

import java.util.Deque;
import java.util.LinkedList;

public class StackTest {
    public static void main(String[] args) {
        StackTest st=new StackTest();
        int num=123;
        int[] nums={186,419,83,408};
        String str="(1+(4+5+2)-3)+(6+8)";
        String str2="0";

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
}
