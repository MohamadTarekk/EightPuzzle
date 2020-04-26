package lib;

import model.PuzzleState;

public class FrontierEntry {
    private String state;
    private PuzzleState parentState;

    public FrontierEntry(String state, PuzzleState parentState) {
        this.state = state;
        this.parentState = parentState;
    }


    /******************************* Getters *******************************/

    public String getState() {
        return state;
    }

    public PuzzleState getParentState() {
        return parentState;
    }
}
