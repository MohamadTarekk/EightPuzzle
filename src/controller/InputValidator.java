package controller;

import java.util.Arrays;
import java.util.HashSet;

class InputValidator {

    static boolean validateArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("\nToo few arguments (" + args.length + ").");
            printCorrectFormat();
            return false;
        }

        if (args.length > 3) {
            System.out.println("\nToo many arguments (" + args.length + ").");
            printCorrectFormat();
            return false;
        }

        if (!validMethod(args[0])) {
            System.out.println("\nUnknown search method (" + args[0] + ").");
            printCorrectFormat();
            return false;
        }

        if (!validState(args[1])) {
            printCorrectFormat();
            return false;
        }

        if (!validHeuristic(args)) {
            printCorrectFormat();
            return false;
        }

        return true;
    }

    private static boolean validMethod(String method) {
        return  method.toLowerCase().equals("dfs") || method.toLowerCase().equals("bfs") || method.toLowerCase().equals("ast");
    }

    private static boolean validState(String state) {
        String[] tiles;
        int sqrt;
        HashSet<String> tilesMap;

        tiles = state.split(",");
        for (String tile : tiles) {
            try {
                Integer.parseInt(tile);
            } catch (Exception e) {
                System.out.println("\nInvalid state! State can contain ONLY comma-separated-integers.");
                return false;
            }
        }

        if (tiles.length == 1) {
            System.out.println("\nInvalid state! Cannot construct puzzle board with 1 tile");
            return false;
        }

        sqrt = (int) Math.sqrt(tiles.length);
        if (Math.pow(sqrt, 2) < tiles.length){
            System.out.println("\nInvalid state! Number of states MUST be a perfect square.");
            return false;
        }

        tilesMap = new HashSet<>(Arrays.asList(tiles));
        for (int i = 0 ; i < tiles.length ; i++) {
            if (!tilesMap.contains(String.valueOf(i))) {
                System.out.println("\nInvalid state! tile (" + i + ") is missing.");
                return false;
            }
        }

        return true;
    }

    private static boolean validHeuristic(String[] args) {
        if (args.length == 2)
            return true;

        if (!args[0].toLowerCase().equals("ast")) {
            System.out.println("\nToo many arguments! Third argument is valid only with (ast).");
            return false;
        }

        if ( !args[2].toLowerCase().equals("man") && !args[2].toLowerCase().equals("ecl") ) {
            System.out.println("\nInvalid heuristic! (" + args[2] + ")");
            return false;
        }

        return true;
    }

    private static void printCorrectFormat() {
        System.out.print("\nExpected arguments format:");
        System.out.println("\t\tdfs/bfs/ast <comma-separated-integers> man/ecl");
        System.out.println("--------------------------------");
        System.out.print("First field  (required): The required search algorithm.");
        System.out.println("  -->  Depth-First-Search(DFS), Breadth-First-search(BFS), A* Search(ast)");
        System.out.print("Second field (required): Puzzle state. It MUST contain 0 for the empty tile position.");
        System.out.println("  -->  The numbers can be (0-8, 0-15, 0-24, etc) in any order");
        System.out.println("Third field  (optional): Heuristic to use with A* search (only).  -->  Manhattan(man)(default) & Euclidean(ecl)");
    }
}
