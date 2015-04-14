//Miriam Summers
//Lab Section 1

import java.util.*;

public class BINGOCard
{
   private  Integer[][] numbers;
   private  boolean[][] called;
   
   /**
    * Sole constructor
    */
   public BINGOCard()
   {
      /*
       *   The BINGO card is a 5x5 array of Integer (wrapped ints). Only arrays of OBJECTS may be shuffled.
       *   The called matrix keeps track of which numbers on the card are called. [2][2] is TRUE.
       */
      numbers = new Integer[5][5];
      called = new boolean[5][5];
      /*
       *  Fill an array with 15 numbers (1-15) for the "B" column, put the numbers in random order, and
       *  put the first 5 of those numbers in the "B" column of the bingo card.
       */
      for(int i=1, j=0; i<62 && j<5; i+=15, j++)
      {
         Integer[] BRow = new Integer[15];
         fillRow(BRow, i);
         randomize (BRow);
         putRowInBingoCard(numbers,j,BRow);
     }
   }
   
      /**
       * Fills an array of Integers with 15 numbers with a given starting value.
       * 
       * @param  theRow the row of the array to fill
       * @param  startValue  the starting value of numbers to put in the row
       * 
       */
      public static void fillRow(Integer[] theRow, int startValue){
         for (int i = 0; i< 15; i++)
            theRow[i]= new Integer(startValue++);
      }
      /**
       *   Take the array of sequential Integers and put them in random order.
       *   
       *   @param theArray  the array to randomize
       */
      public static void randomize (Integer[] theArray) {
         Collections.shuffle(Arrays.asList(theArray));
      }
      /**
       *  The BINGO card needs only five of the shuffled numbers, so just put in the first five.
       *  
       *  @param card  the two dimensional array of to fill
       *  @param column of the array to fill
       *  @param row  the row of shuffled numbers to put in the array
       */
      public static void putRowInBingoCard(Integer[][]card, int column, Integer[] row){
         for (int i=0; i<5; i++)
            card[i][column] = row[i];
      }

	    
	  /**
	   *  Converts the two dimensional array of numbers to a string and shows which numbers have been called
	   *  
	   *  @param bcard the two dimensional array of numbers
	   *  @param called the boolean array showing which numbers have been called
	   *  @return  the array of numbers in string form showing which numbers have been called
	   */
	    public static String toString(BINGOCard bcard)
	    {   
	       // add BINGO label and numbers
	        String returnString = " B    I    N    G    O\n";
	       for (int i=0;i<5;i++)
	       {
			for (int j=0; j<5;j++) 
				if(i==2 && j==2)
				returnString += (" **  "); //free space in the middle of the card
				else if(bcard.called[i][j])
					//print less space if the number is two digits to keep numbers aligned
					if(bcard.numbers[i][j] > 9)
					   returnString += "<"+(bcard.numbers[i][j]).toString()+ "> ";
					else
					   returnString += "<"+(bcard.numbers[i][j]).toString()+">  ";
				else
					if(bcard.numbers[i][j] > 9)
					   returnString += " "+(bcard.numbers[i][j]).toString()+"  ";
					else
					   returnString += " "+(bcard.numbers[i][j]).toString()+"   ";
			returnString += "\n";
	       }
			return returnString;
	  }
	   
	   /**
	    * Updates the boolean array of called numbers
	    * 
	    * @param bcard  the array of numbers
	    * @param called the array showing which numbers have been called
	    */
	   public static void update(BINGOCard card, Integer calledNum)
	   {
	         int i = 0;
	         if(calledNum<=15)
	            i = 0;
	         else if(calledNum<=30)
	            i = 1;
	         else if(calledNum<=45)
	            i = 2;
	         else if(calledNum<=60)
	            i = 3;
	         else if(calledNum<=75)
	            i = 4;
	         for(int j=0; j<card.numbers[i].length; j++)
	            if(card.numbers[j][i].equals(calledNum))
	               card.called[j][i] = true;
			card.called[2][2] = true;
	   }
	   
	   /**
	    *  Checks the array for a win
	    *  
	    *  @param called  the boolean array showing which numbers have been called
	    *  @return true if there's a win, false if there's no win
	    */
	   public static boolean isWinner(BINGOCard card)
	   {
			boolean wonRow = true, wonColumn = true, wonDiagonal1 = true, wonDiagonal2 = true;
			
			//check each row
			for(int i=0; i<card.called.length; i++)
			{	
				wonRow = true;	//for each row, assume won unless one of the numbers was not called
				for(int j=0; j<card.called[i].length; j++)
					if(!(wonRow && card.called[i][j]))
						wonRow = false;
				if(wonRow) break; //break the loop if one of the rows won so that wonRow stays true
			}
			
			//check each column  
			for(int i=0; i<card.called[0].length; i++)  //assuming each column has the same length
			{
				wonColumn = true;
				for(int j=0; j<card.called.length; j++)
					if(!(wonColumn && card.called[j][i]))
						wonColumn = false; 
				if(wonColumn) break;
			}
							
			//check diagonals
			for(int i=0; i<card.called.length && i<card.called[i].length; i++)
			{
				if (!(wonDiagonal1 && card.called[i][i])) 
					wonDiagonal1 = false;	
			}
			
			for(int i=0, j=4; i<card.called.length && j>=0; i++,j--)
			{
				if (!(wonDiagonal2 && card.called[i][j]))
						wonDiagonal2 = false;
			}			
					
			if(wonRow || wonColumn || wonDiagonal1 || wonDiagonal2)
				return true;
			else return false;			

		}		
}