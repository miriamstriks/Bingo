import java.awt.TextArea;

import javax.swing.JOptionPane;

import java.util.*;

/**
 * 
 * @author Miriam Summers, Lab Section 1
 *
 */

public class BingoGame {

      /**
       * Plays the BINGO game with 1 to 5 players
       * 
       */
   
     private int numberOfPlayers;
     private TextArea[] textAreas;
     private BINGOCard[] bingoCards;
     private LinkedList<Integer> listOfCalls;
     private static BingoGUI bingoDisplay;
     
     
     public static void main (String[] args) {
        bingoDisplay = new BingoGUI();
        
     }
     
     
     public void newGame(){
        
        /*
         *  Let user enter number of players
         *  Maker sure number of players is between 1 and 5
         */
        while(true){
           try{
              numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of players from 1 to 5."));
              if(numberOfPlayers<1 || numberOfPlayers>5)
                 throw new IllegalArgumentException();
              break;
           }catch (IllegalArgumentException i){  //catch illegal characters and numbers
              JOptionPane.showMessageDialog(null,"Number of players must be between 1 and 5.");
            }  
        }
        
        /*
         *  Create a list of possible calls
         */
        //create array of possible calls
        Integer[] possibleCalls = new Integer[75];
        for (int i=0; i<possibleCalls.length; i++)
           possibleCalls[i] = i+1;
        Collections.shuffle(Arrays.asList(possibleCalls));
        
        //create linked list of possible calls
        listOfCalls = new LinkedList<Integer>();
        for(int i=0; i<possibleCalls.length; i++)
          listOfCalls.add(possibleCalls[i]);
         bingoCards = new BINGOCard[numberOfPlayers];
          
         /*
          *   Create and display bingo cards
          */
          bingoDisplay.setNumberOfPlayers(numberOfPlayers);    
          bingoDisplay.redraw();
          textAreas = bingoDisplay.getTextAreas();
          for(int i=0; i<numberOfPlayers; i++){
             bingoCards[i] = new BINGOCard();
             textAreas[i].setText(BINGOCard.toString(bingoCards[i]));
           }
     }
     
     
     public void callNumber(){
         /*
          *   Call a number
          */
         String winMessage = "";
         boolean gameOver = false;
         Integer calledNum = listOfCalls.getFirst();
         JOptionPane.showMessageDialog(null, "Called Number: " + calledNum.toString());
         listOfCalls.removeFirst();
         for(int i=0; i<numberOfPlayers; i++){
               BINGOCard.update(bingoCards[i], calledNum);
               textAreas[i].setText(BINGOCard.toString(bingoCards[i]));
               if(BINGOCard.isWinner(bingoCards[i])){
                  winMessage += "Player "+ (i+1) + " has won!\n";
                  textAreas[i].append("Winner!");
                  gameOver = true;
               }
         } 
         if(gameOver == true){
         JOptionPane.showMessageDialog(null, winMessage);
         System.exit(1);
         }
     }

}
