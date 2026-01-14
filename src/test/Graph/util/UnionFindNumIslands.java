package test.Graph.util;

//200. 岛屿数量
public class UnionFindNumIslands{
    int[] parent;
    int[] rank;
    int count;

    public UnionFindNumIslands(char[][] grid) {
        int m=grid.length,n=grid[0].length;
        parent=new int[m*n];
        rank=new int[m*n];
        count=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]=='0') continue;
                int num = i*n+j;
                parent[num]=num;
                rank[num]=1;
                count++;
            }
        }
    }

    public int find(int n){
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
        count--;
        return true;
    }

    public boolean connect(int a,int b){
        return find(a)==find(b);
    }

    public int getCount() {
        return count;
    }
}
