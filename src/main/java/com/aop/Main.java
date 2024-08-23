package com.aop;

public class Main {
    public static Board board;

    public static void main(String[] args) {
        board = Board.getInstance(); // Get New Board
        board.initGame(); //Initilize Game
        while(true){} //Msintain Loop
    }

    
}