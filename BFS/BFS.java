import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BFS
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("infile.dat"));
		String str="",st="";
		while((st=br.readLine())!=null)
			str=str+st+" ";
		String[] fir=str.split(" ");
		int v=Integer.valueOf(fir[0]);
		//adj matrix
		int[][] adjmat=new int[v][v];
		for(int i=0;i<adjmat.length;i++)
		{
			for(int j=0;j<adjmat.length;j++)
				adjmat[i][j]=-1;
		}
		for(int i=2;i<fir.length-1;i=i+2)
		{
			int ad1=Integer.parseInt(fir[i]);
			int ad2=Integer.parseInt(fir[i+1]);
		
			adjmat[ad1][ad2]=0;
			adjmat[ad2][ad1]=0;
			
		}
		//visited is similar to unused
		boolean[] visited=new boolean[v];
		for(int i=0;i<v;i++)
			visited[i]=false;
		//q is S'
		Queue<Integer> q=new LinkedList<>();
		q.add(0);
		//dfn number
		int dfn=0;
		visited[0]=true;
		while(!q.isEmpty())
		{
			int a=q.poll();
			dfn++;
			for(int i=0;i<adjmat.length;i++)
			{
				int b=adjmat[a][i];
				if(b==0 && visited[i]==false)
				{
					q.add(i);
					visited[i]=true;	
				}
			}
			//output
			System.out.print(a+"  "+dfn+"\n");
		}
		br.close();
	}
}