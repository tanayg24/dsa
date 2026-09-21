class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // Skip duplicate
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}