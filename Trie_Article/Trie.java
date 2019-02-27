import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
//Implementation of trie data structure
class Trie
{
	boolean isLeaf;
	Map<Character, Trie> children;

	Trie() { 
		isLeaf=false;
		children=new HashMap<>();
	}
	public void insert(String key)
	{
		Trie curr=this;
		for (int i=0; i<key.length(); i++)
		{
			if (curr.children.get(key.charAt(i))==null)
				curr.children.put(key.charAt(i),new Trie());
			curr=curr.children.get(key.charAt(i));
		}
		curr.isLeaf=true;
	}
	public boolean search(String key)
	{
		Trie curr=this;
		for (int i=0; i<key.length(); i++)
		{
			curr=curr.children.get(key.charAt(i));
			if (curr==null)
				return false;
		}
		return curr.isLeaf;
	}
	public static void main(String args[]) throws IOException
	{
		Trie head=new Trie();	
		String st="",str="";
		BufferedReader br = new BufferedReader(new FileReader("companies.dat"));//Input: Read data from file named "companies.dat".
		while((st=br.readLine())!=null)
			str=str+st+"\n";
		String[] comp=str.split("\n");
		String s3=str;
		String[] print=s3.split("\n");
		String[] printfin=new String[print.length];
		String[][]print1=new String[print.length][print.length];
		int k=0;
		for(int i=0;i<comp.length;i++)
		{
			String s4=print[i].replace("\t","777");
			String[] s5=s4.split("777");
			for(int j=0;j<s5.length;j++)
			{
				print1[i][j]=s5[j];
				if(j==0)
					{
					printfin[k]=s5[j];
					k++;
					}
			}
		}
		String s="";
		String[][] comp_tab = new String[comp.length][10000];
		for(int i=0;i<comp.length;i++)
		{
			//Normalizing companies names
			s=comp[i].replace("\t", "777");
			s=s.replaceAll("\\p{Punct}&&[^&/.,]]+", "");
			s=s.replace("-", " ");
			s=s.replace("'s","");
			s=s.replace("/", " ");
			s=s.replace(".", "");
			s=s.replace(",", "");
			s=s.replace(" & ", " ");
			s=s.replace("&", " ");
			s=s.replaceAll("[\\p{Punct}]+", "");
			s=s.trim();
			String s1[]=s.split("777");
			for(int j=0;j<s1.length;j++)
			{
				comp_tab[i][j]=s1[j];
				if(comp_tab[i][j]!=null)
					head.insert(comp_tab[i][j]);
			}
		}
		int maxlen=0;
		int maxchar=0;
		for(int i=0;i<comp.length;i++)
		{
			for(int j=0;j<comp.length;j++)
			{
				if(comp_tab[i][j]!=null)
				{	String[] max=comp_tab[i][j].split(" ");
					if(maxlen<max.length)
						maxlen=max.length;
					}
			}
		}
		for(int i=0;i<printfin.length;i++)
		{
			int max=printfin[i].length();
			if(maxchar<max)
				maxchar=max;

		}
		System.out.println("enter the news article." );	// Input: Prompt user for a news article.
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		String s1="";
		while(in.hasNext()) //read till (.).
		{
			String s2=in.next();
			s1=s1+s2+" ";
			
			if(End(s2))
			{
				break;
			}
		}
		String s4=s1;
		// Stopwords: Ignore words "a", "an", "the", "and", "or", and "but". 
		s1=s1.replaceAll("\\p{Punct}&&[^&'-.,]]+", "");
		s1=s1.replace("'s", "");
		s1=s1.replace("'", "");
		s1=s1.replace("-", " ");
		s1=s1.replace(".", "");
		s1=s1.replace("/", " ");
		s1=s1.replace(",", "");
		s1=s1.replace(" & ", " ");
		s1=s1.replace("&", " ");
		s1=s1.replace(" a "," ");
		s1=s1.replace(" an "," ");
		s1=s1.replace(" the "," ");
		s1=s1.replace(" or "," ");
		s1=s1.replace(" but "," ");
		s1=s1.replace(" and "," ");
		s1=s1.replace("And ","");
		s1=s1.replace("But ","");
		s1=s1.replace("Or ","");
		s1=s1.replace("The ","");
		s1=s1.replace("An ","");
		s1=s1.replace("A "," ");
		s1=s1.replaceAll("[\\p{Punct}]+", "");
		s1=s1.trim();
		String[] arg1=s1.split(" +");
		int count=arg1.length;
		int length=count;
		s4=s4.replaceAll("\\p{Punct}&&[^&'-.,]]+", "");
		s4=s4.replace("'s", "");
		s4=s4.replace("'", "");
		s4=s4.replace("-", " ");
		s4=s4.replace("/", " ");
		s4=s4.replace(".", "");
		s4=s4.replace(",", "");
		s4=s4.replace(" & ", " ");
		s4=s4.replace("&", " ");
		s4=s4.replaceAll("[\\p{Punct}]+", "");
		s4=s4.trim();
		String[] arg=s4.split(" ");
		int count11=arg.length;
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		int i=0;
		
		//Searching
		while(i<count11)
		{
			boolean found=false;
			found=head.search(arg[i]);
			int len=0;
			int count1=0;
			String a=arg[i];
			boolean d=false;
			if(found==false || found==true)
				for(int j=0;j<comp_tab.length;j++)
				{
					for(int j1=0;j1<comp_tab.length;j1++)
					{
						if(comp_tab[j][j1]!=null)
						{
							String[] ar=comp_tab[j][j1].split(" ");
							if(ar[0].equals(arg[i]) && i+1<count11)
							{
								if(comp_tab[j][j1].contains(arg[i]+" "+arg[i+1]))
								{
									count1++;
								}
							}
						}
					}
				}
			if(count1==0 && found==false) 
			{
				i=i+1;
				continue;
			}
			if(found==false || found==true && count1>0)
			{
				for(int i1=i+1;i1<arg.length+1;i1++)
				{
					a=a+" "+arg[i1];
					a=a.trim();
					String[] ar=a.split(" ");
					found=head.search(a);
					if(found==false && i1==arg.length-1)
					{
						
						i=i+1;
						break;
					}
					
					if(found==false && ar.length>maxlen)
					{
						i=i+1;
						break;
					}
					if(found==true)
					{
						String[] as=a.split(" ");	
						len=as.length;
						if(a.contains(" a ")||a.contains(" and ")||a.contains(" the ")||a.contains(" or ")||a.contains(" an ")||a.contains(" but ")||a.contains("The 
")||a.contains("An ")||a.contains("A ")||a.contains("But ")||a.contains("Or ")||a.contains("And "))
							length=length+1;
						found=true;
						length=length-(len-1);
						break;
					}
				}
			}
			else 
			{
				a=arg[i];
				len=1;
			}

			for (Entry<String, Integer> entry : map.entrySet()) 
			{ 	
				d=false;
				if(found==true && entry.getKey().equals(a))
				{
					map.put(a, map.get(a) + 1);
					d=true;
					break;
				}
			}
			if(d==false && found==true)
			{
				map.put(a, 1);
			}
			i=i+len;
		}
		HashMap<String,Integer> map1=new HashMap<String,Integer>();
		for(int i1=0;i1<comp_tab.length;i1++)
			map1.put(comp_tab[i1][0],0);
		int[] a=new int[comp_tab.length];
		for(int a1=0;a1<comp_tab.length;a1++)
		{
			a[a1]=0;
		}
		for (Entry<String, Integer> entry : map.entrySet()) 
		{ 
			for(int i1=0;i1<comp_tab.length;i1++)
			{
				for(int j=0;j<100;j++)
				{
					if(comp_tab[i1][j]!=null)
					{
						if(entry.getKey().equals(comp_tab[i1][j]))
						{
							a[i1]=a[i1]+entry.getValue();
						}
					}
				}
			}	 
		}
		for(int a2=0;a2<comp_tab.length;a2++)
		{
			map1.replace(comp_tab[a2][0],a[a2]);
		}
		String format = "|%1$-"+(maxchar)+"s|%2$-11s|%3$-10s|";
		//Calculate: Company's Relevance (Must be decimal a value up to 4 digits. Ex: 6.000%)
		DecimalFormat df = new DecimalFormat("###.####");
		//Calculate: Company's hit count (includes synonym)
		// Every line should have Company Name, Hit Count, and the Relevance
		System.out.format(format,"Company Name","Hit Count","Relavance");		
		float tot=(float) 0;
		for (Entry<String, Integer> entry1 : map1.entrySet()) 
		{ 
			String strr="";
			float value1=entry1.getValue();
			float average=(float) (value1/length)*100;
			String aver=df.format(average);
			tot=(tot+entry1.getValue());
			System.out.println("");
			for(int j=0;j<printfin.length;j++)
			{
				String se=printfin[j];
				se=se.replaceAll("[\\p{Punct}&&[^&'-/.,]]+", "");
				se=se.replace("'s", " ");
				se=se.replace("-", " ");
				se=se.replace("/", " ");
				se=se.replace(".", "");
				se=se.replace(",", "");
				se=se.replace(" & ", " ");
				se=se.replace("&", " ");
				se=se.replaceAll("[\\p{Punct}]+", "");
				if(se.equals(entry1.getKey()))
					{
					strr=printfin[j];
					break;
					}
			}

			System.out.format(format,strr,entry1.getValue(),aver+"%");
		}
		System.out.println("");
		System.out.println("");
		float avg1=(tot/length)*100;
		String avg11=df.format(avg1);
		//Output: Second last row should have Total, Total Hit Count, and Total Relevance
		System.out.format(format,"Total",(int)tot,avg11+"%");
		System.out.println("");
		System.out.println("");
		//Output: The last row should have the total number of words in the file
		System.out.println("Total Words       "+length);
		br.close();
	}
	private static boolean End(String s1)
	{
		boolean count=false;
		for(int i=0;i<s1.length();i++)
		{
			char a=s1.charAt(0);
			
			if(a=='.')
			{
				
				count=true;
					
			}
		}
		return count;

	}

}	