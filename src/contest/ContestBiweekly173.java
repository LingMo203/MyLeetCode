package contest;

import java.util.Arrays;
import java.util.HashMap;

//第 173 场双周赛
public class ContestBiweekly173 {

    public static void main(String[] args) {
        ContestBiweekly173 t=new ContestBiweekly173();
        String str="x";
        //System.out.println(t.reversePrefix(str,1));
        int n=10;
        int[][] restrictions = {{3, 1}, {8, 1}};
        int[] diff = {2,2,3,1,4,5,1,1,2};
        System.out.println(t.findMaxVal(n,restrictions,diff));
    }

    //100950. 反转字符串前缀
    public String reversePrefix(String s, int k) {
        StringBuilder sb=new StringBuilder();
        for (int i=k-1;i>=0;i--){
            sb.append(s.charAt(i));
        }
        for (int i=k;i<s.length();i++){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    //100927. 不同元素和至少为 K 的最短子数组长度
    public int minLength(int[] nums, int k) {
        int res=Integer.MAX_VALUE;
        int left=0,right=0,sum=0;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        while (right<nums.length){
            int num=nums[right];
            if (!hashMap.containsKey(num)) {
                hashMap.put(num,1);
                sum += nums[right];
            }else {
                hashMap.put(num,hashMap.get(num)+1);
            }

            while (sum>=k){
                res=Math.min(res,right-left+1);
                int le=nums[left],time=hashMap.get(le);
                if (time<=1) {
                    hashMap.remove(le);
                    sum-=le;
                }else {
                    hashMap.put(le,time-1);
                }
                left++;
            }
            right++;
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    //100613. 找到带限制序列的最大值 (没做出来，差一点点)
    public int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] dp=new int[n];
        Arrays.sort(restrictions,(a,b)->a[0] - b[0]);
        int index=0,res=Integer.MIN_VALUE;
        for (int i=1;i<n;i++){
            int num=Math.max(dp[i-1]+diff[i-1],Math.abs(dp[i-1]-diff[i-1]));
            if (index<restrictions.length&&i==restrictions[index][0]){
                if (num>restrictions[index][1]){
                    num=restrictions[index][1];
                    for (int j=i;j>0;j--) {
                        dp[j - 1] = diff[j - 1] + num;
                    }
                }
                index++;
            }
            dp[i]=num;
        }
        Arrays.sort(dp);
        return dp[dp.length-1];
    }













}
