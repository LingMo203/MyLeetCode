package design;

//208. 实现 Trie (前缀树)
class Trie {
    private final Trie[] children;
    boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for(char c : word.toCharArray()) {
            int id = c - 'a';
            if (node.children[id] == null) {
                node.children[id] = new Trie();
            }
            node = node.children[id];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = this;
        for(char c : word.toCharArray()) {
            int id = c - 'a';
            if (node.children[id] == null) return false;
            node = node.children[id];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = this;
        for(char c : prefix.toCharArray()) {
            int id = c - 'a';
            if (node.children[id] == null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));	// 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
