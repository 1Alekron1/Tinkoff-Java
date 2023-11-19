package edu.project2;

//import java.util.List;

//public class Main {
//    public static void main(String[] args) {
//        int height = 20;
//        int width = 20;
//
//        PrimMazeGenerator mazeGenerator = new PrimMazeGenerator(height, width);
//        Maze maze = mazeGenerator.generateMaze();
//
//        Coordinate start = new Coordinate(0, 0);
//        Coordinate end = new Coordinate(height - 1, width - 1);
//
//        DepthFirstSolver solver = new DepthFirstSolver(maze);
//        List<Coordinate> path = solver.solve(start, end);
//
//        ConsoleRenderer renderer = new ConsoleRenderer();
//        System.out.println("Лабиринт:");
//        System.out.println(renderer.render(maze));
//
//        if (path.isEmpty()) {
//            System.out.println("Путь не найден.");
//        } else {
//            System.out.println("Путь:");
//            System.out.println(renderer.render(mazeGenerator, path));
//        }
//
//
//    }
//
//
//}
