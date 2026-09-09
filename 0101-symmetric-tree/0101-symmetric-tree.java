class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {

        // Both are null
        if (left == null && right == null) {
            return true;
        }

        // Only one is null
        if (left == null || right == null) {
            return false;
        }

        // Values must be equal
        if (left.val != right.val) {
            return false;
        }

        // Cross comparison
        return isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
}