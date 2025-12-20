package test.MyQue;

import java.util.Deque;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue myQueue=new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
//        System.out.println(myQueue.peek());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.empty());

//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        System.out.println(minStack.list);
//        minStack.push(0);
//        System.out.println(minStack.list);
//        minStack.push(-3);
//        System.out.println(minStack.list);
//        System.out.println(minStack.getMin());   //--> 返回 -3.
//        minStack.pop();
//        System.out.println(minStack.top());       //--> 返回 0.
//        System.out.println(minStack.getMin());   //--> 返回 -2.

        //["MinStack","push","push","push","getMin","pop","getMin","pop",
        // "getMin","pop","push","push","push","getMin","pop","top","getMin","pop","getMin","pop"]
        //[[],[0],[1],[0],[],[],[],[],[],[],[-2],[-1],[-2],[],[],[],[],[],[],[]]

        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(-2);
        minStack.push(-1);
        minStack.push(-2);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
    }
    public static void show(MinStack minStack){
        Deque<Integer> deque= minStack.deque;
        System.out.print("show");
        while (!deque.isEmpty()){
            System.out.print(deque.removeLast()+" ");
        }
    }
}
