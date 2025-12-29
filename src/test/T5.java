package test;

public class T5 {
    public static void main(String[] args) {
        T5 t5=new T5();
        int num=123;
        int[] nums={1,3,4,2,2};
        String str="79362";
        System.out.println(t5.findDuplicate(nums));
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














}
