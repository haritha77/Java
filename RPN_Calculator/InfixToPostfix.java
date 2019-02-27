import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class InfixToPostfix 
{
	public static void main(String args[])
	{
		String input;
		String str;
		boolean count1=false,count2=false;
		do {
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			do {
				System.out.println("Please enter the infix expression with space between each operator and operands");
				input=sc.nextLine();	//getting a single line input from user separated by spaces between operands and operators from user
				input=input.trim();
				str=input.replaceAll("POW","^");//replacing POW with ^(bonus points)
				String[] inputst=str.split(" ");//handles spaces in infix expression by ignoring them
				int size=inputst.length;
				for(int i=0;i<size;i++)
				{
					char b = inputst[i].charAt(0);
					boolean a=checkBrackets(inputst);
					boolean b1=checkOperand(inputst);
					boolean d=checkBrack(inputst);
					// an error is raise if the infix expression is written without space or only a single digit is given as an input Example : 2+1
					if(size-1==0)
					{
						count2=false;
						System.out.println("Please enter with space ");
						break;
					}
					// an error is raised if the infix expression has negative operands example: -1 + 2
					else if(i==0 && b=='-')
					{
						count2=false;
						System.out.println("Please enter non negative operands");
						break;
					}

					// error is raised if a postfix or prefix expression is given (Ex: + 1 2 or 1 2 +)
					else if(((i==0)&& (b=='+'||b=='-'||b=='*'||b=='/'||b=='^'||b=='%'))||(( i==size-1) && (b=='+'||b=='-'||b=='*'||b=='/'||b=='^'||b=='%')))	
					{
						count2=false;
						System.out.println("Please enter properly");
						break;
					}
					// error for missing either ( or ) or unbalanced brackets [Ex: ) 2 + 3 ( or ( 2 + 3 or ( ( 2 + 3 )  ]
					else if(a==false)
					{
						System.out.println("Please enter properly");
						count2=false;
						break;
					}
					// here it gives error if the infix is like 1 + / 3 or even for negative numbers ( 1 + -2)
					else if(b1==false)
					{
						System.out.println("Please enter properly");
						count2=false;
						break;
					}
					//checks for error conditions like "operand(" [ 1( ] or "(operator" [ (+ ] or ")operand" [ )4 ] or "operator)" [ +) ]
					else if(d==false)
					{
						System.out.println("Please enter properly");
						count2=false;
						break;
					}
					// throws error if the infix input does contain elements other than digits or operators(+,-,%,^,*,/)
					else if(Character.isDigit(b)||b=='+'||b=='-'||b=='*'||b=='/'||b=='^'||b=='%'||b=='('||b==')')
					{
						count2=true;
					}
					else
					{
						count2=false;
						break;
					}

				}
			}while(count2!=true);
			Queue<String> postQ=infix_to_postfix(str);
			infix_to_postfix_eval(postQ);
			System.out.println("enter y for yes or n for no to exit");		// asking if the user wants to quit or not if no prompt the user to enter infix ex
			char a=sc.next().charAt(0);
			if(a=='y')
				count1=true;
			else
				count1=false;
		}while(count1!=true);
	}	

	// function to throws error if the infix input does contain elements other than digits or operators(+,-,%,^,*,/)
	private static boolean checkBrack(String[] args1)
	{
		for(int i=0;i<args1.length;i++)
		{	char c=args1[i].charAt(0);

		if(c=='('&& i!=args1.length-1 )
		{
			char e=args1[i+1].charAt(0);

			if(e=='+' ||e=='-' ||e=='*' ||e=='%' ||e=='/' ||e=='^' )
			{

				return false;
			}
		}

		else if(c=='(' && i!=0)
		{
			if(Character.isDigit(args1[i-1].charAt(0)))
			{
				return false;
			}
		}

		else if(c==')'&& i!=args1.length-1)
		{	
			if(args1[i+1].charAt(0)=='1'||args1[i+1].charAt(0)=='2'||args1[i+1].charAt(0)=='3'||args1[i+1].charAt(0)=='4'||args1[i+1].charAt(0)=='5'||args1[i+1].charAt(0)=='6'||args1[i+1].charAt(0)=='7'||args1[i+1].charAt(0)=='8'||args1[i+1].charAt(0)=='9'||args1[i+1].charAt(0)=='0')
			{

				return false;
			}
		}
		else if(c==')' && i!=0)
		{
			char e=args1[i-1].charAt(0);
			if(e=='+' ||e=='-' ||e=='*' ||e=='%' ||e=='/' ||e=='^' )
			{
				return false;
			}
		}
		}

		return true;
	}

	// here this function it gives error if the infix is like 1 + / 3 or even for negative numbers
	private static boolean checkOperand(String[] args1) {
		int count_operand=0;
		int count_operator=0;
		for(int i=0;i<args1.length;i++)
		{
			char c=args1[i].charAt(0);
			if(c=='+'||c=='-'||c=='*'||c=='/'||c=='%'||c=='^' )
				count_operator++;
			else if(Character.isDigit(c))
				count_operand++;

		}
		if(count_operand-count_operator==1)
			return true;
		else
			return false;
	}

	// function to throw error for missing either ( or ) or unbalanced brackets
	private static boolean checkBrackets(String[] args1) {
		int count_brack=0;
		for(int i=0;i<args1.length;i++)
		{
			char c=args1[i].charAt(0);
			if(c=='(')
				count_brack++;
			else if(c==')')
				count_brack--;

		}
		if(count_brack==0)
			return true;
		else
			return false;
	}
	//reffered: https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
	//this function converts the infix to postfix using infix queue ,postfix queue and operator stack
	private static Queue<String> infix_to_postfix(String s)
	{
		String[] infix=s.split(" ");
		boolean count1=false;
		Queue<String> infixQ=new LinkedList<>();
		for(int i=0;i<infix.length;i++)
			infixQ.add(infix[i]);
		Stack<Character> stack= new Stack<>();
		Queue<String> postQ=new LinkedList<>();
		while(!infixQ.isEmpty())
		{	
			count1=false;
			String t=infixQ.poll();
			char operand=t.charAt(0);
			for(int i=0;i<t.length();i++)
			{
				char a=t.charAt(i);
				String decimalPattern = "([0-9]*)\\.([0-9]*)";  		//reffered to http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
				if(Character.isDigit(a)|| Pattern.matches(decimalPattern, t))	//supports decimal values
					count1=true;
				else
				{
					count1=false;
					break;
				}
			}
			if(count1==true)
			{
				postQ.add(t);
			}
			else if(operand=='(')
			{
				stack.push(operand);
			}
			else if(operand==')')
			{
				//int index=stack.search('(');
				while(!stack.isEmpty()&& stack.peek()!='(')
				{	
					char poped=stack.pop();
					if(poped!='(')
						postQ.add(Character.toString(poped));			
				}
				stack.pop();
			}
			else if(operand=='+'||operand=='-'||operand=='*'||operand=='/'||operand=='^'||operand=='%')
			{
				if(stack.isEmpty())
				{
					stack.push(operand);
				}
				else
				{
					if(priority(operand)<priority(stack.peek())||priority(operand)==priority(stack.peek()))
					{
						char poped=stack.pop();
						postQ.add(Character.toString(poped));
						stack.push(operand);
					}
					else if(priority(operand)>priority(stack.peek()))
					{
						stack.push(operand);
					}
				}
			}

		}
		while(!stack.isEmpty())
		{
			char poped=stack.pop();
			postQ.add(Character.toString(poped));
		}
		System.out.println("postfix expression: ");
		for(String st : postQ)
		{ 
			System.out.print(st+ " ");	// postfix expression is printed with spaces btw operands and operator
		}


		return postQ;
	}
	// this function evaluates the postfix expression and gives result and it follows BODMAS rule
	public static void infix_to_postfix_eval(Queue<String> postQ)
	{
		try{
			Stack<Float> eval=new Stack<>();

			while(!postQ.isEmpty())
			{
				boolean count2=false;
				String teval=postQ.poll();
				char op=teval.charAt(0);
				for(int i=0;i<teval.length();i++)
				{
					char a=teval.charAt(i);
					if(Character.isDigit(a))
						count2=true;
					else
					{
						count2=false;
						break;
					}
				}
				String decimalPattern = "([0-9]*)\\.([0-9]*)";
				if(count2==true||Pattern.matches(decimalPattern, teval))	//supports decimal values
				{
					eval.push(Float.valueOf(teval));
			
				}
				else
				{
					Float topNum=eval.peek();
					eval.pop();
					Float nextNum=eval.peek();
					eval.pop();
					float answer=0;
					switch(op)
					{
					case '+':
						answer=nextNum+topNum;
						break;
					case '-':
						answer=nextNum-topNum;
						break;
					case '*':
						answer=nextNum*topNum;
						break;
					case '/':
						if (topNum==0)
							   throw new ArithmeticException("Not finite:Divide by Zero Exception");	//Divide By Zero Exception
						else
							answer=nextNum/topNum;
						break;
					case '%':
						answer=nextNum%topNum;
						break;
					case '^':
						answer=(int) Math.pow(nextNum,topNum);
					}
					if(answer==Double.NaN)
						throw new ArithmeticException("NaN exception");		//NaN Exception
					  eval.push((float) (Math.round(answer*100.0)/100.0));	//rounding the answer to 2 decimal places
				}

			}
				
			System.out.println("\nanswer: "+eval.pop());

		}catch(ArithmeticException e)
		{
			System.out.println("\n Arithematic Exception occured"+e); 	
		}
	}

	// this function determines the priority of the operator

	public static int priority(Character c)
	{
		if(c=='^')
			return 3;
		else if(c=='*'|| c=='/'|| c=='%')
			return 2;
		else if(c=='+'||c=='-')
			return 1;
		return 0;
	}	

}