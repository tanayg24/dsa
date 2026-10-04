class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        // 0 = unvisited
        // 1 = visiting
        // 2 = safe
        int[] state = new int[n];

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (isSafe(graph, i, state)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isSafe(int[][] graph, int node, int[] state) {

        // Currently being explored → cycle
        if (state[node] == 1) {
            return false;
        }

        // Already proven safe
        if (state[node] == 2) {
            return true;
        }

        // Mark as currently visiting
        state[node] = 1;

        // Every neighbour must be safe
        for (int next : graph[node]) {

            if (!isSafe(graph, next, state)) {
                return false;
            }
        }

        // All paths from this node are safe
        state[node] = 2;

        return true;
    }
}