package com.aop;

public class Main {
    public static Board board;
    public static final String TEXT_BRIGHT_BG_CYAN   = "\u001B[106m";
    public static final String TEXT_RESET  = "\u001B[0m";
    private static int _currPos = 0;
    private static int _lastPos = -1;

    public static void main(String[] args) {
        board = Board.getInstance();
        board.initGame();

        System.out.println("X starts! \n Use the arrow keys to select your mark. \n Press `ENTER` when you have made your decision");
        render();
        _lastPos += 1;
        while(true){
            if (_currPos != _lastPos) render();
        }
    }

    public static void render(){
        for( int i = 0; i < board.getBoard().length; i++){
            
            if(i == _currPos)
                System.out.print( TEXT_BRIGHT_BG_CYAN + board.getBoard()[i] + " " );
            else System.out.print(TEXT_RESET + board.getBoard()[i] + " ");

            if ((i + 1)%3 == 0)
                System.out.print(TEXT_RESET + "\n");
        }
    }
}