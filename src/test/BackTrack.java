package test;

import java.util.*;

public class BackTrack {
    public static void main(String[] args) {
        BackTrack bt=new BackTrack();
        int[] nums={1,2,3};
        String str="aab";
        //System.out.println(bt.combine(4,2));
        //System.out.println(bt.combinationSum(nums,7));
        //System.out.println(bt.combinationSum2(nums,8));
        //System.out.println(bt.permute(nums));
        //System.out.println(bt.permuteUnique(nums));
        //System.out.println(bt.getPermutation(3,3));
        //System.out.println(bt.letterCombinations(str));
        System.out.println(bt.partition(str));
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

    //90. 子集 II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        res.add(new ArrayList<>(path));
        Arrays.sort(nums);
        backSubsetsWithDup(res,path,0,nums);
        return res;
    }
    public void backSubsetsWithDup(List<List<Integer>> res,List<Integer> path,int start,int[] nums){
        for (int i=start;i<nums.length;i++){
            int num=nums[i];
            if (i>start&&num==nums[i-1]) continue;
            path.add(num);
            backSubsetsWithDup(res, path, i+1, nums);
            res.add(new ArrayList<>(path));
            path.remove(path.size()-1);
        }
    }


    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        HashSet<Integer> hashSet=new HashSet<>();
        backPermute(res,path,hashSet,nums);
        return res;
    }
    public void backPermute(List<List<Integer>> res,List<Integer> path,HashSet<Integer> hashSet,int[] nums){
        if (path.size()==nums.length){
            res.add(new ArrayList<>(path));
        }
        for (int num : nums) {
            if (path.contains(num)) continue;
            path.add(num);
            hashSet.add(num);
            backPermute(res, path, hashSet, nums);
            int n=path.remove(path.size() - 1);
            hashSet.remove(n);
        }
    }


    //47. 全排列 II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num:nums){
            hashMap.put(num,hashMap.getOrDefault(num,0)+1);
        }
        backPermuteUnique(res,path,hashMap,nums.length);
        return res;
    }
    public void backPermuteUnique(List<List<Integer>> res,List<Integer> path,HashMap<Integer,Integer> hashMap,int n){
        if (path.size()==n){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int num:hashMap.keySet()){
            int time = hashMap.get(num);
            if (time<=0) continue;
            hashMap.put(num,time-1);
            path.add(num);
            backPermuteUnique(res, path, hashMap , n);
            int temp=path.remove(path.size()-1);
            hashMap.put(temp,hashMap.get(temp)+1);
        }
    }


    //60. 排列序列 (回溯不是最优解)
    public String getPermutation(int n, int k) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        HashSet<Integer> hashSet=new HashSet<>();
        backGetPermutation(res,path,hashSet,n,k);
        List<Integer> list=res.get(res.size()-1);
        StringBuilder sb=new StringBuilder();
        for (int num:list){
            sb.append(num);
        }
        return sb.toString();
    }
    public void backGetPermutation(List<List<Integer>> res,List<Integer> path,HashSet<Integer> hashSet,int n, int k){
        if (res.size()==k) return;
        if (path.size()==n){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=1;i<=n;i++){
            if (hashSet.contains(i)) continue;
            path.add(i);
            hashSet.add(i);
            backGetPermutation(res, path, hashSet, n, k);
            int num=path.remove(path.size()-1);
            hashSet.remove(num);
        }
    }


    //17. 电话号码的字母组合  没看题解，独立完成 磨了好几个点 太牛逼了
    public List<String> letterCombinations(String digits) {
        char[][] letters={
                {},
                {},
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };
        char[][] numbers=new char[digits.length()][];
        for (int i=0;i<digits.length();i++){
            int n=digits.charAt(i)-'0';
                numbers[i]=letters[n];
        }
        List<String> res=new ArrayList<>();
        StringBuilder path=new StringBuilder();
        backLetterCombinations(res,path,numbers,0,0);
        return res;
    }
    public void backLetterCombinations(List<String> res,StringBuilder path,char[][] numbers,int startI,int startJ){
        if (path.length()==numbers.length){
            res.add(path.toString());
            return;
        }
        for (int i=startI;i<numbers.length;i++){
            for (int j=0;j<numbers[i].length;j++){
                char c=numbers[i][j];
                path.append(c);
                backLetterCombinations(res, path, numbers, i+1, j+1);
                path.deleteCharAt(path.length()-1);
            }
        }
    }


    //131. 分割回文串
    public List<List<String>> partition(String s) {
        List<List<String>> res=new ArrayList<>();
        List<String> path=new ArrayList<>();
        backPartition(res,path,0,s);
        return res;
    }
    public void backPartition(List<List<String>> res,List<String> path,int start,String s){
        if (start==s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=start;i<s.length();i++){
            String str=s.substring(start,i+1);
            boolean f=false;
            for (int left=0,right=str.length()-1;left<right;left++,right--){
                if (str.charAt(left)!=str.charAt(right)) {
                    f=true;
                    break;
                }
            }
            if (f) continue;
            path.add(str);
            backPartition(res, path, i+1, s);
            path.remove(path.size()-1);
        }
    }








}
