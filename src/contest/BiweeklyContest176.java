package contest;

import util.ArrayStringUtils;

import java.util.*;

//第 176 场双周赛
public class BiweeklyContest176 {

    public static void main(String[] args) {
        BiweeklyContest176 t = new BiweeklyContest176();
        int[] nums = {2, 2, 3, 1, 4, 5, 1, 1, 2};
        String str1 = "fusion";
        String str2 = "layout";
        String[] strs = {"query 0 2", "update 1 b", "query 0 2"};
        String strGrid = "[[0,1],[1,2]]";
        int[][] grid = ArrayStringUtils.parse2DIntArray(strGrid);
        //System.out.println(t.prefixConnected(strs, 2));
//        StringBuilder sb = new StringBuilder("abcdefg");
//        sb.replace(2, 3, "p");
//        System.out.println(sb);
        System.out.println(t.palindromePath(3, grid, "aac", strs));
    }

    //100987. 带权单词映射
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for (String str : words) {
            int add = 0;
            for (char c : str.toCharArray()) {
                add += weights[c - 'a'];
            }
            char b = (char) ('z' - (add % 26));
            sb.append(b);
        }
        return sb.toString();
    }

    //100912. 前缀连接组的数目
    public int prefixConnected(String[] words, int k) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String str : words) {
            if (str.length() < k) continue;
            String st = str.substring(0, k);
            hashMap.put(st, hashMap.getOrDefault(st, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() <= 1) continue;
            res++;
        }
        return res;
    }

    //100980. 查询树上回文路径(超时)
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Boolean> res = new ArrayList<>();
        UnionFind uf = new UnionFind(n);
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            uf.union(u, v);
            graph[u].add(v);
            graph[v].add(u);
        }
        StringBuilder sb = new StringBuilder(s);
        for (String str : queries) {
            String[] using = str.split(" ");
            String h = using[0];
            if (h.equals("query")) {
                int u = Integer.parseInt(using[1]), v = Integer.parseInt(using[2]);
                HashMap<Character, Integer> hashMap = new HashMap<>();
                int[] prev = new int[n];
                Arrays.fill(prev, -1);
                boolean[] vis = new boolean[n];
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(u);
                vis[u] = true;
                while (!queue.isEmpty()) {
                    int remove = queue.poll();
                    if (remove == v) break;
                    for (int next : graph[remove]) {
                        if (!vis[next]) {
                            vis[next] = true;
                            prev[next] = remove;
                            queue.offer(next);
                        }
                    }
                }
                int node = v;
                while (node != -1) {
                    char c = sb.charAt(node);
                    hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
                    node = prev[node];
                }
                boolean f = false, t = true;
                int count=0;
                for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
                    count+=entry.getValue();
                    if (entry.getValue() % 2 == 1) {
                        if (!f) {
                            f = true;
                        } else {
                            t = false;
                            break;
                        }
                    }
                }
                if (count%2==0 && f) t =false;
                res.add(t);
            } else if (h.equals("update")) {
                int id = Integer.parseInt(using[1]);
                sb.replace(id, id + 1, using[2]);
            }
        }
        return res;
    }

}
class UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int n) {
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























