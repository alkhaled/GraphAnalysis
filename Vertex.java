
public class Vertex implements Comparable<Vertex> {

    public double key;
    public int id;
    public Vertex pred;

    public Vertex ( int id){
	this.id = id;
    }
    public Vertex (int id, double key , Vertex pred){
	this.id   = id;
	this.key  = key;
	this.pred = pred;
    }

    public int compareTo(Vertex other) {
	
	if (!(other instanceof Vertex)) {
	    System.exit(3);
	    return 0;
	}
	else { 
	    if (key - ((Vertex)other).key > 0)
		return 1;
	    else 
		return -1;
	}
	    
	
    }
}