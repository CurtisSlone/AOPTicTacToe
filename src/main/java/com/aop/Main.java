package com.aop;

public class Main {
    public static Board board;
    public static Player playerX;
    public static Player playerO;
    
    public static void main(String[] args) {
        board = Board.getInstance();
        playerX = new Player(Board.Player.X);
        playerO = new Player(Board.Player.O);

        board.initGame();

    }

}