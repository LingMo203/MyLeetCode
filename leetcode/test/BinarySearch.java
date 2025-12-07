package src.leetcode.test;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs=new BinarySearch();
        int[] nums={1,3};
        System.out.println(bs.search(nums,2));
    }

    //33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        if (nums.length==0) return -1;
        else if (nums.length==1) return nums[0]==target?0:-1;
        int left=0,right=nums.length-1,mid=(right-left)/2+left;
        boolean bLe=false;
        if (target>nums[right]&&target<nums[left]) return -1;
        else if (target>nums[right]) bLe=true;
        while (left<=right){
            mid=(right-left)/2+left;
            if (nums[mid]>nums[nums.length-1]){
                left=mid+1;
            }else {
                right=mid-1;
            }
            if (nums[mid]==target) return mid;
        }
        if (bLe){
            left=0;
        }else {
            left=right+1;
            right=nums.length-1;
        }
        while (left<=right){
            mid=(right-left)/2+left;
            if (nums[mid]>target){
                right=mid-1;
            }else if (nums[mid]==target){
                return mid;
            } else {
                left=mid+1;
            }
        }
        return -1;
    }

    //153. 寻找旋转排序数组中的最小值
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1,mid;
        if (nums[left]<nums[right]) return nums[0];
        while (left<=right){
            mid=(right-left)/2+left;
            if (nums[mid]>nums[nums.length-1]){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return nums[right+1];
    }
}
