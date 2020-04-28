package model.search;

import lib.FrontierEntry;
import lib.HashQueue;
import model.PuzzleState;
import model.SearchResult;

import java.util.HashSet;
import java.util.Set;

public class SearchBFS {
    public static SearchResult bfs(PuzzleState initialState, PuzzleState goalState) {
        HashQueue<FrontierEntry> frontier = new HashQueue<>();      /* FIFO Queue of frontier nodes */
        Set<String> explored = new HashSet<>();                     /* Set of explored nodes */
        FrontierEntry entry;                                        /* Holder of extracted frontier entry */
        PuzzleState currentState = initialState;                    /* Current state object */
        SearchResult result = new SearchResult();                   /* Search results object */
        Long start, end;                                            /* For elapsed time calculation */
        boolean hasNeighbors;                                       /* Flag to indicate whether current state has neighbors */

        /* Start calculating elapsed time */
        start = System.nanoTime();

        entry = new FrontierEntry(initialState.getStateString(), null);
        frontier.enqueue(entry);
        try {
            while (!frontier.isEmpty()) {
                /* Get next state */
                entry = frontier.dequeue();
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
                    entry = new FrontierEntry(neighbor, currentState);
                    if (!frontier.contains(entry) && !explored.contains(neighbor)) {
                        frontier.enqueue(entry);
                        hasNeighbors = true;
                    }
                }
                /* Update search depth */
                if (hasNeighbors)
                    result.updateMaxDepth(currentState.getDepth() + 1);
            }
        } catch (OutOfMemoryError e) {
            frontier.clear();
            explored.clear();
            end = System.nanoTime();
            recordResult(result, currentState, true, start, end);
            return result;
        }

        /* End calculating elapsed time */
        end = System.nanoTime();

        /* Record found results */
        recordResult(result, currentState, false, start, end);

        return result;
    }

    private static void recordResult(SearchResult result, PuzzleState currentState, boolean overflow, Long start, Long end) {
        /* Set used search algorithm */
        result.setSearchAlgorithm("BFS");

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
