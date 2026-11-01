
class Solution {
    public Node connect(Node root) {
        Node currentLevel = root;

        while (currentLevel != null) {
            Node dummy = new Node(0);
            Node tail = dummy;

            while (currentLevel != null) {
                if (currentLevel.left != null) {
                    tail.next = currentLevel.left;
                    tail = tail.next;
                }

                if (currentLevel.right != null) {
                    tail.next = currentLevel.right;
                    tail = tail.next;
                }

                currentLevel = currentLevel.next;
            }

            currentLevel = dummy.next;
        }

        return root;
    }
}
