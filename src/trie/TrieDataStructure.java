package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieDataStructure {
    public static void main(String args[])
    {
        TrieNode trie = new TrieNode();
        trie.insert("hello");
        trie.insert("hi");
        trie.insert("hell");

        System.out.println(trie);

        System.out.println(trie.find("hello"));
        System.out.println(trie.find("hi"));
        System.out.println(trie.find("hio"));
        System.out.println(trie.find("hell"));
    }
}

class TrieNode{
    private Map<Character, TrieNode> children;
    private boolean endOfWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.endOfWord = false;
    }

    public void insert(String word)
    {
        TrieNode current = this;
        for(char ch : word.toCharArray()){
            TrieNode node = current.children.getOrDefault(ch, new TrieNode());
            current.children.put(ch, node);
            current = node;
        }
        current.endOfWord = true;
    }

    public boolean find(String word)
    {
        TrieNode current = this;
        for(char ch : word.toCharArray()){
            TrieNode node = current.children.get(ch);
            if(node == null){
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }
}
