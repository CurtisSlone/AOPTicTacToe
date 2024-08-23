package com.aop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Referee {

    List<String> winningCombinations = Arrays.asList(new String[]{
        "0 3 6",
        "1 4 6",
        "2 5 8",
        "0 1 2",
        "3 4 5",
        "6 7 8",
        "0 4 8",
        "2 4 6"
    });

    @Pointcut("call(* com.aop.Board.changeSlot(..))")
    public void slotChange(){}

    @Around("slotChange()")
    public Object validateMove(ProceedingJoinPoint jp) throws Throwable{
        if (checkDraw()){
            System.out.println("DRAW!");
            Main.board.initGame();
            return null;
        }
        if (Board.getBoard()[Board.getBoardCurrPos()] != "_") {
            System.out.println("Location occupied");
            return null;
        }
        // if(checkDraw()){
        //     System.out.println("Draw:");
        //     Main.board.initGame();
        // }
        System.out.println(buildWinnerCheckString());
        Board.setNextPlayer();
        return jp.proceed();
    }

   
    public void checkIfWinner() {
        

        // boolean winner = false;

        // while(!winner)
        //     winningCombinations.forEach(x->Referee.containsWinningCombination(winnerCheckString, x));
        // if( winner ){
        //     System.out.println("Winner! Player" + Board.getCurrentPlayer().toString());
        //     Main.board.initGame();
        // }
    }

    public String buildWinnerCheckString(){
        String winnerCheckString = new String();
        for(int i = 0; i < 9; i++)
            if(Board.getBoard()[i] == Board.getCurrentPlayer().toString()) 
                winnerCheckString += String.valueOf(i);

        return winnerCheckString;
    }

    public boolean checkDraw(){
        if(!Arrays.toString(Board.getBoard()).contains("_")) return true;
        return false;
    }

    public static boolean containsWinningCombination(String input, String characters){
        Set<Character> charSet = new HashSet<>();
        for (char c : characters.toCharArray())
            if (c != ' ')
                charSet.add(c);

        for (char c : charSet)
            if (input.indexOf(c) == -1)
                return false;

        return true;
    }
}
