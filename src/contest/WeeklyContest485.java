package contest;

import util.ArrayStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

//第 485 场周赛
public class WeeklyContest485 {
    public static void main(String[] args) {
        WeeklyContest485 t=new WeeklyContest485();
        String str="au 123";
        int n=10;
        String strGrid="[[1,2,5],[2,1,7],[3,1,9]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);
        int[] diff = {2,2,2};
        int[] diff2 = {3,5,4};
        //System.out.println(t.vowelConsonantScore(str));
        System.out.println(t.maxCapacity(diff,diff2,5));
    }

    //3813. 元音辅音得分
    public int vowelConsonantScore(String s) {
        int v=0,cr=0;
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if (Character.isDigit(c)||c==' ') continue;
            if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u') v++;
            else cr++;
        }
        if (cr==0) return 0;
        return v/cr;
    }


    //3814. 预算下的最大总容量
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int max=Integer.MIN_VALUE;
        int n=costs.length;
        CC[] ccs=new CC[n];
        for (int i = 0; i < n; i++) {
            ccs[i]=new CC(costs[i],capacity[i]);
        }
        Arrays.sort(ccs,(a,b)->a.cost-b.cost);
        for (int i = 0; i < n; i++) {
            CC cc=ccs[i];
            if (cc.cost>=budget) break;
            max=Math.max(max,cc.capacity);
        }
        for (int i = 0; i < n; i++) {
            CC ccI=ccs[i];
            if (ccI.cost>=budget) break;
            for (int j = i+1; j < n; j++) {
                CC ccJ=ccs[j];
                if (ccI.cost+ccJ.cost>=budget) break;
                max=Math.max(max,ccI.capacity+ccJ.capacity);
            }
        }
        return Math.max(0,max);
    }
    class CC{
        int cost;
        int capacity;
        public CC(int cost, int capacity) {
            this.cost = cost;
            this.capacity = capacity;
        }
    }

}


//3815. 设计拍卖系统
class AuctionSystem {

    HashMap<Integer,AuctionSystemP> userIdMap;
    ArrayList<int[]> listID;
    HashMap<Integer,ArrayList<int[]>> listById;
    public AuctionSystem() {
        userIdMap=new HashMap<>();
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        if (!userIdMap.containsKey(userId)){
            HashMap<Integer,Integer> temp=new HashMap<>();
            temp.put(itemId,bidAmount);
            AuctionSystemP asp=new AuctionSystemP(temp,listID.size());
            userIdMap.put(userId,asp);
            listID.add(new int[]{});
        }else {
            AuctionSystemP asp=userIdMap.get(userId);
            HashMap<Integer,Integer> temp=asp.hashMap;
            temp.put(itemId,bidAmount);
            int id=asp.ID;

        }
    }

    public void updateBid(int userId, int itemId, int newAmount) {

    }

    public void removeBid(int userId, int itemId) {

    }

    public int getHighestBidder(int itemId) {
        return 0;
    }
}
class AuctionSystemP{
    HashMap<Integer,Integer> hashMap;
    int ID;

    public AuctionSystemP(HashMap<Integer, Integer> hashMap, int ID) {
        this.hashMap = hashMap;
        this.ID = ID;
    }
}

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * AuctionSystem obj = new AuctionSystem();
 * obj.addBid(userId,itemId,bidAmount);
 * obj.updateBid(userId,itemId,newAmount);
 * obj.removeBid(userId,itemId);
 * int param_4 = obj.getHighestBidder(itemId);
 */







