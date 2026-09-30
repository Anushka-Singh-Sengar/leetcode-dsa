class Solution {
    public boolean isValidSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    continue;
                }

                int num = board[i][j] - '0';
                int box = (i / 3) * 3 + (j / 3);

                // Duplicate in row
                if (rows[i][num]) {
                    return false;
                }

                // Duplicate in column
                if (cols[j][num]) {
                    return false;
                }

                // Duplicate in 3x3 box
                if (boxes[box][num]) {
                    return false;
                }

                // Mark as seen
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[box][num] = true;
            }
        }

        return true;
    }
}