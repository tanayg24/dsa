
import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        pq.offer(new int[]{grid[0][0], 0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();

            int time = cell[0];
            int r = cell[1];
            int c = cell[2];

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;

            if (r == n - 1 && c == n - 1) {
                return time;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < n &&
                    !visited[nr][nc]) {

                    int nextTime = Math.max(time, grid[nr][nc]);
                    pq.offer(new int[]{nextTime, nr, nc});
                }
            }
        }

        return -1;
    }
}
