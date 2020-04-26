package controller;

import model.PuzzleState;
import model.SearchResult;
import model.heuristic.EuclideanDistance;
import model.heuristic.ManhattanDistance;
import model.search.SearchAstar;
import model.search.SearchBFS;
import model.search.SearchDFS;

public class Test {
    public static void main(String[] args) {
        String s = "0,2,5,3,1,8,4,6,7";

        PuzzleState initial = new PuzzleState(s, null);
        PuzzleState goal = new PuzzleState(initial.getGoalState(), null);
        SearchResult result = SearchAstar.Astar(initial, goal, new ManhattanDistance());
//        SearchResult result = SearchAstar.Astar(initial, goal, new EuclideanDistance());
//        SearchResult result = SearchBFS.bfs(initial, goal);
//        SearchResult result = SearchDFS.dfs(initial, goal);
        System.out.println("Path to Goal: " + result.getPathToGoal().toString());
        System.out.println("Path cost: " + result.getPathCost());
        System.out.println("Number of nodes expanded: " + result.getNodesExpanded());
        System.out.println("Search depth: " + result.getSearchDepth());
        System.out.println("Running time (msec): " + result.getRunningTime());
    }
}
