package design;

//622. 设计循环队列
public class MyCircularQueue {

    private int[] queue;
    private int top, botton, size, number;

    public MyCircularQueue(int k) {
        this.queue = new int[k];
        this.size = k;
        this.top = 0;
        this.botton = 1;
        this.number = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        botton--;
        if (botton < 0) botton = size - 1;
        queue[botton] = value;
        number++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        top--;
        if (top < 0) top = size - 1;
        number--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return queue[top];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return queue[botton];
    }

    public boolean isEmpty() {
        return number == 0;
    }

    public boolean isFull() {
        return number == size;
    }
}
/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */