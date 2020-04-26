package lib;

import model.PuzzleState;

public class MinHeapEntry implements Comparable<MinHeapEntry> {
    private String state;
    private Integer cost;
    private PuzzleState parentState;

    public MinHeapEntry(String state, Integer cost, PuzzleState parentState) {
        this.state = state;
        this.cost = cost;
        this.parentState = parentState;
    }

    @Override
    public int compareTo(MinHeapEntry other) {
        return this.getCost().compareTo(other.getCost());
    }


    /******************************* Getters *******************************/

    public String getState() {
        return state;
    }

    public Integer getCost() {
        return cost;
    }

    public PuzzleState getParentState() {
        return parentState;
    }
}
