package design;

import java.util.HashSet;

//208. 实现 Trie (前缀树)
class Trie {

    HashSet<String> wordSet;
    HashSet<String> prefixSet;

    public Trie() {
        wordSet=new HashSet<>();
        prefixSet=new HashSet<>();
    }

    public void insert(String word) {
        wordSet.add(word);
        StringBuilder sb=new StringBuilder(word);
        while (!sb.isEmpty()){
            prefixSet.add(sb.toString());
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public boolean search(String word) {
        return wordSet.contains(word);
    }

    public boolean startsWith(String prefix) {
        return prefixSet.contains(prefix);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */