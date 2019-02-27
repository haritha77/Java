import java.io.*;
import java.util.*;
class Advance
{
	public static void main(String args[])
	{
		int i;
		
		Scanner c=new Scanner(System.in);
		System.out.println("Enter the start sequence");
		int st = c.nextInt();
		System.out.println("Enter the end sequence");
		int end = c.nextInt();
		generate(st,end);

		
	}	
	public static void generate(int a,int b)
	{
	int size=(b-a)+1;
	
	int[] d=new int[size];
	for(int i=0;i<size;i++)
	{
	d[i]=a;
	a++;
	
	}
	
	FizzBuzzer(d);	
	}
	public static void FizzBuzzer(int[] h)
	{
	int s=h.length;
	for(int i=0;i<s;i++)
	{
		if(h[i]%3==0 && h[i]%5==0)
			System.out.println("BuzzFizz");
		else if(h[i]%3==0)
			System.out.println("Buzz");
		else if(h[i]%5==0)
			System.out.println("Fizz");
		else 
			System.out.println(h[i]);
	}

}

}