package com.aop;

public class Player {
    private Board.Player _playerType;

    public Player(Board.Player type){
        _playerType = type;
    }

    /*
     * GETTERS
     */
     public Board.Player getPlayerType(){
        return _playerType;
     }
}
