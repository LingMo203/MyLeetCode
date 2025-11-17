package src.leetcode.test;

public class MyLinkedList {
    int val;
    MyLinkedList next;
    MyLinkedList head;

    public MyLinkedList() {
        next=null;
    }

    public MyLinkedList(MyLinkedList head) {
        this.head = head;
    }


    public MyLinkedList(int val, MyLinkedList next) {
        this.val = val;
        this.next = next;
    }



    //获取链表长度
    public int length(){
        MyLinkedList cur=this.head;
        int n=0;
        while (cur!=null){
            n++;
            cur=cur.next;
        }
        return n;
    }

    public  void  showList(){
        System.out.print("[");
        MyLinkedList linkedList=this.head;
        while (linkedList!=null){
            if (linkedList.next==null)
                System.out.print(linkedList.val);
            else
                System.out.print(linkedList.val+",");
            linkedList=linkedList.next;
        }
        System.out.println("]");
    }


    public int get(int index) {
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
