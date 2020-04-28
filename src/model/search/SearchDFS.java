package model.search;

import lib.FrontierEntry;
import lib.HashStack;
import model.PuzzleState;
import model.SearchResult;

import java.util.HashSet;
import java.util.Set;

public class SearchDFS {
    public static SearchResult dfs(PuzzleState initialState, PuzzleState goalState) {
        HashStack<FrontierEntry> frontier = new HashStack<>();      /* LIFO Stack of frontier nodes */
        Set<String> explored = new HashSet<>();                     /* Set of explored nodes */
        PuzzleState currentState = initialState;                    /* Current state object */
        FrontierEntry entry;                                        /* Holder of extracted frontier entry */
        SearchResult result = new SearchResult();                   /* Search results object */
        Long start, end;                                            /* For elapsed time calculation */
        boolean hasNeighbors;                                       /* Flag to indicate whether current state has neighbors */

        /* Start calculating elapsed time */
        start = System.nanoTime();

        entry = new FrontierEntry(initialState.getStateString(), null);
        frontier.push(entry);
        try {
            while (!frontier.isEmpty()) {
                /* Get next state */
                entry = frontier.pop();
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
                for (int i = 3; i >= 0; i--) {
                    String neighbor = currentState.getNeighbors().get(i);
                    if (neighbor == null)
                        continue;
                    entry = new FrontierEntry(neighbor, currentState);
                    if (!frontier.contains(entry) && !explored.contains(neighbor)) {
                        frontier.push(entry);
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
        result.setSearchAlgorithm("DFS");

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
