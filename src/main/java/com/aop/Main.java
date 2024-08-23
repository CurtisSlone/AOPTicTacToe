package com.aop;

public class Main {
    public static Board board;

    public static void main(String[] args) {
        board = Board.getInstance();
        board.initGame();
        while(true){}
    }

    
}