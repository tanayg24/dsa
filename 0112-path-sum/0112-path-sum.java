class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // Leaf node
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        int remaining = targetSum - root.val;

        return hasPathSum(root.left, remaining)
            || hasPathSum(root.right, remaining);
    }
}