package test.MyLinkedList;

import java.util.*;

public class MyListNode {
    public static void main(String[] args) {
        int[] a={1,2,2,1};
        int[] b={4,6,8};
        MyListNode a1=new MyListNode();
        ListNode lista=newList(a);
        ListNode listb=newList(b);
//        showList(a1.addTwoNumbers(lista,listb));
        //showList(a1.deleteDuplicates(lista));
        //System.out.println(a1.listLength(lista));
        //System.out.println(Arrays.toString(a1.splitListToParts(lista, 5)));
        //System.out.println(a1.rotateRight(lista,4).val);
        //showList(a1.oddEvenList(lista));
        //showList(a1.deleteDuplicates2(lista));
        //showList(a1.swapNodes(lista,1));
        //showList(a1.deleteMiddle(lista));
        //showList(a1.addTwoNumbers2(lista,listb));
        //showList(a1.reverseList2(lista));
        //showList(a1.addTwoList(lista,listb));
        //showList(a1.reverseByK(lista,2,4));
        //showList(a1.reverseKGroup(lista,3));
        System.out.println(a1.isPalindrome(lista));
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
        System.out.print("[");
        while (listNode!=null){
            if (listNode.next==null)
                System.out.print(listNode.val);
            else
                System.out.print(listNode.val+",");
            listNode=listNode.next;
        }
        System.out.println("]");
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


    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode fast=head!=null&&head.next!=null ? head.next:null;
        while (head!=null&&fast!=null&&fast.next!=null){
            if (head==fast){
                return true;
            }
            head=head.next;
            fast=fast.next.next;
        }
        return false;
    }

