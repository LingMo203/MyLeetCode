package test.TreeNode;

import java.util.*;

public class TreeNodeTest {
    public static void main(String[] args) {
     TreeNodeTest test=new TreeNodeTest();
    }
}

class Solution {

    //144. 二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        preorderTraversalImplement(root,list);
        return list;
    }
    public void preorderTraversalImplement(TreeNode cur,List<Integer> list){
        if (cur==null)
            return;
        list.add(cur.val);
        preorderTraversalImplement(cur.left,list);
        preorderTraversalImplement(cur.right,list);
    }

    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        inorderTraversalImplement(root,list);
        return list;
    }
    public void inorderTraversalImplement(TreeNode cur,List<Integer> list){
        if (cur==null)
            return;
        inorderTraversalImplement(cur.left,list);
        list.add(cur.val);
        inorderTraversalImplement(cur.right,list);
    }

    //145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        postorderTraversalImplement(root,list);
        return list;
    }
    public void postorderTraversalImplement(TreeNode cur,List<Integer> list){
        if (cur==null)
            return;
        postorderTraversalImplement(cur.left,list);
        postorderTraversalImplement(cur.right,list);
        list.add(cur.val);
    }

    //100. 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null)
            return true;
        else if (p==null||q==null)
            return false;
        else if (p.val!=q.val)
            return false;
        else
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if (root!=null) queue.add(root);
        while (!queue.isEmpty()){
            int size= queue.size();
            List<Integer> list=new ArrayList<>();
            while (size-->0){
                TreeNode temp=queue.remove();
                list.add(temp.val);
                if (temp.left!=null) queue.add(temp.left);
                if (temp.right!=null) queue.add(temp.right);
            }
            result.add(list);
        }
        return result;
    }



    //103. 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if (root!=null) queue.add(root);
        boolean s=false;
        while (!queue.isEmpty()){
            int size=queue.size();
            LinkedList<Integer> list=new LinkedList<>();
            while (size-->0){
                TreeNode temp=queue.remove();
                if (s){
                    list.addFirst(temp.val);
                }else {
                    list.addLast(temp.val);
                }
                if (temp.left!=null)
                    queue.add(temp.left);
                if (temp.right!=null)
                    queue.add(temp.right);
            }
            result.add(list);
            s=!s;
        }
        return result;
    }

    //226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        TreeNode rightTree=root.right;
        TreeNode leftTree=itree(root.left);
        return same(leftTree,rightTree);
    }
    public TreeNode itree(TreeNode root){
        if (root==null) return root;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        itree(root.left);
        itree(root.right);
        return root;
    }
    public boolean same(TreeNode root1,TreeNode root2){
        if (root1==null&&root2==null) return true;
        else if (root1==null||root2==null) return false;
        else if (root1.val!= root2.val) return false;
        return same(root1.left,root2.left)&&same(root1.right,root2.right);
    }
    //省去翻转步骤
    public boolean isSymmetric2(TreeNode root){
        return same2(root.left,root.right);
    }
    public boolean same2(TreeNode leftRoot,TreeNode rightRoot){
        if (leftRoot==null&&rightRoot==null) return true;
        else if (leftRoot==null||rightRoot==null) return false;
        else if (leftRoot.val!= rightRoot.val) return false;
        return same2(leftRoot.left,rightRoot.right)&&same2(leftRoot.right,rightRoot.left);
    }

    //104. 二叉树的最大深度
    public int maxDepth2(TreeNode root) {
        return deep(root,0);
    }
    public int deep(TreeNode root,int time){
        if (root==null) return time;
        time++;
        return Math.max(deep(root.left,time),deep(root.right,time));
    }
    //后序遍历
    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }

    //111. 二叉树的最小深度
    public int minDepth(TreeNode root){
        if (root==null) return 0;
        if (root.right==null) return minDepth(root.left)+1;
        else if (root.left==null) return minDepth(root.right)+1;
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }

    //222. 完全二叉树的节点个数 层序遍历
    public int countNodes(TreeNode root) {
        if (root==null) return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int time=0;
        boolean k=true;
        while (!queue.isEmpty()&&k){
            int size=queue.size();
            while (size-->0){
                TreeNode temp=queue.remove();
                time++;
                if (temp.left==null){
                    k=false;
                    break;
                }else {
                    queue.add(temp.left);
                }
                if (temp.right==null){
                    k=false;
                    break;
                }else {
                    queue.add(temp.right);
                }
            }
        }
        while (!queue.isEmpty()){
            queue.remove();
            time++;
        }
        return time;
    }


    //110.平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return ib(root)!=-1;
    }
    public int ib(TreeNode root){
        if (root==null) return 0;
        int left=ib(root.left);
        if (left==-1) return -1;
        int right=ib(root.right);
        if (right==-1) return -1;
        int result;
        if (Math.abs(left-right)>1) return -1;
        else result=Math.max(left,right)+1;
        return result;
    }

    //257. 二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        btp(root,result,list);
        return result;
    }
    public void btp(TreeNode root,List<String> result,List<Integer> list){
        if (root==null) {
            return;
        }
        list.add(root.val);
        if (root.left==null&&root.right==null){
            String re = "";
            for (int i=0;i<list.size();i++){
                if (i==0){
                    re = list.get(i) + "";
                }else {
                    re=re+"->"+list.get(i);
                }
            }
            result.add(re);
            list.remove(list.size()-1);
            return;
        }
        btp(root.left,result,list);
        btp(root.right,result,list);
        list.remove(list.size()-1);
    }

    //199. 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<ArrayList<Integer>> arrayList=new ArrayList<>();
        ArrayList<Integer> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if (root!=null) queue.add(root);
        int size=0;
        while (!queue.isEmpty()){
            size=queue.size();
            ArrayList<Integer> list=new ArrayList<>();
            TreeNode temp = null;
            while (size-->0){
                temp=queue.poll();
                if (temp.left!=null) queue.add(temp.left);
                if (temp.right!=null) queue.add(temp.right);
            }
            result.add(temp.val);
        }
        return result;
    }


    //114. 二叉树展开为链表
    public void flatten(TreeNode root) {
        ArrayList<Integer> list=new ArrayList<>();
        fl(root,list);
        int length=list.size();
        for (int i=0;i<length;i++){
            root.val=list.get(i);
            root.left=null;
            if (root.right==null){
                if (i==length-1) break;
                root.right=new TreeNode();
            }
            root=root.right;
        }
    }
    public void fl(TreeNode root,ArrayList<Integer> list){
        if (root==null) return;
        list.add(root.val);
        fl(root.left,list);
        fl(root.right,list);
    }

    //230. 二叉搜索树中第 K 小的元素
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> list=new ArrayList<>();
        abc(root,list);
        return list.get(k-1);
    }
    public void abc(TreeNode root,ArrayList<Integer> list){
        if (root==null) return;
        abc(root.left,list);
        list.add(root.val);
        abc(root.right,list);
    }

    //107. 二叉树的层序遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if (root==null) return result;
        Deque<List<Integer>> listDeque=new ArrayDeque<>();
        Deque<TreeNode> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            int size=deque.size();
            List<Integer> list=new ArrayList<>();
            while (size-->0){
                TreeNode temp=deque.removeFirst();
                list.add(temp.val);
                if (temp.left!=null) deque.addLast(temp.left);
                if (temp.right!=null) deque.addLast(temp.right);
            }
            listDeque.addLast(list);
        }
        while (!listDeque.isEmpty()){
            result.add(listDeque.removeLast());
        }
        return result;
    }


    //112. 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum){
        if (root==null) return false;
        return dfsHasPathSum(root,targetSum,0);
    }
    public boolean dfsHasPathSum(TreeNode root, int targetSum,int sum){
        if (root==null) return false;
        sum+=root.val;
        if (root.left==null&&root.right==null){
            return sum == targetSum;
        }
        return dfsHasPathSum(root.left,targetSum,sum)||dfsHasPathSum(root.right,targetSum,sum);
    }

    //429. N 叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result =new ArrayList<>();
        if (root==null) return result;
        Deque<Node> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            int size=deque.size();
            List<Integer> list=new ArrayList<>();
            while (size-->0){
                Node temp=deque.removeFirst();
                list.add(temp.val);
                List<Node> nodeList = temp.children;
                for (Node node : nodeList) {
                    deque.addLast(node);
                }
            }
            result.add(list);
        }
        return result;
    }

    //515. 在每个树行中找最大值
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if (root==null) return result;
        Deque<TreeNode> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            int size=deque.size(),max=Integer.MIN_VALUE;
            while (size-->0){
                TreeNode temp=deque.removeFirst();
                max=Math.max(max,temp.val);
                if (temp.left!=null) deque.addLast(temp.left);
                if (temp.right!=null) deque.addLast(temp.right);
            }
            result.add(max);
        }
        return result;
    }


    //637. 二叉树的层平均值
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result=new ArrayList<>();
        if (root==null) return result;
        Deque<TreeNode> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            int size=deque.size(),count=0;
            double sum=0;
            while (size-->0){
                TreeNode temp=deque.removeFirst();
                sum+=temp.val;count++;
                if (temp.left!=null) deque.addLast(temp.left);
                if (temp.right!=null) deque.addLast(temp.right);
            }
            result.add(sum/count);
        }
        return result;
    }

    //116. 填充每个节点的下一个右侧节点指针 和 117. 填充每个节点的下一个右侧节点指针 II
    public Node connect(Node root) {
        if (root==null) return null;
        Deque<Node> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            int size=deque.size();
            while (size-->1){
                Node node=deque.removeFirst();
                node.next=deque.getFirst();
                if (node.left!=null) deque.addLast(node.left);
                if (node.right!=null) deque.addLast(node.right);
            }
            Node node=deque.removeFirst();
            node.next=null;
            if (node.left!=null) deque.addLast(node.left);
            if (node.right!=null) deque.addLast(node.right);
        }
        return root;
    }

    //129. 求根节点到叶节点数字之和
    public int sumNumbers(TreeNode root) {
        if (root==null) return 0;
        List<Integer> list=new ArrayList<>();
        dfsSumNumbers(root,0,list);
        int sum=0;
        for (int num:list){
            sum+=num;
        }
        return sum;
    }
    public void dfsSumNumbers(TreeNode root,int sum,List<Integer> list){
        int num = sum*10 + root.val;
        if (root.left==null&&root.right==null){
            list.add(num);
            return;
        }
        if (root.left != null) {
            dfsSumNumbers(root.left,num,list);
        }
        if (root.right != null) {
            dfsSumNumbers(root.right, num, list);
        }
    }


    //235. 二叉搜索树的最近公共祖先 和 236. 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> listp=new ArrayList<>();
        ArrayList<TreeNode> listq=new ArrayList<>();
        dfsLowestCommonAncestor(root,listp,p);
        dfsLowestCommonAncestor(root,listq,q);
        int i=0;
        while (i < listp.size() && i < listq.size() &&listp.get(i).val==listq.get(i).val){
            i++;
        }
        return listp.get(i-1);
    }
    public boolean dfsLowestCommonAncestor(TreeNode root,ArrayList<TreeNode> list,TreeNode p){
        if (root==null){
            return false;
        }
        list.add(root);
        if (root.val==p.val){
            return true;
        }
        if (dfsLowestCommonAncestor(root.left,list,p)) return true;
        if (dfsLowestCommonAncestor(root.right,list,p)) return true;
        list.remove(list.size()-1);
        return false;
    }

    //98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return dfsIsValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public boolean dfsIsValidBST(TreeNode root,long min,long max){
        if (root==null) return true;
        if (root.val<=min||root.val>=max) return false;
        return dfsIsValidBST(root.left,min,root.val)&&dfsIsValidBST(root.right,root.val,max);
    }










}
