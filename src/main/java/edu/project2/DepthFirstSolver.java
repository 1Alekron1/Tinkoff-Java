package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSolver implements Solver {
    public static final int MAX_NEIGHBOURS = 4;
    private final Maze maze;
    private final Stack<Coordinate> stack;
    private final boolean[][] visited;

    private static final int[] DR = {0, 0, 1, -1};
    private static final int[] DC = {1, -1, 0, 0};

    public DepthFirstSolver(Maze maze) {
        this.maze = maze;
        this.stack = new Stack<>();
        this.visited = new boolean[maze.height()][maze.width()];
    }

    @Override
    public List<Coordinate> solve(Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            visited[current.row()][current.col()] = true;

            if (current.equals(end)) {
                path.add(current);
                break;
            }

            for (int i = 0; i < MAX_NEIGHBOURS; i++) {
                if (current.row() == 0 && DR[i] == -1 || current.col() == 0 && DC[i] == -1) {
                    continue;
                }
                int newRow = current.row() + DR[i];
                int newCol = current.col() + DC[i];

                if (isValid(newRow, newCol) && maze.getCell(newRow, newCol).type() == Cell.Type.PASSAGE
                    && !visited[newRow][newCol]) {
                    stack.push(current);
                    stack.push(new Coordinate(newRow, newCol));
                    break;
                }
            }
        }

        return path;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < maze.height() && col >= 0 && col < maze.width();
    }

}
