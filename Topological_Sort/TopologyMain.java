//reffered to:-https://www.geeksforgeeks.org/all-topological-sorts-of-a-directed-acyclic-graph/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*; 
//Graph implementation
class Gr
{ 
	int V;
	List <Integer> adj[]; 
	@SuppressWarnings("unchecked")
	public Gr(int V)
	{ 
		this.V = V; 
		adj = new ArrayList[V]; 
		for(int i = 0; i < V; i++) 
			adj[i]=new ArrayList<Integer>(); //Adjacency list
	} 
	public void addEdge(int u,int v) 
	{ 
		adj[u].add(v); 
	} 
	//topological sort 1
	public void topologicalSort() 
	{ 
		int indeg[] = new int[V];     
		for(int i = 0; i < V; i++) 
		{ 
			ArrayList<Integer> temp = (ArrayList<Integer>) adj[i]; 
			for(int node : temp) 
			{ 
				indeg[node]++; 
			} 
		} 
		Queue<Integer> q = new LinkedList<Integer>(); 
		for(int i = 0;i < V; i++) 
		{ 
			if(indeg[i]==0) 
				q.add(i); 
		} 
		int count1 = 0; 
		Vector <Integer> topOrder=new Vector<Integer>(); 
		while(!q.isEmpty()) 
		{ 
			int u=q.poll(); 
			topOrder.add(u); 
			for(int node:adj[u]) 
			{ 
				// If in-degree becomes zero, add it to queue 
				if(--indeg[node] == 0) 
					q.add(node); 
			} 
			count1++; 
		}       
		if(count1!=V) 
		{ 
			System.out.println("There exists a cycle in the graph"); 
			return ; 
		}             
		for(int i:topOrder) 
		{ 
			System.out.print(i+" "); 
		} 
	} 
	//topological sort 2
	public void topologicalSort2() {
		int indeg1[] = new int[V];     
		for(int i = 0; i < V; i++) 
		{ 
			ArrayList<Integer> temp = (ArrayList<Integer>) adj[i]; 
			for(int node:temp) 
			{ 
				indeg1[node]++; 
			} 
		} 
		Queue<Integer> q = new LinkedList<Integer>(); 
		int count=0;
		for(int i = 0;i < V; i++) 
		{ 
			if(indeg1[i]==0) 
				q.add(i); 
			if(q.size()==2)
				count++;
			//tweaking one of the id(0)
			if(q.size()==2 && count==1)
			{
				Stack<Integer> stack = new Stack<>(); 
				while (!q.isEmpty()) { 
					stack.add(q.peek()); 
					q.remove(); 
				} 
				while (!stack.isEmpty()) { 
					q.add(stack.peek()); 
					stack.pop(); 
				} 
			}
		} 
		int count1 = 0;
		Vector <Integer> topOrder=new Vector<Integer>(); 
		while(!q.isEmpty()) 
		{ 
			int u=q.poll(); 
			topOrder.add(u); 
			for(int node:adj[u]) 
			{ 
				// If in-degree becomes zero, add it to queue 
				if(--indeg1[node] == 0) 
					q.add(node);
				if(q.size()==2)
					count++;
				//tweaking one of the id(0)
				if(q.size()==2 && count==1)
				{
					Stack<Integer> stack = new Stack<>(); 
					while (!q.isEmpty())
					{ 
						stack.add(q.peek()); 
						q.remove(); 
					} 
					while (!stack.isEmpty())
					{ 
						q.add(stack.peek()); 
						stack.pop(); 
					} 
				}
			} 
			count1++; 
		}       
		if(count1!=V) 
		{ 
			System.out.println("There exists a cycle in the graph"); 
			return ; 
		}             
		for(int i:topOrder) 
		{ 
			System.out.print(i+" "); 
		} 
	} 
}
class TopologyMain
{ 
	public static void main(String args[]) throws IOException 
	{ 
		String st="",str="";
		int e=2;
		BufferedReader br = new BufferedReader(new FileReader("infile.dat")); // Reading input from file withno absolute path
		while((st=br.readLine())!=null)
			str=str+st+" ";
		String[] s1=str.split(" ");
		int node=Integer.parseInt(s1[0]);
		int edge=Integer.parseInt(s1[1]);
		Gr g=new Gr(node); 
		for(int i=0;i<edge;i++)
		{
			int e1=Integer.parseInt(s1[e]);
			int e2=Integer.parseInt(s1[e+1]);
			g.addEdge(e1,e2);
			e=e+2;
		}
		System.out.println("Following is a Topological Sort 1"); 
		g.topologicalSort(); 
		System.out.println("");
		System.out.println("Following is a Topological Sort 2"); 
		g.topologicalSort2();
		br.close();
	} 
}

