package com.aop;

public class Main {
    public static Board board;
    

    public static void main(String[] args) {
        board = Board.getInstance();
        board.initGame();

        System.out.println("X starts! \n Use the arrow keys to select your mark. \n Press `ENTER` when you have made your decision");
        board.render();
        while(true){}
    }

    
}