import java..io.*;
import java.util.*;
public class bubblesort
{
	public static void main(String[] args)
	{
		int l=1,size;
		@SuppressWarnings("unused")
		Scanner sc = new Scanner(System.in);
		int a[]={9,5,2,7,0,4};					//test case in data structure of type integer
		size=a.length;
		System.out.println("array is");			
		for(int i=0;i<size;i++)
		{
			System.out.println(a[i]+"\t");
		}
		System.out.println("length of array is "+size);
			for(int i=0;i<size;i++)
			{
				int k=i/2;
				if(i%2==0)
				{
					for(int j=k;j<=size-(k+2);j++)
					{
						if(a[j]>a[j+1])
						{
							int temp=a[j];
							a[j]=a[j+1];
							a[j+1]=temp;
						}
						else
							continue;
					}
					System.out.println("\nforward pass"); //largest value is sorted at the end of the forward pass
					for(int k1=0;k1<size;k1++)
					{
						System.out.print(a[k1]+"\t");
					}
					
				}
				else
				{	
					l=1;
					for(int j=size-(l+1);j>(l-1);j--)
					{	
						if(a[j]<a[j-1])
						{
							int temp=a[j];
							a[j]=a[j-1];
							a[j-1]=temp;	
						}
						else
							continue;
					}
					l++;
					System.out.println("\nbackward pass"); //smallest value is sorted at the end of the  backward pass
					for(int k1=0;k1<size;k1++)
					{
						System.out.print(a[k1]+"\t");
					}	
				}
			}
			System.out.println("\nSorted array");		// output sorted data
			for(int i=0;i<size;i++)
			{
				
				System.out.println(a[i]);
			}
			
		}
	
}