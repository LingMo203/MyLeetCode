package src.leetcode.test;

import java.util.*;

public class T2 {
    public static void main(String[] args) {
        Solution2 solution=new Solution2();
        String a="aaba";
        String b="ll";
        char[] strs=a.toCharArray();
        int[] nums={-2,9,9,8,4};
        int[] nums2={1,1};
        //System.out.println(solution.secondHighest(a));
        //System.out.println('2'-'0');
        //System.out.println(solution.lengthOfLongestSubstring(a));
        //System.out.println(solution.minSubArrayLen(11,nums));
        //System.out.println(solution.strStr(a,b));
        //System.out.println(Arrays.toString(solution.merge(nums,3,nums2,3)));
        //System.out.println(solution.isPalindrome(a));
        //System.out.println(solution.isHappy(19));
        //System.out.println(solution.abc(4));
        //solution.moveZeroes(nums);
        //System.out.println(solution.reverseVowels(a));
        //System.out.println(solution.reverseStrByID(strs,1,4));
        //System.out.println(solution.reverseStr(a,2));
        //System.out.println(solution.reverseWords(a));
        //System.out.println(Arrays.toString(solution.shortestToChar(a, 'b')));
        //System.out.println(solution.findContentChildren(nums,nums2));
        //System.out.println(solution.canPlaceFlowers(nums,2));
        //System.out.println(solution.lemonadeChange(nums));
        //System.out.println(solution.maximum69Number(9669));
        //System.out.println(solution.largestPerimeter(nums));
        //System.out.println(solution.largestSumAfterKNegations(nums,5));
        //System.out.println(solution.splitNum(4325));
        //System.out.println(solution.guessNumber(10));
        System.out.println(solution.arrangeCoins(5));
    }
}
class Solution2 {

    //1002.查找共用字符 放弃
    public List<String> commonChars(String[] words) {
        List<int[]> list=new ArrayList<>();
        for (String letter:words){
            int[] le=new int[26];
            for (int i=0;i<letter.length();i++){
                char l=letter.charAt(i);
                le[l-'a']++;
            }
            list.add(le);
        }



        return null;
    }

