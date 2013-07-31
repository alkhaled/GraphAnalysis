import java.util.*;
import java.util.PriorityQueue.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Primms{

    /// usage : primms (num of vertices) ( number of trials)
    public static void main(String[] args)  {
	int numV = Integer.parseInt(args[0]);
	int trials = Integer.parseInt(args[1]);
	int numEdges =(int) (2*numV*(Math.log(numV)/Math.log(2))); 

	double avg = 0;
	for(int i=0; i< trials; i++){
	    double[][] m = new double [numV][numV];		
	    m    = randomFill(m,numEdges);
	    avg += primms(m, numEdges);
	}
	avg = avg/trials;

	//	System.out.println(" avg cost: " + avg + "   O((avgE)lgV): " + avg*(Math.log(numV)/Math.log(2)));
	//System.out.println(" Edges: " + numEdges +  "        O((Edges)lgV): " + numEdges*(Math.log(numV)/Math.log(2))) ; 
	//System.out.println(" ratio   : " + avg/numEdges);

    }
    
    
    public static double[][] randomFill ( double[][] m, int n){
	// create a directed graph with n randomly weighted edges
	for ( int i = 0; i<n;i++){
	    int a = (int)(Math.random()*m.length);
	    int b = (int)(Math.random()*m.length);
	    if ( m[a][b] == 0 && a!=b)       //  ensure no self loops & that an empty spot is being filled
		m [a][b] =  (Math.random());
	    else 
		i--;
	}
	return m;
    }

    public static double primms ( double[][]m, int n){

	PriorityBlockingQueue<Vertex> Q;

	//// init PQ /////////////////////////
	Q = new PriorityBlockingQueue<Vertex>();		
	for (int i = 0; i< m.length;i++){
	    Q.add(new Vertex(i,9999,null));
	}
	//////////////////////////////////////
	
	int dKCost  = 0; // decrease key count
	long dKTime = 0;
	long start  = 0;
	while (Q.size() != 0){ 
	    // (1) Q contains vertices not yet in the MST

	    Vertex u = Q.remove();
	    int pos = u.id;
	    for ( Vertex v : Q ){		
		// (2) all remaining vertices have unupdated v.key for given u. Cut property preserved as only edges not yet in the mst are examined
		if (v.key > m[pos][v.id] && m[pos][v.id] > 0){
		    v.key = m[pos][v.id];
		    v.pred = u;
		    dKCost += Math.log(Q.size())/Math.log(2);
		    Q.remove(v);
		    start = System.nanoTime();
		    Q.add(v); // O(lgn) ( From java api page)
		    dKTime    += System.nanoTime() - start;
		}
		// (2) checked vertices V that exist in Q and had key > w(u,v) have updated key

	    }
	    // (1) min vertex u with edge cost u.key is removed from Q and added to MST,and (2) ensures next smallest edge is ready to be removed while respecting the cut theorem

	}

	
	////Print path ( when v< alphabet size//////////////
	// if (u.pred == null){
	// 	System.out.println("Root: " + "id: "+ u.id);// (char)(65+u.id));
	// }
	// else 
	// 	System.out.println("id: "+ /*(char)(65+*/u.id/*)*/ + " key: " + u.key + " pred: " + u.id);// (char)(65+u.pred.id));    
	// ///////////////////////////////////////////////////

	return dKCost;
    }    
}