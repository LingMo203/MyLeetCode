package src.leetcode.test;

import java.util.*;

public class T4 {
    public static void main(String[] args) {
        T4 t4=new T4();
        int num=123;
        String str="3+ 2 *2";
        System.out.println(t4.calculate(str));
    }


    //227. 基本计算器 II
    public int calculate(String s) {
        Deque<Object> deque=new LinkedList<>();
        List<Object> list=new ArrayList<>();
        long num=0;
        char c = 0;
        boolean f = false;
        int length=s.length();
        for (int i=0;i<=length;i++){
            if (i==length) c=' ';
            else c=s.charAt(i);
            if (Character.isDigit(c)){
                num=num*10+(c-'0');
                f=true;
            }else {
                if (f){
                    list.add(num);
                    num=0;
                    f=false;
                }
                if (c==' '){
                    continue;
                }else {
                    list.add(c);
                }
            }
        }
        long result=0,temp=0;
        boolean t=false,y=true;
        for (Object ob : list) {
            if (t) {
                if (y) {
                    deque.addLast((long) ob * temp);
                } else {
                    deque.addLast(temp / (long) ob);
                }
                temp = 0;
                t = false;
                continue;
            }
            if (ob instanceof Long) {
                deque.addLast(ob);
            } else if (ob instanceof Character) {
                if (ob.equals('+') || ob.equals('-')) {
                    deque.addLast(ob);
                } else {
                    if (ob.equals('*')) {
                        temp = (long) deque.removeLast();
                        y = true;
                    } else {
                        temp = (long) deque.removeLast();
                        y = false;
                    }
                    t = true;
                }
            }
        }
        long temp2=0;
        boolean u=false,k=false;
        Object ob = null;
        while (!deque.isEmpty()){
            ob = deque.removeFirst();
            if (u){
                if (k)
                    deque.addFirst(temp2+(long) ob);
                else
                    deque.addFirst(temp2-(long) ob);
                u=false;
                continue;
            }
            if (ob instanceof Long) {
                temp2 = (long) ob;
            }else {
                k= ob.equals('+');
                u=true;
            }
        }
        return (int) temp2;
    }
}
