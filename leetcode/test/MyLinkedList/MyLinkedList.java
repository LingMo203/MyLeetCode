package src.leetcode.test.MyLinkedList;

//707. 设计链表
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

    //打印链表数据
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


    //获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1
    public int get(int index) {
        MyLinkedList list=this.head;
        if (index<0)
            return -1;
        while (list!=null){
            if (index==0)
                return list.val;
            list=list.next;
            index--;
        }
        return -1;
    }

    // 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
    public void addAtHead(int val) {
        this.head=new MyLinkedList(val,head);
    }

    //将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
    public void addAtTail(int val) {
        if (head==null){
            head=new MyLinkedList(val,null);
            return;
        }
        MyLinkedList cur= this.head;
        while (cur.next!=null){
            cur=cur.next;
        }
        cur.next=new MyLinkedList(val,null);
    }

    // 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
    // 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
    // 如果 index 比长度更大，该节点将 不会插入 到链表中。
    public void addAtIndex(int index, int val) {
        MyLinkedList dummy=new MyLinkedList(0,this.head);
        if (index==0){
            head=new MyLinkedList(val,head);
        }
        while (dummy.next!=null&&index!=0){
            dummy=dummy.next;
            index--;
        }
        if (index==0){
            MyLinkedList temp = dummy.next;
            dummy.next=new MyLinkedList(val,temp);
        }
    }

    //如果下标有效，则删除链表中下标为 index 的节点。
    public void deleteAtIndex(int index) {
        if (index==0&&head!=null){
            head=head.next;
        }
        MyLinkedList dummy=new MyLinkedList(0,this.head);
        while (dummy.next!=null&&index!=0){
            dummy=dummy.next;
            index--;
        }
        if (index==0){
            if (dummy.next != null) {
                dummy.next=dummy.next.next;
            }
        }
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
