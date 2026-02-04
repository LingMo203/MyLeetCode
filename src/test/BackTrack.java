package test;

import util.*;

import java.util.*;

public class BackTrack {
    public static void main(String[] args) {
        BackTrack bt=new BackTrack();
        int[] nums={4,6,7,7};
        String str="ABCESEEEFS";
        String strGrid="[[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);

        String charInput2 = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"E\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]";
        char[][] charArray2 = ArrayStringUtils.parse2DCharArraySmart(charInput2);
//        System.out.println(str2.matches("0\\d+"));
        //System.out.println(bt.combine(4,2));
        //System.out.println(bt.combinationSum(nums,7));
        //System.out.println(bt.combinationSum2(nums,8));
        //System.out.println(bt.permute(nums));
        //System.out.println(bt.permuteUnique(nums));
        //System.out.println(bt.getPermutation(3,3));
        //System.out.println(bt.letterCombinations(str));
        //System.out.println(bt.partition(str));
        //System.out.println(bt.restoreIpAddresses(str));
        //System.out.println(bt.findSubsequences(nums));
        //System.out.println(bt.solveNQueens(4));
        //System.out.println(bt.countNumbersWithUniqueDigits(2));
//        bt.solveSudoku(board);
//        System.out.println(T4.isValidSudoku(board));
        //System.out.println(bt.exist(charArray2,str));
        System.out.println(bt.rotatedDigits(10));
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


    //93. 复原 IP 地址
    public List<String> restoreIpAddresses(String s) {
        List<String> res=new ArrayList<>();
        List<String> path=new ArrayList<>();
        backRestoreIpAddresses(res,path,s,0);
        return res;
    }
    public void backRestoreIpAddresses(List<String> res,List<String> path,String s,int start){
        if (path.size()>4){
            return;
        }
        if (path.size()==4&&start>=s.length()){
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<path.size();i++){
                sb.append(path.get(i));
                if (i<path.size()-1) sb.append(".");
            }
            res.add(sb.toString());
            return;
        }
        for (int i=start;i<s.length();i++){
            String str=s.substring(start,i+1);
            if (Integer.parseInt(str)>255|| str.matches("0\\d+")) return;
            path.add(str);
            backRestoreIpAddresses(res, path , s, i+1);
            path.remove(path.size()-1);
        }
    }


