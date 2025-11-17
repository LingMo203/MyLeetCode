package src.leetcode.test;

public class MyLinkedListTest {
    public static void main(String[] args) {
        int[] a={1,2,3};
        MyLinkedList list=new MyLinkedList(newList(a));
        list.showList();
        System.out.println(list.length());
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
