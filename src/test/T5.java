package test;

import java.util.*;

public class T5 {
    public static void main(String[] args) {
        T5 t5=new T5();
        int num=123;
        int[] nums={10,5,2,6};
        int[] nums2={5,4,3,4,2};
        int[][] numsD={{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] numsD3={{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        int[][] numsD2={{1,2,3},{4,5,6},{7,8,9}};
        String str="AB";
        String str2="cae";
        String[] strs={"flower","flow","flight"};
        //System.out.println(t5.findDuplicate(nums));
        //System.out.println(t5.numMagicSquaresInside(numsD));
        //System.out.println(t5.canCompleteCircuit(nums,nums2));
        //System.out.println(t5.searchMatrixII(numsD,-5));
        //System.out.println(t5.spiralOrder(numsD2));
        //System.out.println(1/2);
        //t5.rotate(numsD3);
        //System.out.println(t5.numSubarrayProductLessThanK(nums,100));
        //System.out.println(t5.longestCommonPrefix(strs));
        //System.out.println(t5.minWindow(str,str2));
        //System.out.println(t5.intToRoman(60));
        //System.out.println(t5.convert(str,1));
        System.out.println(Arrays.deepToString(t5.generateMatrix(3)));
    }

    //287. 寻找重复数
    public int findDuplicate(int[] nums) {
        int slow=0,fast=0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);
        int cur=0;
        while (cur!=slow){
            cur=nums[cur];
            slow = nums[slow];
        }
        return cur;
    }

    //840. 矩阵中的幻方
    public int numMagicSquaresInside(int[][] grid){
        int rowLength=grid.length,colLength=grid[0].length;
        if (rowLength<3||colLength<3) return 0;
        int count=0;
        for (int i=1;i<rowLength-1;i++){
            int row1=0,row2=0,row3=0,col1=0,col2=0,col3=0,dia1=0,dia2=0;
            for (int j=1;j<colLength-1;j++){
                if(grid[i - 1][j - 1]<=0||grid[i - 1][j - 1]>9) continue;
                if(grid[i-1][j]<=0||grid[i-1][j]>9) continue;
                if(grid[i-1][j+1]<=0||grid[i-1][j+1]>9) continue;
                if(grid[i][j-1]<=0||grid[i][j-1]>9) continue;
                if(grid[i][j]<=0||grid[i][j]>9) continue;
                if(grid[i][j+1]<=0||grid[i][j+1]>9) continue;
                if(grid[i+1][j-1]<=0||grid[i+1][j-1]>9) continue;
                if(grid[i+1][j]<=0||grid[i+1][j]>9) continue;
                if(grid[i+1][j+1]<=0||grid[i+1][j+1]>9) continue;
                HashSet<Integer> hashSet=new HashSet<>();
                hashSet.add(grid[i - 1][j - 1]);
                if (!hashSet.add(grid[i-1][j])) continue;
                if (!hashSet.add(grid[i-1][j+1])) continue;
                if (!hashSet.add(grid[i][j-1])) continue;
                if (!hashSet.add(grid[i][j])) continue;
                if (!hashSet.add(grid[i][j+1])) continue;
                if (!hashSet.add(grid[i+1][j-1])) continue;
                if (!hashSet.add(grid[i+1][j])) continue;
                if (!hashSet.add(grid[i+1][j+1])) continue;
                row1=grid[i-1][j-1]+grid[i-1][j]+grid[i-1][j+1];
                row2=grid[i][j-1]+grid[i][j]+grid[i][j+1];
                row3=grid[i+1][j-1]+grid[i+1][j]+grid[i+1][j+1];
                col1=grid[i-1][j-1]+grid[i][j-1]+grid[i+1][j-1];
                col2=grid[i-1][j]+grid[i][j]+grid[i+1][j];
                col3=grid[i-1][j+1]+grid[i][j+1]+grid[i+1][j+1];
                dia1=grid[i-1][j-1]+grid[i][j]+grid[i+1][j+1];
                dia2=grid[i-1][j+1]+grid[i][j]+grid[i+1][j-1];
                if (row1==row2&&row1==row3&&row1==col1&&row1==col2&&row1==col3&&row1==dia1&&row1==dia2) count++;
            }
        }
        return count;
    }

    //134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
        if (n==1) return gas[0]>=cost[0]?0:-1;
        int i=0;
        while (i<n){
            int nowGas=0,j=0;
            boolean f=true;
            for (j=0;j<n;j++){
                int index=(j+i)%n;
                nowGas+=gas[index];
                nowGas-=cost[index];
                if (nowGas<0){
                    f=false;
                    break;
                }
            }
            if (f) return i;
            i=i+j+1;
        }
        return -1;
    }


    //74. 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] nums:matrix){
            if (nums[0]<=target&&target<=nums[nums.length-1]){
                int left=0,right=nums.length-1,mid;
                while (left<=right){
                    mid=(right-left)/2+left;
                    if (nums[mid]==target){
                        return true;
                    }else if (target<nums[mid]){
                        right=mid-1;
                    }else {
                        left=mid+1;
                    }
                }
            }
        }
        return false;
    }

    //240. 搜索二维矩阵 II
    public boolean searchMatrixII(int[][] matrix, int target) {
        int  left=0,right=matrix[0].length-1, search=matrix[0][right];
        while (left<matrix.length&&right>=0){
            int num=matrix[left][right];
            if (num==target) return true;
            else if (target<num) right--;
            else left++;
        }
        return false;
    }

    //73. 矩阵置零
    public void setZeroes(int[][] matrix) {
        ArrayList<int[]> list=new ArrayList<>();
        int m=matrix.length,n=matrix[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (matrix[i][j]==0) list.add(new int[]{i,j});
            }
        }
        for (int[] nums:list){
            for (int i=0;i<n;i++){
                matrix[nums[0]][i]=0;
            }
            for (int i=0;i<m;i++){
                matrix[i][nums[1]]=0;
            }
        }
    }

    //54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        List<Integer> result=new ArrayList<>();
        int left=0,right=n-1,top=0,botton=m-1;
        while (top <= botton && left <= right){
            boolean a=true;
            for (int i=left;i<=right;i++){
                result.add(matrix[top][i]);
                a=false;
            }
            if (a) break;
            a=true;
            top++;
            for (int i=top;i<=botton;i++){
                result.add(matrix[i][right]);
                a=false;
            }
            if (a) break;
            a=true;
            right--;
            for (int i=right;i>=left;i--){
                result.add(matrix[botton][i]);
                a=false;
            }
            if (a) break;
            a=true;
            botton--;
            for (int i=botton;i>=top;i--){
                result.add(matrix[i][left]);
                a=false;
            }
            if (a) break;
            a=true;
            left++;
        }
        return result;
    }


    //48. 旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length,top=0,end=n-1;
        while (top<end){
            for (int i=0;i<n;i++){
                int temp=matrix[top][i];
                matrix[top][i]=matrix[end][i];
                matrix[end][i]=temp;
            }
            top++;end--;
        }
        Deque<int[]> right=new ArrayDeque<>();
        Deque<int[]> left=new ArrayDeque<>();
        right.addLast(new int[]{0,n-1});
        left.addLast(new int[]{n-1,0});
        for (int i=0;i<n;i++){
            int size=right.size();
            while (size-->0){
                int[] a1=right.removeFirst();
                int[] a2=left.removeFirst();
                int i1=a1[0],j1=a1[1],i2=a2[0],j2=a2[1];
                if (right.isEmpty()) right.addLast(new int[]{i1,j1-1});
                else if (!Arrays.equals(new int[]{i1,j1-1},right.getLast())) right.addLast(new int[]{i1,j1-1});
                right.addLast(new int[]{i1+1,j1});
                if (left.isEmpty()) left.addLast(new int[]{i2-1,j2});
                else if (!Arrays.equals(new int[]{i2-1,j2},left.getLast())) left.addLast(new int[]{i2-1,j2});
                left.addLast(new int[]{i2,j2+1});
                int temp=matrix[i1][j1];
                matrix[i1][j1]=matrix[i2][j2];
                matrix[i2][j2]=temp;
            }
        }
    }

    //713. 乘积小于 K 的子数组
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k<=1) return 0;
        int j=0,res=0;
        long pro=1;
        for (int i=0;i<nums.length;i++){
            pro*=nums[i];
            while (pro>=k) {
                pro/=nums[j];
                j++;
            }
            res+=(i-j)+1;
        }
        return res;
    }

    //14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs[0].isEmpty()) return "";
        StringBuilder sb=new StringBuilder(strs[0]);
        for (int i=1;i<strs.length;i++){
            String str=strs[i];
            int sbIndex=0,strIndex=0;
            while (sbIndex<sb.length()&&strIndex<str.length()){
                if (sb.charAt(sbIndex)!=str.charAt(strIndex)) break;
                sbIndex++;strIndex++;
            }
            if (sbIndex>=sb.length()) continue;
            sb.delete(sbIndex,sb.length());
        }
        return sb.toString();
    }


    //1390. 四因数
    public int sumFourDivisors(int[] nums) {
        int res=0;
        for (int num : nums){
            int count=0,a1=0,a2=0;
            for (int i=1;i*i<=num;i++){
                if (num%i==0){
                    count++;
                    if (count>=3) break;
                    a1=i;a2=num/i;
                }
            }
            if (count==2){
                if (a1!=a2)  res+=1+num+a1+a2;
            }
        }
        return res;
    }

    //76. 最小覆盖子串
    public String minWindow(String s, String t) {
        HashSet<Character> hashSet=new HashSet<>();
        HashSet<Character> timeSet=new HashSet<>();
        HashMap<Character,Integer> hashMap=new HashMap<>();
        String res = "";
        for (int i=0;i<t.length();i++){
            char c=t.charAt(i);
            hashSet.add(c);
            timeSet.add(c);
            hashMap.put(c,hashMap.getOrDefault(c,0)+1);
        }
        StringBuilder sb=new StringBuilder();
        for (int right=0;right<s.length();right++){
            char c=s.charAt(right);
            sb.append(c);
            if (!hashSet.contains(c)) continue;
            int time=hashMap.get(c);
            hashMap.put(c,time-1);
            if (time<=1) timeSet.remove(c);
            while (!sb.isEmpty()&&(timeSet.isEmpty()||!hashSet.contains(sb.charAt(0)))){
                if (timeSet.isEmpty()) {
                    if (res.isEmpty()||res.length()>sb.length()) res = new String(sb);
                }
                char remove=sb.charAt(0);
                sb.deleteCharAt(0);
                if (!hashSet.contains(remove)) continue;
                int timeRe=hashMap.get(remove);
                hashMap.put(remove,timeRe+1);
                if (timeRe>=0) timeSet.add(remove);
            }
        }
        return res;
    }

    //12. 整数转罗马数字
    public String intToRoman(int num) {
        char[] chars=String.valueOf(num).toCharArray();
        int p=1;
        StringBuilder sb=new StringBuilder();
        for (int i=chars.length-1;i>=0;i--) {
            int n=(chars[i]-'0')*p;
            if (n<10){
                if (n<4){
                    sb.append("I".repeat(n));
                }else if (n==4){
                    sb.insert(0,"IV");
                }else if (n<9){
                    sb.insert(0,"V");
                    sb.append("I".repeat(n - 5));
                }else {
                    sb.append("IX");
                }
            }else if (n<100){
                StringBuilder b=new StringBuilder();
                int m=n/10;
                if (m<4){
                    b.append("X".repeat(m));
                }else if (m==4){
                    b.insert(0,"XL");
                }else if (m<9){
                    b.insert(0,"L");
                    b.append("X".repeat(m - 5));
                }else {
                    b.append("XC");
                }
                sb.insert(0,b);
            }else if (n<1000){
                StringBuilder b=new StringBuilder();
                int m=n/100;
                if (m<4){
                    b.append("C".repeat(m));
                }else if (m==4){
                    b.insert(0,"CD");
                }else if (m<9){
                    b.insert(0,"D");
                    b.append("C".repeat(m - 5));
                }else {
                    b.append("CM");
                }
                sb.insert(0,b);
            }else {
                int m=n/1000;
                for (int j=0;j<m;j++){
                    sb.insert(0,'M');
                }
            }
            p*=10;
        }
        return sb.toString();
    }


    //6. Z 字形变换
    public String convert(String s, int numRows) {
        if (numRows==1) return s;
        StringBuilder[] sbs=new StringBuilder[numRows];
        StringBuilder res=new StringBuilder();
        for (int i=0;i<numRows;i++){
            sbs[i]=new StringBuilder();
        }
        int j=0;
        boolean f=true;
        for (int i=0;i<s.length();i++){
            sbs[j].append(s.charAt(i));
            if (f){
                if (j==numRows-1) {
                    j--;
                    f=false;
                    continue;
                }
                j++;
            }else {
                if (j==0) {
                    j++;
                    f=true;
                    continue;
                }
                j--;
            }
        }
        for (StringBuilder sb:sbs){
            res.append(sb);
        }
        return res.toString();
    }


    //59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] res=new int[n][n];
        int top=0,bot=n-1,left=0,right=n-1,num=1;
        while (left<=right&&top<=bot){
            int i=top,j=left;
            for (;j<=right;j++,num++){
                res[i][j]=num;
            }
            for (i++,j--;i<=bot;i++,num++){
                res[i][j]=num;
            }
            for (j--,i--;j>=left;j--,num++){
                res[i][j]=num;
            }
            for (i--,j++;i>top;i--,num++){
                res[i][j]=num;
            }
            top++;bot--;left++;right--;
        }
        return res;
    }


    //1266. 访问所有点的最小时间
    public int minTimeToVisitAllPoints(int[][] points) {
        int res=0,x0=points[0][0],y0=points[0][1];
        for (int i=1;i<points.length;i++){
            int x1=points[i][0],y1=points[i][1];
            res+=Math.max(Math.abs(x1-x0),Math.abs(y1-y0));
            x0=x1;
            y0=y1;
        }
        return res;
    }



    //3507. 移除最小数对使数组有序 I
    public int minimumPairRemoval(int[] nums) {
        ArrayList<Integer> list=new ArrayList<>();
        for (int num:nums){
            list.add(num);
        }
        int res=0;
        while (list.size()>1){
            int n=list.size(),index = 0,last=list.get(0),minAdd=Integer.MAX_VALUE;
            boolean f=true;
            for (int i = 1; i <n; i++) {
                int num=list.get(i);
                if (last>num) f=false;
                int add=num+last;
                if (add<minAdd){
                    index=i-1;
                    minAdd=add;
                }
                last=num;
            }
            if (f) break;
            list.set(index,minAdd);
            list.remove(index+1);
            res++;
        }
        return res;
    }


    //1877. 数组中最大数对和的最小值
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res=Integer.MIN_VALUE;
        for (int i = 0 , j = nums.length-1; i < nums.length && j>=0 ; i++ , j--) {
            res=Math.max(res,nums[i]+nums[j]);
        }
        return res;
    }
















}
