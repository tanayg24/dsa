import java.util.*;

class Solution {
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int R = grid.length, C = grid[0].length();
        int N = R * C, food = -1, mouse = -1, cat = -1;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int p = r * C + c;
                char ch = grid[r].charAt(c);
                if (ch == 'M') mouse = p;
                if (ch == 'C') cat = p;
                if (ch == 'F') food = p;
            }
        }

        List<Integer>[] mm = new ArrayList[N];
        List<Integer>[] cm = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            mm[i] = new ArrayList<>();
            cm[i] = new ArrayList<>();
            if (grid[i / C].charAt(i % C) == '#') continue;

            mm[i].add(i);
            cm[i].add(i);

            int r = i / C, c = i % C;
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int d = 0; d < 4; d++) {
                for (int s = 1; s <= Math.max(mouseJump, catJump); s++) {
                    int nr = r + dr[d] * s;
                    int nc = c + dc[d] * s;

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C
                            || grid[nr].charAt(nc) == '#') break;

                    int q = nr * C + nc;
                    if (s <= mouseJump) mm[i].add(q);
                    if (s <= catJump) cm[i].add(q);
                }
            }
        }

        // outcome[m][c][turn]: 0 unknown, 1 mouse wins, 2 cat wins
        int[][][] outcome = new int[N][N][2];
        int[][][] degree = new int[N][N][2];

        for (int m = 0; m < N; m++) {
            for (int c = 0; c < N; c++) {
                if (!valid(grid, m, C) || !valid(grid, c, C)) continue;
                degree[m][c][0] = mm[m].size();
                degree[m][c][1] = cm[c].size();
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int m = 0; m < N; m++) {
            for (int c = 0; c < N; c++) {
                if (!valid(grid, m, C) || !valid(grid, c, C)) continue;

                if (m == food || c == food || m == c) {
                    int winner = (m == food) ? 1 : 2;
                    for (int turn = 0; turn < 2; turn++) {
                        outcome[m][c][turn] = winner;
                        queue.offer(new int[]{m, c, turn, winner});
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int m = state[0], c = state[1], turn = state[2], winner = state[3];

            if (turn == 0) {
                // Previous turn was cat's: predecessor has cat at a prior position.
                for (int pc : cm[c]) {
                    if (outcome[m][pc][1] != 0) continue;

                    if (winner == 2) {
                        outcome[m][pc][1] = 2;
                        queue.offer(new int[]{m, pc, 1, 2});
                    } else if (--degree[m][pc][1] == 0) {
                        outcome[m][pc][1] = 1;
                        queue.offer(new int[]{m, pc, 1, 1});
                    }
                }
            } else {
                // Previous turn was mouse's: predecessor has mouse at a prior position.
                for (int pm : mm[m]) {
                    if (outcome[pm][c][0] != 0) continue;

                    if (winner == 1) {
                        outcome[pm][c][0] = 1;
                        queue.offer(new int[]{pm, c, 0, 1});
                    } else if (--degree[pm][c][0] == 0) {
                        outcome[pm][c][0] = 2;
                        queue.offer(new int[]{pm, c, 0, 2});
                    }
                }
            }
        }

        // Unknown states are draws; the 1000-turn rule makes them cat wins.
        return outcome[mouse][cat][0] == 1;
    }

    private boolean valid(String[] grid, int p, int cols) {
        return grid[p / cols].charAt(p % cols) != '#';
    }
}