package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ContestWeekly484 {
    public static void main(String[] args) {
        ContestWeekly484 t=new ContestWeekly484();
        int[] nums = {2,2,3,1,4,5,1,1,2};
        String str1="fusion";
        String str2="layout";
        String[] strs={"a","a","a"};
        //System.out.println(t.residuePrefixes(str));
        System.out.println(t.countPairs(strs));
    }

    //100959. 统计残差前缀
    public int residuePrefixes(String s) {
        int length =s.length();
        int res=0;
        HashSet<Character> hashSet=new HashSet<>();
        for (int i=0;i<length;i++){
            char c=s.charAt(i);
            hashSet.add(c);
            if ((i+1)%3==hashSet.size()){
                res++;
            }
        }
        return res;
    }


    //3804. 中心子数组的数量
    public int centeredSubarrays(int[] nums) {
        int res=0;
        for (int i=0;i<nums.length;i++){
            int sum=nums[i];
            HashSet<Integer> hashSet=new HashSet<>();
            res++;
            hashSet.add(nums[i]);
            for (int j=i+1;j<nums.length;j++){
                hashSet.add(nums[j]);
                sum+=nums[j];
                if (hashSet.contains(sum)) res++;
            }
        }
        return res;
    }


    //100956. 统计凯撒加密对数目
    public long countPairs(String[] words) {
        long res=0;
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (String str:words){
            char first=str.charAt(0);
            int[] nums=new int[str.length()];
            for (int i=1;i<str.length();i++){
                int n=str.charAt(i)-first;
                nums[i]=n<0?n+26:n;
            }
            String s= Arrays.toString(nums);
            int time= hashMap.getOrDefault(s, 0);
            res+=time;
            hashMap.put(s,time+1);
        }
        return res;
    }
    //超时
    public long countPairs2(String[] words) {
        long res=0;
        for (int i=0;i<words.length;i++){
            char[] words1=words[i].toCharArray();
            for (int j=i+1;j< words.length;j++){
                char[] words2=words[j].toCharArray();
                if (words1.length!=words2.length) continue;
                boolean f=false;
                int last=words1[0]-words2[0];
                if (last<0) last+=26;
                for (int w1=1,w2=1;w1<words1.length&&w2<words2.length;w1++,w2++){
                    int num=words1[w1]-words2[w2];
                    if (num<0) num+=26;
                    if (num!=last){
                        f=true;
                        break;
                    }
                }
                if (f) continue;
                res++;
            }
        }
        return res;
    }


























}
