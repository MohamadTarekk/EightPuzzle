package model;

import java.util.ArrayList;

public class PuzzleState {
    /* Layout of the puzzle board */
    private Integer[][] puzzleBoard;
    /* Side length of he puzzle board */
    private int dimension;
    /* List of possible next state */
    private ArrayList<String> neighbors = new ArrayList<>(4);

    /* Constructor */
    public PuzzleState(String inputState) {
        String[] tiles;         /* Tiles arrangement in the initial state */
        int x, y;               /* Coordinates of tiles in the board */

        /* Initialize puzzle board dimensions */
        tiles = inputState.split(",");
        dimension = (int) Math.sqrt(tiles.length);
        puzzleBoard = new Integer[dimension][dimension];

        /* Lay initial state on puzzle board */
        for (int i = 0 ; i < tiles.length ; i++) {
            x = i / dimension;
            y = i % dimension;
            try {
                puzzleBoard[x][y] = Integer.parseInt(tiles[i]);
            } catch (Exception e) {
                puzzleBoard[x][y] = -1;
            }
        }
    }

    /* Generate the string that represents the goal state */
    public String getGoalState() {
        StringBuilder s = new StringBuilder();
        for (int i = 0 ; i < dimension*dimension ; i++) {
            s.append(i);
            s.append(",");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    /* Generate possible neighbors based on the current state */
    public void findNeighbors() {
        int x, y;               /* Coordinates of tiles in the board */
        x = y = 0;
        for (; x < dimension; x++){
            for (y = 0; y < dimension; y++)
                if (puzzleBoard[x][y] == 0)
                    break;
        }
        neighbors.add(0, getNeighbor("up", x, y));
        neighbors.add(1, getNeighbor("down", x, y));
        neighbors.add(2, getNeighbor("left", x, y));
        neighbors.add(3, getNeighbor("right", x, y));
    }

    /* Get a string representation for the requested neighbor */
    private String getNeighbor(String position, int x, int y) {
        String neighbor;            /* String representation of requested neighbor */

        neighbor = null;
        switch (position){
            case "up":
                if ((x - 1) >= 0) {
                    swap(x, y, x - 1, y);
                    neighbor = this.toString();
                    swap(x, y, x - 1, y);
                }
                break;
            case "down":
                if ((x + 1) < dimension) {
                    swap(x, y, x + 1, y);
                    neighbor = this.toString();
                    swap(x, y, x + 1, y);
                }
                break;
            case "left":
                if ((y - 1) >= 0) {
                    swap(x, y, x, y - 1);
                    neighbor = this.toString();
                    swap(x, y, x, y - 1);
                }
                break;
            case "right":
                if ((y + 1) < dimension) {
                    swap(x, y, x, y + 1);
                    neighbor = this.toString();
                    swap(x, y, x, y + 1);
                }
                break;
        }

        return neighbor;
    }

    /* Swap to tiles in the puzzle board */
    private void swap(int x1, int y1, int x2, int y2) {
        int temp;
        temp = puzzleBoard[x1][y1];
        puzzleBoard[x1][y1] = puzzleBoard[x2][y2];
        puzzleBoard[x2][y2] = temp;
    }

    /* Getter for the list of strings representing the neighbors */
    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                s.append(puzzleBoard[i][j].toString());
                s.append(",");
            }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }
}
