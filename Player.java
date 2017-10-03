//eduardo hernadez 
import java.util.Scanner;
import java.util.Random;

class Player                       /*here i have a player class i will create 2 more classes that extend this class*/
{
    protected char charXO;
    protected int position;
    protected int player12;
    public void msg()
    {
	System.out.println("hello world");
    }
    public int prompt(boolean [] arr, char[][] Gboard)  /*this function will ask for input and will return legal input*/
    {
	return 10;
    }
    public char GetcharXO()                           /*accesor function */
    {
        return charXO;
    }
    public int Getplayer()
    {
	return player12;
    }

}

class Human extends Player
{
    Human(char p)
    {
	if(p == 'x' || p == 'X')
	    {
		charXO = 'X';
		player12 = 1;
	    }
	else {
	    charXO = 'O';
	    player12 = 2;
	     }
    }

    public int Getplayer()
     {
	 return player12;
     }

    public char GetcharXO()
     {
	return charXO;
    }
    public boolean legalPos(int x, boolean[] arr)
    {
	if(x > 0 && x < 10)
	    return !arr[x-1];  // if the slot chosen is false(not yet occupied) and legal 1-9 return true
 
	return false;
    }

    public int prompt(boolean[] arr,char[][] Gboard) /*ask the user for input and return only a legal position*/
    {
        Scanner input = new Scanner(System.in);
	int position = 0;
	do{

	System.out.print("Player "+ player12 +", please enter a move(1-9):");
	position = input.nextInt();

	}while(!legalPos(position,arr));
	return position;
    }
}

class Computer extends Player 
{
    Computer(char p)
    {
        if(p == 'x' || p == 'X')
            {
                charXO = 'X';
                player12 = 1;
            }
        else {
            charXO = 'O';
            player12 = 2;
	}
    }
    public int Getplayer()
    {
	return player12;
    }
    public char GetcharXO()
    {
        return charXO;
    }      
    public boolean legalPos(int x, boolean[] arr)
    {
        if(x > 0 && x < 10)
            return !arr[x-1];  // if the slot chosen is false(not yet occupied) and legal 1-9 return true

        return false;
    }

    public int prompt(boolean [] arr,char[][] Gboard)
    {
        Random rnd = new Random();
	char oponent;
	if(charXO == 'X'){oponent = 'O';}
	else{oponent = 'X';}
	System.out.print("Player "+ player12 +", please enter a move(1-9):");

	do{
	    
	    if(arr[4]== false){position= 5; }
	    else if(scanRows(Gboard,charXO) > 0){  position = scanRows(Gboard,charXO);}
	    else if(scanRows(Gboard,oponent) > 0){position = scanRows(Gboard,oponent);}
	    else{  position = rnd.nextInt(9)+ 1;}

        }while(!legalPos(position,arr));
	System.out.println(position);

        return position;

    }
    int scanRows(char[][] arr, char player) /*i will scann all rows and columns and look for possible winning and losing opertunity and return the position*/
    {
	int loc = 0;
	int counter = 0;
	int returnMe = -1;
	for(int j = 0; j < 5; j+= 2)
	    {
		counter = 0;
		returnMe = -1;

	      for(int i = 1; i < 11; i+= 4)
	       {
		   loc++;
		   if(arr[j][i] != 'X' && arr[j][i] != 'O')
		       {
			   returnMe = loc;
		       }
                   if(arr[j][i] == player)
                       {
                           counter++;
                       }
	       }
	     
	     
	      if(counter == 2){return returnMe;}
	    }

	      loc = 1;
	      for(int i = 1; i < 11; i+= 4)
		  {
		     
		      if(i == 5){loc = 2;}
		      else if(i == 9){loc = 3;}
		      counter = 0;
		      returnMe = -1;

		      for(int j = 0; j < 5; j+= 2)
			  {
			      if(arr[j][i] != 'X' && arr[ j][i] != 'O')
				  {
				      returnMe = loc;
				  }
			      if(arr[j][i] == player)
				  {
				      counter++;
				  }
                              loc = loc +3;

		            }
		    
		      if(counter == 2){return returnMe;}

	    }
	      counter = 0;
	      loc = 1;
	      int j = 1;
	      returnMe = -1;
	      for(int i = 0; i <5; i+=2)
		  {

		      if(arr[i][j] != 'X' && arr[i][j]!='O' )
			  {
			      returnMe = loc;
			  }
		      if(arr[i][j]== player)
			  {
			      counter++;
			  }
		      loc = loc +4;
		      j= j +4;
		  }

	      if(counter == 2){return returnMe;}

              counter = 0;
              loc = 3;
              j = 9;
              returnMe = -1;
              for(int i = 0; i <5; i+=2)
                  {

                      if(arr[i][j] != 'X' && arr[i][j]!='O' )
                          {
                              returnMe = loc;
                          }
                      if(arr[i][j]== player)
                          {
                              counter++;
                          }
                      j= j -4;
		      loc = loc + 2;
                  }
              if(counter == 2){return returnMe;}


	      return -1;  /* return only -1 if nothing was found*/
    
    }

}
