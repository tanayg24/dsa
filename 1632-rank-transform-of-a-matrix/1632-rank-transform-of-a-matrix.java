import java.util.*;

class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] answer = new int[m][n];
        int[] rowMax = new int[m];
        int[] colMax = new int[n];

        TreeMap<Integer, List<int[]>> map = new TreeMap<>();

        // Group cells by value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(matrix[i][j],
                    k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        for (List<int[]> cells : map.values()) {
            int size = cells.size();
            int[] parent = new int[m + n];
            int[] rank = new int[m + n];

            for (int i = 0; i < m + n; i++) {
                parent[i] = i;
            }

            // Connect cells sharing a row or column
            for (int[] cell : cells) {
                int r = cell[0];
                int c = cell[1] + m;

                union(parent, rank, r, c);
            }

            Map<Integer, Integer> groupMax = new HashMap<>();

            // Find the maximum existing rank for each group
            for (int[] cell : cells) {
                int root = find(parent, cell[0]);

                int maxRank = Math.max(
                    rowMax[cell[0]],
                    colMax[cell[1]]
                );

                groupMax.put(root,
                    Math.max(groupMax.getOrDefault(root, 0), maxRank));
            }

            // Assign ranks and update row/column maximums
            for (int[] cell : cells) {
                int r = cell[0];
                int c = cell[1];
                int root = find(parent, r);

                answer[r][c] = groupMax.get(root) + 1;
            }

            for (int[] cell : cells) {
                int r = cell[0];
                int c = cell[1];

                rowMax[r] = Math.max(rowMax[r], answer[r][c]);
                colMax[c] = Math.max(colMax[c], answer[r][c]);
            }
        }

        return answer;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}