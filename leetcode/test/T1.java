package src.leetcode.test;

import java.util.*;

public class T1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {1,2,2,4};
        int[] num2 = {2,2};
        String s="Bob";
        String t="abcde";
        String[] strings={};
        //int[] num2 = {2, 2};
        //System.out.println(Arrays.toString(solution.twoSum(num, 6)));
//        solution.isPalindrome(1000000001);
//        System.out.println(solution.isPowerOfTwo(0));
        //int[] a=solution.plusOne(new int[]{9,8,7,6,5,4,3,2,1,0});
        //System.out.println("123"+12345%10);
//        for (int j=0;j<a.length;j++){
//            System.out.println(a[j]);
//        }
//        int a = '[' - ']';
//        int a2 = '(' - ')';
//        int a3 = '[' - ']';
//        System.out.println(a + " " + a2 + " " + a3);
//        System.out.println(solution.isValid("[()]"));
        //System.out.println(solution.mySqrt(8));
        //System.out.println(solution.search(num,1));
        //System.out.println(solution.searchInsert(num,5));
        //System.out.println(solution.myPow(2.00000, -2147483648));
        //System.out.println(Arrays.toString(solution.searchRange(num, 8)));
//        System.out.println(solution.isAnagram("a","b"));
//        String le="abcdefghijklmnopqrstuvwxyz";
        //System.out.println(le.charAt(12));
        // System.out.println(Arrays.toString(solution.intersection(num, num2)));
        //System.out.println(solution.romanToInt("IX"));
        //System.out.println(solution.majorityElement(num));
        //System.out.println(Arrays.toString(solution.plusOne(num)));
        //System.out.println(solution.containsNearbyDuplicate(num,2));
        //System.out.println(solution.missingNumber(num));
        //System.out.println(solution.wordPattern(pattern,s));
        //System.out.println(solution.letterCombinations("23"));
        //System.out.println(Arrays.toString(solution.intersect(num, num2)));
        //System.out.println(solution.findDisappearedNumbers(num));
        //System.out.println(Arrays.toString(solution.findErrorNums(num)));
        //System.out.println(solution.findTheDifference(s,t));
        System.out.println(solution.mostCommonWord(s,strings));
    }
}


class Solution {

