package edu.project2;

import java.util.List;

public class ConsoleRenderer implements Renderer {
    public String render(Maze maze) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                Cell cell = maze.getCell(i, j);
                if (cell.type() == Cell.Type.WALL) {
                    builder.append("# ");
                } else {
                    builder.append("  ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder builder = new StringBuilder();
        boolean[][] pathMap = new boolean[maze.height()][maze.width()];

        for (Coordinate coordinate : path) {
            pathMap[coordinate.row()][coordinate.col()] = true;
        }

        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                if (maze.getCell(row, col).type() == Cell.Type.WALL) {
                    builder.append("#"); // Стена
                } else if (pathMap[row][col]) {
                    builder.append("."); // Путь
                } else {
                    builder.append(" "); // Проход
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
