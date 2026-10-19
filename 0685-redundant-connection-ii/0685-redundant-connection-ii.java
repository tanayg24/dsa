
class Solution {
    int[] parent;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parentNode = new int[n + 1];

        int[] edge1 = null;
        int[] edge2 = null;

        // Check whether a node has two parents
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (parentNode[v] == 0) {
                parentNode[v] = u;
            } else {
                edge1 = new int[]{parentNode[v], v};
                edge2 = edge;
                break;
            }
        }

        // Union-Find initialization
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Detect cycle, skipping the second parent edge if it exists
        for (int[] edge : edges) {
            if (edge == edge2) {
                continue;
            }

            int rootU = find(edge[0]);
            int rootV = find(edge[1]);

            if (rootU == rootV) {
                return edge1 == null ? edge : edge1;
            }

            parent[rootU] = rootV;
        }

        return edge2;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}
