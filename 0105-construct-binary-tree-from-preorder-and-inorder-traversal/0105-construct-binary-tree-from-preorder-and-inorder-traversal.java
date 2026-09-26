import java.util.*;

class Solution {

    private Map<Integer, Integer> map;
    private int preorderIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        preorderIndex = 0;

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {

        if (left > right) {
            return null;
        }

        // First element of preorder is root
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int rootIndex = map.get(rootValue);

        // Build left subtree first
        root.left = build(preorder, left, rootIndex - 1);

        // Then build right subtree
        root.right = build(preorder, rootIndex + 1, right);

        return root;
    }
}