// eduardo hernandez ech15

class TicTacToe
{
    private char[][] board;   /*i have to board one  is just a display board (board) and the other will display the modified board (Gboard)*/
    private char[][] Gboard;
    private boolean[] slots; // this will keep track of the taken board positions
  
    public boolean[] getSlots()
    {
	return slots;
    }
    public char[][] Getboard()
    {
	return Gboard;
    }
    public TicTacToe()                  /*here i initalize my board in the constructor*/
    {
	     board = new char[][]{{' ', '1',' ','|',' ','2',' ','|',' ','3',' '},
                       	          {'-', '-','-','-','-','-','-','-','-','-','-'},
                                  {' ', '4',' ','|',' ','5',' ','|',' ','6',' '},
                                  {'-', '-','-','-','-','-','-','-','-','-','-'},
      	                          {' ', '7',' ','|',' ','8',' ','|',' ','9',' '}}; 

             Gboard = new char[][]{{' ',' ',' ','|',' ',' ',' ','|',' ',' ',' '},
                                  {'-', '-','-','-','-','-','-','-','-','-','-'},
                                  {' ', ' ',' ','|',' ',' ',' ','|',' ',' ',' '},
                                  {'-', '-','-','-','-','-','-','-','-','-','-'},
                                  {' ', ' ',' ','|',' ',' ',' ','|',' ',' ',' '}};

	     slots = new boolean[9];   /*this array will keep track of the open positions initalized to FALSE*/

    }
  
    public void updateGboard(int loc, char player )  /* this function will update the board putting the proper char in position*/
    {
	if(loc == 1){Gboard[0][1] = player;}
	else if(loc == 2){Gboard[0][5] = player;}
	else if(loc == 3){Gboard[0][9] = player;}
	else if(loc == 4){Gboard[2][1] = player;}
        else if(loc == 5){Gboard[2][5] = player;}
        else if(loc == 6){Gboard[2][9] = player;}
        else if(loc == 7){Gboard[4][1] = player;}
        else if(loc == 8){Gboard[4][5] = player;}
        else if(loc == 9){Gboard[4][9] = player;}

    }
    public boolean checkWin( char player)  /*check all 8 possible win locations*/
    {
        if(Gboard[0][1] == player && Gboard[2][1] == player && Gboard[4][1] == player ){ return true;}  // check to see if the first colunm is a win
        else if(Gboard[0][5]== player && Gboard[2][5]== player && Gboard[4][5]== player){ return true;} // second |
        else if(Gboard[0][9]== player && Gboard[2][9]== player && Gboard[4][9]== player){  return true;} // third |

        else if(Gboard[0][1]== player && Gboard[0][5]== player && Gboard[0][9]== player){ return true;} // check the first row
        else if(Gboard[2][1]== player && Gboard[2][5]== player && Gboard[2][9]== player){ return true;} // check second row
        else if(Gboard[4][1]== player && Gboard[4][5]== player && Gboard[4][9]== player){ return true;} // check third row

        else if(Gboard[4][1]== player && Gboard[2][5]== player && Gboard[0][9]== player){  return true;} // check /
        else if(Gboard[0][1]== player && Gboard[2][5]== player && Gboard[4][9]== player){  return true;} // check \
	// if no conditional statment is not met than there is no winner yet
	return false; 
    } 

    public void updateSlots(int x) /*this will update my bolean array that keeps track of my used positions*/
    {
	slots[x-1] = true ;
    }
    public void displayGrid()   /* display modifiable board and position board side by side*/
    {
	System.out.println("Game Board:\t\t\tPositions:\n"); 
	for(int i = 0; i !=5 ;i++ )
	    { 

		for(int j = 0; j != 11; j++)
		    {
	         System.out.print( Gboard[i][j] );         /* display Game board row and TAB then display 2nd array board*/
		    }

	      	 System.out.print("\t\t\t");

		  for(int k = 0; k != 11; k++)
                    {
		System.out.print( board[i][k] );
	            }		 
		System.out.println("");
	    } 
	System.out.println("");
    } 

    public static void main(String[] args)
    {
	TicTacToe game = new TicTacToe();  /*instantiate my TTT class */
	boolean cont = true;
	Player[] Parray = new Player[2];
	int slot =0, counter = 0, index =0;
        if(args.length == 0)               /*these first conditional statements will check for args and set the appropriate game up*/
            {
		Parray[0] = new Human('X');
		Parray[1] = new Human('O');
            }
	else if(args.length == 1 && args[0].compareTo("-c")== 0)
	    {
                Parray[0] = new Computer('X');
                Parray[1] = new Computer('O');
	    }
	else if((args.length == 2 && args[0].compareTo("-c")== 0) && args[1].compareTo("1")== 0 )
	    {
		Parray[0] = new Computer('X');
		Parray[1] = new Human('O');
	    }
	else if((args.length == 2 && args[0].compareTo("-c")== 0) && args[1].compareTo("2")== 0 )
	    {
		Parray[0] = new Human('X');
		Parray[1] = new Computer('O');
	    }
	else{
	    System.out.println("Usage: java TicTacToe [-c [1|2]]");
	    counter =9;
        }
	if(counter < 9) /*if the else is used i dont want to display board and i also dont want to play the game*/
	game.displayGrid();
	
	while(counter != 9) /*if the game is longer than 9 loop its a draw */
	    {
	index = counter % 2;
	slot = Parray[index].prompt(game.getSlots(),game.Getboard());
	game.updateSlots(slot);
	game.updateGboard(slot, Parray[index].GetcharXO());
        game.displayGrid();

	if(game.checkWin(Parray[index].GetcharXO()))
	      {
		  System.out.println("Player " + Parray[index].Getplayer() + " WINS! ");
		  break;
	      }
        counter++;
	 if(counter == 9)
	      {
	          System.out.println("DRAW! :(");
	      }
	    }
    }
}
