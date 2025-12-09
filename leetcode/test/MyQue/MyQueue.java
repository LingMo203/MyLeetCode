package src.leetcode.test.MyQue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//232. 用栈实现队列
class MyQueue {
    Deque<Integer> deque;
    Deque<Integer> temp;
    public MyQueue() {
        this.deque=new ArrayDeque<>();
        this.temp=new ArrayDeque<>();
    }

    public void push(int x) {

        while (!deque.isEmpty()){
            temp.add(deque.pop());
        }
        temp.add(x);
        while (!temp.isEmpty()){
            deque.add(temp.pop());
        }
    }

    public int pop() {
        while (!deque.isEmpty()){
            temp.add(deque.pop());
        }
        int result=temp.pop();
        while (!temp.isEmpty()){
            deque.add(temp.pop());
        }
        return result;
    }

    public int peek() {
        while (!deque.isEmpty()){
            temp.add(deque.pop());
        }
        int result=temp.pop();
        deque.add(result);
        while (!temp.isEmpty()){
            deque.add(temp.pop());
        }
        return result;
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
