//referred to-https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

class HuffmanNode 
{ 
	Float data; 
	char c; 
	HuffmanNode left; 
	HuffmanNode right; 
} 
class MyComparator implements Comparator<HuffmanNode> { 
	public int compare(HuffmanNode x, HuffmanNode y) 
	{ 

		return (int) (x.data - y.data); 
	} 
} 
public class Huffman 
{ 
	static char a[]=new char[45];
	static String b[]=new String[45];
	static String c="";
	public static HashMap<Character, String> map1 = new HashMap<Character, String>();

	public static HashMap<Character, String> printCode(HuffmanNode root, String s)
	{ 
		if (root.left == null && root.right == null && Character.isLetterOrDigit(root.c)) 
		{ 
			map1.put(root.c,s);
			return null; 
		} 
		printCode(root.left, s + "0"); 
		printCode(root.right, s + "1"); 
		for (@SuppressWarnings("unused") Entry<Character, String> entry : map1.entrySet()) { 

		}
		return map1;
	} 
	public static void main(String[] args) throws IOException 
	{ 
		String str = "",st="";
		int count1=0;
		BufferedReader br = new BufferedReader(new FileReader("infile.dat"));//Reads from infile.dat in the current working directory
		while((st=br.readLine())!=null)
			str=str+st;
		//Read text from input file. (Ignore blanks, punctuation and symbols while parsing)
		str=str.trim();
		Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
		Matcher match= pt.matcher(str);
		while(match.find())
		{
			String s1= match.group();
			str=str.replaceAll("\\"+s1, "");
		}
		HashMap<Character, Float> map = new HashMap<Character, Float>();
		for (int i1 = 0; i1 <str.length(); i1++)
		{
			char c = str.charAt(i1);
			Float val = map.get(c);        
			if (val != null) 
			{
				map.put(c, (val + 1));
			}
			else 
			{
				map.put(c,(float) 1);
				count1++;
			}
		}
		LinkedHashMap<Character, Float> sortedMap = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		char[] charArray=new char[count1];
		Float[] charfreq=new Float[count1];
		int j=0;
		int i = 0;
		for (Entry<Character, Float> entry : sortedMap.entrySet()) 
		{ 
			charArray[i]=entry.getKey();
			i++;
			charfreq[j]=entry.getValue();
			j++;
		}
		int n=count1;
		//Generate Huffman codes for every symbol in input text 
		//Encoding follows Huffman logic: Most frequent symbol has the smallest binary code. 
		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator()); 
		for (int i1 = 0; i1 < n; i1++)
		{ 
			HuffmanNode hn = new HuffmanNode(); 
			hn.c = charArray[i1]; 
			hn.data = charfreq[i1]; 
			hn.left = null; 
			hn.right = null; 
			q.add(hn); 
		} 
		HuffmanNode root = null; 
		while (q.size() > 1)
		{ 
			HuffmanNode x = q.peek(); 
			q.poll(); 
			HuffmanNode y = q.peek(); 
			q.poll(); 
			HuffmanNode f = new HuffmanNode(); 
			f.data = x.data + y.data; 
			f.c = '-'; 
			f.left = x; 
			f.right = y;  
			root = f;  
			q.add(f); 
		} 
		HashMap<Character, String> map1 = new HashMap<Character, String>();
		map1 = printCode(root, ""); 
		HashMap<Character, Float> result = new HashMap<>();
		int h=str.length();
		//Generate frequency table for every symbol in input text. 
		for (Map.Entry<Character,Float> entry : sortedMap.entrySet()) 
		{ 
			char key1 = entry.getKey();
			float current = entry.getValue();
			float avg=(100*(current/h));
			result.put(key1,avg);

		} 
		BufferedWriter writer = new BufferedWriter(new FileWriter("outfile.dat"));//Using outfile.dat for the output file.
		//Write frequency table to output file (in DESC order of frequency)
		//Output file formatted for easy reading: 
		writer.write("Symbol"+" "+"Frequency");
		writer.newLine();
		LinkedHashMap<Character, Float> sortedMap4 = new LinkedHashMap<>();
		result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedMap4.put(x.getKey(), x.getValue()));
		for (Map.Entry<Character,Float> entry : sortedMap4.entrySet())
		{ 
			writer.write(entry.getKey() + ",     " + entry.getValue()+"%"); 
			writer.newLine();
		}
		writer.newLine();
		//write to output file (in DESC order of frequency).
		//Output file formatted for easy reading: 
		writer.write("Symbol"+" "+"Huffman Codes");
		writer.newLine();
		int bit_rate=0;
		for (Entry<Character, Float> me : sortedMap.entrySet()) 
		{
			for (Entry<Character, String> me1 : map1.entrySet()) 
			{
				if(me.getKey().equals(me1.getKey())) 
				{
					int le=length(me1.getValue());
					//Compute length of coded message in terms of Huffman codes. 
					bit_rate=(int) (bit_rate+(le*me.getValue()));
					writer.write(me.getKey()+",      "+me1.getValue());
					writer.newLine();
				}
			}

		}
		writer.newLine();
		//Write length of coded message in terms of Huffman codes to output file.
		writer.write("Total Bits: "+" "+bit_rate);
		writer.close();
	}
	private static int length(String value)
	{
		int count=0;
		for(int i=0;i<value.length();i++)
		{
			count++;
		}
		return count;
	}
}