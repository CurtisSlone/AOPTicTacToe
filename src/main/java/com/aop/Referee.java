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
        "1 4 7",
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
        String currentBoardState = buildWinnerCheckString();

        for (String combination : winningCombinations) {
            if (containsWinningCombination(currentBoardState, combination)) {
                System.out.println("We have a winner! Player" + Board.getCurrentPlayer().toString());
                Board.reset();
                return null;
            }
        }

        if (checkDraw()){
            System.out.println("DRAW!");
            Board.reset();
            return null;
        }
        if (Board.getBoard()[Board.getBoardCurrPos()] != "_") {
            System.out.println("Location occupied");
            return null;
        }

        Board.setNextPlayer();
        return jp.proceed();
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

   public static boolean containsWinningCombination(String input, String combination){
        // Create a set from the combination for easy lookup
        Set<Character> comboSet = new HashSet<>();
        for (char c : combination.toCharArray()) {
            if (c != ' ') {
                comboSet.add(c);
            }
        }

        // Check if all characters in the combination are present in the input
        for (char c : comboSet) {
            if (input.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

}