    //961. 在长度 2N 的数组中找出重复 N 次的元素
    public int repeatedNTimes(int[] nums) {
        int n=nums.length/2;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num:nums){
            if (!hashMap.containsKey(num)){
                hashMap.put(num,1);
            }else {
                hashMap.put(num,hashMap.get(num)+1);
            }
        }
        for (Map.Entry<Integer,Integer> map: hashMap.entrySet()){
            int key=map.getKey();
            int val=map.getValue();
            if (val==n){
                return key;
            }
        }
        return 0;
    }

    //1796. 字符串中第二大的数字
    public int secondHighest(String s) {
        int[] nums=new int[10];
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)>='0' && s.charAt(i)<='9'){
                int a=s.charAt(i)-'0';
                nums[a]++;
            }
        }
        int time=0;
        for (int i=nums.length-1;i>=0;i--){
            if (nums[i]!=0){
                time++;
            }
            if (time==2){
                return i;
            }
        }
        return -1;
    }

    //3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==1)
            return 1;
        HashMap<Character,Integer> hashMap=new HashMap<>();
        int count=0,result=0;
        for (int i=0;i<s.length();i++){
            char a=s.charAt(i);
            if (!hashMap.containsKey(a)){
                hashMap.put(a,i);
                count++;
            }else {
                i=hashMap.get(a);
                hashMap.clear();
                if (count>result)
                    result=count;
                count=0;
            }

        }
        return Math.max(count, result);
    }

    //49. 字母异位词分组 放弃
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<HashSet<Character>> temp=new ArrayList<>();
        List<List<String>> result=new ArrayList<>();
        for (String str:strs){
            ArrayList<Character> arrayList=new ArrayList<>();
            for (int i=0;i<str.length();i++){
                char a=str.charAt(i);
                arrayList.add(a);
            }
        }
        return null;
    }

    //26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        HashSet<Integer> hashSet=new HashSet<>();
        int count=0;
        for (int num:nums){
            if (!hashSet.contains(num)){
                hashSet.add(num);
                nums[count]=num;
                count++;
            }
        }
        return count;
    }

    //27. 移除元素
    public int removeElement(int[] nums, int val) {
        int i=0;
        for (int num:nums){
            if (num!=val){
                nums[i]=num;
                i++;
            }
        }
        return i;
    }


    //997. 找到小镇的法官 放弃
    public int findJudge(int n, int[][] trust) {
        HashSet<Integer> hashSet=new HashSet<>();
        HashSet<Integer> reSet=new HashSet<>();
        for (int i=0 ;i<trust.length; i++) {
            int[] a=trust[i];
            for (int betrust:a){
                if (i==0){
                    hashSet.add(betrust);
                }else {
                    if (hashSet.contains(betrust)){
                        reSet.add(betrust);
                    }
                }
            }
            reSet.remove(trust[i][0]);
        }
        for (int f:reSet){
            return f;
        }
        return -1;
    }

    //977. 有序数组的平方
    public int[] sortedSquares(int[] nums) {
        int[] result=new int[nums.length];
        int i=0,j=nums.length-1,k=result.length-1;
        while (i<=j){
            int i2=nums[i]*nums[i],j2=nums[j]*nums[j];
            if (i2>=j2){
                result[k]=i2;
                i++;
            }else {
                result[k]=j2;
                j--;
            }
            k--;
        }
        return result;
    }

    //209. 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int i=0,sum=0,result = 99999999;
        for (int j=0;j<nums.length;j++){
            sum=sum+nums[j];
            while (sum>=target){
                if (result>j-i){
                    result=j-i+1;
                }
                sum=sum-nums[i];
                i++;
            }
        }
        if (result==99999999)
            return 0;
        return result;
    }

    //28. 找出字符串中第一个匹配项的下标
    public int strStr(String haystack, String needle) {
        String re="";
        int i=0;
        for (int j=0;j<haystack.length();j++){
            char a=haystack.charAt(j);
            re=re+a;
            if (j-i+1==needle.length()){
                if (needle.equals(re)){
                    return i;
                }else {
                    re=re.substring(1);
                    i++;
                }
            }
        }
        return -1;
    }

    //88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,k=nums1.length-1;
        while (k>=0){
            if (i<0){
                nums1[k] = nums2[j];
                j--;
            } else if (j<0) {
                break;
            } else if (i>=0 && j>=0){
                if (nums1[i] > nums2[j]) {
                    nums1[k] = nums1[i];
                    i--;
                } else {
                    nums1[k] = nums2[j];
                    j--;
                }
            }
            k--;
        }
    }


    //125. 验证回文串
    public boolean isPalindrome(String s) {
        s=s.toLowerCase();
        int i=0,j=s.length()-1;
        while (i<=j){
            char a=s.charAt(i);
            char b=s.charAt(j);
            if (!Character.isLetter(a) && !Character.isDigit(a)){
                i++;
                continue;
            }
            if (!Character.isLetter(b) && !Character.isDigit(b)){
                j--;
                continue;
            }
            if (a!=b){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //202. 快乐数
    public boolean isHappy(int n) {
        int slow=n,fast=n;
        do {
            slow=abc(slow);
            fast=abc(fast);
            fast=abc(fast);
            if (slow==1 || fast==1){
                return true;
            }
        }while (slow!=fast);
        return false;
    }
    public int abc(int x){
        int sum=0;
        String a=String.valueOf(x);
        for (int i=0;i<a.length();i++){
            int num=a.charAt(i)-'0';
            sum=sum+num*num;
        }
        return sum;
    }

    //283.移动零
    public void moveZeroes(int[] nums) {
        int j=0;
        for (int num : nums){
            if (num!=0){
                nums[j]=num;
                j++;
            }
        }
        int i=nums.length-1;
        for (int zro=nums.length-j;zro>0;zro--){
            nums[i]=0;
            i--;
        }
    }

    //344. 反转字符串
    public void reverseString(char[] s) {
        int i=0,j=s.length-1;
        while (i<j){
            char temp=s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }

    //345. 反转字符串中的元音字母
    public String reverseVowels(String s) {
        HashSet<Character> hashSet=new HashSet<>();
        hashSet.add('a');hashSet.add('A');
        hashSet.add('e');hashSet.add('E');
        hashSet.add('i');hashSet.add('I');
        hashSet.add('o');hashSet.add('O');
        hashSet.add('u');hashSet.add('U');
        int i=0,j=s.length()-1;
        char[] st=s.toCharArray();
        while (i<=j){
            char a=st[i];
            char b=st[j];
            if (hashSet.contains(a)&&hashSet.contains(b)){
                st[i]=b;
                st[j]=a;
            }else if (hashSet.contains(a)){
                i--;
            }else if (hashSet.contains(b)){
                j++;
            }
            i++;
            j--;
        }
        return new String(st);
    }

    //392. 判断子序列
    public boolean isSubsequence(String s, String t) {
        int i,j=0;
        if (s.equals(""))
            return true;
        for (i=0;i<t.length();i++){
            char a=t.charAt(i);
            if (a==s.charAt(j)){
                j++;
                if (j==s.length()-1){
                    return true;
                }
            }
        }
        return false;
    }


    //541. 反转字符串 II        答案 reverseStrByID(str,i,Math.min(i+k,s.length())-1);
    //   "abcdefg"        "bacd feg"   2
    //   "abcd"  "bacd"
    public String reverseStr(String s, int k) {
        char[] str=s.toCharArray();
        for (int i=0;i<str.length;i+=2*k){
            reverseStrByID(str,i,Math.min(i+k,s.length())-1);
        }
        return new String(str);
    }
    public char[] reverseStrByID(char[] str,int first,int end){
        while (first<end){
            char temp=str[first];
            str[first]=str[end];
            str[end]=temp;
            first++;
            end--;
        }
        return str;
    }

    //557. 反转字符串中的单词 III
    public String reverseWords(String s) {
        String[] strs=s.split(" ");
        for (int k=0;k<strs.length;k++){
            char[] str=strs[k].toCharArray();
            int i=0,j=str.length-1;
            while (i<j){
                char temp=str[i];
                str[i]=str[j];
                str[j]=temp;
                i++;
                j--;
            }
            strs[k]=new String(str);
        }
        String result = "";
        for (int i=0;i<strs.length;i++){
            result=result+strs[i];
            if (i!=strs.length-1){
                result=result+" ";
            }
        }
        return result;
    }

    //821. 字符的最短距离  [3,2,1,0,1,0,0,1,2,2,1,0]  [2,1,0,1]
    public int[] shortestToChar(String s, char c) {
        int[] result=new int[s.length()];
        int i=0,last=-999999999;
        for (int j=0;j<s.length();j++){
            char a=s.charAt(j);
            if (a==c){
                while (i<=j){
                    result[i]=j-i;
                    if (result[i]!=0 && j!=i)
                        result[i]=Math.min(j-i,i-last);
                    i++;
                }
                last=j;
            }
        }
        int te=last+1;
        while (te<=s.length()-1){
            result[te]=te-last;
            te++;
        }
        return result;
    }


    //455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i=g.length-1,j=s.length-1,time=0;
        while (i>=0&&j>=0){
            if (g[i]<=s[j]){
                time++;
                i--;j--;
            }else {
                i--;
            }
        }
        return time;
    }


    //605. 种花问题  1  0  1  0
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i,time=0;
        for (i=0;i<flowerbed.length;i++){
            if (flowerbed[i]==0){
                if (i==0){
                    if (flowerbed.length==1) {
                        return true;
                    }
                    if (flowerbed[i+1]==0) {
                        flowerbed[i] = 1;
                        time++;
                    }
                }else if (i==flowerbed.length-1){
                    if (flowerbed[i-1]==0){
                        flowerbed[i] = 1;
                        time++;
                    }
                }else {
                    if (flowerbed[i+1]==0&&flowerbed[i-1]==0) {
                        flowerbed[i] = 1;
                        time++;
                    }
                }
            }
            if (time>=n)
                return true;
        }
        return false;
    }

    //860. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        int[] l=new int[3];
        for (int num:bills){
            if (num==5){
                l[0]++;
            }else if (num==10){
                l[0]--;
                l[1]++;
            }else if (num==20){
                if (l[1]>0){
                    l[1]--;
                }else{
                    l[0]-=2;
                }
                l[0]--;
                l[2]++;
            }
            if (l[0]<0||l[1]<0){
                return false;
            }
        }
        return true;
    }

    //561. 数组拆分
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for (int i=0;i<nums.length;i+=2){
            sum=sum+nums[i];
        }
        return sum;
    }


    //1323. 6 和 9 组成的最大数字
    public int maximum69Number (int num) {
        String re= String.valueOf(num);
        int sum=0;
        boolean a=true;
        for (int i=0;i<re.length();i++){
            if (re.charAt(i)=='6'&&a){
                sum=sum*10+9;
                a=false;
            }else {
                sum=sum*10+(re.charAt(i)-'0');
            }

        }
        return sum;
    }

    //976. 三角形的最大周长
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int a=0,b = 0,c=0;
        for (int i=nums.length-1;i>=0;i--){
            c=b;
            b=a;
            a=nums[i];
            if ((a+b>c && a+c>b && c+b>a)&&nums.length-i>=3){
                return a+b+c;
            }
        }
        return 0;
    }

    //1005. K 次取反后最大化的数组和
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum=0;
        int temp=-999999999;
            for (int i=0;i< nums.length;i++) {
            int num=nums[i];
            if (k > 0) {
                if (i>=nums.length-1){
                    if (temp<0) {
                        if (-temp < num) {
                            if (k % 2 != 0) {
                                sum = sum + temp+temp;
                                sum=sum+num;
                            }
                        } else {
                            if (k % 2 != 0) {
                                sum = sum - num;
                            } else {
                                sum = sum + num;
                            }
                        }
                    }else {
                        if (k % 2 != 0) {
                            sum = sum - num;
                        } else {
                            sum = sum + num;
                        }
                    }
                    break;
                }else {
                    if (num < 0) {
                        sum = sum - num;
                        k--;
                    } else if (num == 0) {
                        k = 0;
                    } else {
                        if (temp<0) {
                            if (-temp < num) {
                                if (k % 2 != 0) {
                                    sum = sum + temp+temp;
                                }
                                sum=sum+num;
                            } else {
                                if (k % 2 != 0) {
                                    sum = sum - num;
                                } else {
                                    sum = sum + num;
                                }
                            }
                        }else {
                            if (k % 2 != 0) {
                                sum = sum - num;
                            } else {
                                sum = sum + num;
                            }
                        }
                        k = 0;
                    }
                }
                temp=num;
            }else
                sum = sum + num;
        }
        return sum;
    }

    //2578. 最小和分割
    public int splitNum(int num) {
        char[] chars=Integer.toString(num).toCharArray();
        Arrays.sort(chars);
        int Lsum=0,Rsum=0;
        for (int i=0;i<chars.length;i++){
            if (i%2!=0){
                Lsum=Lsum*10+chars[i]-'0';
            }else {
                Rsum=Rsum*10+chars[i]-'0';
            }
        }
        return Lsum+Rsum;
    }

    //374. 猜数字大小
    public int guessNumber(int n) {
        int right=n,left=0,mid=(right-left)/2+left,result=-1;
        while (left<=right){
            mid=(right-left)/2+left;
            result=guess(mid);
            if (result==-1){
                right=mid-1;
            }else if (result==1){
                left=mid+1;
            }else if (result==0){
                return mid;
            }
        }
        return mid;
    }
    int guess(int num){
        if (num==6)
            return 0;
        else if (num>6)
            return -1;
        else if (num<6)
            return 1;
        return -2;
    }

    //441. 排列硬币 暴力破解
    public int arrangeCoins(int n) {
        int i=1;
        long sum=0;
        while (sum<n){
            sum+=i;
            i++;
        }
        if (sum==n)
            return i-1;
        return i-1-1;
    }






}

