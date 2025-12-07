package src.leetcode.test;

import java.util.*;

public class T3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int a = 499979;
        int[] nums = {2,0,2,1,1,0};
        int[][] intervals = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        String s = "cbahbacl";
        String h = "abc";
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        String b= String.valueOf(Integer.MAX_VALUE);
//        System.out.println(solution.myAtoi(h));
//        int n=(Integer.MAX_VALUE-6)/10;
        //214748364
        //System.out.println(n);
        //-214748364<=
//        int m=((Integer.MIN_VALUE+7)/10);
//        System.out.println(m);
        //System.out.println(Arrays.toString(solution.searchRange(nums, 2)));
        //System.out.println(solution.letterCombinations(s));
        //System.out.println(Arrays.deepToString(solution.merge(intervals)));
//        long fh=Integer.MAX_VALUE;
//        System.out.println(fh+1);
//        System.out.println(solution.thirdMax(nums));
        //System.out.println(solution.groupAnagrams(strs));
        //System.out.println(solution.findAnagrams(s,h));
        //System.out.println(solution.longestConsecutive(nums));
        //System.out.println(solution.trap(nums));
        //System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, 5)));
        //solution.rotate(nums,7);
        //solution.sortColors(nums);
        //System.out.println(solution.countPrimes(a));
        System.out.println(solution.computeArea(-3,0,3,4,0,-1,9,2));
    }
}
class Solution3 {

    //7. 整数反转
    public int reverse(int x) {
        boolean f=false;
        if (x<0){
            x=x*-1;
            f=true;
        }
        String num=Integer.toString(x);
        int length=num.length();
        int result=0;
        for (int i=length-1;i>=0;i--){
            if (result>=214748365)
                return 0;
            result=result*10+(num.charAt(i)-'0');
        }
        return !f ? result:result*-1;
    }

