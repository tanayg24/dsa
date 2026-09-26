class Solution {

    public TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // Find middle node
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect left half from middle
        prev.next = null;

        // Middle becomes root
        TreeNode root = new TreeNode(slow.val);

        // Left subtree
        root.left = sortedListToBST(head);

        // Right subtree
        root.right = sortedListToBST(slow.next);

        return root;
    }
}