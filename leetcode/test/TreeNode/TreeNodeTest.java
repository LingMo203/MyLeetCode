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
        pt(root,list);
        return list;
    }
    public void pt(TreeNode cur,List<Integer> list){
        if (cur==null)
            return;
        list.add(cur.val);
        pt(cur.left,list);
        pt(cur.right,list);
    }
}
