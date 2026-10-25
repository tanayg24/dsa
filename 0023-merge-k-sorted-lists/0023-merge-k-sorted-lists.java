import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();

            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                pq.offer(smallest.next);
            }
        }

        current.next = null;
        return dummy.next;
    }
}