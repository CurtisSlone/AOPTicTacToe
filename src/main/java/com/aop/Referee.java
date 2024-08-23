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

    // Winning Tic Tac Toe Combinations
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
        /*
         * Check for Win, Draw, and position check.
         */
        String currentBoardState = buildWinnerCheckString(); // Get current player locations as string

        for (String combination : winningCombinations) {
            //Check current player string contains any winning combination
            if (containsWinningCombination(currentBoardState, combination)) {
                System.out.println("We have a winner! Player" + Board.getCurrentPlayer().toString());
                Board.reset();
                return null;
            }
        }

        if (checkDraw()){ // Calls checkdraw
            System.out.println("DRAW!");
            Board.reset();
            return null;
        }
        if (Board.getBoard()[Board.getBoardCurrPos()] != "_") { // checks if space is not empty
            System.out.println("Location occupied");
            return null;
        }

        Board.setNextPlayer(); // Turn over, set next player
        return jp.proceed();
    }

    public String buildWinnerCheckString(){
        /*
         * Build current player string to check for winning combinations
         * Uses indice of Board Array
         */
        String winnerCheckString = new String();
        for(int i = 0; i < 9; i++)
            if(Board.getBoard()[i] == Board.getCurrentPlayer().toString()) 
                winnerCheckString += String.valueOf(i); // Add index of all current player locations to string

        return winnerCheckString;
    }

    public boolean checkDraw(){
        if(!Arrays.toString(Board.getBoard()).contains("_")) return true; // Checks for if players have taken all slots
        return false;
    }

   public static boolean containsWinningCombination(String input, String combination){
        /*
         * Compares current player string with selected combination
         */

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
