package controller;

import model.PuzzleState;
import model.SearchResult;
import model.heuristic.EuclideanDistance;
import model.heuristic.ManhattanDistance;
import model.search.SearchAstar;
import model.search.SearchBFS;
import model.search.SearchDFS;

class GameDriver {

    void run(String[] args) {
        if (!InputValidator.validateArgs(args))
            return;

        PuzzleState initial = new PuzzleState(args[1], null);
        PuzzleState goal = new PuzzleState(initial.getGoalState(), null);
        SearchResult result = new SearchResult();
        OutputGenerator outputGenerator = new OutputGenerator();

        switch (args[0].toLowerCase()) {
            case "bfs":
                result = SearchBFS.bfs(initial, goal);
                break;
            case "dfs":
                result = SearchDFS.dfs(initial, goal);
                break;
            case "ast":
                try {
                    if (args[2].equals("man"))
                        result = SearchAstar.Astar(initial, goal, new ManhattanDistance());
                    else if (args[2].equals("ecl"))
                        result = SearchAstar.Astar(initial, goal, new EuclideanDistance());
                } catch (Exception e) {
                    result = SearchAstar.Astar(initial, goal, new ManhattanDistance());
                }
                break;
        }

        System.out.println("Used search algorithms: " + result.getSearchAlgorithm());
        System.out.println("Found goal state: " + result.isFound());
        System.out.println("Path to Goal: " + result.getPathToGoal().toString());
        System.out.println("Cost of path: " + result.getPathCost());
        System.out.println("Nodes expanded: " + result.getNodesExpanded());
        System.out.println("Search depth: " + result.getSearchDepth());
        System.out.println("Max search depth: " + result.getMaxSearchDepth());
        System.out.println("Running time: " + result.getRunningTime());

        System.out.println("Output saved to: " + outputGenerator.save(result.toString(), result.getSearchAlgorithm()));
    }
}
