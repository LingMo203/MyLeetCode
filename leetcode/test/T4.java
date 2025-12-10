package src.leetcode.test;

import java.util.*;

public class T4 {
    public static void main(String[] args) {
        T4 t4=new T4();
        int num=123;
        int[] nums={1,2,3,4,5};
        String str="(()))())(";
        String str2="0";
        //System.out.println(t4.calculate(str));
        //System.out.println(t4.countOdds(3,7));
        //System.out.println(t4.countTriples(5));
        //System.out.println(t4.maximumSwap(9973));
        //System.out.println(t4.compareVersion(str,str2));
        //System.out.println(t4.trailingZeroes(30));
        //System.out.println(Arrays.toString(t4.dailyTemperatures(nums)));
        //System.out.println(t4.largestRectangleArea(nums));
        System.out.println(t4.longestValidParentheses(str));
    }


    //227. 基本计算器 II
    public int calculate(String s) {
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











}
