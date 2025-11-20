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









}
