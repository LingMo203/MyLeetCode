package test;

import java.util.*;

public class T4 {
    public static void main(String[] args) {
        T4 t4=new T4();
        int num=123;
        double dnum=2.0;
        int[] nums={1000000000,1000000000,1000000000,1000000000};
        String str="ccc";
        String str2="0";
        //System.out.println(t4.calculate(str));
        //System.out.println(t4.countOdds(3,7));
        //System.out.println(t4.countTriples(5));
        //System.out.println(t4.maximumSwap(9973));
        //System.out.println(t4.compareVersion(str,str2));
        //System.out.println(t4.trailingZeroes(30));
        //System.out.println(Arrays.toString(t4.dailyTemperatures(nums)));
        //System.out.println(t4.largestRectangleArea(nums));
        //System.out.println(t4.longestValidParentheses(str));
        //System.out.println(t4.calculate(str));
        //System.out.println(Arrays.toString(t4.productExceptSelf(nums)));
        //System.out.println(t4.coinChange(nums,6249));
        //System.out.println(t4.removeDuplicates(nums));
        //System.out.println(t4.reverseWords(str));
        //System.out.println(t4.myPow(dnum,10));
        //System.out.println(Arrays.toString(t4.topKFrequent(nums, 3)));
        //System.out.println(t4.longestPalindrome(str));
        System.out.println(t4.fourSum(nums,-294967296));
    }


    //227. 基本计算器 II
    public int calculate2(String s) {
        Deque<Object> deque=new LinkedList<>();
        List<Object> list=new ArrayList<>();
        long num=0;
        char c = 0;
        boolean f = false;
        int length=s.length();
        for (int i=0;i<=length;i++){
            if (i==length) c=' ';
            else c=s.charAt(i);
            if (Character.isDigit(c)){
                num=num*10+(c-'0');
                f=true;
            }else {
                if (f){
                    list.add(num);
                    num=0;
                    f=false;
                }
                if (c==' '){
                    continue;
                }else {
                    list.add(c);
                }
            }
        }
        long result=0,temp=0;
        boolean t=false,y=true;
        for (Object ob : list) {
            if (t) {
                if (y) {
                    deque.addLast((long) ob * temp);
                } else {
                    deque.addLast(temp / (long) ob);
                }
                temp = 0;
                t = false;
                continue;
            }
            if (ob instanceof Long) {
                deque.addLast(ob);
            } else if (ob instanceof Character) {
                if (ob.equals('+') || ob.equals('-')) {
                    deque.addLast(ob);
                } else {
                    if (ob.equals('*')) {
                        temp = (long) deque.removeLast();
                        y = true;
                    } else {
                        temp = (long) deque.removeLast();
                        y = false;
                    }
                    t = true;
                }
            }
        }
        long temp2=0;
        boolean u=false,k=false;
        Object ob = null;
        while (!deque.isEmpty()){
            ob = deque.removeFirst();
            if (u){
                if (k)
                    deque.addFirst(temp2+(long) ob);
                else
                    deque.addFirst(temp2-(long) ob);
                u=false;
                continue;
            }
            if (ob instanceof Long) {
                temp2 = (long) ob;
            }else {
                k= ob.equals('+');
                u=true;
            }
        }
        return (int) temp2;
    }

    //224. 基本计算器
    public int calculate(String s) {
        Deque<Object> deque=new LinkedList<>();
        int length=s.length(),num=0;
        boolean isNum=false;
        for (int i=0;i<length;i++){
            char c=s.charAt(i);
            if (Character.isDigit(c)){
                num=num*10+(c-'0');
                isNum=true;
            }else {
                if (isNum){
                    deque.add(num);
                    num=0;
                }
                isNum=false;
                if (c==' ') continue;
                else if (c==')'){
                    Object obj=deque.removeLast();
                    if (obj.equals('(')) continue;
                    Deque<Object> temp=new LinkedList<>();
                    while (!obj.equals('(')){
                        temp.addFirst(obj);
                        obj=deque.removeLast();
                    }
                    int temnum=0;
                    while (temp.size()>1){
                        Object ob=temp.removeFirst();
                        if (ob instanceof Integer){
                            temnum=(int)ob;
                        }else {
                            int nex=(int)temp.removeFirst();
                            if (ob.equals('+')){
                                temp.addFirst(temnum+nex);
                            }else if (ob.equals('-')){
                                temp.addFirst(temnum-nex);
                            }
                        }
                    }
                    deque.addLast(temp.remove());
                    continue;
                }
                deque.add(c);
            }
        }
        if (isNum){
            deque.add(num);
        }
        int temp=0;
        while (deque.size()>1){
            Object obj=deque.removeFirst();
            if (obj instanceof Integer){
                temp=(int) obj;
            }else {
                int next=(int)deque.removeFirst();
                if (obj.equals('+')) deque.addFirst(temp+next);
                else if (obj.equals('-')) deque.addFirst(temp-next);
            }
        }
        return (int)deque.removeFirst();
    }


