import java.util.*;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // First leaf found = minimum depth
                if (current.left == null && current.right == null) {
                    return depth;
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            depth++;
        }

        return depth;
    }
}