
public class Edge implements Comparable<Edge> {

    public double key;
    public int u;
    public int v;

    public Edge (int u, int v , double key){
	this.u   = u;
	this.key  = key;
	this.v = v;
    }

    public int compareTo(Edge other) {
	
	if (!(other instanceof Edge)) {
	    System.exit(3);
	    return 0;
	}
	else { 
	    if (key - ((Edge)other).key > 0)
		return 1;
	    else 
		return -1;
	}
	    
	
    }
}