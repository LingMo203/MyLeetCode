package contest;

import java.util.*;

//第 483 场周赛
public class ContestWeekly483 {

    public static void main(String[] args) {
        ContestWeekly483 t=new ContestWeekly483();
        String str="2211112112";
        int n=10;
        int[] diff = {2,2,3,1,4,5,1,1,2};
        String[] strs={"able","area","echo","also"};
        int[][] restrictions = {{6}, {1, 6}, {2, 3}};
        //System.out.println(t.largestEven(str));
        //System.out.println(4/2);
        System.out.println(t.wordSquares(strs));
        //System.out.println(t.minMergeCost(restrictions));
    }

    //3798. 最大的偶数
    public String largestEven(String s) {
        StringBuilder sb=new StringBuilder(s);
        while (!sb.isEmpty()&&sb.charAt(sb.length()-1)!='2'){
            sb.delete(sb.length()-1,sb.length());
        }
        return sb.toString();
    }

    //3799. 单词方块 II (没做出)
    public List<List<String>> wordSquares2(String[] words) {
        List<List<String>> res=new ArrayList<>();
        String[] strs=new String[4];
        int j=0,leftI=0,rightI=0;
        while (leftI<words.length-2){
            strs[j]=words[rightI];
            if (j==3){
                String top=strs[0],bottom=strs[1],left=strs[2],right=strs[3];
                isRes(res, strs, top, bottom, left, right);
                top=strs[0];bottom=strs[2];left=strs[1];right=strs[3];
                isRes(res, strs, top, bottom, left, right);
                top=strs[0];bottom=strs[2];left=strs[3];right=strs[1];
                isRes(res, strs, top, bottom, left, right);
                top=strs[0];bottom=strs[1];left=strs[3];right=strs[2];
                isRes(res, strs, top, bottom, left, right);
                top=strs[0];bottom=strs[3];left=strs[1];right=strs[2];
                isRes(res, strs, top, bottom, left, right);
                top=strs[0];bottom=strs[3];left=strs[2];right=strs[1];
                isRes(res, strs, top, bottom, left, right);

                top=strs[1];bottom=strs[2];left=strs[3];right=strs[0];
                isRes(res, strs, top, bottom, left, right);
                top=strs[1];bottom=strs[2];left=strs[0];right=strs[3];
                isRes(res, strs, top, bottom, left, right);
                top=strs[1];bottom=strs[0];left=strs[2];right=strs[3];
                isRes(res, strs, top, bottom, left, right);
                top=strs[1];bottom=strs[0];left=strs[3];right=strs[2];
                isRes(res, strs, top, bottom, left, right);
                top=strs[1];bottom=strs[3];left=strs[0];right=strs[2];
                isRes(res, strs, top, bottom, left, right);
                top=strs[1];bottom=strs[3];left=strs[2];right=strs[0];
                isRes(res, strs, top, bottom, left, right);

                top=strs[2];bottom=strs[3];left=strs[0];right=strs[1];
                isRes(res, strs, top, bottom, left, right);
                top=strs[2];bottom=strs[3];left=strs[1];right=strs[0];
                isRes(res, strs, top, bottom, left, right);
                top=strs[2];bottom=strs[0];left=strs[1];right=strs[3];
                isRes(res, strs, top, bottom, left, right);
                top=strs[2];bottom=strs[0];left=strs[3];right=strs[1];
                isRes(res, strs, top, bottom, left, right);
                top=strs[2];bottom=strs[1];left=strs[0];right=strs[3];
                isRes(res, strs, top, bottom, left, right);
                top=strs[2];bottom=strs[1];left=strs[3];right=strs[0];
                isRes(res, strs, top, bottom, left, right);

                top=strs[3];bottom=strs[0];left=strs[1];right=strs[2];
                isRes(res, strs, top, bottom, left, right);
                top=strs[3];bottom=strs[0];left=strs[2];right=strs[1];
                isRes(res, strs, top, bottom, left, right);
                top=strs[3];bottom=strs[1];left=strs[2];right=strs[0];
                isRes(res, strs, top, bottom, left, right);
                top=strs[3];bottom=strs[1];left=strs[0];right=strs[2];
                isRes(res, strs, top, bottom, left, right);
                top=strs[3];bottom=strs[2];left=strs[0];right=strs[1];
                isRes(res, strs, top, bottom, left, right);
                top=strs[3];bottom=strs[2];left=strs[1];right=strs[0];
                isRes(res, strs, top, bottom, left, right);

                j=0;
                leftI=rightI;
            }
            rightI++;j++;
        }
        res.sort(Comparator.comparing(list -> String.join("", list)));
        return res;
    }
    public void isRes(List<List<String>> res,String[] strs, String top, String bottom, String left, String right) {
        if (top.charAt(0)==left.charAt(0) && top.charAt(3)==right.charAt(0) && bottom.charAt(0)==left.charAt(3) && bottom.charAt(3)==right.charAt(3)){
            List<String> list= new ArrayList<>();
            list.add(top);
            list.add(left);
            list.add(right);
            list.add(bottom);
            res.add(list);
        }
    }

    //. 合并有序列表的最小成本 (没做出)
    public long minMergeCost(int[][] lists) {
        ArrayList<ArrayList<Integer>> lAL=new ArrayList<>();
        for (int[] ints : lists) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int anInt : ints) {
                list.add(anInt);
            }
            lAL.add(list);
        }
        int res=0;
        while (lAL.size()>1){
            ArrayList<Integer> a=lAL.remove(0);
            ArrayList<Integer> b=lAL.remove(0);
            HashSet<Integer> hsa=new HashSet<>(a);
            int lenA=a.size();
            for (int i=0;i<b.size();i++){
                if (hsa.contains(b.get(i))) {
                    b.remove(i);
                    i--;
                }
            }
            int lenB=b.size();
            int am=a.get(lenA%2==1?lenA/2:lenA/2-1);
            int index = lenB % 2 == 1 ? lenB / 2 : lenB / 2 - 1;
            int bm=b.get(index);
            int g=Math.abs(am-bm);
            res+=(lenA+lenB+g);
            a.addAll(b);
            Collections.sort(a);
            lAL.add(a);
            lAL.sort(Comparator.comparingInt(ArrayList::size));
        }
        return res;
    }


    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res=new ArrayList<>();
        List<String> path=new ArrayList<>();
        backWordSquares(res,path,0,words);
        res.sort(Comparator.comparing(list -> String.join("", list)));
        return res;
    }
    public void backWordSquares(List<List<String>> res,List<String> path,int index,String[] words){
        if (path.size()==4){
            char[] top= path.get(0).toCharArray(), left= path.get(1).toCharArray(), right= path.get(2).toCharArray(), bottom= path.get(3).toCharArray();
            if (top[0] == left[0]&& top[3] == right[0] &&
            bottom[0] == left[3]&& bottom[3] == right[3]){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i=index;i<words.length;i++){
            if (!path.isEmpty()&& Objects.equals(path.get(path.size()-1), words[i])) continue;
            path.add(words[i]);
            backWordSquares(res, path, i+1, words);
            path.remove(path.size()-1);
        }
    }





}
