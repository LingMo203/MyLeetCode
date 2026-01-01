package test;

import java.util.*;

public class T5 {
    public static void main(String[] args) {
        T5 t5=new T5();
        int num=123;
        int[] nums={4,5,3,1,4};
        int[] nums2={5,4,3,4,2};
        int[][] numsD={{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] numsD3={{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},{13,14,15,16}};
        int[][] numsD2={{1,2,3}};
        String str="79362";
        //System.out.println(t5.findDuplicate(nums));
        //System.out.println(t5.numMagicSquaresInside(numsD));
        //System.out.println(t5.canCompleteCircuit(nums,nums2));
        //System.out.println(t5.searchMatrixII(numsD,-5));
        System.out.println(t5.spiralOrder(numsD2));
        //System.out.println(1/2);
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

    //134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
        if (n==1) return gas[0]>=cost[0]?0:-1;
        int i=0;
        while (i<n){
            int nowGas=0,j=0;
            boolean f=true;
            for (j=0;j<n;j++){
                int index=(j+i)%n;
                nowGas+=gas[index];
                nowGas-=cost[index];
                if (nowGas<0){
                    f=false;
                    break;
                }
            }
            if (f) return i;
            i=i+j+1;
        }
        return -1;
    }


    //74. 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] nums:matrix){
            if (nums[0]<=target&&target<=nums[nums.length-1]){
                int left=0,right=nums.length-1,mid;
                while (left<=right){
                    mid=(right-left)/2+left;
                    if (nums[mid]==target){
                        return true;
                    }else if (target<nums[mid]){
                        right=mid-1;
                    }else {
                        left=mid+1;
                    }
                }
            }
        }
        return false;
    }

    //240. 搜索二维矩阵 II
    public boolean searchMatrixII(int[][] matrix, int target) {
        int  left=0,right=matrix[0].length-1, search=matrix[0][right];
        while (left<matrix.length&&right>=0){
            int num=matrix[left][right];
            if (num==target) return true;
            else if (target<num) right--;
            else left++;
        }
        return false;
    }

    //73. 矩阵置零
    public void setZeroes(int[][] matrix) {
        ArrayList<int[]> list=new ArrayList<>();
        int m=matrix.length,n=matrix[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (matrix[i][j]==0) list.add(new int[]{i,j});
            }
        }
        for (int[] nums:list){
            for (int i=0;i<n;i++){
                matrix[nums[0]][i]=0;
            }
            for (int i=0;i<m;i++){
                matrix[i][nums[1]]=0;
            }
        }
    }

    //54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        List<Integer> result=new ArrayList<>();
        int left=0,right=n-1,top=0,botton=m-1;
        while (top <= botton && left <= right){
            boolean a=true;
            for (int i=left;i<=right;i++){
                result.add(matrix[top][i]);
                a=false;
            }
            if (a) break;
            a=true;
            top++;
            for (int i=top;i<=botton;i++){
                result.add(matrix[i][right]);
                a=false;
            }
            if (a) break;
            a=true;
            right--;
            for (int i=right;i>=left;i--){
                result.add(matrix[botton][i]);
                a=false;
            }
            if (a) break;
            a=true;
            botton--;
            for (int i=botton;i>=top;i--){
                result.add(matrix[i][left]);
                a=false;
            }
            if (a) break;
            a=true;
            left++;
        }
        return result;
    }












}
