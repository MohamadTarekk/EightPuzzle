package model.heuristic;

import model.PuzzleState;

public class EuclideanDistance extends Heuristic {
    @Override
    public  int calculateCost(String stateStr) {
        PuzzleState state;              /* Puzzle state object */
        Integer[][] puzzleBoard;        /* Puzzle board */
        int goalX;                      /* Goal X coordinate of tile */
        int goalY;                      /* Goal Y coordinate of tile */
        int distance;                   /* Manhattan distance of tile */

        state = new PuzzleState(stateStr);
        puzzleBoard = state.getPuzzleBoard();
        distance = 0;
        for (int x = 0 ; x < state.getDimension() ; x++) {
            for (int y = 0 ; y < state.getDimension() ; y++) {
                goalX = puzzleBoard[x][y] / state.getDimension();
                goalY = puzzleBoard[x][y] % state.getDimension();
                distance += Math.sqrt(Math.pow((goalX - x), 2)) + Math.sqrt(Math.pow((goalY - y), 2));
            }
        }
        return distance;
    }
}
