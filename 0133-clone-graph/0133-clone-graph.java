import java.util.*;

class Solution {

    private Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        // If already cloned, return the existing clone
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create clone
        Node clone = new Node(node.val);

        // IMPORTANT: store before visiting neighbors
        map.put(node, clone);

        // Clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}