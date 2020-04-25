package model.search;

import lib.HashQueue;
import model.PuzzleState;

import java.util.HashSet;
import java.util.Set;

public class SearchBFS {
    public static boolean bfs(PuzzleState initialState, PuzzleState goalState) {
        HashQueue<String> frontier = new HashQueue<>();
        Set<String> explored = new HashSet<>();
        PuzzleState currentState;

        frontier.enqueue(initialState.toString());
        while (!frontier.isEmpty()) {
            /* Get next state */
            currentState = new PuzzleState(frontier.dequeue());
            explored.add(currentState.toString());
            currentState.printState();
            /* Check if goal state reached */
            if (goalState.toString().equals(currentState.toString()))
                return true;
            /* Add neighbors to frontier */
            currentState.findNeighbors();
            for (String neighbor:
                    currentState.getNeighbors()) {
                if ( !frontier.contains(neighbor) && !explored.contains(neighbor) && (neighbor != null) )
                    frontier.enqueue(neighbor);
            }
        }

        return false;
    }
}
