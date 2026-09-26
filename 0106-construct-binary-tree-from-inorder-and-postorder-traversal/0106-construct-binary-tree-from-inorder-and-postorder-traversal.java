import java.util.*;

class Solution {

    private Map<Integer, Integer> map;
    private int postorderIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        postorderIndex = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {

        if (left > right) {
            return null;
        }

        // Last element of postorder is root
        int rootValue = postorder[postorderIndex--];

        TreeNode root = new TreeNode(rootValue);

        int rootIndex = map.get(rootValue);

        // IMPORTANT: build right subtree first
        root.right = build(postorder, rootIndex + 1, right);

        // Then build left subtree
        root.left = build(postorder, left, rootIndex - 1);

        return root;
    }
}