package com.aop;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends Frame implements KeyListener {

    private static Board _board = null;
    public static enum Player {
        X,
        O
       }

   private static String[] _boardArr;
   private static Player _currPlayerTurn;


   public static Board getInstance(){
    if (_board == null) _board = new Board();
    return _board;
   }

   public void initGame(){
    addKeyListener(this);
    setVisible(true);
    _boardArr = new String[] 
        { "_", "_", "_",
        "_", "_", "_",
        "_" , "_", "_" };

    _currPlayerTurn = Player.X;
   }

   /*
    * GETTERS
    */
   public String[] getBoard(){
    return _boardArr;
   }

   public Player getPlayerTurn(){
    return _currPlayerTurn;
   }


   /*
    * SETTERS
    */
   public void setPlayerTurn(Player player ){
    _currPlayerTurn = player;
   }

   public void changeSlot(int i){
    _boardArr[i] = _currPlayerTurn.toString();
   }

   /*
    * KEY LISTENER METHODS
    */

    
    @Override
    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();
        switch(kc) {
            case KeyEvent.VK_UP:
                System.out.println("Up arrow key pressed");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Down arrow key pressed");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Left arrow key pressed");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Right arrow key pressed");
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("ENTER key pressed");
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
