package model.heuristic;

import model.PuzzleState;

public class ManhattanDistance extends Heuristic {
    @Override
    public  int calculateCost(String stateStr) {
        PuzzleState state;              /* Puzzle state object */
        Integer[][] puzzleBoard;        /* Puzzle board */
        int goalX;                      /* Goal X coordinate of tile */
        int goalY;                      /* Goal Y coordinate of tile */
        int distance;                   /* Manhattan distance of tile */

        state = new PuzzleState(stateStr, null);
        puzzleBoard = state.getPuzzleBoard();
        distance = 0;
        for (int x = 0 ; x < state.getDimension() ; x++) {
            for (int y = 0 ; y < state.getDimension() ; y++) {
                goalX = puzzleBoard[x][y] / state.getDimension();
                goalY = puzzleBoard[x][y] % state.getDimension();
                distance += Math.abs(goalX - x) + Math.abs(goalY - y);
            }
        }
        return distance;
    }
}
