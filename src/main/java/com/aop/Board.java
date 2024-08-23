package com.aop;

public class Board {

    private static Board _board = null;
    public static enum Player {
        X,
        O
       }

   private static String[][] _boardArr;
   private static Player _currPlayerTurn;


   public static Board getInstance(){
    if (_board == null) _board = new Board();
    return _board;
   }

   public void initGame(){
    _boardArr = new String[][] {
        {"_","_", "_"},
        {"_","_", "_"},
        {"_","_", "_"}
    };

    _currPlayerTurn = Player.X;
   }

   /*
    * GETTERS
    */
   public String[][] getBoard(){
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

   public void changeSlot(int r, int c){
    _boardArr[r][c] = _currPlayerTurn.toString();
   }

}
