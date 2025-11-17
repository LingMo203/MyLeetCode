package src.leetcode.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MyListNode {
    public static void main(String[] args) {
        int[] a={1,2,3};
        int[] b={1,2,3};
        MyListNode a1=new MyListNode();
        ListNode lista=newList(a);
        ListNode listb=newList(b);
//        showList(a1.addTwoNumbers(lista,listb));
        //showList(a1.deleteDuplicates(lista));
        //System.out.println(a1.listLength(lista));
        //System.out.println(Arrays.toString(a1.splitListToParts(lista, 5)));
    }
    public static ListNode newList(int[] nums)  {
        ListNode list=new ListNode(0,null);
        ListNode cur=list;
        for (int num:nums){
            cur.next=new ListNode(num,null);
            cur=cur.next;
        }
        return list.next;
    }

    public static void  showList(ListNode listNode){
        while (listNode!=null){
            if (listNode.next==null)
                System.out.print(listNode.val);
            else
                System.out.print(listNode.val+",");
            listNode=listNode.next;
        }
    }




    //160.相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a=headA;
        ListNode b=headB;
        while (a!=b){
            a=a==null?headB:a.next;
            b=b==null?headA:b.next;
        }
        return a;
    }

    //206. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode re=null;
        ListNode he=head;
        while (he!=null){
            head=head.next;
        }
        return null;
    }

    //203.移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        while (head!=null&&head.val==val){
            head=head.next;
        }
        ListNode cur=head;
        while (cur!=null&&cur.next!=null){
            if (cur.next.val==val){
                cur.next=cur.next.next;
            }else {
                cur=cur.next;
            }
        }
        return head;
    }
    public ListNode removeElements2(ListNode head, int val){
        ListNode result=new ListNode(0,head);
        ListNode cur=result;
        while (cur.next!=null){
            if (cur.next.val==val){
                cur.next=cur.next.next;
            }else {
                cur=cur.next;
            }
        }
        return result.next;
    }

    //21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dumHead=new ListNode(0,null);
        ListNode c=dumHead;
        while (list1!=null&&list2!=null){
            if (list1.val<= list2.val){
                c.next=list1;
                list1=list1.next;
            }else {
                c.next=list2;
                list2=list2.next;
            }
            c=c.next;
        }
        c.next=list1!=null?list1:list2;
        return dumHead.next;
    }


    //19. 删除链表的倒数第 N 个结点    1,2,3,4,5  1,2,3,5  2
    //                              0 1 2 3 4 i-x=0 x=-i
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode del =new ListNode(0,head);
        ListNode cur=del;
        ListNode rehead=del;
        int i=-n;
        while (cur!=null){
            if (i>=1){
                del=del.next;
            }
            cur=cur.next;
            i++;
        }
        if (del != null&&del.next!=null) {
            del.next=del.next.next;
        }
        return rehead.next;
    }

    //24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        ListNode dummy=new ListNode(0,head);
        ListNode cur=dummy;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode c1=cur.next;
            ListNode c2=c1.next;
            ListNode temp=c2.next;
            cur.next=c2;
            c2.next=c1;
            c1.next=temp;
            cur=c1;
        }
        return dummy.next;
    }

    //2. 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(0,null);
        ListNode cur=dummy;
        boolean add1=false;
        while (l1!=null&&l2!=null){
            int a=l1.val+ l2.val;
            if (add1){
                a++;
            }
            if (a<10){
                cur.next= new ListNode(a,null);
                add1=false;
            }else {
                cur.next= new ListNode(a%10,null);
                add1=true;
            }
            l1=l1.next;
            l2=l2.next;
            cur=cur.next;
        }
        while (l1==null&&l2!=null){
            int a=l2.val;
            if (add1){
                a++;
            }
            if (a<10){
                cur.next= new ListNode(a,null);
                add1=false;
            }else {
                cur.next= new ListNode(a%10,null);
                add1=true;
            }
            l2=l2.next;
            cur=cur.next;
        }
        while (l2==null&&l1!=null){
            int a=l1.val;
            if (add1){
                a++;
            }
            if (a<10){
                cur.next= new ListNode(a,null);
                add1=false;
            }else {
                cur.next= new ListNode(a%10,null);
                add1=true;
            }
            l1=l1.next;
            cur=cur.next;
        }
        if (add1){
            cur.next= new ListNode(1,null);
        }
        return dummy.next;
    }


    //83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=new ListNode(0,head);
        ListNode cur=dummy;
        int last=-9999;
        while (cur!=null&&cur.next!=null){
            if (cur.next.val!=last){
                last=cur.next.val;
                cur=cur.next;
            }else {
                cur.next=cur.next.next;
            }
        }
        return dummy.next;
    }


    //86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        //ListNode dummy=new ListNode(0,head);
        ListNode dhead=new ListNode(0,null);
        ListNode curd=dhead;
        ListNode xhead=new ListNode(0,null);
        ListNode curx=xhead;
        ListNode cur=head;
        while (cur!=null){
            if (cur.val<x){
                curd.next=new ListNode(cur.val,null);
                curd=curd.next;
            }else {
                curx.next=new ListNode(cur.val,null);
                curx=curx.next;
            }
            cur=cur.next;
        }
        curd.next=xhead.next;
        return dhead.next;
    }


    //725. 分隔链表
    public ListNode[] splitListToParts(ListNode head, int k) {
        int length=listLength(head);
        int number=length/k!=0?length/k:length;
        int remainder=length/k!=0?length%k:0;
        int j=0;
        ListNode[] result=new ListNode[k];
        while (head!=null){
            int n=length<k?1:number;
            if (remainder>0){
                n++;
                remainder--;
            }
            ListNode temp=new ListNode(0,null);
            ListNode curt=temp;
            while (n>0){
                curt.next=new ListNode(head.val,head.next);
                curt=curt.next;
                head=head.next;
                n--;
            }
            curt.next=null;
            result[j]=temp.next;
            j++;

        }
        return result;
    }
    public int listLength(ListNode head){
        int length=0;
        ListNode cur=head;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }

    //876. 链表的中间结点
    public ListNode middleNode(ListNode head) {
        List<ListNode> list=new ArrayList<>();
        int length=0;
        while (head!=null){
            length++;
            list.add(head);
            head=head.next;
        }
        return list.get(length/2);
    }


    //-------------------------










}












