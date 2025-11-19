package src.leetcode.test.TreeNode;

import java.util.ArrayList;
import java.util.List;

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











}
