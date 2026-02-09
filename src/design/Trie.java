package design;

import java.util.HashSet;

//208. 实现 Trie (前缀树)
class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (!cur.vis[id]){
                cur.vis[id] = true;
                cur.next[id] = new Node();
            }
            cur = cur.next[id];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (!cur.vis[id]) return false;
            cur = cur.next[id];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int id = prefix.charAt(i) - 'a';
            if (!cur.vis[id]) return false;
            cur = cur.next[id];
        }
        return true;
    }
}

class Node {
    boolean[] vis;
    Node[] next;
    boolean isEnd;

    public Node() {
        vis = new boolean[26];
        next = new Node[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */