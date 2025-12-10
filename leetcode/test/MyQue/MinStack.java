package src.leetcode.test.MyQue;

import java.util.*;
//155. 最小栈 题解妙
class MinStack {
    Deque<Integer> deque;
    Deque<Integer> minDeque;

    public MinStack() {
        deque=new LinkedList<>();
        minDeque=new ArrayDeque<>();
        minDeque.addLast(Integer.MAX_VALUE);
    }

    public void push(int val) {
        deque.addLast(val);
        minDeque.addLast(Math.min(val,minDeque.getLast()));
    }

    public void pop() {
        deque.removeLast();
        minDeque.removeLast();
    }

    public int top() {
        return deque.getLast();
    }

    public int getMin() {
        return minDeque.getLast();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
