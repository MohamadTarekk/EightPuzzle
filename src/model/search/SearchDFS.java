package model.search;

import model.PuzzleState;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SearchDFS {
    public static boolean dfs(PuzzleState initialState, PuzzleState goalState) {
        Stack<String> frontier = new Stack<>();
        Set<String> explored = new HashSet<>();
        PuzzleState currentState;

        frontier.push(initialState.toString());
        while (!frontier.isEmpty()) {
            // Get next state
            currentState = new PuzzleState(frontier.pop());
            explored.add(currentState.toString());
            // Check if goal state reached
            if (goalState.toString().equals(currentState.toString()))
                return true;
            // Add neighbors to frontier
            currentState.findNeighbors();
            for (String neighbor:
                    currentState.getNeighbors()) {
                if ( !frontier.contains(neighbor) && !explored.contains(neighbor) && (neighbor != null) )
                    frontier.push(neighbor);
            }
        }

        return false;
    }
}
