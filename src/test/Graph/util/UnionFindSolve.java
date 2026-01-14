package test.Graph.util;

//130. 被围绕的区域
public class UnionFindSolve {
    int[] parent;
    int[] rank;

    public UnionFindSolve(char[][] board) {
        int m=board.length,n=board[0].length;
        parent=new int[m*n];
        rank=new int[m*n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num=i*n+j;
                if (i==0||j==0||i==m-1||j==n-1) {
                    parent[num]=0;
                }else {
                    if (board[i][j] == 'X') continue;
                    parent[num] = num;
                    rank[num] = 1;
                }
            }
        }
    }

    private int find(int n){
        if (parent[n]==n) return n;
        parent[n]=find(parent[n]);
        return parent[n];
    }

    public boolean union(int a,int b){
        a=find(a);
        b=find(b);
        if (a==b) return false;
        if (rank[a]<rank[b]) parent[a]=b;
        else if (rank[a]>rank[b]) parent[b]=a;
        else {
            parent[a]=b;
            rank[b]++;
        }
        return true;
    }

    public boolean connect(int a,int b){
        return find(a)==find(b);
    }
}
