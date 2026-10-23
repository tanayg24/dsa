import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        // Step 1: Dijkstra from node n
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[1], b[1])
        );

        pq.offer(new long[]{n, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long d = cur[1];

            if (d != dist[u]) continue;

            for (int[] e : graph[u]) {
                int v = e[0];
                long nd = d + e[1];

                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{v, nd});
                }
            }
        }

        // Step 2: Sort by increasing distance
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i + 1;
        }

        Arrays.sort(order, (a, b) -> Long.compare(dist[a], dist[b]));

        // Step 3: Dynamic programming
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int u : order) {
            if (u == n) continue;

            long ways = 0;

            for (int[] e : graph[u]) {
                int v = e[0];

                // Restricted path must move to a smaller distance
                if (dist[v] < dist[u]) {
                    ways = (ways + dp[v]) % MOD;
                }
            }

            dp[u] = (int) ways;
        }

        return dp[1];
    }
}