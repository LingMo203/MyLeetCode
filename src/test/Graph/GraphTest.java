package test.Graph;

import java.util.*;

public class GraphTest {
    public static void main(String[] args) {
        GraphTest gt=new GraphTest();
        int[][] grid={{1, 1, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0}};
        char[][] chars = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        //System.out.println(gt.findJudge(2,numss));
        //System.out.println(gt.allPathsSourceTarget(numss));
        //System.out.println(gt.numIslands(chars));
        System.out.println(Arrays.deepToString(gt.findFarmland(grid)));
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
        //int res=bfsNumIslands(grid);
        int res=numIslands2(grid);
        return res;
    }
    //BFS
    public int bfsNumIslands(char[][] grid){
        int res=0,m=grid.length,n=grid[0].length;
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
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
    //DFS
    public int numIslands2(char[][] grid){
        int res=0,m=grid.length,n=grid[0].length;
        boolean[][] already =new boolean[m][n];
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        for (int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if (already[i][j]) continue;
                already[i][j]=true;
                if (grid[i][j]=='0') continue;
                dfsNumIslands(grid,already,i,j,direction);
                res++;
            }
        }
        return res;
    }
    public void dfsNumIslands(char[][] grid,boolean[][] already,int x,int y,final int[][] direction){
        int m=grid.length,n=grid[0].length;
        for (int[] dir:direction){
            int nextX=x+dir[0];
            int nextY=y+dir[1];
            if ((nextX<0||nextX>=m) || (nextY<0||nextY>=n) || already[nextX][nextY]) continue;
            already[nextX][nextY]=true;
            if (grid[nextX][nextY]=='0') continue;
            dfsNumIslands(grid, already, nextX, nextY, direction);
        }
    }


