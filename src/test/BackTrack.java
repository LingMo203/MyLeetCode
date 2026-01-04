package test;

import java.util.*;

public class BackTrack {
    public static void main(String[] args) {
        BackTrack bt=new BackTrack();
        int[] nums={10,1,2,7,6,1,5};
        //System.out.println(bt.combine(4,2));
        //System.out.println(bt.combinationSum(nums,7));
        System.out.println(bt.combinationSum2(nums,8));
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


    //40. 组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        Arrays.sort(candidates);
        backTrackingCombinationSum2(res,path,0,candidates,target,0);
        return res;
    }
    public void backTrackingCombinationSum2(List<List<Integer>> res,List<Integer> path,int index,int[] candidates, int target,int sum){
        if (sum>=target){
            if (sum==target){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i=index;i<candidates.length;i++){
            int num=candidates[i];
            if (i>index && num==candidates[i-1]) continue;
            sum+=num;
            path.add(num);
            backTrackingCombinationSum2(res, path, i+1, candidates, target, sum);
            int temp=path.remove(path.size()-1);
            sum-=temp;
        }
    }


    //216. 组合总和 III
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        backTrackingCombinationSum3(res,path,1,k,n,0);
        return res;
    }
    public void backTrackingCombinationSum3(List<List<Integer>> res,List<Integer> path,int index,int k, int n,int sum){
        if (sum>n||path.size()>k) return;
        if (sum==n){
            if (path.size()==k) res.add(new ArrayList<>(path));
            return;
        }
        for (int i=index;i<=9;i++){
            sum+=i;
            path.add(i);
            backTrackingCombinationSum3(res, path, i+1, k, n, sum);
            int temp=path.remove(path.size()-1);
            sum-=temp;
        }
    }


    //78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        res.add(new ArrayList<>(path));
        backSubsets(res,path,0,nums);
        return res;
    }
    public void backSubsets(List<List<Integer>> res,List<Integer> path,int start,int[] nums){
        for (int i=start;i<nums.length;i++){
            int num=nums[i];
            path.add(num);
            backSubsets(res, path, i+1, nums);
            res.add(new ArrayList<>(path));
            path.remove(path.size()-1);
        }
    }
















}