    //491. 非递减子序列 hashSet在同一层建立 层级去重 太妙了
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        backFindSubsequences(res,path,nums,0);
        return res;
    }
    public void backFindSubsequences(List<List<Integer>> res,List<Integer> path,int[] nums,int start){
        if (path.size()>1){
            res.add(new ArrayList<>(path));
        }
        HashSet<Integer> hashSet=new HashSet<>();
        for (int i=start;i<nums.length;i++){
            int num=nums[i];
            if (hashSet.contains(num)) continue;
            if (!path.isEmpty()&&path.get(path.size()-1)>num) continue;
            path.add(num);
            hashSet.add(num);
            backFindSubsequences(res, path, nums, i+1);
            path.remove(path.size()-1);
        }
    }


    //51. N 皇后
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<>();
        List<int[]> path=new ArrayList<>();
        HashSet<Integer> rowAppend=new HashSet<>();
        HashSet<Integer> colAppend=new HashSet<>();
        HashSet<Integer> diag1=new HashSet<>();
        HashSet<Integer> diag2=new HashSet<>();
        backSolveNQueens(res,path,rowAppend,colAppend,diag1,diag2,n,0);
        return res;
    }
    public void backSolveNQueens(List<List<String>> res,List<int[]> path,HashSet<Integer> rowAppend,HashSet<Integer> colAppend,HashSet<Integer> diag1,HashSet<Integer> diag2,int n,int start){
        if (path.size()==n){
            List<String> list=new ArrayList<>();
            int index=0;
            int[] t=path.get(index);
            for (int i=0;i<n;i++){
                StringBuilder sb=new StringBuilder();
                for (int j=0;j<n;j++){
                    if (t[0]==i&&t[1]==j){
                        sb.append("Q");
                        index++;
                        if (index<path.size()) t=path.get(index);
                        continue;
                    }
                    sb.append(".");
                }
                list.add(sb.toString());
            }
            res.add(list);
            return;
        }
        for (int i=start;i<n;i++){
            if (rowAppend.contains(i)) continue;
            for (int j=0;j<n;j++){
                if (rowAppend.contains(i)) continue;
                if (colAppend.contains(j)) continue;
                if (diag1.contains(i+j)) continue;
                if (diag2.contains(i-j)) continue;
                path.add(new int[]{i,j});
                rowAppend.add(i);
                colAppend.add(j);
                diag1.add(i+j);
                diag2.add(i-j);
                backSolveNQueens(res, path, rowAppend, colAppend, diag1,diag2, n, i+1);
                int[] remove=path.remove(path.size()-1);
                int i1=remove[0],j1=remove[1];
                rowAppend.remove(i1);
                colAppend.remove(j1);
                diag1.remove(i1+j1);
                diag2.remove(i1-j1);
            }
        }
    }
    //52. N 皇后 II 最优解就是打表
    public int totalNQueens(int n) {
        int[] res ={0,1,0,0,2,10,4,40,92,352};
        return res[n];
    }


    //357. 统计各位数字都不同的数字个数
    public int countNumbersWithUniqueDigits(int n) {
        int[] res={0};
        HashSet<Integer> hashSet=new HashSet<>();
        ArrayList<Integer> path=new ArrayList<>();
        backCountNumbersWithUniqueDigits(res,path,hashSet,n,1);
        return res[0];
    }
    public void backCountNumbersWithUniqueDigits(int[] res,ArrayList<Integer> path,HashSet<Integer> hashSet,int n,int first){
        res[0]++;
        if (path.size()==n){
            return;
        }
        for (int i=first;i<=9;i++){
            if (hashSet.contains(i)) continue;
            path.add(i);
            hashSet.add(i);
            backCountNumbersWithUniqueDigits(res, path, hashSet, n,0);
            int num = path.remove(path.size()-1);
            hashSet.remove(num);
        }
    }


    //37. 解数独
    public void solveSudoku(char[][] board) {
        HashSet<Integer>[] row=new HashSet[9];
        HashSet<Integer>[] column=new HashSet[9];
        HashSet<Integer>[] div=new HashSet[9];
        for (int i=0;i<9;i++){
            row[i]=new HashSet();
            column[i]=new HashSet();
            div[i]=new HashSet();
        }
        int empty=0;
        ArrayList<int[]> emptyIndex=new ArrayList<>();
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                char c=board[i][j];
                if (c=='.') {
                    empty++;
                    emptyIndex.add(new int[]{i,j});
                    continue;
                }
                int num=c-'0',x=(i / 3) * 3 + (j / 3);
                row[i].add(num);
                column[j].add(num);
                div[x].add(num);
            }
        }
        ArrayList<int[]> path=new ArrayList<>();
        backsolveSudoku(emptyIndex,path,row,column,div,empty,0);
        for (int[] nums:path){
            board[nums[1]][nums[2]]=(char) (nums[0]+'0');
        }
    }
    public boolean backsolveSudoku(ArrayList<int[]> emptyIndexs,ArrayList<int[]> path,HashSet<Integer>[] row,HashSet<Integer>[] col,HashSet<Integer>[] div,int empty,int start){
        if (path.size()==empty) return true;
        for (int p=start;p<emptyIndexs.size();p++){
            int[] emptyIndex= emptyIndexs.get(p);
            int i=emptyIndex[0],j=emptyIndex[1];
            int x=(i / 3) * 3 + (j / 3);
            int k=1;
            for (;k<=9;k++){
                if (row[i].contains(k)||col[j].contains(k)||div[x].contains(k)) continue;
                int[] pathNums=new int[]{k,i,j,x};// k=填入的数字 i=行 j=列 x=3*3块
                row[i].add(k);
                col[j].add(k);
                div[x].add(k);
                path.add(pathNums);
                if (backsolveSudoku(emptyIndexs, path, row, col, div, empty, p+1)) return true;
                int[] remove=path.remove(path.size()-1);
                int num=remove[0];
                row[remove[1]].remove(num);
                col[remove[2]].remove(num);
                div[remove[3]].remove(num);
            }
            return false;
        }
        return false;
    }


    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        int m=board.length,n=board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]!=word.charAt(0)) continue;
                boolean[][] visit=new boolean[m][n];
                StringBuilder sb=new StringBuilder();
                sb.append(word.charAt(0));
                ArrayList<int[]> path=new ArrayList<>();
                visit[i][j]=true;
                path.add(new int[]{i,j});
                int[] k={1};
                if (backExist(board,path,word,sb,i,j,visit,k)) return true;
            }
        }
        return false;
    }
    public boolean backExist(char[][] board, ArrayList<int[]> path, String word,StringBuilder sb,int x,int y,boolean[][] visit,int[] j){
        if (sb.toString().equals(word)) {
            return true;
        }
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//四个方向 左 上 右 下
        for (int[] dir:direction){
            int nextX=x+dir[0],nextY=y+dir[1];
            if (nextX<0||nextX>=board.length||nextY<0||nextY>=board[0].length||visit[nextX][nextY]) continue;
            if (board[nextX][nextY]!=word.charAt(j[0])) continue;
            sb.append(board[nextX][nextY]);
            visit[nextX][nextY]=true;
            j[0]++;
            path.add(new int[]{nextX,nextY});
            if (backExist(board, path, word, sb, nextX, nextY, visit, j)) return true;
            sb.deleteCharAt(sb.length()-1);
            int[] remove=path.remove(path.size()-1);
            visit[remove[0]][remove[1]]=false;
            j[0]--;
        }
        return false;
    }

    //788. 旋转数字
    public int rotatedDigits(int n) {
        int[] path = {0, 0};
        int[] res = {0};
        int[] vis = {2, 5, 6, 9, 1, 8, 0};
        backRotatedDigits(res, path, n, vis);
        return res[0];
    }
    public void backRotatedDigits(int[] res, int[] path, int n, int[] vis) {
        for (int num : vis) {
            if (path[0] * 10 + num > n) continue;
            if (num == 2 || num == 5 || num == 6 || num == 9) path[1]++;
            path[0] = path[0] * 10 + num;
            if (path[0] == 0) return;
            if (path[1] > 0) res[0]++;
            backRotatedDigits(res, path, n, vis);
            int a = path[0] % 10;
            if (a == 2 || a == 5 || a == 6 || a == 9) path[1]--;
            path[0] /= 10;
        }
    }


}
