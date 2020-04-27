package controller;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0)
            args = new String[]{"ast", "1,2,0,3,4,5,6,7,8", "man"};
        GameDriver gameDriver = new GameDriver();
        gameDriver.run(args);
    }
}
