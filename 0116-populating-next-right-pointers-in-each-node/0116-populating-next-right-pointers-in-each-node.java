
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node levelStart = root;

        while (levelStart.left != null) {
            Node current = levelStart;

            while (current != null) {
                // Connect left child to right child
                current.left.next = current.right;

                // Connect right child to next parent's left child
                if (current.next != null) {
                    current.right.next = current.next.left;
                }

                current = current.next;
            }

            levelStart = levelStart.left;
        }

        return root;
    }
}
