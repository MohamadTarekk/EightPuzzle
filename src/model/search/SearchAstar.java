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
        PuzzleState currentState = initialState;                            /* Current state object */
        MinHeapEntry entry;                                                 /* Min Heap entry object */
        int cost;                                                           /* Cost of state heuristic function */
        SearchResult result = new SearchResult();                           /* Search results object */
        Long start, end;                                                    /* For elapsed time calculation */
        boolean hasNeighbors;                                               /* Flag to indicate whether current state has neighbors */

        /* Start calculating elapsed time */
        start = System.nanoTime();

        cost = costFunction.calculateCost(initialState.getStateString());
        entry = new MinHeapEntry(initialState.getStateString(), cost, null);
        frontier.add(entry);
        try {
            while (!frontier.isEmpty()) {
                /* Get next state */
                entry = frontier.poll();
                currentState = new PuzzleState(entry.getState(), entry.getParentState());
                explored.add(currentState.getStateString());
                /* Check if goal state reached */
                if (goalState.getStateString().equals(currentState.getStateString())) {
                    result.setFound(true);
                    break;
                }
                /* Mark state as expanded */
                result.updateExpandedNodes();
                /* Add neighbors to frontier */
                hasNeighbors = false;
                currentState.findNeighbors();
                for (String neighbor :
                        currentState.getNeighbors()) {
                    if (neighbor == null)
                        continue;
                    cost = costFunction.calculateCost(neighbor);
                    entry = new MinHeapEntry(neighbor, cost, currentState);
                    if (!frontier.contains(entry) && !explored.contains(neighbor)) {
                        frontier.add(entry);
                        hasNeighbors = true;
                    }
                }
                if (hasNeighbors)
                    result.updateMaxDepth(currentState.getDepth() + 1);
            }
        } catch (OutOfMemoryError e) {
            frontier.clear();
            explored.clear();
            end = System.nanoTime();
            recordResult(result, currentState, true, start, end, costFunction);
            return result;
        }

        /* End calculating elapsed time */
        end = System.nanoTime();

        /* Record found results */
        recordResult(result, currentState, false, start, end, costFunction);

        return result;
    }
    private static void recordResult(SearchResult result, PuzzleState currentState, boolean overflow, Long start, Long end, Heuristic costFunction) {
        /* Set used search algorithm */
        result.setSearchAlgorithm("A star search (" + costFunction.getClass().getSimpleName() + ")");

        /* Calculate elapsed time */
        result.calculateRunningTime(start, end);

        /* Find path to goal */
        result.findPathToGoal(currentState, overflow);

        /* Update path depth */
        result.updateDepth();

        /* Update path cost */
        result.updateCost();
    }
}
