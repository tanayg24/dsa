import java.util.*;

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (int num : nums) {
            TreeNode curr = new TreeNode(num);

            while (!stack.isEmpty() && stack.peek().val < num) {
                curr.left = stack.pop();
            }

            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }

            stack.push(curr);
        }

        return stack.peekLast();
    }
}