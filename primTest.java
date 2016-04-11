/*
* JAVA implementation of Prims MST algorithm
*/
package prim;
import java.util.*;
import java.io.File;

public class PrimTest
{
	public static void primStart()
	{
		Graph input = Graph.build("input2.txt");
		int result= Prim.primMST(input, 0);
		printResult(input);
	}

	private static void printResult(Graph result)
	{
		System.out.println("Result:");
		for(Vertex a: result.vertices)
		{
			System.out.println(a.vertexID+"->"+a.pi);
		}
	}
}

class Prim
{
	public static int primMST(Graph input, int rootID)
	{
		for(Vertex a: input.vertices)
		{
			a.key=Integer.MAX_VALUE;
			a.pi=-1;
		}

		input.vertices.get(rootID).key=0;

		PriorityQueue<Vertex> q= new PriorityQueue<Vertex>(input.vertices.size(), new VertexComparator());
		for(Vertex a: input.vertices)
		{
			q.offer(a);
		}

		while(q.size()!=0)
		{
//			Vertex min=q.poll();
			Vertex min=q.peek();
			for(Edge a: min.adjascencyList)
			{
				Vertex v=input.vertices.get(a.otherVertexID);
				if(q.contains(v) && a.weight<v.key)
				{
					v.pi=min.vertexID;
					v.key=a.weight;
//					System.err.println("min.vertexID "+min.vertexID+" a.weight "+a.weight);
				}
			}
			/*System.err.println("VertexID "+min.vertexID);
			for(Vertex v:input.vertices)
			{
				System.err.println("vid "+v.vertexID+" vpi "+v.pi+" vkey "+v.key);
			}*/
			q.remove(min);

		}

		return 0;
	}
}

class Graph
{
	public ArrayList<Vertex> vertices= new ArrayList<Vertex>();

	public static Graph build(String fileName)
	{
		Graph graph= new Graph();
		try(Scanner scanner= new Scanner(new File(fileName)))
		{
			int size=scanner.nextInt();
                        scanner.nextInt();//ignoring #edges
//			System.err.println(size);
			for(int i=0; i<size; i++)
			{
				Vertex nw = new Vertex();
				nw.vertexID=i;
				for(int j=0; j<size; j++)
				{
					int val=scanner.nextInt();
//					System.err.println(" val "+val);
					if(val!=0)
					{
						Edge edge=new Edge();
						edge.weight=val;
						edge.otherVertexID=j;
						nw.adjascencyList.add(edge);
						//System.err.println(edge.weight);
					}
				}
				graph.vertices.add(nw);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

/*		for(Vertex x:graph.vertices)
		{
			System.err.println(x.vertexID+" ");
			for(Edge e:x.adjascencyList)
			{
				System.err.println(e.otherVertexID+" "+e.weight);
			}
		}
*/		return graph;
	}
}

class Vertex
{
	public Vertex()
	{

	}
	public Vertex(int id)
	{
		vertexID=id;
	}

	public ArrayList<Edge> adjascencyList= new ArrayList<Edge>();
	public int vertexID;
	public int key;
	public int pi;

	public boolean equals (Vertex vx)
	{
		return vx.vertexID==vertexID;
	}
}


class VertexComparator implements Comparator<Vertex>
{
	public int compare(Vertex v1, Vertex v2)
	{
		if(v1.key>v2.key)	return 1;
		else if(v1.key<v2.key)	return -1;
		else			return 0;
	}
}

class Edge
{
	public int weight;
	public int otherVertexID;
}
