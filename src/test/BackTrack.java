package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BackTrack {
    public static void main(String[] args) {
        BackTrack bt=new BackTrack();
        int[] nums={2,3,6,7};
        //System.out.println(bt.combine(4,2));
        System.out.println(bt.combinationSum(nums,7));
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

    //39. 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        backTrackingCombinationSum(res,path,0,candidates,target,0);
        return res;
    }
    public void backTrackingCombinationSum(List<List<Integer>> res,List<Integer> path,int index,int[] candidates, int target,int sum){
        if (sum>=target){
            if (sum==target){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i=index; i<candidates.length;i++) {
            int num=candidates[i];
            path.add(num);
            sum+=num;
            backTrackingCombinationSum(res, path,i, candidates, target, sum);
            int t =  path.remove(path.size()-1);
            sum-=t;
        }
    }




















}
