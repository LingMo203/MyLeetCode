package src.leetcode.test;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs=new BinarySearch();
        int[] nums={0,0};
        //System.out.println(bs.search(nums,2));
        //System.out.println(bs.findMin2(nums));
        System.out.println(Arrays.toString(bs.twoSum(nums, 0)));
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


    ///154. 寻找旋转排序数组中的最小值 II
    //{3,4,1,3};
    public int findMin2(int[] nums) {
        int left=0,right=nums.length-1,mid;
        if (right+1==1) return nums[0];
        if (nums[left]<nums[right]) return nums[0];
        else if (nums[left]==nums[right]){
            right--;
            while (nums[left]==nums[right]){
                right--;
                if (right<=0) return nums[0];
            }
        }
        int q=right;
        while (left<=right){
            mid=(right-left)/2+left;
            if (nums[mid]>nums[q]){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        right++;
        return Math.min(nums[right],nums[q-1]);
    }

    //167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        for (int i=0;i<numbers.length;i++){
            int tar=target-numbers[i],left=i+1,right=numbers.length-1,mid;
            while (left<=right){
                mid=(right-left)/2+left;
                if (numbers[mid]<tar){
                    left=mid+1;
                }else if (numbers[mid]>tar){
                    right=mid-1;
                }else if (numbers[mid]==tar){
                    if (i==mid){
                        boolean t=false;
                        if (mid!=0&&numbers[mid-1]==tar){
                            mid--;
                            t=true;
                        }
                        if (t) mid=i;
                        if (mid!=numbers.length-1&&numbers[mid+1]==tar){
                            mid++;
                        }
                    }
                    return new int[]{i+1,mid+1};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
