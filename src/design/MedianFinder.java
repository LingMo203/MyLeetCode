package design;

import java.util.Collections;
import java.util.PriorityQueue;

//295. 数据流的中位数
public class MedianFinder {

    PriorityQueue<Integer> maxNum;
    PriorityQueue<Integer> minNum;

    public MedianFinder() {
        maxNum = new PriorityQueue<>();
        minNum = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        minNum.add(num);
        for (int minLength = minNum.size(), maxLength = maxNum.size(); minLength - 1 > maxLength ; minLength--, maxLength++) {
            maxNum.add(minNum.remove());
        }
        while (!maxNum.isEmpty()&&!minNum.isEmpty()&&minNum.peek()>maxNum.peek()){
            int minN=minNum.remove();
            int maxN=maxNum.remove();
            minNum.add(maxN);
            maxNum.add(minN);
        }
    }

    public double findMedian() {
        return minNum.size()==maxNum.size() ? (double) (minNum.peek() + maxNum.peek()) /2 : minNum.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian());  // 返回 1.5 ((1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
}


/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */