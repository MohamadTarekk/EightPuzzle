package model;

import java.util.ArrayList;

public class SearchResult {
    private String searchAlgorithm;
    private ArrayList<String> pathToGoal;
    private Integer nodesExpanded;
    private Integer pathCost;
    private Integer maxSearchDepth;
    private Integer searchDepth;
    private Double runningTime;
    private boolean found;

    public SearchResult() {
        pathToGoal = new ArrayList<>();
        nodesExpanded = 0;
        pathCost = 0;
        maxSearchDepth = 0;
        searchDepth = 0;
        runningTime = (double) 0;
        found = false;
    }

    public void setSearchAlgorithm(String searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void findPathToGoal(PuzzleState puzzleState) {
        int index;

        if (!this.found) {
            this.pathToGoal.add("Goal not found!");
            return;
        }

        while (puzzleState.getPreviousState() != null) {
            index = puzzleState.getPreviousState().getNeighbors().indexOf(puzzleState.toString());
            switch (index) {
                case 0:
                    this.pathToGoal.add(0, "Up");
                    break;
                case 1:
                    this.pathToGoal.add(0, "Down");
                    break;
                case 2:
                    this.pathToGoal.add(0, "Left");
                    break;
                case 3:
                    this.pathToGoal.add(0, "Right");
                    break;
            }
            puzzleState = puzzleState.getPreviousState();
        }
    }

    public void updateExpandedNodes() {
        this.nodesExpanded++;
    }

    public void updateCost() {
        if (!this.found) {
            this.pathCost = -1;
            return;
        }
        this.pathCost = pathToGoal.size();
    }

    public void updateMaxDepth(Integer depth) {
        if (this.maxSearchDepth < depth)
            this.maxSearchDepth = depth;
    }

    public void updateDepth() {
        if (!this.found) {
            this.searchDepth = this.maxSearchDepth;
            return;
        }
        this.searchDepth = this.pathToGoal.size();
        if (this.maxSearchDepth < this.searchDepth)
            this.maxSearchDepth = this.searchDepth;
    }

    public void calculateRunningTime(Long start, Long end) {
        this.runningTime = (double) ((end - start) / 1000000);
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    /******************************* Getters *******************************/

    public String getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public ArrayList<String> getPathToGoal() {
        return pathToGoal;
    }

    public Integer getNodesExpanded() {
        return nodesExpanded;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public Integer getMaxSearchDepth() {
        return maxSearchDepth;
    }

    public Integer getSearchDepth() {
        return searchDepth;
    }

    public Double getRunningTime() {
        return runningTime;
    }

    public boolean isFound() {
        return found;
    }
}
