package model.search;

import lib.MinHeapEntry;
import model.PuzzleState;
import model.heuristic.Heuristic;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SearchAstar {
    public static boolean Astar(PuzzleState initialState, PuzzleState goalState, Heuristic costFunction) {
        PriorityQueue<MinHeapEntry> frontier = new PriorityQueue<>();       /* Min Heap of frontier nodes */
        Set<String> explored = new HashSet<>();                             /* Set of explored nodes */
        PuzzleState currentState;                                           /* Current state object */
        MinHeapEntry entry;                                                 /* Min Heap entry object */
        int cost;                                                           /* Cost of state heuristic function */

        cost = costFunction.calculateCost(initialState.toString());
        frontier.add(new MinHeapEntry(initialState.toString(), cost));
        while (!frontier.isEmpty()) {
            /* Get next state */
            currentState = new PuzzleState(frontier.poll().getState());
            explored.add(currentState.toString());
            currentState.printState();
            /* Check if goal state reached */
            if (goalState.toString().equals(currentState.toString()))
                return true;
            /* Add neighbors to frontier */
            currentState.findNeighbors();
            for (String neighbor:
                    currentState.getNeighbors()) {
                if (neighbor == null)
                    continue;
                cost = costFunction.calculateCost(neighbor);
                entry = new MinHeapEntry(neighbor, cost);
                if ( !frontier.contains(entry) && !explored.contains(neighbor) ) {
                    frontier.add(entry);
                }
            }
        }

        return false;
    }
}