    //8. 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        int result=0;
        boolean fu=false,w=false,g=true,k=true;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (c==' '&&k) continue;
            else if(c==' ') break;
            else if (c=='-'){
                if (w) break;
                fu=true;
                w=true;
                k=false;
                continue;
            }else if (c=='+'){
                if (w) break;
                k=false;
                w=true;
            } else if (c=='0'&&g) {
                w=true;
                k=false;
                continue;
            }
            else if (!Character.isDigit(c)||(!Character.isDigit(c)&&w)) break;
            else if (Character.isDigit(c)){
                int a=c-'0';
                if (result*-1<((Integer.MIN_VALUE+a)/10)&&fu)
                    return Integer.MIN_VALUE;
                else if (result>(Integer.MAX_VALUE-a)/10&&!fu)
                    return Integer.MAX_VALUE;
                result=result*10+a;
                w=true;
                g=false;
                k=false;
            }
        }
        return fu ? result*-1:result;
    }

    //3190. 使所有元素都可以被 3 整除的最少操作数
    public int minimumOperations(int[] nums) {
        int time=0;
        for (int num:nums){
            if (num%3==0) continue;
            time++;
        }
        return time;
    }

    //34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0) return new int[]{-1,-1};
        int left=0,right=nums.length-1,mid;
        int[] result={-1,-1};
        while (left<=right){
            mid=(right-left)/2+left;
            if (nums[mid]==target){
                int le=mid,ri=mid;
                boolean leb=false,rib=false;
                while (nums[le]==target){
                    if (le==0) {
                        leb=false;
                        break;
                    }
                    le--;
                    leb=true;
                }
                while (nums[ri]==target){
                    if (ri==nums.length-1) {
                        rib=false;
                        break;
                    }
                    ri++;
                    rib=true;
                }
                result[0]=leb? le+1:le;
                result[1]=rib? ri-1:ri;
                break;
            }else if (nums[mid]>target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return result;
    }

    //11. 盛最多水的容器
    public int maxArea(int[] height) {
        int max=0,left=0,right=height.length-1;
        while (left<=right){
            int length=right-left,width=Math.min(height[left],height[right]);
            max=Math.max(max,length*width);
            if (height[left]<height[right]) left++;
            else right--;
        }
        return max;
    }



    //414. 第三大的数
    public int thirdMax(int[] nums) {
        long first=Integer.MIN_VALUE,second=Integer.MIN_VALUE,third=Integer.MIN_VALUE;
        first--;second--;third--;
        for (long num:nums){
            if (num>first){
                third=second;
                second=first;
                first=num;
            }else if (num>second&&num!=first){
                third=second;
                second=num;
            }else if (num>third&&num!=first&&num!=second){
                third=num;
            }
        }
        return third<Integer.MIN_VALUE? (int) first :(int)third;
    }

    //412. Fizz Buzz
    public List<String> fizzBuzz(int n) {
        List<String> result=new ArrayList<>();
        for (int i=1;i<=n;i++){
            if (i%3==0&&i%5==0){
                result.add("FizzBuzz");
            }else if (i%3==0){
                result.add("Fizz");
            }else if (i%5==0){
                result.add("Buzz");
            }else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    //485. 最大连续 1 的个数
    public int findMaxConsecutiveOnes(int[] nums) {
        int result=0,count=0;
        for (int num:nums){
            if (num==1){
                count++;
            }else {
                result=Math.max(count,result);
                count=0;
            }
        }
        return Math.max(count,result);
    }

    //49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result=new ArrayList<>();
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (String str : strs) {
            int[] temp = new int[26];
            for (int i = 0; i < str.length(); i++) {
                int b = str.charAt(i) - 'a';
                temp[b]++;
            }
            String tostr = Arrays.toString(temp);
            if (!hashMap.containsKey(tostr)) {
                hashMap.put(tostr, hashMap.size());
                List<String> list = new ArrayList<>();
                list.add(str);
                result.add(list);
            } else {
                result.get(hashMap.get(tostr)).add(str);
            }
        }
        return result;
    }

    //438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        int[] sstrs=new int[26];
        int[] pstrs=new int[26];
        for (int i=0;i<p.length();i++){
            pstrs[p.charAt(i)-'a']++;
        }
        List<Integer> result=new ArrayList<>();
        int length=p.length(),j = 0;
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (i>=length){
                sstrs[s.charAt(j)-'a']--;
                j++;
            }
            sstrs[c-'a']++;
            if (Arrays.equals(sstrs,pstrs)){
                result.add(i-length+1);
            }
        }
        return result;
    }

    //128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet=new HashSet<>();
        for (int num:nums) hashSet.add(num);
        int maxCount=0;
        for (int num:nums){
            int temp=num-1,count=1;
            while (hashSet.contains(temp)){
                hashSet.remove(temp);
                temp--;
                count++;
            }
            temp=num+1;
            while (hashSet.contains(temp)){
                hashSet.remove(temp);
                temp++;
                count++;
            }
            maxCount=Math.max(maxCount,count);
        }
        return maxCount;
    }

    //42. 接雨水
    public int trap(int[] height) {
        int left=0,right = 0,sum=0,last=height.length-1;
        for (int k=0;k<height.length;k++){
            if (height[k]!=0){
                left=right=k;
                break;
            }
        }
        for (int k=height.length-2;k>=0;k--){
            if (height[k]<height[last]) break;
            last--;
        }
        ArrayList<Integer> temp=new ArrayList<>();
        for (right=right+1;right<=last;right++){
            if (height[left]<=height[right]){
                for (int t:temp){
                    int h=height[left]-t;
                    sum=sum+h;
                }
                left=right;
                temp.clear();
                continue;
            }
            temp.add(height[right]);
        }
        if (!temp.isEmpty()){
            temp.add(0,height[left]);
            int size=temp.size();
            right=size-1;
            ArrayList<Integer> tt=new ArrayList<>();
            for (int sleft=right-1;sleft>=0;sleft--){
                if (temp.get(right)<=temp.get(sleft)){
                    for (int t:tt){
                        int h=temp.get(right)-t;
                        sum=sum+h;
                    }
                    right=sleft;
                    tt.clear();
                    continue;
                }
                tt.add(temp.get(sleft));
            }
        }
        return sum;
    }



    //189. 轮转数组
    public void rotate(int[] nums, int k) {
        k=k%nums.length;
        int[] be=Arrays.copyOf(nums,nums.length-k);
        int[] end=Arrays.copyOfRange(nums,nums.length-k,nums.length);
        System.arraycopy(end,0,nums,0,end.length);
        System.arraycopy(be,0,nums,end.length,be.length);
    }

    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        HashSet<Integer> hashSet=new HashSet<>();
        for (int num:nums){
            if (!hashSet.contains(num)){
                hashSet.add(num);
            }else {
                hashSet.remove(num);
            }
        }
        for (int num:hashSet){
            return num;
        }
        return -1;
    }

    //75. 颜色分类
    public void sortColors(int[] nums) {
        int a=0,b=0,c=0;
        for (int num:nums){
            switch (num) {
                case 0 -> a++;
                case 1 -> b++;
                case 2 -> c++;
            }
        }
        Arrays.fill(nums,0,a,0);
        Arrays.fill(nums,a,a+b,1);
        Arrays.fill(nums,nums.length-c,nums.length,2);
    }

    //204. 计数质数 当n = 499979超时
    public int countPrimes(int n) {
        ArrayList<Integer> list=new ArrayList<>();
        if (n==0||n==1) return 0;
        else if (n==2) return 0;
        list.add(2);
        for (int i=3;i<n;i+=2){
            boolean is=true;
            for (int num:list){
                if (i%num==0){
                    is=false;
                    break;
                }
            }
            if (is){
                list.add(i);
            }
        }
        return list.size();
    }

    //223. 矩形面积
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        boolean is=false;
        int left=0,right=0,length=0;
        if (ax2<=bx1||bx2<=ax1) is=true;
        else {
            left = Math.max(bx1, ax1);
            right = Math.min(ax2,bx2);
        }
        length=right-left;
        int top=0,bottom=0,height=0;
        if (by1>=ay2||by2<=ay1) is=true;
        else {
            top=Math.min(ay2,by2);
            bottom=Math.max(ay1,by1);
        }
        height=top-bottom;
        int s1=(ax2-ax1)*(ay2-ay1);
        int s2=(bx2-bx1)*(by2-by1);
        if (is) return s1+s2;
        return s1+s2-length*height;
    }
























}
