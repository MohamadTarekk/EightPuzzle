package model.search;

import lib.MinHeapEntry;
import model.PuzzleState;
import model.SearchResult;
import model.heuristic.Heuristic;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SearchAstar {
    public static SearchResult Astar(PuzzleState initialState, PuzzleState goalState, Heuristic costFunction) {
        PriorityQueue<MinHeapEntry> frontier = new PriorityQueue<>();       /* Min Heap of frontier nodes */
        Set<String> explored = new HashSet<>();                             /* Set of explored nodes */
        PuzzleState currentState = null;                                    /* Current state object */
        MinHeapEntry entry;                                                 /* Min Heap entry object */
        int cost;                                                           /* Cost of state heuristic function */
        SearchResult result = new SearchResult();                           /* Search results object */
        Long start, end;                                                    /* For elapsed time calculation */
        boolean hasNeighbors;                                               /* Flag to indicate whether current state has neighbors */

        /* Start calculating elapsed time */
        start = System.currentTimeMillis();

        cost = costFunction.calculateCost(initialState.toString());
        entry = new MinHeapEntry(initialState.toString(), cost, null);
        frontier.add(entry);
        while (!frontier.isEmpty()) {
            /* Get next state */
            entry = frontier.poll();
            currentState = new PuzzleState(entry.getState(), entry.getParentState());
            explored.add(currentState.toString());
            result.updateExpandedNodes();
            /* Check if goal state reached */
            if (goalState.toString().equals(currentState.toString()))
                break;
            /* Add neighbors to frontier */
            hasNeighbors = false;
            currentState.findNeighbors();
            for (String neighbor:
                    currentState.getNeighbors()) {
                if (neighbor == null)
                    continue;
                cost = costFunction.calculateCost(neighbor);
                entry = new MinHeapEntry(neighbor, cost, currentState);
                if ( !frontier.contains(entry) && !explored.contains(neighbor) ) {
                    frontier.add(entry);
                    hasNeighbors = true;
                }
            }
            if (!hasNeighbors)
                result.updateDepth(currentState);
        }

        /* Calculate elapsed time */
        end = System.currentTimeMillis();
        result.calculateRunningTime(start, end);

        /* Find path to goal */
        result.findPathToGoal(currentState);

        /* Update path cost */
        result.updateCost();

        return result;
    }
}
