import java.util.*;

class Solution {

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    private TrieNode root;
    private List<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        result = new ArrayList<>();

        // Build Trie
        for (String word : words) {
            insert(word);
        }

        int rows = board.length;
        int cols = board[0].length;

        // Start DFS from every cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root);
            }
        }

        return result;
    }

    private void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.word = word;
    }

    private void dfs(char[][] board, int row, int col, TrieNode node) {

        // Out of bounds
        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length) {
            return;
        }

        char ch = board[row][col];

        // Already visited
        if (ch == '#') {
            return;
        }

        TrieNode next = node.children[ch - 'a'];

        // Character not present in Trie
        if (next == null) {
            return;
        }

        // Word found
        if (next.word != null) {
            result.add(next.word);

            // Prevent duplicate result
            next.word = null;
        }

        // Mark visited
        board[row][col] = '#';

        // Up
        dfs(board, row - 1, col, next);

        // Down
        dfs(board, row + 1, col, next);

        // Left
        dfs(board, row, col - 1, next);

        // Right
        dfs(board, row, col + 1, next);

        // Restore cell
        board[row][col] = ch;
    }
}