    //1523. 在区间范围内统计奇数数目
    // 3 4 5 6 7
    // 8 9 10
    // 15 16 17 18 19
    public int countOdds(int low, int high) {
        if (low==high){
            if (low%2==0) return 0;
            else return 1;
        }
        int result=0;
        if (high%2==1) result++;
        int count=high-low-1;
        if (low%2==0){
            if (count%2==0){
                result+=count/2;
            }else {
                result+=count/2+1;
            }
        }else {
            result+=count/2+1;
        }
        return result;
    }

    //670. 最大交换 9243 暂时遗弃
    public int maximumSwap(int num) {
        String str=String.valueOf(num);
        char[] chars=str.toCharArray();
        HashSet<Character> hashSet=new HashSet<>();
        char[] cs=Arrays.copyOf(chars,chars.length);
        Arrays.sort(cs);
        //int charsMax=chars.length-1,charsMin=0,min=0,max=0,p;
        int j=chars.length-1;
        while (j>0){

        }
        return 0;
    }

    //1925. 统计平方和三元组的数目
    public int countTriples(int n) {
        HashSet<String> hashSet=new HashSet<>();
        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                for (int k=1;k<=n;k++){
                    int max=Math.max(i,Math.max(j,k));
                    int a=i*i,b=j*j,c=k*k;
                    if (a+b==c||a+c==b||b+c==a){
                        if (i!=max&&j!=max){
                            hashSet.add(String.valueOf(i)+String.valueOf(j)+String.valueOf(max));
                        }
                    }
                }
            }
        }
        return hashSet.size();
    }

    //165. 比较版本号
    public int compareVersion(String version1, String version2) {
        int[] v1=change(version1.split("\\."));
        int[] v2=change(version2.split("\\."));
        int i=0,j=0;
        while (i<v1.length&&j<v2.length){
            if (v1[i]>v2[j]) return 1;
            else if (v1[i]<v2[j]) return -1;
            i++;j++;
        }
        if (i>=v1.length){
            while (j<v2.length){
                if (v2[j]!=0) return -1;
                j++;
            }
        } else if (j>=v2.length) {
            while (i<v1.length){
                if (v1[i]!=0) return 1;
                i++;
            }
        }
        return 0;
    }
    public int[] change(String[] strs){
        int[] result=new int[strs.length];
        for (int i=0;i<strs.length;i++){
            String str=strs[i];
            int num=0;
            for (int j=0;j<str.length();j++){
                int n=str.charAt(j)-'0';
                num=num*10+n;
            }
            result[i]=num;
        }
        return result;
    }

    //172. 阶乘后的零 爆long上限了 题解妙啊
    public int trailingZeroes2(int n) {
        if (n==0) return 0;
        long num=1;
        for (int i=1;i<=n;i++){
            num*=i;
        }
        String str=String.valueOf(num);
        int count=0;
        for (int i=str.length()-1;i>=0;i--){
            if ((str.charAt(i)-'0')==0) count++;
            else break;
        }
        return count;
    }
    public int trailingZeroes(int n){
        int count=0;
        while (n!=0){
            n=n/5;
            count+=n;
        }
        return count;
    }

    //739. 每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int length=temperatures.length;
        if (length==1) return new int[]{0};
        Deque<Integer> deque=new LinkedList<>();
        int[] result=new int[length];
        deque.add(0);
        for (int i=1;i<temperatures.length;i++){
            int num=temperatures[i];
            while (!deque.isEmpty()&&num>temperatures[deque.getLast()]){
                int j=deque.removeLast();
                result[j]=i-j;
            }
            deque.addLast(i);
        }
        return result;
    }

    //84. 柱状图中最大的矩形 放弃
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> deque=new ArrayDeque<>();
        int max=0;
        for (int num:heights){
            if (deque.isEmpty()){
                deque.add(num);
                max=Math.max(max,num);
            }else {
                if (num<deque.getLast()){
                    int newS=(num*(deque.size()+1));
                    int oldS=deque.getLast()*(deque.size());
                    if (oldS>newS){
                        deque.clear();
                    }else {
                        max=Math.max(max,newS);
                    }
                    deque.addLast(num);
                }else {
                    int min=deque.getLast();
                    int s=min*(deque.size()+1);
                    if (num>=s){
                        deque.clear();
                        max=Math.max(max,num);
                    }else {
                        max=Math.max(max,s);
                    }
                    deque.addFirst(num);
                }
            }
        }
        return max;
    }

    //32. 最长有效括号
    public int longestValidParentheses(String s) {
        Deque<Integer> deque=new LinkedList<>();
        int max=0,length=s.length();
        for (int i=0;i<length;i++){
            char c=s.charAt(i);
            if (deque.isEmpty()){
                deque.addLast(i);
                continue;
            }
            if (c=='('){
                deque.addLast(i);
            }else {
                char top=s.charAt(deque.getLast());
                if (top=='('){
                    deque.removeLast();
                }else {
                    deque.addLast(i);
                }
            }
        }
        int temp=-1,i=0;
        if (deque.isEmpty()) return length;
        while (!deque.isEmpty()){
            int j=deque.removeFirst();
            max=Math.max(max,j-temp-1);
            temp=j;
            i++;
        }
        if (i==1) return Math.max(temp,length-temp-1);
        return Math.max(max,length-temp-1);
    }


    //229. 多数元素 II
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result=new ArrayList<>();
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num:nums){
            if (!hashMap.containsKey(num)){
                hashMap.put(num,1);
            }else {
                hashMap.put(num,hashMap.get(num)+1);
            }
        }
        int time=nums.length/3;
        for (Map.Entry<Integer,Integer> map: hashMap.entrySet()){
            int key=map.getKey();
            int val=map.getValue();
            if (val>time) result.add(key);
        }
        return result;
    }


    //238. 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int[] left=new int[nums.length];
        int[] right=new int[nums.length];
        int[] result=new int[nums.length];
        int last=1,pro=1;
        for (int i=0;i<nums.length;i++){
            pro=pro*last;
            left[i]=pro;
            last=nums[i];
        }
        last=1;pro=1;
        for (int i=nums.length-1;i>=0;i--){
            pro=pro*last;
            right[i]=pro;
            last=nums[i];
        }
        for (int i=0;i<nums.length;i++){
            result[i]=left[i]*right[i];
        }
        return result;
    }

    //137. 只出现一次的数字 II
    public int singleNumber2(int[] nums) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num:nums){
            if (!hashMap.containsKey(num)){
                hashMap.put(num,1);
            }else {
                hashMap.put(num,3);
            }
        }
        for (Map.Entry<Integer,Integer> map:hashMap.entrySet()){
            int val=map.getValue();
            if (val==1) return map.getKey();
        }
        return 0;
    }

    //260. 只出现一次的数字 III
    public int[] singleNumber3(int[] nums) {
        int[] result=new int[2];
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num:nums){
            if (!hashMap.containsKey(num)){
                hashMap.put(num,1);
            }else {
                hashMap.put(num,3);
            }
        }
        int i=0;
        for (Map.Entry<Integer,Integer> map:hashMap.entrySet()){
            int val=map.getValue();
            if (val==1) {
                result[i]=map.getKey();
                i++;
            }
        }
        return result;
    }

    //462. 最小操作次数使数组元素相等 II
    public int minMoves2(int[] nums) {
        int count=0;
        Arrays.sort(nums);
        int mid=nums.length/2-1;
        for (int num:nums){
            count+=Math.abs(num-nums[mid]);
        }
        return count;
    }


    //80. 删除有序数组中的重复项 II
    public int removeDuplicates(int[] nums) {
        int length=nums.length;
        for (int i=0;i<length;i++){
            int num=nums[i],j=i+1;
            while (j<length&&num==nums[j]){
                j++;
            }
            if (j-i>2){
                int h=i+2;
                for (int k=j;k<length;k++){
                    nums[h]=nums[k];
                    h++;
                }
                length-=j-i-2;
            }
        }
        return length;
    }



    //151. 反转字符串中的单词
    public String reverseWords(String s) {
        String[] strs=s.trim().split("\s+");
        String result = "";
        for (int i=strs.length-1;i>=0;i--){
            if (i==0) {
                result = result + strs[i];
                break;
            }
            result=result+strs[i]+" ";
        }
        return result;
    }

    //50. Pow(x, n)
    public double myPow(double x, int n) {
        boolean f=false;
        long m=n;
        if (m==0) return 1;
        else if (m<0) {
            m*=-1;
            f = true;
        }
        if (x==1) return 1;
        boolean o=false;
        if (m!=1&&m%2==1){
            m--;
            o=true;
        }
        if (x==-1){
            return o?-1:1;
        }
        int time=0;
        while (m%2!=1){
            m=m/2;
            time++;
        }
        double result=x;
        for (int i=1;i<m;i++){
            result*=x;
        }
        while (time-->0){
            result*=result;
        }
        if (o) result*=x;
        return f?1/result:result;
    }


    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        String[] strs=s.trim().split("\s+");
        return strs[strs.length-1].length();
    }

    //347. 前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num:nums){
            if (!hashMap.containsKey(num)) hashMap.put(num,1);
            else hashMap.put(num,hashMap.get(num)+1);
        }
        ArrayList<Integer> list = new ArrayList<>(hashMap.values());
        list.sort(Collections.reverseOrder());
        int j=0;
        for (Map.Entry<Integer,Integer> map: hashMap.entrySet()){
            int val=map.getValue();
            for (int i=0;i<k;i++){
                if (val==list.get(i)){
                    k--;
                    list.remove(i);
                    result[j++]=map.getKey();
                    break;
                }
            }
            if (k<=0) break;
        }
        return result;
    }


    //5. 最长回文子串
    public String longestPalindrome(String s) {
        String result="";
        int length=s.length();
        if (length==1) return s;
        StringBuilder temp=new StringBuilder();
        for (int i=0;i<length-1;i++){
            int left=i-1,right=i+1;
            if (right>=length) break;
            temp= new StringBuilder();
            char c=s.charAt(i);
            temp.append(c);
            while (c==s.charAt(right)){
                temp.append(s.charAt(right));
                right++;
                if (right>=length) break;
            }
            while ((left>=0&&right<length)&&s.charAt(left)==s.charAt(right)){
                temp.insert(0,s.charAt(left));
                temp.append(s.charAt(right));
                left--;right++;
            }
            if (temp.length()>result.length()){
                result=temp.toString();
            }
        }
        return result.isEmpty() ?temp.toString():result;
    }


    //3074. 重新分装苹果
    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int count=0,sum=0;
        for (int num : apple){
            sum+=num;
        }
        for (int i=capacity.length-1;i>=0;i--){
            int num=capacity[i];
            sum-=num;
            count++;
            if (sum<=0){
                break;
            }
        }
        return count;
    }

    //3075. 幸福值最大化的选择方案
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long result=0;
        int count=0,i=happiness.length-1;
        while (k-->0){
            int num=happiness[i];
            result+=(Math.max(num - count, 0));
            count++;i--;
        }
        return result;
    }


    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result=new ArrayList<>();
        HashSet<List<Integer>> hashSet=new HashSet<>();
        for (int i=0;i<nums.length;i++){
            int target=-nums[i],left=i+1,right=nums.length-1;
            while (left<right){
                List<Integer> list=new ArrayList<>();
                int sum=nums[left]+nums[right];
                if (target==sum){
                    list.add(-target);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    left++;right--;
                    if (!hashSet.contains(list)) {
                        result.add(list);
                        hashSet.add(list);
                    }
                }else if (sum<target){
                    left++;
                }else if (sum>target){
                    right--;
                }
            }
        }
        return result;
    }


    //16. 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        int result=Integer.MAX_VALUE,distance=Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            int left=i+1,right=nums.length-1;
            while (left<right){
                int n=nums[i]+nums[left]+nums[right];
                int newDis=Math.max(target,n)-Math.min(target,n);
                if (newDis<distance){
                    result=n;
                    distance=newDis;
                }
                if (n==target) return target;
                else if (n<target) left++;
                else right--;
            }
        }
        return result;
    }

    //18. 四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        HashSet<List<Integer>> hashSet=new HashSet<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                int left=j+1,right=nums.length-1;
                while (left<right){
                    long sum=(long)nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum==target){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        if (!hashSet.contains(list)){
                            result.add(list);
                            hashSet.add(list);
                        }
                        left++;right--;
                    }else if (sum<target) left++;
                    else right--;
                }
            }
        }
        return result;
    }


}
