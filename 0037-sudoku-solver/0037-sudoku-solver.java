class Solution {

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {

                    for (char num = '1'; num <= '9'; num++) {

                        if (isValid(board, row, col, num)) {

                            // Place the number
                            board[row][col] = num;

                            // Solve remaining cells
                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = '.';
                        }
                    }

                    // No number works here
                    return false;
                }
            }
        }

        // No empty cells left
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {

        // Check row and column
        for (int i = 0; i < 9; i++) {

            if (board[row][i] == num) {
                return false;
            }

            if (board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {

                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}