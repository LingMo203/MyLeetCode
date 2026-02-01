package contest;

import util.ArrayStringUtils;

import java.util.*;

//第 487 场周赛
public class WeeklyContest487 {

    public static void main(String[] args) {
        WeeklyContest487 t=new WeeklyContest487();
        String str="au 123";
        int n=10;
        String strGrid="[[0,1],[0,2],[0,3]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);
        int[] nums = {1,5,2};
        int[] nums2 = {3,1};
        int[] nums3 = {6,2};
        System.out.println(t.finalElement(nums2));
    }


    //3827. 统计单比特整数
    public int countMonobit(int n) {
        return Integer.toBinaryString(n+1).length();
    }


    //3828. 删除子数组后的最终元素
    public int finalElement(int[] nums) {
        return Math.max(nums[0], nums[nums.length - 1]);
    }


}


//3829. 设计共享出行系统
class RideSharingSystem {
    Deque<Integer> rider ;
    Deque<Integer> driver ;
    HashSet<Integer> riderSet;
    HashSet<Integer> usingRider;
    public RideSharingSystem() {
        rider = new ArrayDeque<>();
        driver = new ArrayDeque<>();
        riderSet = new HashSet<>();
        usingRider = new HashSet<>();
    }

    public void addRider(int riderId) {
        rider.addLast(riderId);
        riderSet.add(riderId);
    }

    public void addDriver(int driverId) {
        driver.addLast(driverId);
    }

    public int[] matchDriverWithRider() {
        if (driver.isEmpty() || rider.isEmpty()) return new int[]{-1, -1};
        int riderId = rider.removeFirst();
        while (!riderSet.contains(riderId)){
            if (rider.isEmpty()) return new int[]{-1, -1};
            riderId = rider.removeFirst();
        }
        int driverId = driver.removeFirst();
        usingRider.add(riderId);
        return new int[]{driverId,riderId};
    }

    public void cancelRider(int riderId) {
        if (usingRider.contains(riderId)) return;
        riderSet.remove(riderId);
    }
}

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * RideSharingSystem obj = new RideSharingSystem();
 * obj.addRider(riderId);
 * obj.addDriver(driverId);
 * int[] param_3 = obj.matchDriverWithRider();
 * obj.cancelRider(riderId);
 */
