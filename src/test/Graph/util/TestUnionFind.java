package test.Graph.util;

import java.util.Arrays;

public class TestUnionFind {
    public static void main(String[] args) {
        // 测试1：基本功能
        UnionFind uf = new UnionFind(5);
        System.out.println("初始状态:");
        System.out.println("Count: " + uf.getCount());  // 5

        // 合并测试
        uf.union(0, 1);
        System.out.println("合并(0,1)后 Count: " + uf.getCount());  // 4
        System.out.println("0和1是否连通: " + uf.connect(0, 1));     // true

        uf.union(1, 2);
        System.out.println("合并(1,2)后 Count: " + uf.getCount());  // 3
        System.out.println("0和2是否连通: " + uf.connect(0, 2));     // true

        uf.union(3, 4);
        System.out.println("合并(3,4)后 Count: " + uf.getCount());  // 2

        // 测试不同集合
        System.out.println("0和3是否连通: " + uf.connect(0, 3));     // false

        // 合并两个集合
        uf.union(2, 3);
        System.out.println("合并(2,3)后 Count: " + uf.getCount());  // 1
        System.out.println("0和4是否连通: " + uf.connect(0, 4));     // true

        // 测试2：按秩合并验证
        UnionFind uf2 = new UnionFind(4);
        System.out.println("\n按秩合并测试:");

        // 初始rank都是1
        uf2.union(0, 1);  // rank[0]和rank[1]都是1，合并后rank[0]变为2
        uf2.union(2, 3);  // rank[2]和rank[3]都是1，合并后rank[2]变为2

        // 现在合并两个高度为2的树
        uf2.union(0, 2);  // rank[0]和rank[2]都是2，合并后rank[0]变为3

        System.out.println("最终连通分量数: " + uf2.getCount());  // 1
    }
}
