package src.leetcode.test.TreeNode;

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
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        Queue<TreeNode> queue=new LinkedList<>();
//        List<List<Integer>> result=new ArrayList<>();
//        if (root!=null)
//            queue.add(root);
//        while (!queue.isEmpty()){
//            int size=queue.size();
//            List<Integer> temp=new ArrayList<>();
//            while (size-->0){
//                TreeNode a=queue.remove();
//                temp.add(a.val);
//                if (a.left!=null)
//                    queue.add(a.left);
//                if (a.right!=null)
//                    queue.add(a.right);
//            }
//            result.add(temp);
//        }
//        return result;
//    }
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

    //112. 路径总和 放弃
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null)
            return false;
        if (targetSum==0)
            return true;
        targetSum-=root.val;
        return hasPathSum(root.left,targetSum)||hasPathSum(root.right,targetSum);
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












}
