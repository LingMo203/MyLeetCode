package test.Graph.util;

//684. 冗余连接
public class UnionFindFindRedundantConnection {
    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFindFindRedundantConnection(int n) {
        parent=new int[n];
        rank=new int[n];
        count=n;
        for (int i = 0; i < n; i++) {
            parent[i]=i;
            rank[i]=1;
        }
    }

    private int find(int n){
        if (parent[n]==n) return n;
        return parent[n]=find(parent[n]);
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

    public int getCount() {
        return count;
    }
}
