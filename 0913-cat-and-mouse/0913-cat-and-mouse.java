import java.util.*;

class Solution {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        // 0 = draw/unknown, 1 = mouse wins, 2 = cat wins
        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];

        // Count legal moves from each state
        for (int m = 0; m < n; m++) {
            for (int c = 1; c < n; c++) {
                degree[m][c][0] = graph[m].length;
                degree[m][c][1] = graph[c].length;

                // Cat cannot move to hole 0
                for (int next : graph[c]) {
                    if (next == 0) {
                        degree[m][c][1]--;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        // Terminal states: mouse reaches hole
        for (int c = 1; c < n; c++) {
            for (int turn = 0; turn < 2; turn++) {
                color[0][c][turn] = 1;
                queue.offer(new int[]{0, c, turn, 1});
            }
        }

        // Terminal states: cat catches mouse
        for (int i = 1; i < n; i++) {
            for (int turn = 0; turn < 2; turn++) {
                color[i][i][turn] = 2;
                queue.offer(new int[]{i, i, turn, 2});
            }
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();

            int m = state[0];
            int c = state[1];
            int turn = state[2];
            int winner = state[3];

            // Find states that could move into this state
            if (turn == 0) {
                // Previous move was by cat
                for (int prevC : graph[c]) {
                    if (prevC == 0) continue;

                    process(
                        queue, color, degree,
                        m, prevC, 1, winner
                    );
                }
            } else {
                // Previous move was by mouse
                for (int prevM : graph[m]) {
                    process(
                        queue, color, degree,
                        prevM, c, 0, winner
                    );
                }
            }
        }

        return color[1][2][0];
    }

    private void process(
        Queue<int[]> queue,
        int[][][] color,
        int[][][] degree,
        int m,
        int c,
        int turn,
        int winner
    ) {
        if (color[m][c][turn] != 0) {
            return;
        }

        // The player whose turn it is can choose a winning move
        if ((turn == 0 && winner == 1) ||
            (turn == 1 && winner == 2)) {

            color[m][c][turn] = winner;
            queue.offer(new int[]{m, c, turn, winner});

        } else {
            // This move leads to the opponent winning.
            // Remove it from the available winning options.
            degree[m][c][turn]--;

            // If every legal move loses, this state is a loss.
            if (degree[m][c][turn] == 0) {
                int opponent = (turn == 0) ? 2 : 1;

                color[m][c][turn] = opponent;
                queue.offer(new int[]{m, c, turn, opponent});
            }
        }
    }
}