    //1.两数之和 暴力循环
    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{j, i};
                }
            }
        }
        return null;
    }
    //1.两数之和 哈希表解法
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (hashMap.containsKey(num))
                return new int[]{i, hashMap.get(num)};
            hashMap.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    //2.两数相加 放弃
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        while (l1.next == null) {
            if (result == null) {
                result.val = l1.val + l2.val;
                result.next = result;
            }
        }
        System.out.println();
        return result;
    }

    //9.回文数
    public boolean isPalindrome(int x) {
        long result = 0;
        int b = x;
        if (x < 0) {
            return false;
        }
        while (x != 0) {
            result = result + x % 10;
            x = x / 10;
            result = result * 10;
        }
        result = result / 10;
        return result == b;
    }

    //231.2的幂 用ai解的
    public boolean isPowerOfTwo(int n) {
//        int x=2;
        if (n == 0)
            return false;
//        while( x<n){
//            x*=2;
//            if (x==n)
//                return true;
//        }
        return (n & (n - 1)) == 0;
    }

    //66.加一
    public int[] plusOne2(int[] digits) {
        //[9,8,7,6,5,4,3,2,1,0]
        long num = 0;
        for (int i = 0; i <= digits.length - 1; i++) {
            num = num * 10 + digits[i];
        }
        num += 1;
        System.out.println(num);
        ArrayList<Integer> nu = new ArrayList<>();
        while (num != 0) {
            nu.add((int) num % 10);
            num = num / 10;
        }
        int[] result = new int[nu.size()];
        int i = nu.size() - 1;
        for (int j = 0; j < nu.size(); j++) {
            result[j] = nu.get(i);
            i--;
        }
        return result;
    }
    public int[] plusOne(int[] digits){
        int n =digits.length;
        boolean flag=false;

        for (int numa:digits){
            if (numa==9) {
                flag=true;
            }else {
                flag=false;
                break;
            }

        }
        if (flag){
            int[] a=new int[digits.length+1];
            a[0]=1;
            return a;
        }

        for (int i=n-1;i>=0;i--){
            if (digits[i]!=9){
                digits[i]++;
                break;
            }else {
                for (int j=i;j>=i;j--){
                    if (digits[j]==9){
                        digits[j]=0;
                    }
                }
            }
        }
        return digits;
    }

    //20.有效括号
    public boolean isValid(String s) {
        ArrayList<Character> arrayList = new ArrayList<Character>();
        arrayList.add(0, 's');
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - arrayList.get(arrayList.size() - 1);
            if (num == 2 || num == 1) {
                arrayList.remove(arrayList.size() - 1);
            } else {
                arrayList.add(s.charAt(i));
            }
        }
        return arrayList.size() == 1;
    }

    //69.x的平方根 ai解决
    public int mySqrt2(int x) {
        int left = 0, right = x, middle = (left + right) / 2;
        int re = 0;
        while (left < right) {
            middle = (left + right) / 2;
            re = middle * middle;
            if (re > x) {
                right = middle - 1;
            } else if (re < x) {
                left = middle + 1;
            }

//            if (  re==x || left>=right){
//                return middle;
//            } else if (right-left==1) {
//                if (middle==right)
//                    return middle-1;
//                return middle;
//            }
        }
        if (left == right)
            return left;
        return middle;
    }
    public int mySqrt(int x) {
        long left = 0, right = x, mid = 0, sq;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            sq = mid * mid;
            if (sq == x) {
                return (int) mid;
            } else if (sq < x) {
                left = mid + 1;
            } else if (sq > x) {
                right = mid - 1;
            }
        }
        return (int) right;
    }
    //答案
    public int mySqrt3(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    //704.二分查找
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle;
        while (left <= right) {
            middle = (right - left) / 2 + left;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else if (target == nums[middle]) {
                return middle;
            }
        }
        return -1;
    }

    //35.搜索插入位置 ai解决

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            }
        }
        return left;
    }



    //242.有效的字母异位词
    public boolean isAnagram(String s, String t) {
        int[] sq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sq[s.charAt(i) - 'a']++;
        }
        for (int j = 0; j < t.length(); j++) {
            sq[t.charAt(j) - 'a']--;
        }
        for (int x = 0; x < sq.length - 1; x++) {
            if (sq[x] != 0)
                return false;
        }
        return true;
    }

    //349.两个数组交集
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();
        for (int k : nums1) {
            hashSet.add(k);
        }
        for (int k : nums2) {
            if (hashSet.contains(k)) {
                resSet.add(k);
            }
        }
        int[] result = new int[resSet.size()];
        int j = 0;
        for (int i : resSet)
            result[j++] = i;
        return result;
    }

    //13.罗马数字转整数
    public int romanToInt(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int result = 0;
        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);
        char le = ' ';
        for (int i = 0; i < s.length(); i++) {
            result = result + hashMap.get(s.charAt(i));
            String a= ""+le+s.charAt(i);
            result = switch (a) {
                case "IV", "IX" -> result - 2 ;
                case "XL", "XC" -> result - 20 ;
                case "CD", "CM" -> result - 200;
                default -> result;
            };
            le=s.charAt(i);
        }
        return result;
    }

    //169.多数元素
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num : nums){
            if (!hashMap.containsKey(num))
                hashMap.put(num,0);
            hashMap.replace(num,hashMap.get(num)+1);
        }
        for (Map.Entry<Integer,Integer> map:hashMap.entrySet()){
            int key=map.getKey();
            int value=map.getValue();
            if (value>(nums.length/2))
                return key;
        }
        return 0;
    }

    //217.存在重复元素
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet=new HashSet<>();
        for (int num : nums){
            if (!hashSet.contains(num)){
                hashSet.add(num);
            }else {
                return true;
            }
        }
        return false;
    }

    //218.存在重复元素II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],i);
            }else {
                if (i-hashMap.get(nums[i])<=k){
                    return true;
                }else {
                    hashMap.put(nums[i],i);
                }
            }
        }
        return false;
    }

    //268.丢失的数字
    public int missingNumber(int[] nums) {
        int[] result=new int[nums.length+1];
        for (int num : nums){
            result[num]++;
        }
        for (int i=0;i<=nums.length;i++ ){
            if (result[i]==0)
                return i;
        }
        return 0;
    }

    //290.单词规律
    public boolean wordPattern(String pattern, String s) {
        String letter = "";
        char pat='a';
        int time=0;
        String result="";
        HashMap<String,Character> hashMap=new HashMap<>();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)!=32){
                letter=letter+s.charAt(i);
            }else if (s.charAt(i)==32 || i==s.length()-1){
                if (!hashMap.containsKey(letter)){
                    hashMap.put(letter, (char) (pat+time));
                    time++;
                }
                result=result+hashMap.get(letter);
                letter="";
            }
        }
        if (!hashMap.containsKey(letter)){
            hashMap.put(letter, (char) (pat+time));
        }
        result=result+hashMap.get(letter);
        HashMap<Character,Character> pmap=new HashMap<>();
        int tim=0;
        char pat2='a';
        String re2="";
        for (int i=0;i<pattern.length();i++){
            if (!pmap.containsKey(pattern.charAt(i))){
                pmap.put(pattern.charAt(i), (char) (pat2+tim++));
            }
            re2=re2+pmap.get(pattern.charAt(i));
        }
        System.out.println(result+" "+re2);
        return Objects.equals(result, re2);
    }

    //17.电话号码的字母组合 放弃
    public List<String> letterCombinations(String digits) {
        String[] numbers={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> temp=new ArrayList<>();
        for (int i=0;i<digits.length();i++){
            int num=digits.charAt(i)-'0';
            String numS=numbers[num];
            temp.add(numS);
        }
        for (int i=0;i<temp.size();i++){
            String tepS=temp.get(i);
            System.out.println(tepS);
        }
        System.out.println(temp);
        return null;
    }

    //350.两个数组交集II
    public int[] intersect(int[] nums1, int[] nums2) {
        //HashSet<Integer> hashSet=new HashSet<>();
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num : nums1) {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num,1);
            }else {
                int a=hashMap.get(num);
                hashMap.put(num,a+1);
            }
        }
        List<Integer> list=new ArrayList<>();
        for (int num:nums2) {
            if (hashMap.containsKey(num)&&hashMap.get(num)!=0) {
                list.add(num);
                int a=hashMap.get(num);
                hashMap.put(num,a-1);
            }
        }
        int[] result=new int[list.size()];
        for (int i=0;i<result.length;i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    //448.找到所有数组中消失的数字
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n=nums.length;
        int[] re=new int[n+1];
        List<Integer> list=new ArrayList<>();
        for (int num : nums) {
            re[num] = 1;
        }
        for (int i=0;i<re.length;i++){
            if (re[i]==0){
                list.add(i);
            }
        }
        list.remove(0);
        return list;
    }

    //575.分糖果
    public int distributeCandies(int[] candyType) {
        int length=candyType.length;
        HashSet<Integer> hashSet=new HashSet<>();
        for (int j : candyType) {
            hashSet.add(j);
        }
        int n=length/2;
        return Math.min(hashSet.size(), n);
    }

    //645.错误的集合
    public int[] findErrorNums(int[] nums) {
        int[] result=new int[2];
        int n=nums.length;
        int[] re=new int[n+1];
        for (int num:nums){
            re[num]++;
        }
        for (int i=1;i<re.length;i++){
            if (re[i]==0)
                result[1]=i;
            else if (re[i]==2)
                result[0]=i;
        }
        return result;
    }

    //389.找不同
    public char findTheDifference(String s, String t) {
        HashMap<Character,Integer> hashMap=new HashMap<>();
        for (int i=0;i<t.length();i++){
            char le=t.charAt(i);
            if (!hashMap.containsKey(le)){
                hashMap.put(le,1);
            }else {
                hashMap.put(le,hashMap.get(le)+1);
            }
        }
        for (int j=0;j<s.length();j++){
            char le=s.charAt(j);
            if (hashMap.containsKey(le)){
                hashMap.put(le,hashMap.get(le)-1);
            }else {
                return le;
            }
        }
        for (Map.Entry<Character, Integer> map:hashMap.entrySet()){
            char key=map.getKey();
            int value=map.getValue();
            if (value!=0)
                return key;
        }
        return 0;
    }

    //819.最常见的单词
    public String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> hashSet=new HashSet<>();
        Collections.addAll(hashSet, banned);
        List<String> letterList=new ArrayList<>();
        HashMap<String,Integer> hashMap=new HashMap<>();

        paragraph=paragraph.toLowerCase();
        String letter="";
        boolean tt=true;
        for (int i=0;i<paragraph.length();i++){
            char le=paragraph.charAt(i);
            if (le>='a'&&le<='z'){
                letter=letter+le;
            }else {
                if (!letter.equals("")) {
                    char a = letter.charAt(0);
                    if (a - 'a' < 0 || a - 'a' > 25 ) {
                        letter = "";
                        if (i==paragraph.length()-1)
                            tt=false;
                        continue;
                    }
                }else {
                    continue;
                }
                if (i==paragraph.length()-1&&tt)
                    letterList.add(letter);
                letterList.add(letter);
                letter="";
            }
            //char a = letter.charAt(0);

                //letterList.add(letter);
        }

        for (String le : letterList) {
            if (!hashSet.contains(le)){
                if (!hashMap.containsKey(le)){
                    hashMap.put(le,1);
                }else {
                    int time=hashMap.get(le);
                    hashMap.put(le,time+1);
                }
            }
        }

        int max=0;
        String result = null;
        for (Map.Entry<String,Integer> map:hashMap.entrySet()){
            String key=map.getKey();
            int value=map.getValue();
            if (value>max) {
                max = value;
                result=key;
            }
        }
        return result;
    }






}