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

        entry = new FrontierEntry(initialState.toString(), null);
        frontier.push(entry);
        while (!frontier.isEmpty()) {
            /* Get next state */
            entry = frontier.pop();
            currentState = new PuzzleState(entry.getState(), entry.getParentState());
            explored.add(currentState.toString());
            /* Check if goal state reached */
            if (goalState.toString().equals(currentState.toString())) {
                result.setFound(true);
                break;
            }
            /* Mark state as expanded */
            result.updateExpandedNodes();
            /* Add neighbors to frontier */
            hasNeighbors = false;
            currentState.findNeighbors();
            for (int i = 3 ; i >= 0 ; i--) {
                String neighbor = currentState.getNeighbors().get(i);
                if (neighbor == null)
                    continue;
                entry = new FrontierEntry(neighbor, currentState);
                if ( !frontier.contains(entry) && !explored.contains(neighbor) ) {
                    frontier.push(entry);
                    hasNeighbors = true;
                }
            }
            /* Update search depth */
            if (hasNeighbors)
                result.updateMaxDepth(currentState.getDepth() + 1);
        }

        /* Calculate elapsed time */
        end = System.nanoTime();
        result.calculateRunningTime(start, end);

        /* Set used search algorithm */
        result.setSearchAlgorithm("DFS");

        /* Find path to goal */
        result.findPathToGoal(currentState);

        /* Update path depth */
        result.updateDepth();

        /* Update path cost */
        result.updateCost();

        return result;
    }
}
