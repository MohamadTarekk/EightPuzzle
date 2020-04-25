package model.search;

import model.PuzzleState;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SearchBFS {
    public static boolean bfs(PuzzleState initialState, PuzzleState goalState) {
        LinkedList<String> frontier = new LinkedList<>();
        Set<String> explored = new HashSet<>();
        PuzzleState currentState;

        frontier.add(initialState.toString());
        while (!frontier.isEmpty()) {
            // Get next state
            currentState = new PuzzleState(frontier.poll());
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
