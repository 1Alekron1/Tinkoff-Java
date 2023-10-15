package edu.hw1;

public class Task8 {
    final static int MAXIMUM_OFFSET = 2;
    final static int MINIMUM_OFFSET = 1;

    public static boolean knightBoardCapture(int[][] board) {
        int[][] moves = {
            {-MAXIMUM_OFFSET, -MINIMUM_OFFSET}, {-MAXIMUM_OFFSET, MINIMUM_OFFSET},
            {-MINIMUM_OFFSET, -MAXIMUM_OFFSET}, {-MINIMUM_OFFSET, MAXIMUM_OFFSET},
            {MINIMUM_OFFSET, -MAXIMUM_OFFSET}, {MINIMUM_OFFSET, MAXIMUM_OFFSET},
            {MAXIMUM_OFFSET, -MINIMUM_OFFSET}, {MAXIMUM_OFFSET, MINIMUM_OFFSET}
        };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    for (var move : moves) {
                        int newRow = i + move[0];
                        int newCol = j + move[1];
                        if (isValidPosition(newRow, newCol, board.length) && board[newRow][newCol] == 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isValidPosition(int row, int col, int size) {
        return row >= 0 && col >= 0 && row < size && col < size;
    }

    private Task8() {
    }
}
