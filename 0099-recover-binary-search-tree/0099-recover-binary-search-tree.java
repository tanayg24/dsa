class Solution {
    public void recoverTree(TreeNode root) {

        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode current = root;

        while (current != null) {

            if (current.left == null) {

                // Visit current node
                if (prev != null && prev.val > current.val) {
                    if (first == null) {
                        first = prev;
                    }
                    second = current;
                }

                prev = current;
                current = current.right;

            } else {

                // Find inorder predecessor
                TreeNode predecessor = current.left;

                while (predecessor.right != null &&
                       predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {

                    // Create temporary thread
                    predecessor.right = current;
                    current = current.left;

                } else {

                    // Remove temporary thread
                    predecessor.right = null;

                    // Visit current node
                    if (prev != null && prev.val > current.val) {
                        if (first == null) {
                            first = prev;
                        }
                        second = current;
                    }

                    prev = current;
                    current = current.right;
                }
            }
        }

        // Swap the values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}