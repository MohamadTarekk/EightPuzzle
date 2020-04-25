package lib;

public class MinHeapEntry implements Comparable<MinHeapEntry> {
    private String state;
    private Integer cost;

    public MinHeapEntry(String state, Integer cost) {
        this.state = state;
        this.cost = cost;
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
}
