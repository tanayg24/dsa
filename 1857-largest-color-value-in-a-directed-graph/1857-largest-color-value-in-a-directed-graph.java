
import java.util.*;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            indegree[edge[1]]++;
        }

        // dp[node][color] = maximum count of that color
        // on a path ending at this node
        int[][] dp = new int[n][26];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processed = 0;
        int answer = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;

            int colorIndex = colors.charAt(node) - 'a';

            // Include the current node's color
            dp[node][colorIndex]++;

            answer = Math.max(answer, dp[node][colorIndex]);

            for (int neighbor : graph[node]) {

                for (int c = 0; c < 26; c++) {
                    dp[neighbor][c] = Math.max(
                        dp[neighbor][c], dp[node][c]
                    );
                }

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If not all nodes were processed, graph has a cycle
        return processed == n ? answer : -1;
    }
}
