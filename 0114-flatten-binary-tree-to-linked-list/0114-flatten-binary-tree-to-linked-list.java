class Solution {
    public void flatten(TreeNode root) {

        TreeNode current = root;

        while (current != null) {

            if (current.left != null) {

                // Find the rightmost node of left subtree
                TreeNode predecessor = current.left;

                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // Connect left subtree to original right subtree
                predecessor.right = current.right;

                // Move left subtree to right
                current.right = current.left;

                // Left must become null
                current.left = null;
            }

            // Move to next node
            current = current.right;
        }
    }
}