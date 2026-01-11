package test.Graph;

import java.util.*;

public class GraphTest {
    public static void main(String[] args) {
        GraphTest gt=new GraphTest();
        int[][] numss={{1, 2}, {3}, {3}, {}};
        char[][] chars = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        //System.out.println(gt.findJudge(2,numss));
        //System.out.println(gt.allPathsSourceTarget(numss));
        System.out.println(gt.numIslands(chars));
    }


    //997. 找到小镇的法官
    public int findJudge(int n, int[][] trust) {
        if (n==1) return 1;
        HashMap<Integer, HashSet<Integer>> hashMap=new HashMap<>();
        HashSet<Integer> hashSet=new HashSet<>();
        for (int[] nums:trust){
            int beTrust=nums[1];
            int trustOther=nums[0];
            hashSet.add(trustOther);
            if (!hashMap.containsKey(beTrust)){
                HashSet<Integer> hashSet2=new HashSet<>();
                hashSet2.add(trustOther);
                hashMap.put(beTrust,hashSet2);
            }else {
                hashMap.get(beTrust).add(trustOther);
            }
        }
        for (Map.Entry<Integer, HashSet<Integer>> map: hashMap.entrySet()){
            if (map.getValue().size()==n-1&&!hashSet.contains(map.getKey())){
                return map.getKey();
            }
        }
        return -1;
    }


    //797. 所有可能的路径
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        path.add(0);
        dfsAllPathsSourceTarget(res,path,graph,0);
        return res;
    }
    public void dfsAllPathsSourceTarget(List<List<Integer>> res,List<Integer> path,int[][] graph,int now){
        if (now==graph.length-1){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int num:graph[now]){
            path.add(num);
            dfsAllPathsSourceTarget(res, path, graph, num);
            path.remove(path.size()-1);
        }
    }


    //200. 岛屿数量
    public int numIslands(char[][] grid) {
        int res=bfsNumIslands(grid);
        return res;
    }
    public int bfsNumIslands(char[][] grid){
        int res=0,m=grid.length,n=grid[0].length;
        int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        boolean[][] already =new boolean[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (already[i][j]) continue;
                if (grid[i][j]=='0'){
                    already[i][j]=true;
                    continue;
                }
                Deque<int[]> deque=new ArrayDeque<>();
                deque.addLast(new int[]{i,j});
                already[i][j]=true;
                while (!deque.isEmpty()){
                    int[] remove=deque.removeFirst();
                    int x=remove[0],y=remove[1];
                    for (int[] add:direction){
                        int nextX=x+add[0];
                        int nextY=y+add[1];
                        if ((nextX<0||nextX>=m) || (nextY<0||nextY>=n) || already[nextX][nextY]) continue;
                        if (grid[nextX][nextY]=='0'){
                            already[nextX][nextY]=true;
                            continue;
                        }
                        deque.addLast(new int[]{nextX,nextY});
                        already[nextX][nextY]=true;
                    }
                }
                res++;
            }
        }
        return res;
    }

























}
