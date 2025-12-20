package test.MyLinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) {
        int[] a={1,2,3};
        //MyLinkedList list=new MyLinkedList(newList(a));
        MyLinkedList list=new MyLinkedList();
        //list.showList();
//        System.out.println(list.length());
//        System.out.println(list.get(2));
//        list.addAtHead(1);
//        list.addAtTail(99);
//        list.addAtIndex(5,66);
//        list.deleteAtIndex(6);
//        list.showList();

        MyLinkedList myLinkedList = new MyLinkedList();
        //["MyLinkedList","addAtTail","addAtTail","get"] [[],[1],[3],[1]]
        myLinkedList.showList();
        myLinkedList.addAtTail(1);
        myLinkedList.showList();
        myLinkedList.addAtTail(3);
        myLinkedList.showList();

    }


    public static MyLinkedList newList(int[] nums)  {
        MyLinkedList list=new MyLinkedList(0,null);
        MyLinkedList cur=list;
        for (int num:nums){
            cur.next=new MyLinkedList(num,null);
            cur=cur.next;
        }
        return list.next;
    }



}
