package contest;

import util.ArrayStringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

//第 486 场周赛
public class WeeklyContest486 {

    public static void main(String[] args) {
        WeeklyContest486 t=new WeeklyContest486();
        String str="au 123";
        int n=10;
        String strGrid="[[0,1],[0,2],[0,3]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);
        int[] diff = {5,4,-9,6};
        int[] diff2 = {1,-2,3,-4};
        int[] diff3 = {6,2};
        //System.out.println(Arrays.toString(t.rotateElements(diff3, 18863)));
        System.out.println(t.specialNodes(4,grid,1,2,3));
    }


    //100971. 移除前缀使数组严格递增
    public int minimumPrefixLength(int[] nums) {
        int res=0,last=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (last>=nums[i]){
                res=i;
            }
            last=nums[i];
        }
        return res;
    }

    //100944. 非负元素轮替
    public int[] rotateElements(int[] nums, int k) {
        ArrayList<Integer> fuShu=new ArrayList<>();
        ArrayList<Integer> zhengShu=new ArrayList<>();
        int n=nums.length;
        int[] res=new int[n];
        for (int i = 0; i < n; i++) {
            int num=nums[i];
            if (num<0){
                fuShu.add(i);
            }else {
                zhengShu.add(num);
            }
        }
        if (zhengShu.isEmpty()) return nums;
        k = k%zhengShu.size();
        ArrayList<Integer> temp=new ArrayList<>();
        for (int i = k; i < zhengShu.size(); i++) {
            temp.add(zhengShu.get(i));
        }
        for (int i = 0; i < k; i++) {
            temp.add(zhengShu.get(i));
        }
        for (int index : fuShu) {
            int num = nums[index];
            temp.add(index, num);
        }
        for (int i = 0; i < n; i++) {
            res[i]=temp.get(i);
        }
        return res;
    }


    //100953. 树上的勾股距离节点
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        int res=0;
        ArrayList<Integer>[] graph=new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i]=new ArrayList();
        }
        for (int[] nums:edges) {
            int u=nums[0],v=nums[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] dX = bfsSpecialNodes(graph, x);
        int[] dY = bfsSpecialNodes(graph, y);
        int[] dZ = bfsSpecialNodes(graph, z);
        for (int i = 0; i < n; i++) {
            int dx=dX[i],dy=dY[i],dz=dZ[i];
            if (dx*dx+dy*dy==dz*dz||dx*dx+dz*dz==dy*dy||dz*dz+dy*dy==dx*dx) res++;
        }
        return res;
    }
    public int[] bfsSpecialNodes(ArrayList<Integer>[] graph,int a){
        int[] res=new int[graph.length];
        Arrays.fill(res,-1);
        Deque<Integer> deque=new ArrayDeque<>();
        deque.addLast(a);
        res[a]=0;
        while (!deque.isEmpty()){
            int remove = deque.removeFirst();
            for (int next : graph[remove]) {
                if (res[next]>-1) continue;
                deque.addLast(next);
                res[next] = res[remove]+1;
            }
        }
        return res;
    }




























}
