package src.leetcode.test;

public class MyListNode {
    public static void main(String[] args) {
        int[] a={2,4,3};
        int[] b={2,4,3};
        MyListNode a1=new MyListNode();
        ListNode lista=newList(a);
        ListNode listb=newList(b);
        showList(a1.addTwoNumbers(lista,listb));
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





    //-------------------------










}