    //695. 岛屿的最大面积
    public int maxAreaOfIsland(int[][] grid) {
        //return bfsMaxAreaOfIsland(grid);
        return maxAreaOfIsland2(grid);
    }
    //BFS
    public int bfsMaxAreaOfIsland(int[][] grid){
        int res=0,m=grid.length,n=grid[0].length;
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        boolean[][] already =new boolean[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (already[i][j]) continue;
                if (grid[i][j]==0){
                    already[i][j]=true;
                    continue;
                }
                Deque<int[]> deque=new ArrayDeque<>();
                deque.addLast(new int[]{i,j});
                already[i][j]=true;
                int s=0;
                while (!deque.isEmpty()){
                    int[] remove=deque.removeFirst();
                    int x=remove[0],y=remove[1];
                    s++;
                    for (int[] add:direction){
                        int nextX=x+add[0];
                        int nextY=y+add[1];
                        if ((nextX<0||nextX>=m) || (nextY<0||nextY>=n) || already[nextX][nextY]) continue;
                        if (grid[nextX][nextY]==0){
                            already[nextX][nextY]=true;
                            continue;
                        }
                        deque.addLast(new int[]{nextX,nextY});
                        already[nextX][nextY]=true;
                    }
                }
                res=Math.max(res,s);
            }
        }
        return res;
    }
    //DFS
    public int maxAreaOfIsland2(int[][] grid){
        int res=0,m=grid.length,n=grid[0].length;
        boolean[][] already =new boolean[m][n];
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        for (int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if (already[i][j]) continue;
                already[i][j]=true;
                if (grid[i][j]==0) continue;
                int[] s={0};
                dfsMaxAreaOfIsland(grid,already,i,j,direction,s);
                res=Math.max(res,s[0]);
            }
        }
        return res;
    }
    public void dfsMaxAreaOfIsland(int[][] grid,boolean[][] already,int x,int y,final int[][] direction,int[] s){
        int m=grid.length,n=grid[0].length;
        s[0]++;
        for (int[] dir:direction){
            int nextX=x+dir[0];
            int nextY=y+dir[1];
            if ((nextX<0||nextX>=m) || (nextY<0||nextY>=n) || already[nextX][nextY]) continue;
            already[nextX][nextY]=true;
            if (grid[nextX][nextY]==0) continue;
            dfsMaxAreaOfIsland(grid, already, nextX, nextY, direction,s);
        }
    }


    //3619. 总价值可以被 K 整除的岛屿数目
    public int countIslands(int[][] grid, int k) {
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        int res=0,m=grid.length,n=grid[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==0) continue;
                Deque<int[]> deque=new ArrayDeque<>();
                long cost=0;
                deque.addLast(new int[]{i,j});
                while (!deque.isEmpty()){
                    int[] remove = deque.removeFirst();
                    int x=remove[0],y=remove[1];
                    if (grid[x][y]==0) continue;
                    cost+=grid[x][y];
                    grid[x][y]=0;
                    for (int[] dir:direction){
                        int nextX=x+dir[0];
                        int nextY=y+dir[1];
                        if ((nextX<0 || nextX>=m) || (nextY<0 || nextY>=n) || grid[nextX][nextY]==0) continue;
                        deque.addLast(new int[]{nextX,nextY});
                    }
                }
                if (cost%k==0) res++;
            }
        }
        return res;
    }


    //1905. 统计子岛屿
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        int res=0,m=grid2.length,n=grid2[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid2[i][j]==0) continue;
                Deque<int[]> deque=new ArrayDeque<>();
                deque.addLast(new int[]{i,j});
                grid2[i][j]=0;
                boolean f= grid1[i][j] != 0;
                while (!deque.isEmpty()){
                    int[] remove=deque.removeFirst();
                    int x=remove[0],y=remove[1];
                    if (grid1[x][y]==0) f=false;
                    for (int[] dir : direction){
                        int nextX=x+dir[0],nextY=y+dir[1];
                        if ((nextX<0 || nextX>=m) || (nextY<0 || nextY>=n) || grid2[nextX][nextY]==0) continue;
                        deque.addLast(new int[]{nextX,nextY});
                        grid2[nextX][nextY]=0;
                    }
                }
                if (f) res++;
            }
        }
        return res;
    }


    //1992. 找到所有的农场组
    public int[][] findFarmland(int[][] land) {
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//左 上 右 下
        ArrayList<int[]> res=new ArrayList<>();
        int m=land.length,n=land[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (land[i][j]==0) continue;
                Deque<int[]> deque=new ArrayDeque<>();
                deque.addLast(new int[]{i,j});
                land[i][j]=0;
                int[] re=new int[4];
                re[0]=i;re[1]=j;
                re[2]=i;re[3]=j;
                while (!deque.isEmpty()){
                    int[] remove=deque.removeFirst();
                    int x=remove[0],y=remove[1];
                    if (x>re[2]||y>re[3]){
                        re[2]=x;re[3]=y;
                    }
                    for (int[] dir:direction){
                        int nextX=x+dir[0],nextY=y+dir[1];
                        if (nextX<0||nextX>=m||nextY<0||nextY>=n||land[nextX][nextY]==0) continue;
                        deque.addLast(new int[]{nextX,nextY});
                        land[nextX][nextY]=0;
                    }
                }
                res.add(re);
            }
        }
        return res.toArray(new int[0][]);
    }


    //994. 腐烂的橘子
    public int orangesRotting(int[][] grid) {
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//四个方向 左 上 右 下
        //res=-1 所有初始腐烂橘子已经在队列中 第一次循环处理的是第0分钟的腐烂橘子
        int res=-1,m=grid.length,n=grid[0].length;
        Deque<int[]> deque=new ArrayDeque<>(); //队列内数组长度为2 记录i坐标 j坐标
        int fresh=0;//新鲜橘子的个数
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==2) {
                    deque.addLast(new int[]{i, j});//遇到腐烂的橘子添加至队列
                    grid[i][j]=0;    //标记为已访问
                }else if (grid[i][j]==1) fresh++; //统计新鲜橘子
            }
        }
        while (!deque.isEmpty()){   //当队列为空时代表感染结束
            int size=deque.size();  //记录当前层腐烂的橘子的个数
            while (size-->0){   //处理当前层腐烂的橘子
                int[] remove=deque.removeFirst();
                int x=remove[0],y=remove[1];    //获得当前腐烂的橘子的坐标
                for (int[] dir:direction){  //向四个方向 左 上 右 下 感染新鲜橘子
                    int nextX=x+dir[0],nextY=y+dir[1];  //获得下一个方向坐标
                    //当坐标出界 或者 已经遍历过或者空位置 跳过
                    if (nextX<0||nextX>=m||nextY<0||nextY>=n||grid[nextX][nextY]==0) continue;
                    deque.addLast(new int[]{nextX,nextY});  //将感染的橘子添加至队列
                    grid[nextX][nextY]=0;   //标记为已访问
                    fresh--;    //感染时减少
                }
            }
            res++;  //当前层向外扩展了一层  第一次循环处理的是第0分钟的腐烂橘子
        }
        if (fresh>0) return -1; //如果还有腐烂的橘子返回-1
        return Math.max(0,res); //如果初始就没有腐烂橘子返回0
    }


    //133. 克隆图
    public Node cloneGraph(Node node) {
        if (node==null) return null;
        HashMap<Node,Node> hashMap=new HashMap<>();
        Node newNode=new Node(node.val);
        hashMap.put(node,newNode);
        Deque<Node> deque=new ArrayDeque<>();
        deque.addLast(node);
        while (!deque.isEmpty()){
            Node remove=deque.removeFirst();
            for (Node temp: remove.neighbors){
                if (hashMap.containsKey(temp)) continue;
                Node temp2 =new Node(temp.val);
                hashMap.put(temp, temp2);
                deque.addLast(temp);
            }
        }
        deque.addLast(node);
        HashSet<Node> hashSet=new HashSet<>();
        hashSet.add(node);
        while (!deque.isEmpty()){
            Node removeOld=deque.removeFirst();
            Node removeNew=hashMap.get(removeOld);
            for (Node temp: removeOld.neighbors){
                removeNew.neighbors.add(hashMap.get(temp));
                if (hashSet.contains(temp)) continue;
                deque.addLast(temp);
                hashSet.add(temp);
            }
        }
        return newNode;
    }














}
