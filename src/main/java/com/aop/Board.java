package com.aop;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends Frame implements KeyListener {

    public static final String TEXT_BRIGHT_BG_CYAN   = "\u001B[106m";
    public static final String TEXT_RESET  = "\u001B[0m";
    private static Board _board = null;
    public static enum Player {
        O,
        X
    }

   private static String[] _boardArr;
   private static Player _currPlayerTurn;
   private static int _currPos;


   public static Board getInstance(){
    if (_board == null) _board = new Board();
    return _board;
   }

   public void initGame(){
    addKeyListener(this);
    setVisible(true);
    Board.reset();
    render();
   }

   public static void reset(){
        Board._currPos = 0;
        Board._boardArr = new String[] 
            { "_", "_", "_",
            "_", "_", "_",
            "_" , "_", "_" };

        Board._currPlayerTurn = Player.X;
        System.out.println("X starts! \n Use the arrow keys to select your mark. \n Press `ENTER` when you have made your decision");
   }

   public void render(){
    for( int i = 0; i < 9; i++){
        if(i == _currPos)
            System.out.print( TEXT_BRIGHT_BG_CYAN + _boardArr[i] + " " );
        else System.out.print(TEXT_RESET + _boardArr[i] + " ");

        if ((i + 1)%3 == 0)
            System.out.print(TEXT_RESET + "\n");
    }
   }

   private void changeSlot(int i){
    _boardArr[i] = Board._currPlayerTurn.toString();
    
   }

   public static void setNextPlayer(){
    if( Board._currPlayerTurn == Player.X ) Board._currPlayerTurn = Player.O;
    else if( Board._currPlayerTurn == Player.O ) Board._currPlayerTurn = Player.X;
    
   }

   /*
    * GETTERS
    */
   public static String[] getBoard(){
    return _boardArr;
   }

   public static int getBoardCurrPos(){
    return Board._currPos;
   }

   public static Player getCurrentPlayer(){
    return Board._currPlayerTurn;
   }
  
   /*
    * KEY LISTENER METHODS
    */

    @Override
    public void keyPressed(KeyEvent e) {
        for(int n = 0; n < 35; n++ ) System.out.println("");
        int kc = e.getKeyCode();
        switch(kc) {
            case KeyEvent.VK_UP:
                if( Board._currPos - 3 < 0) Board._currPos += 6;
                else Board._currPos -= 3;
                break;
            case KeyEvent.VK_DOWN:
                if( Board._currPos + 3 > 8) Board._currPos -= 6;
                else Board._currPos += 3;
                break;
            case KeyEvent.VK_LEFT:
                if(Board._currPos == 0) Board._currPos = 8;
                else Board._currPos -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                if(Board._currPos == 8) Board._currPos = 0;
                else Board._currPos += 1;
                break;
            case KeyEvent.VK_ENTER:
                changeSlot(Board._currPos);
                break;
            default:
                break;
        }

        render();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
