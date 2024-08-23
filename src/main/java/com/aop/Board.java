package com.aop;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends Frame implements KeyListener {

    /*
     * TERMINAL COLOR
     */
    public static final String TEXT_BRIGHT_BG_CYAN   = "\u001B[106m";
    public static final String TEXT_RESET  = "\u001B[0m";

    //BOARD SINGLETONG
    private static Board _board = null;

    // PLAYER ENUM
    public static enum Player {
        O,
        X
    }


   private static String[] _boardArr; // Current tic tac toe board
   private static Player _currPlayerTurn; // Current Player to represent whose turn it is
   private static int _currPos; // Cursor position


   public static Board getInstance(){
    /*
     * RETURNS BOARD INSTANCE
     */
    if (_board == null) _board = new Board();
    return _board;
   }

   
   public void initGame(){
    /*
     * INITIALIZE GAME
     *  Adds key listener for directional keys and enter key
     */
    addKeyListener(this);
    setVisible(true);
    Board.reset();
    render();
   }

   
   public static void reset(){
    /*
    * RESET BOARD AND CURSOR POSITION
    */
        Board._currPos = 0;
        Board._boardArr = new String[] 
            { "_", "_", "_",
            "_", "_", "_",
            "_" , "_", "_" };

        Board._currPlayerTurn = Player.X;
        System.out.println("X starts! \n Use the arrow keys to select your mark. \n Press `ENTER` when you have made your decision");
   }

   
   public void render(){
    /*
     * RENDER BOARD
     */
    for( int i = 0; i < 9; i++){
        if(i == _currPos)
            System.out.print( TEXT_BRIGHT_BG_CYAN + _boardArr[i] + " " ); // Highlight cursor position
        else System.out.print(TEXT_RESET + _boardArr[i] + " "); // Render unselected positions as normal

        if ((i + 1)%3 == 0)
            System.out.print(TEXT_RESET + "\n"); //Create line break for 3x3 representation
    }
   }

   private void changeSlot(int i){
    /*
     * Places X or O on board
     */
    _boardArr[i] = Board._currPlayerTurn.toString(); 
   }

   public static void setNextPlayer(){
    /*
     * SET NEXT PLAYER
     */
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
    * Implemented from KeyListener Interface
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

        render(); // Force render on Key Event
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
