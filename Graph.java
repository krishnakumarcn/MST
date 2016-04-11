package Kruskals;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author krishnakumar
 *
 */

public class Graph {

    public static Graph build(String inputtxt) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Graph nw= new Graph();
       
       try(Scanner scanner=new Scanner(new File(inputtxt)))
       {
           nw.V=scanner.nextInt();
           nw.E=scanner.nextInt();
           nw.edge= new Edge[nw.E];
           for(int i=0; i<nw.E; ++i)
              nw.edge[i]=new Edge();
           int index=0;   
           for(int i=0; i<nw.V; i++){
               for(int j=0; j<nw.V; j++){
                    int val=scanner.nextInt();
                    if(val==0||j<i)
                       continue;
                    else{
                        nw.edge[index].weight=val;
			nw.edge[index].source=i;
                        nw.edge[index].destination=j;
                        index++;
                        //System.err.println(edge.weight);
                    }                  
                   
            }
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return nw;
    }
	
	int V,E;	//no,of vertices and edges
	Edge edge[];

	Graph(int v,int e){
		V=v;
		E=e;
		edge=new Edge[E];
		for(int i=0;i<E;i++){
			edge[i]=new Edge();
		}
	}

        Graph() {
            // throw new UnsupportedOperationException("Not supported yet.");
        }
	
	class Subset{
		int parent,rank;
	}

	public static void mainGraph(){
		//int V=9;//no.of vertices
		//int E=14;//no.of edges
		Graph graph=Graph.build("input2.txt");                
                /*
		//setting graph edges
		
		graph.edge[0].source=0;
		graph.edge[0].destination=1;
		graph.edge[0].weight=4;

		graph.edge[1].source=0;
		graph.edge[1].destination=7;
		graph.edge[1].weight=8;


		graph.edge[2].source=1;
		graph.edge[2].destination=2;
		graph.edge[2].weight=8;

		graph.edge[3].source=1;
		graph.edge[3].destination=7;
		graph.edge[3].weight=11;

		graph.edge[4].source=7;
		graph.edge[4].destination=8;
		graph.edge[4].weight=7;


		graph.edge[5].source=7;
		graph.edge[5].destination=6;
		graph.edge[5].weight=1;

		graph.edge[6].source=2;
		graph.edge[6].destination=8;
		graph.edge[6].weight=2;

		graph.edge[7].source=8;
		graph.edge[7].destination=6;
		graph.edge[7].weight=6;



		graph.edge[8].source=2;
		graph.edge[8].destination=3;
		graph.edge[8].weight=7;

		graph.edge[9].source=2;
		graph.edge[9].destination=5;
		graph.edge[9].weight=4;

		graph.edge[10].source=6;
		graph.edge[10].destination=5;
		graph.edge[10].weight=2;


		graph.edge[11].source=3;
		graph.edge[11].destination=4;
		graph.edge[11].weight=9;

		graph.edge[12].source=3;
		graph.edge[12].destination=5;
		graph.edge[12].weight=14;

		graph.edge[13].source=5;
		graph.edge[13].destination=4;
		graph.edge[13].weight=10;
		
*/
		System.out.println("The input graph is:");
		for(int i=0;i<graph.E;i++)
                   System.out.println(graph.edge[i].source + "-->" + graph.edge[i].destination + "= " + graph.edge[i].weight);
		
		final long startTime =System.nanoTime();
		graph.kruskalMST();
		final long endTime=System.nanoTime();
		final long execTime=endTime-startTime;
		System.out.println("\nExecution Time for Kruskal Algorithm:" + execTime+" nanoseconds");
		

	}

	private void kruskalMST() {
		Edge[] result=new Edge[V];	//for storing result	
		int e=0,i=0;
		for(i=0;i<V;i++)
			result[i]=new Edge();
		
		//first step : sort using weight
		
		Arrays.sort(edge);
	
		
		//creating V subsets with single elements
		Subset subsets[]=new Subset[V];
		for(i=0;i<V;i++){
			subsets[i]=new Subset();
			subsets[i].parent=i;
			subsets[i].rank=0;
		}
		

		i=0;e=0;
		//V-1 edges will be taken
		while( e< V-1){
			Edge next_edge=new Edge();
			next_edge=edge[i++];
			int x=find(subsets,next_edge.source);
			int y=find(subsets,next_edge.destination);
			//include the edge if not cycle formation
			if(x!=y){
				result[e++]=next_edge;
				Union(subsets,x,y);
			}
			
		}
		//display output
		System.out.println("\nThe edges of MST are:");
		for(i=0;i<e;i++)
			System.out.println(result[i].source + "-->" + result[i].destination);
		
		
		
	}

	private void Union(Subset[] subsets, int x, int y) {
		int xroot=find(subsets,x);
		int yroot=find(subsets,y);
		
		//attach smaller ranked tree under union of higher ranked
		if(subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent=yroot;
		else if(subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent=xroot;
		else{
			subsets[yroot].parent=xroot;
			subsets[xroot].rank++;
		}
	}

	private int find(Subset[] subsets, int index) {
		if(subsets[index].parent!=index)
			subsets[index].parent=find(subsets,subsets[index].parent);
		return subsets[index].parent;
	}
        
        

	

}

class Edge implements Comparable<Edge>{
		int source,destination,weight;

		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}

        }

