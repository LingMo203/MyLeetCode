package contest;

import util.ArrayStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//第 174 场双周赛
public class ContestBiweekly174 {
    public static void main(String[] args) {
        ContestBiweekly174 t=new ContestBiweekly174();
        String str="x";
        int n=10;
        String strGrid="[[1,2,5],[2,1,7],[3,1,9]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);
        int[] diff = {17218,0};
        int[] diff2 = {5,5,9};
        //System.out.println(Arrays.toString(t.bestTower(grid, diff, 2)));
        //System.out.println(t.minOperations(diff,diff2));
        System.out.println(t.alternatingXOR(diff,17218,27973));
    }


    //100960. 最好可到达的塔
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int x1=center[0],y1=center[1];
        ArrayList<int[]> list=new ArrayList<>();
        for (int[] nums:towers){
            int xi=nums[0],yi=nums[1],q1=nums[2];
            int c=Math.abs(xi-x1)+Math.abs(yi-y1);
            if (c<=radius){
                if (list.isEmpty()){
                    list.add(nums);
                }else {
                    int n=list.get(0)[2];
                    if (q1>n){
                        list.clear();
                        list.add(nums);
                    }else if (q1==n){
                        list.add(nums);
                    }
                }
            }
        }
        list.sort(Arrays::compare);
        if (!list.isEmpty()) {
            int[] l=list.get(0);
            return new int[]{l[0],l[1]};
        }
        return new int[]{-1,-1};
    }

    //100963. 变成目标数组的最少操作次数
    public int minOperations(int[] nums, int[] target) {
        HashSet<Integer> hashSet=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=target[i]) hashSet.add(nums[i]);
        }
        return hashSet.size();
    }

    //100918. 交替按位异或分割的数目(没做出来)
    public int alternatingXOR(int[] nums, int target1, int target2) {
        int res=0;
        for (int i = 0; i < nums.length; i++) {
            int n=0;
            for (int j = i; j < nums.length; j++) {
                int num=nums[i];
                n=n^num;
                if (n == target1 || n == target2) res++;
            }
        }
        return res%((int)Math.pow(10,9)+7);
    }
    public int alternatingXOR2(int[] nums, int target1, int target2) {
        int left=0,right=0;
        for (int num:nums){
            right=right^num;
        }
        int res=0;
        if (left==target1||right==target2) res++;
        for (int num:nums){
            left=left^num;
            right=right^num;
            if (left==target1||right==target2) res++;
        }
        res=res%((int)Math.pow(10,9)+7);
        return res;
    }













}
