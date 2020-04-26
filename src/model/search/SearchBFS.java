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
        PuzzleState currentState = null;                            /* Current state object */
        SearchResult result = new SearchResult();                   /* Search results object */
        Long start, end;                                            /* For elapsed time calculation */
        boolean hasNeighbors;                                       /* Flag to indicate whether current state has neighbors */

        /* Start calculating elapsed time */
        start = System.currentTimeMillis();

        entry = new FrontierEntry(initialState.toString(), null);
        frontier.enqueue(entry);
        while (!frontier.isEmpty()) {
            /* Get next state */
            entry = frontier.dequeue();
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
                entry = new FrontierEntry(neighbor, currentState);
                if ( !frontier.contains(entry) && !explored.contains(neighbor) ) {
                    frontier.enqueue(entry);
                    hasNeighbors = true;
                }
            }
            /* Update search depth */
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
