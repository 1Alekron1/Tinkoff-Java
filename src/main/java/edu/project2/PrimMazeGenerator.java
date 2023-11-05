package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator extends Maze {
    public static final double CONST_1 = 0.7;
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private final Random random;

    public PrimMazeGenerator(int height, int width) {
        super(height, width, new Cell[height][width]);
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        this.random = new Random();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (random.nextDouble() < CONST_1) {
                    grid[row][col] = new Cell(row, col, Cell.Type.WALL);
                } else {
                    grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                }
            }
        }
    }

    public Maze generateMaze() {
        int startRow = random.nextInt(height);
        int startCol = random.nextInt(width);

        grid[startRow][startCol] = new Cell(startRow, startCol, Cell.Type.PASSAGE);

        List<Cell> walls = getConnectedWalls(startRow, startCol);

        while (!walls.isEmpty()) {
            int randomIndex = random.nextInt(walls.size());
            Cell wall = walls.get(randomIndex);

            List<Cell> neighbors = getConnectedPassageNeighbors(wall);
            if (!neighbors.isEmpty()) {
                int randomNeighborIndex = random.nextInt(neighbors.size());
                Cell passageNeighbor = neighbors.get(randomNeighborIndex);

                grid[wall.row()][wall.col()] = new Cell(wall.row(), wall.col(), Cell.Type.PASSAGE);
                grid[passageNeighbor.row()][passageNeighbor.col()] =
                    new Cell(passageNeighbor.row(), passageNeighbor.col(), Cell.Type.PASSAGE);

                List<Cell> newWalls = getConnectedWalls(passageNeighbor.row(), passageNeighbor.col());
                walls.addAll(newWalls);
            }

            walls.remove(wall);
        }

        return new Maze(height, width, grid);
    }

    private List<Cell> getConnectedWalls(int row, int col) {
        List<Cell> walls = new ArrayList<>();

        if (row - 2 >= 0 && grid[row - 2][col].type() == Cell.Type.WALL) {
            walls.add(grid[row - 2][col]);
        }
        if (row + 2 < height && grid[row + 2][col].type() == Cell.Type.WALL) {
            walls.add(grid[row + 2][col]);
        }
        if (col - 2 >= 0 && grid[row][col - 2].type() == Cell.Type.WALL) {
            walls.add(grid[row][col - 2]);
        }
        if (col + 2 < width && grid[row][col + 2].type() == Cell.Type.WALL) {
            walls.add(grid[row][col + 2]);
        }

        return walls;
    }

    private List<Cell> getConnectedPassageNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int row = cell.row();
        int col = cell.col();

        if (row - 2 >= 0 && grid[row - 2][col].type() == Cell.Type.PASSAGE) {
            neighbors.add(grid[row - 2][col]);
        }
        if (row + 2 < height && grid[row + 2][col].type() == Cell.Type.PASSAGE) {
            neighbors.add(grid[row + 2][col]);
        }
        if (col - 2 >= 0 && grid[row][col - 2].type() == Cell.Type.PASSAGE) {
            neighbors.add(grid[row][col - 2]);
        }
        if (col + 2 < width && grid[row][col + 2].type() == Cell.Type.PASSAGE) {
            neighbors.add(grid[row][col + 2]);
        }

        return neighbors;
    }
}
