import java.util.*;

public class Kruskals{

   
    static int [] parent;
    static int [] rank;

    public static void main ( String[] args){
	int size = Integer.parseInt(args[0]);
	parent   = new int [size];
	rank     = new int [size];
	makeSet(size);

	int numEdges =(int) (2*size*(Math.log(size)/Math.log(2)));

	int trials = Integer.parseInt(args[1]);
	double avg = 0;
	for ( int i = 0; i< trials; i++){
	    avg  += kruskals( size , numEdges);
	    makeSet(size);
	}
	avg = avg/trials;
	System.out.println(avg);
    }

    public static int kruskals (int size, int edges ){

	PriorityQueue<Edge> Q;
	Q = new PriorityQueue<Edge>();
	boolean [][] m = new boolean [size][size];

	/// populate/// 
	for ( int i = 0; i< edges ;i++){
	    int a = (int)(Math.random()*m.length);
	    int b = (int)(Math.random()*m.length);
	    if ( m[a][b] == false && a!=b)
		Q.add(new Edge (a,b,Math.random()));
	    else 
		i--;
	}

	int edgesFound = 0;
	int iterations = 0;
	while ( Q.size() != 0 && edgesFound < (m.length-1)){
	    Edge e = Q.remove();
	    int uParent = find(e.u);
	    int vParent = find(e.v);
	    iterations++;
	    if ( uParent != vParent){
		union (uParent, vParent);
		edgesFound++;					   
	    }
	}

	return iterations;
	
    }
	

    public static void makeSet( int size){


	for (int i = 0; i<size; i++){
	    parent[i] = i;
	    rank  [i] = 0;
	}
    }


    public static int find ( int i){
	// with path compression

	if (i != parent[i])
	    parent [i] = find(parent[i]);
	
	return parent[i];
	
    }

    public static void union (int v, int u){
	// by rank
	if (rank[v] > rank[u]){
	    parent[u] = v;
	}
	else {
	    parent[v] = u;
	    if (rank[v] == rank[u])
		rank[u]++;
	}
    }
	
}