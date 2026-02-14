package test.Graph;

import test.Graph.util.*;
import util.ArrayStringUtils;

import java.util.*;

import static util.ArrayStringUtils.parse2DCharArraySmart;

public class GraphTest {
    public static void main(String[] args) {
        GraphTest gt=new GraphTest();
        String strGrid="[[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]";
        int[][] grid= ArrayStringUtils.parse2DIntArray(strGrid);
        String charInput2 = "[[\"X\",\"X\",\"X\",\"X\"],[\"X\",\"O\",\"O\",\"X\"],[\"X\",\"X\",\"O\",\"X\"],[\"X\",\"O\",\"X\",\"X\"]]";
        char[][] charArray2 = parse2DCharArraySmart(charInput2);
        //System.out.println(gt.findJudge(2,numss));
        //System.out.println(gt.allPathsSourceTarget(numss));
        //System.out.println(gt.numIslands(charArray2));
        //System.out.println(Arrays.deepToString(gt.findFarmland(grid)));
        //System.out.println(gt.minimumEffortPath(grid));
        //System.out.println(gt.findCircleNum(grid));
        gt.solve2(charArray2);
        System.out.println(Arrays.deepToString(charArray2));
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
        //return bfsNumIslands(grid);  //BFS
        //return numIslands2(grid);   //DFS
        return numIslands3(grid);   //并查集
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
    //并查集
    public int numIslands3(char[][] grid) {
        int m=grid.length,n=grid[0].length;
        UnionFindNumIslands uf=new UnionFindNumIslands(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]=='0') continue;
                int num=i*n+j;
                if (i>0&&grid[i-1][j]=='1'){
                    int top=num-n;
                    uf.union(num,top);
                }
                if (j>0&&grid[i][j-1]=='1'){
                    int left=num-1;
                    uf.union(num,left);
                }
            }
        }
        return uf.getCount();
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


    //1254. 统计封闭岛屿的数目
    public int closedIsland(int[][] grid) {
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//四个方向 左 上 右 下
        int res=0,m=grid.length,n=grid[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==1) continue;
                Deque<int[]> deque=new ArrayDeque<>();
                deque.addLast(new int[]{i,j});
                grid[i][j]=1;
                boolean f=true;
                while (!deque.isEmpty()){
                    int[] remove=deque.removeFirst();
                    int x=remove[0],y=remove[1];
                    for (int[] dir:direction){
                        int nextX=x+dir[0],nextY=y+dir[1];
                        if (nextX<0 || nextX>=m || nextY<0 || nextY>=n){
                            f=false;
                            continue;
                        }
                        if (grid[nextX][nextY]==1) continue;
                        deque.addLast(new int[]{nextX,nextY});
                        grid[nextX][nextY]=1;
                    }
                }
                if (f) res++;
            }
        }
        return res;
    }


    //547. 省份数量
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        UnionFind uf=new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j]==0) continue;
                uf.union(i,j);
            }
        }
        return uf.getCount();
    }


    //130. 被围绕的区域
    public void solve(char[][] board){
        int m=board.length,n=board[0].length;
        boolean[][] visited=new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]=='X'||visited[i][j]) continue;
                boolean[] f={true};
                ArrayList<int[]> path=new ArrayList<>();
                visited[i][j]=true;
                path.add(new int[]{i,j});
                dfsSolve(board,path,visited,f,i,j);
                if (f[0]){
                    for (int[] pa:path){
                        board[pa[0]][pa[1]]='X';
                    }
                }
            }
        }
    }
    //DFS
    public void dfsSolve(char[][] board,ArrayList<int[]> path,boolean[][] visited,boolean[] f,int x,int y){
        final int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};//四个方向 左 上 右 下
        for (int[] dir:direction){
            int nextX=x+dir[0],nextY=y+dir[1];
            if (nextX<0||nextX>=board.length||nextY<0||nextY>=board[0].length){
                f[0]=false;
                continue;
            }
            if (board[nextX][nextY]=='X'||visited[nextX][nextY]) continue;
            path.add(new int[]{nextX,nextY});
            visited[nextX][nextY]=true;
            dfsSolve(board, path, visited, f, nextX, nextY);
        }
    }
    //并查集
    public void solve2(char[][] board) {
        int m=board.length,n=board[0].length;
        UnionFindSolve uf=new UnionFindSolve(board);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]=='X') continue;
                int num=i*n+j;
                if (i>0&&board[i-1][j]=='O'){
                    uf.union(num-n,num);
                }
                if (j>0&&board[i][j-1]=='O'){
                    uf.union(num-1,num);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]=='X') continue;
                if (!uf.connect(0,i*n+j)){
                    board[i][j]='X';
                }
            }
        }
    }


    //684. 冗余连接
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        UnionFindFindRedundantConnection uf=new UnionFindFindRedundantConnection(n);
        int[] res=new int[2];
        for (int[] edge:edges){
            int a=edge[0]-1,b=edge[1]-1;
            if (!uf.union(a,b)){
                res[0]=a+1;res[1]=b+1;
            }
        }
        return res;
    }


    //1319. 连通网络的操作次数
    public int makeConnected(int n, int[][] connections) {
        UnionFindMakeConnected uf=new UnionFindMakeConnected(n);
        int c=0;
        for (int[] nums:connections){
            if (!uf.union(nums[0],nums[1])) c++;
        }
        return (uf.getCount()-1)>c?-1: uf.getCount()-1;
    }


    //1926. 迷宫中离入口最近的出口
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length, step = 0;
        final int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};//左 上 右 下
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(entrance);
        maze[entrance[0]][entrance[1]] = '+';
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                int[] remove = deque.removeFirst();
                int x = remove[0], y = remove[1];
                for (int[] dir : direction) {
                    int nextX = x + dir[0], nextY = y + dir[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                        if (x != entrance[0] || y != entrance[1]) return step;
                        continue;
                    }
                    if (maze[nextX][nextY] == '+') continue;
                    deque.addLast(new int[]{nextX, nextY});
                    maze[nextX][nextY] = '+';
                }
            }
            step++;
        }
        return -1;
    }









}






























