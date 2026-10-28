
import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countPaths(int n, int[][] roads) {
        List<long[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];

            graph[u].add(new long[]{v, time});
            graph[v].add(new long[]{u, time});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        long[] ways = new long[n];

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        dist[0] = 0;
        ways[0] = 1;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            int node = (int) curr[0];
            long time = curr[1];

            if (time > dist[node]) {
                continue;
            }

            for (long[] edge : graph[node]) {
                int next = (int) edge[0];
                long travelTime = edge[1];

                long newTime = time + travelTime;

                if (newTime < dist[next]) {
                    dist[next] = newTime;
                    ways[next] = ways[node];

                    pq.offer(new long[]{next, newTime});
                } else if (newTime == dist[next]) {
                    ways[next] = (ways[next] + ways[node]) % MOD;
                }
            }
        }

        return (int) ways[n - 1];
    }
}
