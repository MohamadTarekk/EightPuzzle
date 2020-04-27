package model;

import java.util.ArrayList;

public class PuzzleState {
    /* Layout of the puzzle board */
    private Integer[][] puzzleBoard;
    /* Side length of he puzzle board */
    private Integer dimension;
    /* Depth of puzzle state node in graph */
    private Integer depth;
    /* List of possible next state */
    private ArrayList<String> neighbors = new ArrayList<>(4);
    /* Previous puzzle state object */
    private PuzzleState previousState;

    /* Constructor */
    public PuzzleState(String inputState, PuzzleState previousState) {
        String[] tiles;         /* Tiles arrangement in the initial state */
        int x, y;               /* Coordinates of tiles in the board */

        /* Initialize puzzle board dimensions */
        tiles = inputState.split(",");
        dimension = (int) Math.sqrt(tiles.length);

        /* Lay initial state on puzzle board */
        puzzleBoard = new Integer[dimension][dimension];
        for (int i = 0 ; i < tiles.length ; i++) {
            x = i / dimension;
            y = i % dimension;
            try {
                puzzleBoard[x][y] = Integer.parseInt(tiles[i]);
            } catch (Exception e) {
                puzzleBoard[x][y] = -1;
            }
        }

        /* Save previous state */
        this.previousState = previousState;
        /* Set depth */
        if (previousState == null)
            this.depth = 0;
        else
            this.depth = previousState.getDepth() + 1;
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

    public void printState() {
        for (int x = 0 ; x < dimension ; x++) {
            for (int y = 0; y < dimension; y++)
                System.out.print(puzzleBoard[x][y] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    /* Generate possible neighbors based on the current state */
    public void findNeighbors() {
        int x, y;               /* Coordinates of tiles in the board */
        boolean found;          /* Boolean to indicate finding the empty tile */

        y = 0;
        found = false;
        for (x = 0 ; x < dimension; x++){
            for (y = 0; y < dimension; y++)
                if (puzzleBoard[x][y] == 0) {
                    found = true;
                    break;
                }
            if (found)
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


    /******************************* Getters *******************************/

    /* Getter for puzzle board layout */
    public Integer[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    /* Getter for puzzle board side length */
    public int getDimension() {
        return dimension;
    }

    /* Getter for puzzle state node depth in graph */
    public Integer getDepth() {
        return depth;
    }

    /* Getter for the list of strings representing the neighbors */
    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    /* Getter for previous state */
    PuzzleState getPreviousState() {
        return previousState;
    }

}
