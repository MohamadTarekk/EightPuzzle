package model;

import java.util.ArrayList;

public class SearchResult {
    private ArrayList<String> pathToGoal;
    private Integer nodesExpanded;
    private Integer pathCost;
    private Integer searchDepth;
    private Long runningTime;

    public SearchResult() {
        pathToGoal = new ArrayList<>();
        nodesExpanded = 0;
        pathCost = 0;
        searchDepth = 0;
        runningTime = 0L;
    }

    public void findPathToGoal(PuzzleState puzzleState) {
        int index;
        while (puzzleState.getPreviousState() != null) {
            index = puzzleState.getPreviousState().getNeighbors().indexOf(puzzleState.toString());
            switch (index) {
                case 0:
                    this.pathToGoal.add(0, "Up");
                case 1:
                    this.pathToGoal.add(0, "Down");
                case 2:
                    this.pathToGoal.add(0, "Left");
                case 3:
                    this.pathToGoal.add(0, "Right");
            }
            puzzleState = puzzleState.getPreviousState();
        }
    }

    public void updateExpandedNodes() {
        this.nodesExpanded++;
    }

    public void updateCost() {
        this.pathCost = pathToGoal.size();
        if (this.searchDepth < this.pathCost)
            this.searchDepth = this.pathCost;
    }

    public void updateDepth(PuzzleState leaf) {
        if (this.searchDepth < leaf.getDepth())
            this.searchDepth = leaf.getDepth();
    }

    public void calculateRunningTime(Long start, Long end) {
        this.runningTime = end - start;
    }


    /******************************* Getters *******************************/

    public ArrayList<String> getPathToGoal() {
        return pathToGoal;
    }

    public Integer getNodesExpanded() {
        return nodesExpanded;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public Integer getSearchDepth() {
        return searchDepth;
    }

    public Long getRunningTime() {
        return runningTime;
    }
}
