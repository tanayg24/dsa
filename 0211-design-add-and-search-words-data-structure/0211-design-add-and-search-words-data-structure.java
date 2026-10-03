class WordDictionary {

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode node, String word, int index) {

        if (node == null) {
            return false;
        }

        // Complete word
        if (index == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(index);

        // Normal character
        if (c != '.') {
            int childIndex = c - 'a';

            return dfs(
                node.children[childIndex],
                word,
                index + 1
            );
        }

        // '.' → try all characters
        for (int i = 0; i < 26; i++) {

            if (node.children[i] != null &&
                dfs(node.children[i], word, index + 1)) {
                return true;
            }
        }

        return false;
    }
}