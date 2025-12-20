package test;

//380. O(1) 时间插入、删除和获取随机元素

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class RandomizedSet {

    HashMap<Integer,Integer> hashMap;
    ArrayList<Integer> list;
    Random random;

    public RandomizedSet() {
        hashMap=new HashMap<>();
        list=new ArrayList<>();
        random=new Random();
    }

    public boolean insert(int val) {
        if (!hashMap.containsKey(val)){
            hashMap.put(val,list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (hashMap.containsKey(val)){
            int i=hashMap.get(val);
            int last=list.get(list.size()-1);
            list.set(i,last);
            list.remove(list.size()-1);
            hashMap.put(last,i);
            hashMap.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int rand= random.nextInt(list.size());
        return list.get(rand);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
