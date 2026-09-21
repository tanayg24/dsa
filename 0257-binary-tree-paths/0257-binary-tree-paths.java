import java.util.*;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        dfs(root, "", result);

        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) {
            return;
        }

        if (path.isEmpty()) {
            path = String.valueOf(node.val);
        } else {
            path += "->" + node.val;
        }

        // Leaf node
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }
}