    //142. 环形链表II
    public ListNode detectCycle(ListNode head) {
        if (head==null||head.next==null)
            return null;
        ListNode slow=head;
        ListNode fast=head;
        ListNode cur=head;
        do {
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                while (cur!=slow) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return cur;
            }
        }while (slow!=null&&fast!=null&&fast.next!=null);
        return null;
    }


    //2154. 将找到的值乘以 2
    public int findFinalValue(int[] nums, int original) {
        HashSet<Integer> hashSet=new HashSet<>();
        for (int num:nums){
            hashSet.add(num);
        }
        while (hashSet.contains(original)){
            original*=2;
        }
        return original;
    }
    //-------------------------

    //61. 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null) return head;
        int i=0,length=length(head);
        k=k>=length?k%length:k;
        if (k==0) return head;
        ListNode temp=null,cur=head,newHead=null;
        int h=length-k-1;
        while (cur.next!=null){
            if (i==h){
                temp=cur;
            }
            i++;
            cur=cur.next;
        }
        cur.next=head;
        newHead=temp.next;
        temp.next=null;
        return newHead;
    }
    public int length(ListNode head){
        int i=0;
        while (head!=null){
            i++;
            head=head.next;
        }
        return i;
    }

    //237. 删除链表中的节点
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

    //328. 奇偶链表
    public ListNode oddEvenList(ListNode head) {
        if (head==null) return null;
        ListNode doubleHead=new ListNode(0,null);
        ListNode curDouble=doubleHead;
        ListNode cur=head;
        while (cur.next!=null&&cur.next.next!=null){
            curDouble.next=cur.next;
            curDouble=curDouble.next;
            cur.next=cur.next.next;
            cur=cur.next;
        }
        if (cur.next!=null){
            curDouble.next=cur.next;
            curDouble=curDouble.next;
        }
        curDouble.next=null;
        cur.next=doubleHead.next;
        return head;
    }



    //82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates2(ListNode head) {
        if (head==null) return null;
        ListNode dummy=new ListNode(0,head);
        ListNode cur=dummy;
        while (cur.next!=null&&cur.next.next!=null){
            boolean f=false;
            while (cur.next.next!=null&&(cur.next.val==cur.next.next.val)){
                cur.next=cur.next.next;
                f=true;
            }
            if (f) cur.next=cur.next.next;
            else cur=cur.next;
        }
        return dummy.next;
    }


    //1721. 交换链表中的节点
    public ListNode swapNodes(ListNode head, int k) {
        if (head==null) return null;
        ListNode cur=head,be = head,end=cur;
        int i=1,j=-(k);
        while (cur!=null){
            if (i==k) be=cur;
            if (j>=0) end=end.next;
            cur=cur.next;
            i++;j++;
        }
        int temp=be.val;
        be.val=end.val;
        end.val=temp;
        return head;
    }


    //2095. 删除链表的中间节点
    public ListNode deleteMiddle(ListNode head) {
        if (head==null) return null;
        ListNode dummy=new ListNode(0,head);
        ListNode low=dummy,fast=dummy.next;
        while (fast!=null&&fast.next!=null){
            low=low.next;
            fast=fast.next.next;
        }
        low.next=low.next.next;
        return dummy.next;
    }

    //92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        Deque<ListNode> deque=new ArrayDeque<>();
        ListNode dummy=new ListNode(0,head);
        ListNode cur=dummy,l=dummy,r;
        int i=1;
        while (i<=right){
            if (i>=left){
                deque.addLast(cur.next);
            }else {
                l=l.next;
            }
            cur=cur.next;
            i++;
        }
        r=cur.next;
        while (!deque.isEmpty()){
            l.next=deque.removeLast();
            l=l.next;
        }
        l.next=r;
        return dummy.next;
    }

    //445. 两数相加 II
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int l1length=length(l1),l2length=length(l2);
        Deque<Integer> deque=new ArrayDeque<>();
        while (l1length>l2length){
            deque.addLast(l1.val);
            l1=l1.next;
            l1length--;
        }
        while (l1length<l2length){
            deque.addLast(l2.val);
            l2=l2.next;
            l2length--;
        }
        while (l1!=null&&l2!=null){
            deque.addLast(l1.val+l2.val);
            l1=l1.next;
            l2=l2.next;
        }
        int size=deque.size()-1;
        int[] nums=new int[size+1];
        boolean isAddOne=false;
        while (!deque.isEmpty()){
            int num=deque.removeLast();
            if (isAddOne){
                num++;
                isAddOne=false;
            }
            if (num>9){
                num-=10;
                isAddOne=true;
            }
            nums[size--]=num;
        }
        return isAddOne ? new ListNode(1,newList(nums)) : newList(nums);
    }


    //206. 反转链表
    public ListNode reverseList2(ListNode head) {
        ListNode cur=head,last=null;
        while (cur!=null&&cur.next!=null){
            ListNode next=cur.next;
            cur.next=last;
            last=cur;
            cur=next;
        }
        if (cur != null) {
            cur.next=last;
        }
        return cur;
    }

    //143. 重排链表
    public void reorderList(ListNode head) {
        Deque<ListNode> deque=new ArrayDeque<>();
        ListNode cur=head;
        int size=0;
        while (cur!=null){
            size++;
            deque.addLast(cur);
            cur=cur.next;
        }
        cur=new ListNode();
        if (size%2==0){
            while (!deque.isEmpty()){
                ListNode start=deque.removeFirst();
                ListNode end=deque.removeLast();
                cur.next=start;
                start.next=end;
                end.next=null;
                cur=end;
            }
        }else {
            while (deque.size()>1){
                ListNode start=deque.removeFirst();
                ListNode end=deque.removeLast();
                cur.next=start;
                start.next=end;
                end.next=null;
                cur=end;
            }
            cur.next=deque.removeLast();
            cur.next.next=null;
        }
    }

    //23. 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0) return null;
        else if (lists.length==1) return lists[0];
        ListNode addList=addTwoList(lists[0],lists[1]);
        for (int i=2;i<lists.length;i++){
            addList=addTwoList(addList,lists[i]);
        }
        return addList;
    }
    public ListNode addTwoList(ListNode l1,ListNode l2){
        ListNode newDummyHead=new ListNode();
        ListNode cur=newDummyHead;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                cur.next=l1;
                l1=l1.next;
            }else {
                cur.next=l2;
                l2=l2.next;
            }
            cur=cur.next;
        }
        if (l1!=null){
            cur.next=l1;
        }else if (l2!=null){
            cur.next=l2;
        }
        return newDummyHead.next;
    }

    //25. K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        int length=length(head);
        for (int i=0;i+k<=length;i+=k){
            head=reverseByK(head,i,k);
        }
        return head;
    }
    public ListNode reverseByK(ListNode head,int start,int k){
        ListNode cur=head,curNewHead=new ListNode(0,head);
        for (int i=0;i<start;i++){
            cur=cur.next;
            curNewHead=curNewHead.next;
        }
        ListNode last=null;
        for (int i=0;i<k;i++){
            if (cur!=null) {
                ListNode next = cur.next;
                cur.next = last;
                last = cur;
                cur = next;
            }
        }
        ListNode reend=last;
        while (reend!=null&&reend.next!=null){
            reend=reend.next;
        }
        reend.next=cur;
        if (start>0) curNewHead.next=last;
        return start>0?head:last;
    }


    //234. 回文链表
    public boolean isPalindrome(ListNode head) {
        ListNode slow=head,fast=head,last=null;
        int length=listLength(head);
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            ListNode temp=slow.next;
            slow.next=last;
            last=slow;
            slow=temp;
        }
        if (length%2==1&&slow != null) slow=slow.next;
        while (last!=null&&slow!=null){
            if (last.val!=slow.val) return false;
            last=last.next;
            slow=slow.next;
        }
        return true;
    }








}












