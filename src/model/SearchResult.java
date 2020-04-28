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

    public void findPathToGoal(PuzzleState puzzleState, boolean overflow) {
        int index;

        if (overflow) {
            this.pathToGoal.add(this.searchAlgorithm + " failed! (Out of memory space)");
            return;
        }

        if (!this.found) {
            this.pathToGoal.add("Goal not found!");
            return;
        }

        while (puzzleState.getPreviousState() != null) {
            index = puzzleState.getPreviousState().getNeighbors().indexOf(puzzleState.getStateString());
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
            this.pathCost = this.nodesExpanded;
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
        this.runningTime = (double) (end - start);
        for (int i = 0 ; i < 9 ; i++)
            this.runningTime /= 10;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public String toString() {
        return  "path_to_Goal: " + this.getPathToGoal().toString() + "\n" +
                "cost_of_path: " + this.getPathCost() + "\n" +
                "nodes_expanded: " + this.getNodesExpanded() + "\n" +
                "search_depth: " + this.getSearchDepth() + "\n" +
                "max_search_depth: " + this.getMaxSearchDepth() + "\n" +
                "running_time: " + this.getRunningTime().floatValue() + "\n";
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
