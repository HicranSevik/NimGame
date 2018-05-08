package Game;

import java.util.Random;

public class NimGame {
	public static int computer=2;
	public static int player=1;
	
	public static int nim_sum;  
	
	public static int calculate(int piles[],int length){
		nim_sum=piles[0];
		for(int i=1;i<length;i++) {
			
			nim_sum=nim_sum^piles[i];
		}
		return nim_sum;
	}
	
	public static void learnBeforeGame(int piles[],int length,int whoseTurn) {
		
		System.out.println("Guess Before Game :");
		
		if(calculate(piles,length)!=0) {
			if(whoseTurn==computer) {
				System.out.println("Computer will win");
			}
			else
				System.out.println("Player will win");
		} 
		else
			if(whoseTurn==computer) {
				System.out.println("Player will win");
			}
			else
				System.out.println("Computer will win");		
	}
	
	public static boolean gameOver(int piles[],int length) {
		for(int i=0;i<length;i++) {
			if(piles[i]!=0) {
				return false;
			}
									
		}
		return true;		
	}
	
	public static void letMeSeePiles(int piles[],int length) {

	    String result="Current Game Status -> ";
	    for (int i=0; i<length; i++)	{
	    	result +=piles[i]+ " ";
	    }
	    System.out.println(result);
	}
	
	public static void WhoIsWinner(int whoseTurn)
	{
	    if (whoseTurn == computer)
	        System.out.println("\nPLAYER won\n\n");
	    else
	        System.out.println("\nCOMPUTER won\n\n");
	    return;
	}
	
	public static void makeMove(int piles[], int length, Move moves)
	{
		 Random r = new Random();
	    int nim_sum = calculate(piles,length );
	    if (nim_sum != 0)
	    {
	        for (int i=0; i<length; i++)
	        {
	            if ((piles[i] ^ nim_sum) < piles[i])
	            {
	                moves.pile_index = i;
	                moves.stones_removed = piles[i]-(piles[i]^nim_sum);
	                piles[i] = (piles[i] ^ nim_sum);
	                break;
	            }
	        }
	    }	    
	    else
	    {
	        int count = 0;
	        int[]  non_zero_indices=new int[length];
	 
	        for (int i=0; i<length; i++) {
	        	 
	            if (piles[i] > 0)
	                non_zero_indices [count++] = i; 
	        }
	       // System.out.println("RANDOM "+r.nextInt(count));
	        moves.pile_index = r.nextInt(count);
	        try {
	        	moves.stones_removed =  1+(r.nextInt(piles[moves.pile_index]));
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	        piles[moves.pile_index] = piles[moves.pile_index] - moves.stones_removed;
	 
	        if (piles[moves.pile_index] < 0)
	            piles[moves.pile_index]=0;
	        
	    }
	    
	}
	public static void startGame(int piles[],int length,int whoseTurn) {
		Move moves = new Move();
		System.out.println("Game Starts");
		
		 while (gameOver(piles, length) == false)
		    {
			 letMeSeePiles(piles,length);
			 makeMove(piles, length, moves);
			 
			 if (whoseTurn == computer)
		        {
		            System.out.println("COMPUTER removes "+ moves.stones_removed+ "stones from pile at index "+moves.pile_index);
		            whoseTurn = player;
		        }
		        else
		        {
		        	System.out.println("PLAYER removes "+ moves.stones_removed +"stones from pile at index"+moves.pile_index);
		        	whoseTurn = computer;
		        }
			 
		    }
		 
		 letMeSeePiles(piles, length);
		 WhoIsWinner(whoseTurn);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] piles={3,4,5};
		int pileLength= piles.length;
		
	  
    	learnBeforeGame(piles,pileLength,computer);
		startGame(piles,pileLength,computer);
		
		
		int piles2[] = {3, 4, 7};
		learnBeforeGame(piles2,pileLength,computer);
		startGame(piles2,pileLength,computer);

	}

}
