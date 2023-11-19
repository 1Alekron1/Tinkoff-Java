package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    protected final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }


    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void clearMaze() {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }

}

