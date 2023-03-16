//Vasiliki Katogianni AM 4696 Zoi Kouvaka AM 4706 Ioannis Tsironis AM 4908 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Cell { //klasi pou afora to keli tou lavirintou
    private int[] position = {0, 0}; //pedio pou periexei tin thesi tou kathe keliou diladi to x kai y axona
    private boolean isBlocked = false; //pedio pou deixnei an ena keli exei empodio 
    private boolean isColored=false; // boolean metavliti pou koita an exoume valei ton komvo sto metopo xana
    private int val = -1; //kathe eleuthero keli exei enan arithmo apo 0 eos 4. arxika to thetoume sto -1
    private List<Cell> neighbors; //lista me tous geitones kathe keliou
    private Cell parent; //o goneas tou keliou diladi to keli pou to epekteiname kai valame tous geitones tou sto metopo anazitisis
    private double gn=0;//apostasi keliou apo tin arxiki katastasi
    private HashMap<Cell,Double> neighborCosts=new HashMap<Cell,Double>(); //lexiko pou periexei tous geitones tou keliou me to kostos tou keliou apo ton geitona
    
    public Cell(int x, int y, boolean isBlocked) { //constructor
        position[0] = x; //dinei tin timi pou tou dothike os parametros stin orizontia thesi tou keliou 
        position[1] = y; //dinei tin timi pou tou dothike os parametros stin katakorifi thesi tou keliou 
        this.isBlocked = isBlocked; //thetei an tha exei empodio to kelli
    }
    public void changePosition(int x, int y) { //methodos pou allazoume tin x kai y thesi tou keliou me tis times pou tou dothikan os parametroi
        position[0] = x;
        position[1] = y;
    }
    public void changeState(boolean isBlocked) { //methodos pou allazoume sto keli tin katastasi tou an tha exei empodio
        this.isBlocked = isBlocked; //tou dinoume os parametro to an tha exei empodio kai to thetei
    } 
    public int[] getPosition() { //accessor method gia na paroume to pedio position
        return position;
    }
    public boolean isBlocked() { //accessor method gia na paroume to pedio isBlocked
        return isBlocked;
    }
    public boolean isColored() //accessor method gia na paroume to pedio isColored
    {
        return isColored;
    }
    public void setColor(boolean color)//mutator method gia na allaxoume to pedio isColored
    {
        isColored=color;
    }
    public Double getH(Cell f1, Cell f2) { //ipologizei tin euretiki sinartisi. Pairnei san orisma 2 telikes katastaseis
    	double dx;
    	double dy;
    	double h1;
    	double h2;
    	//gia tin proti teliki katastasi
		dx = position[0] - f1.getPosition()[0];  //vriskei tin orizontia apostasi tou keliou apo tin teliki katastasi
    	dy = position[1] - f1.getPosition()[1]; //vriskei tin katheti apostasi tou keliou apo tin teliki katastasi
    	h1 = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) * 0.5; //kanoume tin euklidia apostasi kai to pollaplasiazoume me to mikrotero kostos to 0.5
    	//gia tin deuteri teliki katastasi
    	dx = position[0] - f2.getPosition()[0]; //vriskei tin orizontia apostasi tou keliou apo tin teliki katastasi
    	dy = position[1] - f2.getPosition()[1];  //vriskei tin katheti apostasi tou keliou apo tin teliki katastasi
    	h2 = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) * 0.5; //kanoume tin euklidia apostasi kai to pollaplasiazoume me to mikrotero kostos to 0.5
    	return Math.min(h1,h2); //dialegoume tin mikroteri euretiki apo tis dio
    			 		
    }
    public String toString() { //tiponei tin thesi kai ton arithmo kathe keliou
        return "("+position[0]+","+position[1]+","+val+")";
    }

    public int getValue() { //accessor method gia na paroume to pedio val
        return val;
    }
	public List<Cell> getneigh() { //accessor method gia na paroume to pedio neighbors
        return neighbors;
    }
    public void setValue(int calculateValue) { //mutator method gia na allaxoume to pedio val
        val = calculateValue;
    }

    public void setNeighbors(List<Cell> neighbors) { //mutator method gia na allaxoume to pedio neighbors
        this.neighbors = neighbors;
    }
    public void setMovingCosts() { // vazei sto lexiko neighborCosts tous geitones tou keliou me to kostos metakinisis tou keliou na paei se allo geitona
    	this.neighborCosts = new HashMap<>();
    	double cost = 0; //arxikopoioume to kostos arxika sto 0
    	for (Cell neighbor: neighbors) { //gia kathe geitona 
    		if (!neighbor.isBlocked()) { //an den exei empodio to keli
    			int[] neighborPosition = neighbor.getPosition(); //kratame tin thesi tou geitona 
        		//periptoseis ton diagonion kiniseon
        		if ((neighborPosition[0] > this.position[0] || neighborPosition[0] < this.position[0] ) && (neighborPosition[1] > this.position[1] || neighborPosition[1] < this.position[1])) {
        			cost = (double) (Math.abs(this.val - neighbor.getValue())+ 0.5); // to kostos tha einai |val(x,y)-val(x’,y’)| + 0.5
        			neighborCosts.put(neighbor, cost); //prosthetei sto lexiko ton geitona kai to kostos
        		}
        		// periptoseis orizontion kai katheton kiniseon
        		else if ((neighborPosition[0] > this.position[0] || neighborPosition[0] < this.position[0]) || (neighborPosition[1] > this.position[1] || neighborPosition[1] < this.position[1])) {
        			cost = (double) (Math.abs(this.val - neighbor.getValue())+ 1); // to kostos tha einai |val(x,y)-val(x’,y’)| + 1
        			neighborCosts.put(neighbor, cost); //prosthetei sto lexiko ton geitona kai to kostos
        		}
        		// an ginei lathos
        		else {
        			cost = -1; //to arxikopoio sto -1
        			neighborCosts.put(neighbor, cost); //prosthetei sto lexiko ton geitona kai to kostos
        		}
    		}
  
    	} 
    }
    public String neighborsToString() { //tiponei tous geitones tou keliou
        String stringNeighbors = "";
        for (Cell i: neighbors) {
            stringNeighbors += i;
        }
        return stringNeighbors;
    }
    
    public String movingCostsToString() { //tiponei to keli me kathe geitona tou kai to kostos tou mexri ekei

    	String movingCosts = "";
    	for(Map.Entry<Cell, Double> entry : this.neighborCosts.entrySet()) {
    		Cell cell = entry.getKey();
    	    Double value = entry.getValue();
    	    movingCosts += "from cell:"+this.toString()+" to "+ cell.toString() +" with cost "+ value.toString()+"\n";
    	}
    	return movingCosts;
    }
    
    public Cell getCellForMinMovingCost() { //dialegei apo tous geitones tou keliou to keli me to mikrotero kostos
    	Cell minCostNeighbor = Collections.min(neighborCosts.entrySet(), Map.Entry.comparingByValue()).getKey();
    	return minCostNeighbor;
    }
    public void setParent(Cell parent) //mutator method gia na allaxoume to pedio parent
    {
        this.parent=parent;
    }

    public void setGn(double gn)  //mutator method gia na allaxoume to pedio gn
    {
        this.gn=gn;
    }

    public double getGn() //accessor method gia na paroume to pedio gn
    {
        return gn;
    }

    public Cell getParent() //accessor method gia na paroume to pedio parent
    {
        return parent;
    }

    public HashMap<Cell,Double> getNeighborCosts() //accessor method gia na paroume to pedio neighborCosts
    {
        return neighborCosts;
    }
}
