package src.leetcode.test;

class MyLinkedList {
    int val;
    MyLinkedList next;
    MyLinkedList duhead=new MyLinkedList();

    public MyLinkedList() {
        next=null;
    }

    public int get(int index) {
        MyLinkedList cur=duhead.next;
        int time=0;

        while (index>0){
            cur=cur.next;
            index--;
        }
        return -1;
    }

    public void addAtHead(int val) {

    }

    public void addAtTail(int val) {

    }

    public void addAtIndex(int index, int val) {

    }

    public void deleteAtIndex(int index) {

    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
