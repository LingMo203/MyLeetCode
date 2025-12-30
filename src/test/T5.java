package test;

import java.util.HashSet;

public class T5 {
    public static void main(String[] args) {
        T5 t5=new T5();
        int num=123;
        int[] nums={1,3,4,2,2};
        int[][] numsD={{10, 3, 5}, {1, 6, 11}, {7, 9, 2}};
        String str="79362";
        //System.out.println(t5.findDuplicate(nums));
        System.out.println(t5.numMagicSquaresInside(numsD));
    }

    //287. 寻找重复数
    public int findDuplicate(int[] nums) {
        int slow=0,fast=0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);
        int cur=0;
        while (cur!=slow){
            cur=nums[cur];
            slow = nums[slow];
        }
        return cur;
    }

    //840. 矩阵中的幻方
    public int numMagicSquaresInside(int[][] grid){
        int rowLength=grid.length,colLength=grid[0].length;
        if (rowLength<3||colLength<3) return 0;
        int count=0;
        for (int i=1;i<rowLength-1;i++){
            int row1=0,row2=0,row3=0,col1=0,col2=0,col3=0,dia1=0,dia2=0;
            for (int j=1;j<colLength-1;j++){
                if(grid[i - 1][j - 1]<=0||grid[i - 1][j - 1]>9) continue;
                if(grid[i-1][j]<=0||grid[i-1][j]>9) continue;
                if(grid[i-1][j+1]<=0||grid[i-1][j+1]>9) continue;
                if(grid[i][j-1]<=0||grid[i][j-1]>9) continue;
                if(grid[i][j]<=0||grid[i][j]>9) continue;
                if(grid[i][j+1]<=0||grid[i][j+1]>9) continue;
                if(grid[i+1][j-1]<=0||grid[i+1][j-1]>9) continue;
                if(grid[i+1][j]<=0||grid[i+1][j]>9) continue;
                if(grid[i+1][j+1]<=0||grid[i+1][j+1]>9) continue;
                HashSet<Integer> hashSet=new HashSet<>();
                hashSet.add(grid[i - 1][j - 1]);
                if (!hashSet.add(grid[i-1][j])) continue;
                if (!hashSet.add(grid[i-1][j+1])) continue;
                if (!hashSet.add(grid[i][j-1])) continue;
                if (!hashSet.add(grid[i][j])) continue;
                if (!hashSet.add(grid[i][j+1])) continue;
                if (!hashSet.add(grid[i+1][j-1])) continue;
                if (!hashSet.add(grid[i+1][j])) continue;
                if (!hashSet.add(grid[i+1][j+1])) continue;
                row1=grid[i-1][j-1]+grid[i-1][j]+grid[i-1][j+1];
                row2=grid[i][j-1]+grid[i][j]+grid[i][j+1];
                row3=grid[i+1][j-1]+grid[i+1][j]+grid[i+1][j+1];
                col1=grid[i-1][j-1]+grid[i][j-1]+grid[i+1][j-1];
                col2=grid[i-1][j]+grid[i][j]+grid[i+1][j];
                col3=grid[i-1][j+1]+grid[i][j+1]+grid[i+1][j+1];
                dia1=grid[i-1][j-1]+grid[i][j]+grid[i+1][j+1];
                dia2=grid[i-1][j+1]+grid[i][j]+grid[i+1][j-1];
                if (row1==row2&&row1==row3&&row1==col1&&row1==col2&&row1==col3&&row1==dia1&&row1==dia2) count++;
            }
        }
        return count;
    }














}
