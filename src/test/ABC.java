package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class ABC {
    public static void main(String[] args) {

    }


    //通过
    public void t1A(){
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        System.out.println(a+b%7);
    }


    public void t2b(){
        Scanner scanner=new Scanner(System.in);
        int time=0;
        int n=scanner.nextInt();
        int b=scanner.nextInt();
        int[] nums=new int[n];
        for (int i=0;i<n;i++){
            nums[i]=scanner.nextInt();
            if (nums[i]<b)
                time++;
        }
        System.out.println(time);
    }

    public void t3c(){
        Scanner scanner=new Scanner(System.in);
        int time=0;
        int n=scanner.nextInt();
        int b=scanner.nextInt();
        int[] nums=new int[n];
        for (int i=0;i<n-1;i++){
            nums[i]=scanner.nextInt();
            if (nums[i]<=b)
                time++;
        }
        System.out.println(time);
    }

    //通过
    public void t4d(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i=0;i<n;i++){
            int num=scanner.nextInt();
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            }else {
                hashMap.put(num,hashMap.get(num)+1);
            }
        }
        for (Map.Entry<Integer,Integer> map: hashMap.entrySet()){
            int key= map.getKey();
            int value=map.getValue();
            if (value==1){
                System.out.println(key);
            }
        }
    }

    public void t5e(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        HashSet<Integer> times=new HashSet<>();
        int t=0;
        for (int i=0;i<n;i++){
            int herta=scanner.nextInt();
            if (herta==1){
                t++;
            } else if (herta==0) {
                times.add(t);
                t=0;
            }
        }
        times.add(t);
        int temp=0;
        for (int time:times){
            System.out.println( time);
            if (time>temp)
                temp=time;
        }
        System.out.println(temp);
    }


    public void t6f(){
        Scanner scanner=new Scanner(System.in);
        long time=0;
        int length =scanner.nextInt();
        int[] songs=new int[length];
        for (int i=0;i<length;i++){
            songs[i]=scanner.nextInt();
        }
        for (int i=0;i<length;i++){
            time+=songs[i];
            if (time>=36000){
                System.out.println(i);
                break;
            }
        }
    }
    public void t7g(){
    }
    public void t8h(){
        Scanner scanner=new Scanner(System.in);
        int n =scanner.nextInt();
        int[] nums=new int[n];
        for (int i=0;i<n;i++){
            nums[i]=scanner.nextInt();
        }

        boolean flag=false;
        for (int num:nums){
            if (num!=9) {
                flag=false;
                break;
            }
            flag=true;
        }

        for (int i=n-1;i>=0;i--){
            if (nums[i]!=9){
                nums[i]++;
                break;
            }else {
                for (int j=i;j>=i;j--){
                    if (nums[j]==9){
                        nums[j]=0;
                    }
                }
            }
        }
        if (flag){
            int[] a=new int[nums.length+1];
            a[0]=1;
            for (int num:a){
                System.out.print(num);
            }
        }else {
            for (int num:nums){
                System.out.print(num);
            }
        }
    }
}
