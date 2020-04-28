package controller;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0)
            args = new String[]{"ast", "1,2,3,7,0,4,5,6,8,9,10,11,12,13,14,15"};
        GameDriver gameDriver = new GameDriver();
        gameDriver.run(args);
    }
}
