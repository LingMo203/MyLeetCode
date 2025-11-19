package src.leetcode.test.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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










}
