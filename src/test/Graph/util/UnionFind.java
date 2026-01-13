package test.Graph.util;

public class UnionFind {
    int[] parent;
    int[] rank;
    int count;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int n) {
        if (parent[n] == n) return parent[n];
        parent[n] = find(parent[n]);
        return parent[n];
    }

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        if (rank[a] > rank[b]) parent[b] = a;
        else if (rank[a] < rank[b]) parent[a] = b;
        else {
            parent[b] = a;
            rank[a]++;
        }
        count--;
        return true;
    }

    public boolean connect(int a, int b) {
        return find(a) == find(b);
    }

    public int getCount() {
        return count;
    }
}
