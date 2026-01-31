package contest;

import util.ArrayStringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

//第 175 场双周赛
public class BiweeklyContest175 {

    public static void main(String[] args) {
        BiweeklyContest175 t=new BiweeklyContest175();
        String str="au 123";
        int n=10;
        String strGrid="[[0,1],[0,2],[0,3]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);
        int[] diff = {5,4,7};
        int[] diff2 = {0,2,14};
        int[] diff3 = {2,8,11};
        //System.out.println(t.minimumK(diff2));
        //System.out.println(-5&7);
        System.out.println(t.longestSubsequence(diff3));
    }

    //3823. 反转一个字符串里的字母后反转特殊字符
    public String reverseByType(String s) {
        Deque<Character> letters=new ArrayDeque<>();
        Deque<Character> symbol=new ArrayDeque<>();
        int length=s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)){
                letters.addLast(c);
            }else {
                symbol.addLast(c);
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)){
                sb.append(letters.removeLast());
            }else {
                sb.append(symbol.removeLast());
            }
        }
        return sb.toString();
    }


    //3824. 减小数组使其满足条件的最小 K 值
    public int minimumK(int[] nums) {
        long left = 1, right = nums[0], res = Integer.MAX_VALUE, sum = 0;
        for (int num : nums) {
            right = Math.max(right, num);
            sum += num;
        }
        right += sum;
        while (left <= right) {
            long mid = (right - left) / 2 + left, time = 0;
            for (int num : nums) {
                time += num % mid == 0 ? num / mid : num / mid + 1;
            }
            long a = mid * mid;
            if (time > a) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = Math.min(res, mid);
            }
        }
        return (int) res;
    }


    //3825. 按位与结果非零的最长上升子序列(没做出)
    public int longestSubsequence(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i]) || nums[i] == 0) continue;
            hashSet.add(nums[i]);
            int and = nums[i], count = 1, last = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] <= last) continue;
                int n = and & nums[j];
                if (n == 0) break;
                hashSet.add(nums[j]);
                and = n;
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }


    //3826. 最小分割分数(没做出)
    public long minPartitionScore(int[] nums, int k) {
        long[] res={Long.MIN_VALUE};
        ArrayList<Integer> path=new ArrayList<>();
        long[] sum=new long[nums.length];
        int add=0;
        for (int i = 0; i < nums.length; i++) {
            add+=nums[i];
            sum[i]=add;
        }
        backMinPartitionScore(res,path,nums,k,sum,0);
        return res[0];
    }
    public void backMinPartitionScore(long[] res,ArrayList<Integer> path,int[] nums, int k,long[] sum,int start){
        if (path.size()==k-1){
            int last=path.get(0);
            long re=0;
            for (int i = 0; i < k; i++) {
                long now = sum[path.get(i)];
                long las = sum[path.get(last)];
                long temp = now - las + nums[last];
                long a = temp * (temp+1)/2;
                re+=a;
                last= path.get(i);
            }
            res[0]=Math.min(res[0],re);
        }
        for (int i = start; i < nums.length; i++) {
            path.add(i);
            backMinPartitionScore(res, path, nums, k, sum, i+1);
            path.remove(path.size()-1);
        }
    }









}
