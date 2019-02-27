import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class TicTacToe extends Check_input
{
	public static void main(String args[])
	{ 

		boolean count1=false,count4=false,count2=false;
		char a='n';
		do 
		{
			@SuppressWarnings("resource")
			Scanner stdin=new Scanner(System.in);
			do 
			{
				System.out.println("press n for new game or press s for saved game"); //Asking user to choose for a new game or a saved game
				char option_to_save_or_new = stdin.next().charAt(0);
				if(option_to_save_or_new=='n')						// new game
				{	
					newGame();
					count1=true;
				}
				else if(option_to_save_or_new=='s')					//save game
				{
					load();
					count1=true;
				}
				else 
				{
					System.out.println("invalid option press s or n");
					count1=false;
				}

			}while(count1!=true);
			do{
				System.out.println("do you want to quit press y for yes and n for no"); //asking if the user wants to quit or not
				a= stdin.next().charAt(0); 
				if(a=='y')
				{
					count4=true;
					count2=true;
				}
				else if(a=='n')
				{
					count4=false;
					count2=true;
				}
				else
				{
					System.out.println("Invalid input:enter again");
					count2=false;
				}
			}while(count2!=true);
		}while(count4!=true);
	}

	static void newGame()
	{
		int s,size,users,win_seq,current_player=1;
		boolean count4=false,count1=false,count2=false;
		Check_input cc=new Check_input();	
		do 
		{
			System.out.println("Enter the board size 1-999");    //getting the board size from the user(if the input is not a integer an error is displayed and the user is asked to enter again)
			cc.checkinput();
			s=cc.s;
			//the board size cannot be less than one or greater than 999
			if(s>1&&s<=999)
				count2=true;
			//if the board size is 1 no win is possible
			else if(s==1) {
				System.out.println("with board size 1 only one can play and no one will win!So to play a game and win enter board size greater than 2");
				count2=false;
			}
			//the board size cannot be less than one or greater than 999
			else
			{
				count2=false;
				System.out.println("the board size cant be less than 1 or greater than 999");
			}
		}while(count2!=true);
		size=s+1;
		int board[][]=new int[size][size];
		board=boardCreation(size,board);
		do
		{
			System.out.println("Enter no users max 26");//getting no of users from the user(if the input is not a integer an error is displayed and the user is asked to enter again)
			cc.checkinput();
			users = cc.s;
			//the no of users cannot be greater than the square of board size(eg board size 3 no of users can't be greater than 9)
			if(users>((size-1)*(size-1)))
			{
				System.out.println("Error these many players cant play for the given board size");
				count4=false;
			}
			//the number of users cannot be greater than 26 or less than 26
			else if(users<1 || users>26) 
			{
				System.out.println("please enter a value less than 26 and greater than 1");
				count4=false;
			}
			//if the number of users is equal to the square of board size it will result in tie only
			else if(users==((size-1)*(size-1)))
			{
				System.out.println("no one can win and will result in tie!please enter again ");
				count4=false;
			}
			//if there is only one user the game can't be won by anyone except that user
			else if(users==1)
			{
				System.out.println("please make sure to enter no of users greter than 1 to play and win the game");
				count4=false;
			}
			else
				count4=true;
		}while(count4!=true);
		char user[]=user(users);
		for(int j=0;j<user.length;j++)
			System.out.println("Player " +(j+1)+" is "+user[j]);
		do
		{
			System.out.println("Enter win sequence");		// getting the win sequence from the user(if the input is not a integer an error is displayed and the user is asked to enter again)
			cc.checkinput();
			win_seq= cc.s;
			//win sequence cannot be greater than the size of the game board
			if(win_seq + 1 >size)
			{
				System.out.println("error:win sequence cannot be greater than the size of the game board.Enter again");
				count1=false;
			}
			//if win sequence is 1 the first player will always win 
			else if(win_seq==1)
			{
				System.out.println("error:always the first player wins .To play a proper game Enter again");
				count1=false;
			}
			//win sequence can't be 0
			else if(win_seq==0)
			{
				System.out.println("error:win sequence cant be 0.Enter again");
				count1=false;
			}
			else
			{
				count1=true;
			}
		}while(count1!=true);
		gameLogic(size,board,users,user,win_seq,current_player);
	}
	// referred to :-http://www.techiedelight.com/convert-int-array-string-array-java/
	//referreed to :-https://www.geeksforgeeks.org/java-string-trim-method-example/
	static void load() //loading the game if the user chooses to start with a saved game
	{
		try
		{	
			boolean count1=false;
			String file2,st,str="",brac="";
			do 
			{
				System.out.println("enter the file to be read from");
				@SuppressWarnings("resource")
				Scanner stdin =new Scanner(System.in);
				file2=stdin.next();
				File file=new File(file2);
				boolean exsist=file.exists();
				if(exsist==false)  					//if the file entered by the user does not exists ask the user to enter a proper file 
				{
					count1=false;
					System.out.println("no file exsists");
				}
				else
					count1=true;
			}while(count1!=true);
			int size,count=0,c,win_seq,indexs,indexu,indexw,indexb,indexc,current;
			BufferedReader br = new BufferedReader(new FileReader(file2));
			while((st=br.readLine())!=null)
				str=str+st;
			indexs=(str.indexOf("s"))+1;
			indexu=(str.indexOf("u"))+1;
			indexw=(str.indexOf("w"))+1;
			indexc=(str.indexOf("c"))+1;
			indexb=(str.indexOf("b"))+1;
			size=Character.digit(str.charAt(indexs),10);
			c=Character.digit(str.charAt(indexu),10);
			win_seq=Character.digit(str.charAt(indexw),10);
			current=Character.digit(str.charAt(indexc),10);
			System.out.println("size of board "+size+"no of users "+c+"win sequence "+win_seq);
			brac=str.substring(indexb);
			String[] arg=brac.split(" ");
			int boards[]=new int[arg.length];
			for(String a:arg)
			{
				boards[count]=Integer.parseInt(a);
				count++;	
			}
			int size1=(int) Math.sqrt(boards.length);
			count=0;
			int board[][]=new int[size1][size1];
			for(int i=0; i<size1;i++)
			{
				for(int j=0;j<size1;j++)
				{
					board[i][j] = boards[count]; 
					count++;
				}
			}
			char a[]=user(c);
			boardCreation(size,board);
			gameLogic(size,board,c,a,win_seq,current); //Loading the game......
			br.close();
		}catch(IOException e )
		{
			System.out.println(e);
		}

	}
//referred to:- http://www.coderslexicon.com/a-beginner-tic-tac-toe-class-for-java/
	static int[][] boardCreation(int n,int[][] board) //creating the board 
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++) 
			{
				if(i == 0 && j == 0)
				{
					System.out.print(" "+" ");
				}
				else if(i==0 && j>0 && j<=9)
				{
					board[i][j] = j;
					System.out.print("   "+j+"");
				}
				else if(i==0 && j>=10 && j<=99)
				{
					board[i][j] = j;
					System.out.print("   \b"+j+"");
				}
				else if(i==0 && j>=100 && j<=999)
				{
					board[i][j] = j;
					System.out.print("   \b"+j+"");
				}
				else if(j==0 && i>0 && i <=9)
				{
					System.out.print(" ");
					System.out.print(i);
					System.out.print("  ");
				}
				else if(j==0 && i>=10 && i<=99)
				{
					System.out.print(" ");
					System.out.print(i);
					System.out.print(" ");
				}
				else if(j==0 && i>=100 && i<=999)
				{
					System.out.print(" ");
					System.out.print(i);
				}
				else
				{
					char b=(char)board[i][j];
					System.out.print(" "+b+" ");
					if(i > 0 && i<=9 &&j<=n-2 )
					{
						System.out.print("|");
					}
					if(i>9 && i<=99 && j<=n-2)
					{
						System.out.print("|");
					}
					if(i>99 && i<=999 && j<=n-2)
					{
						System.out.print("|");
					}
				}
			}
			if(i>0)
			{
				System.out.print("\n");
			}
			if(i>0 && i<n-1)
			{
				for(int k = 0; k < n-1; k++)
				{
					if(k == 0)
					{
						System.out.print("    ");
					}
					System.out.print("---");
					if(k<n-2)
					{
						System.out.print("+");
					}
				}
			}
			System.out.println("");
		}
		return board;
	}

	static char[] user(int c) //assigning users with symbols in the order XOABCDEFGHIJKLMNPQRSTUVWYZ
	{
		char a[]=new char[c];
		char d='A';
		a[0]='X';
		a[1]='O';
		for(int i=2; i<c ;i++)
		{					
			while(d<='Z')
			{
				if(d=='X')
				{								
					d++;
					continue;
				}
				if(d=='O')
				{
					d++;
					continue;
				}
				else
					a[i]=d;
				break;
			}
			d++;
		}
		return a;
	}
	static void gameLogic(int size,int[][] board,int c,char[] a,int win_seq,int current_player) //the game logic
	{
		int r1,c1,r,co,y1,n;
		char choice;
		boolean count2=true,win=false,count5=false,count3=false,count7=false;
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		n=current_player%c;
		if(n==0)
			y1=0;
		else
			y1=n-1;	
		do
		{	
			for(int y=y1 ;y<c ;y++)
			{			
				do
				{     
					System.out.println("Player " +a[y]+  " turn.");
					Check_input che=new Check_input();
					che.checkinput2(); //getting the row and column from the user (if the input is not a integer an error is displayed and the user is asked to enter again)
					r1=che.r1;
					c1=che.c1;
					//the entered row or column can't be greater than the size of the board
					if(r1>size-1||c1>size-1) 
					{
						System.out.println("Error:row or column out of the board.Enter again");
						count2=false;
					}
					//the user can't enter the same row and column which is already occupied
					else if(board[r1][c1]!=0)
					{
						System.out.println("Error:row or column already taken.Enter again");
						count2=false;
					}
					//row or column cannot be 0
					if(r1==0||c1==0) 
					{
						System.out.println("Error:row or column cant be 0.Enter again");
						count2=false;
					}	
					else
						count2=true;
				}while(count2!=true);
				r=r1;
				co=c1;
				board[r][co]=a[y];
				boardCreation(size,board);
				//checking horizontal 
				int maxr=1;
				for(int ir=co-1;ir>=0;ir--)
				{
					if( board[r][ir]==a[y])
					{
						maxr++;
					}
					else
						break;
				}
				for(int jr=co+1;jr<size;jr++)
				{
					if(board[r][jr]==a[y])
					{
						maxr++;
					}
					else
						break;
				}
				if(maxr>=win_seq)
				{
					win=true;
				}
				else
				{
					win=false;
				}
				if(win==true)
				{
					System.out.println("\n"+a[y]+" won");
					break;
				}
				//checking the verticals
				int maxc=1;

				for(int ic=r-1;ic>=0;ic--)
				{
					if( board[ic][co]==a[y])
					{
						maxc++;
					}
					else 
						break;
				}

				for(int jc=r+1;jc<size;jc++)
				{
					if( board[jc][co]==a[y])
					{
						maxc++;
					}
					else
						break;
				}
				if(maxc>=win_seq)
				{
					win=true;
				}
				else
				{
					win=false;
				}
				if(win==true)
				{
					System.out.println("\n"+a[y]+" won");
					break;
				}	
				// checking the diagonals
				int maxd=1;
				for(int id=r-1,jd=co-1;id>=0;id--,jd--) 
				{
					if(jd>=0 && board[id][jd]==a[y])
					{
						maxd++;	
					}
					else 
						break;
				}
				for(int m=r+1,nn=co+1;m<size;m++,nn++)
				{
					if(nn<size && board[m][nn]==a[y])
					{
						maxd++;
					}
					else
						break;
				}
				if(maxd>=win_seq)
				{
					win=true;
				}
				else
				{
					win=false;
				}	
				for(int mm=r+1,nnn=co-1;mm<size;mm++,nnn--)
				{
					if(nnn<size && board[mm][nnn]==a[y])
					{
						maxd++;
					}
					else
						break;
				}
				if(maxd>=win_seq)
				{
					win=true;
				}
				else
				{
					win=false;
				}							
				if(win==true)
				{
					System.out.println("\n"+a[y]+" won");
					break;
				}
				if(win!=true)//asking the user every time if he wants to quit and save the current game
				{
					do {
						System.out.println("do you want to quit and save the game y or n");
						choice = sc.next().charAt(0);
						if(choice=='y')
						{
							count5=true;
							count7=true;	
						}	
						else if(choice=='n')
						{
							count7=true;
						}
						else
						{
							System.out.println("error :enter y or n only");
							count7=false;
						}

					}while(count7!=true);
					if (choice=='y')
					{
						break;
					}
				}
				current_player++;	
			}
			//checking for tie condition
			if(win!=true)
			{
				for(int g=1;g<size;g++)
				{
					for(int w=1;w<size;w++)
					{
						if(board[g][w]!=0)
						{
							count3 = true;
							continue;
						}
						else
						{
							count3 = false;
							break;
						}
					}
					if(count3 == false)
						break;
				}
			}
			if(count3== true)
			{
				System.out.println("tie");
				break;
			}	
			if(count5==true)
			{	
				saveGame(size,board,c,win_seq,current_player);
				break;
			}
			current_player++;
			y1=0;
		}while(win !=true);
	}
	static void saveGame(int size,int[][] board,int users,int win_seq,int current_player)
	{ //if the user want to stop a game in between and save
		try
		{
			boolean count1=false;
			@SuppressWarnings("resource")
			Scanner stdin=new Scanner(System.in);
			String file2;
			do 
			{	
				System.out.println("enter file name including .txt"); 
				file2=stdin.next();
				File file=new File(file2);
				boolean exsist=file.exists();			//checking if the a file name already exists and asking the user to give another name
				if(exsist==true) 
				{
					count1=false;
					System.out.println("file exsists enter again");
				}
				else
					count1=true;
			}while(count1!=true);
			String file3=file2;
			BufferedWriter writer =new BufferedWriter(new FileWriter(file3));
			writer.write("s"+size);	
			writer.write("u"+users);	
			writer.write("w"+win_seq);
			writer.write("c"+(current_player+1));
			StringBuilder builder=new StringBuilder();
			writer.newLine();
			writer.write("b");
			for(int i=0;i<size;i++)
			{
				for(int j=0;j<size;j++)
				{
					builder.append(board[i][j]+"");
					if(j< board.length -1)
						builder.append(" ");
				}
				builder.append(" ");
			}
			writer.write(builder.toString());
			writer.close();
		}catch (IOException e)
		{
			System.out.println(e);
		}

	}
}
class Check_input // this class checks if the user enters only integer for the values that have data type of integer
{

	int r1,c1,s;
	static boolean count1=true;
	boolean count2=true;
	Check_input()
	{
		r1=0;
		c1=0;
		s=0;
	}
	public void checkinput()
	{
		do 
		{
			count2=true;
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			try {
				s=sc.nextInt();
			}
			catch(InputMismatchException e) 
			{
				System.out.println("incorrect value:enter again");
				count2=false;
			}
		}while(count2!=true);
	}
	public void checkinput2()
	{
		do 
		{
			System.out.println("Enter row and column");
			count1=true;
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			try {
				r1=sc.nextInt();
				c1=sc.nextInt();
			}
			catch(InputMismatchException e) 
			{
				System.out.println("incorrect value for row or column");
				count1=false;
			}
		}while(count1!=true);
	}

}