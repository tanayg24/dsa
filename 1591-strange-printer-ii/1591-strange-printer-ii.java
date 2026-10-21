import java.util.*;

class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;

        int[] minRow = new int[61];
        int[] maxRow = new int[61];
        int[] minCol = new int[61];
        int[] maxCol = new int[61];

        Arrays.fill(minRow, m);
        Arrays.fill(minCol, n);
        Arrays.fill(maxRow, -1);
        Arrays.fill(maxCol, -1);

        // Find the bounding rectangle of each color
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j];

                minRow[color] = Math.min(minRow[color], i);
                maxRow[color] = Math.max(maxRow[color], i);
                minCol[color] = Math.min(minCol[color], j);
                maxCol[color] = Math.max(maxCol[color], j);
            }
        }

        // Build dependency graph
        List<Set<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[61];

        for (int i = 0; i <= 60; i++) {
            graph.add(new HashSet<>());
        }

        for (int color = 1; color <= 60; color++) {
            if (maxRow[color] == -1) {
                continue;
            }

            for (int i = minRow[color]; i <= maxRow[color]; i++) {
                for (int j = minCol[color]; j <= maxCol[color]; j++) {
                    int other = targetGrid[i][j];

                    if (other != color && graph.get(color).add(other)) {
                        indegree[other]++;
                    }
                }
            }
        }

        // Topological sort
        Queue<Integer> queue = new LinkedList<>();
        int totalColors = 0;

        for (int color = 1; color <= 60; color++) {
            if (maxRow[color] != -1) {
                totalColors++;

                if (indegree[color] == 0) {
                    queue.offer(color);
                }
            }
        }

        int printed = 0;

        while (!queue.isEmpty()) {
            int color = queue.poll();
            printed++;

            for (int next : graph.get(color)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return printed == totalColors;
    }
}