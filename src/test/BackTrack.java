package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BackTrack {
    public static void main(String[] args) {
        BackTrack bt=new BackTrack();
        System.out.println(bt.combine(4,2));
    }



    //77. 组合
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        backTrackingCombine(res,path,1,n,k);
        return res;
    }
    public void backTrackingCombine(List<List<Integer>> res,List<Integer> path,int index,int n,int k){
        if (path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=index;i<=n;i++){
            path.add(i);
            backTrackingCombine(res,path,i+1,n,k);
            path.remove(path.size()-1);
        }
        return;
    }




















}
