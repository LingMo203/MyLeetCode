package test.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GraphTest {
    public static void main(String[] args) {
        GraphTest gt=new GraphTest();
        int[][] numss={{1, 2}};
        System.out.println(gt.findJudge(2,numss));
    }


    //997. 找到小镇的法官
    public int findJudge(int n, int[][] trust) {
        if (n==1) return 1;
        HashMap<Integer, HashSet<Integer>> hashMap=new HashMap<>();
        HashSet<Integer> hashSet=new HashSet<>();
        for (int[] nums:trust){
            int beTrust=nums[1];
            int trustOther=nums[0];
            hashSet.add(trustOther);
            if (!hashMap.containsKey(beTrust)){
                HashSet<Integer> hashSet2=new HashSet<>();
                hashSet2.add(trustOther);
                hashMap.put(beTrust,hashSet2);
            }else {
                hashMap.get(beTrust).add(trustOther);
            }
        }
        for (Map.Entry<Integer, HashSet<Integer>> map: hashMap.entrySet()){
            if (map.getValue().size()==n-1&&!hashSet.contains(map.getKey())){
                return map.getKey();
            }
        }
        return -1;
    }




























}
