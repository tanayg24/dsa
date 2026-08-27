class Solution {

    int n;
    int count = 0;

    boolean[] cols;
    boolean[] diag1;
    boolean[] diag2;

    public int totalNQueens(int n) {

        this.n = n;

        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];

        backtrack(0);

        return count;
    }

    void backtrack(int row) {

        // All rows completed
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col + n - 1;
            int d2 = row + col;

            // Position is already attacked
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Move to next row
            backtrack(row + 1);

            // Remove queen (backtrack)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}