import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
//https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
// is an implementation of BST of type integer
public class Sortedset{
	class Node { 
		int key; 
		Node left, right; 
		public Node(int item) { 
			key = item; 
			left = right = null; 		// BST implementation as Linked list
		} 
	} 

	static Node root; 

	Sortedset() {  
		root = null;  
	} 
	//to remove from BST and to handle child promotion if deleted node is non-leaf	
	void remove(int key) 
	{ 
		root = deleteRec(root, key); 
	} 
	Node deleteRec(Node root, int key) 
	{ 

		if (root == null)  return root;
		if (key < root.key) 
			root.left = deleteRec(root.left, key); 
		else if (key > root.key) 
			root.right = deleteRec(root.right, key);  
		else
		{ 
			if (root.left == null) 
				return root.right; 
			else if (root.right == null) 
				return root.left; 

			root.key = minValue(root.right); 
			root.right = deleteRec(root.right, root.key); 
		} 
		return root; 
	} 

	int minValue(Node root) 
	{ 
		int minv = root.key; 
		while (root.left != null) 
		{ 
			minv = root.left.key; 
			root = root.left; 
		} 
		return minv; 
	} 
	//to check if the tree is empty
	boolean empty() 
	{
		if (root == null)
			return true;
		else
			return false;

	}
	// to insert an element into BST at correct position ignoring duplicates 
	void add(int key) { 
		root = insertRec(root, key); 
	} 
	Node insertRec(Node root, int key)
	{ 
		if (root == null) { 
			root = new Node(key); 
			return root; 
		} 
		if (key < root.key) 
			root.left = insertRec(root.left, key); 
		else if (key > root.key) 
			root.right = insertRec(root.right, key); 

		return root; 
	} 
	// to search BST for a given value
	int contains(int k)
	{
		root=searchre(root,k);
		if(root!=null)
			return root.key;
		return -1;
	}
	public Node searchre(Node root, int key) 
	{ 
		if (root==null || root.key==key) 
		{	
			return root; 
		}
		if (root.key > key) 
			return searchre(root.left, key); 
		return searchre(root.right, key); 
	} 
	void inorder()  { 
		inorderRec(root); 
	} 
	void inorderRec(Node root) { 
		if (root != null) { 
			inorderRec(root.left); 
			System.out.println(root.key); 
			inorderRec(root.right); 
		} 
	} 
	public static void main(String[] args) throws IOException { 
		Sortedset tree = new Sortedset(); 
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		int h=0;
		String str ="  ",st;
		try {
			BufferedReader br = new BufferedReader(new FileReader("infile.dat"));//reading from infile.dat
			while((st=br.readLine())!=null)
				str=str+st;
			str=str.trim();
			str = str.replaceAll("\\s","");	//white-spaces are handled 
			String arg[]=str.split(",");	//the input file is comma separated list of numbers
			int a1[]=new int[arg.length];	//translating input data into an array of numbers
			for(int i=0;i<arg.length;i++) 
			{
				a1[h]=Integer.parseInt(arg[i]);
				tree.add(a1[h]);
				h++;
			}

			tree.inorder(); 
			//test case for deletion
			/* 
			System.out.println("enter the element to be delete");
			int c=sc.nextInt();
	        	tree.remove(c);
	        	tree.inorder();
			 */
			//searching of an element
			System.out.println("enter the element to be searched");//prompt the user for the input value to be searched
			int b=sc.nextInt();
			int a=tree.contains(b);
			if(a==b)
				System.out.println("yes");
			else
				System.out.println("no");

			br.close();

		} catch (InputMismatchException e) {

			System.out.println("Error:InputmismatchException");//non numeric values are handled
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error:Filenotfound or invalid file");//invalid file is handled
		}
	} 
} 
