//Vasiliki Katogianni AM 4696 Zoi Kouvaka AM 4706 Ioannis Tsironis AM 4908 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid { //klasi pou afora ton lavirinto
    private Cell[][] maze; //pedio gia na pairnoume ena cell tou grid
    private Random rand = new Random(); //antikeimeno gia na pairnoume tixaious arithmous
    private int N; //megethos grid
    private double p; //pithanotita
    
    public Grid(int N, double p) { //constructor
        this.N = N; //dinoume timi sto N
        this.p = p; //dinoume timi stin pithanotita
        this.maze = generateMaze(N); //ftiaxnoume to grid me ta kelia
        setUpNeighbors(maze); //vriskei tous geitones tou keliou
        setUpMoveCosts(maze); //vriskei ta kosti apo kathe geitona
    }
	public Cell[][] getmaze() {//accessor method gia na paroume to pedio maze
        return maze;
    }
    private void setUpNeighbors(Cell[][] maze) { //methodos pou gia kathe keli tou grid vriskei tous geitones tou 
        for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maze[i][j].setNeighbors(findNeighbors(maze[i][j]));
            }
        }
    }
    private void setUpMoveCosts(Cell[][] maze) { //methodos pou gia kathe keli tou grid vriskei to kostos tou
    	for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maze[i][j].setMovingCosts();
            }
        }
    }
    
    private Cell[][] generateMaze(int N) { //prosthetoume ta kelia sto grid 
        Cell[][] maze = new Cell[N][N];
        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                maze[i][j] = new Cell(i, j, isBlocked());
                if (!maze[i][j].isBlocked()) {
                    maze[i][j].setValue(calculateValue());
                }
                else {
                    maze[i][j].setValue(-1);
                }
            }
        }
        return maze;
    }

    private List<Cell> findNeighbors(Cell cell) { //vriskei tous geitones sosta
        List<Cell> neighbors = new ArrayList<>();
        int[] position = cell.getPosition();
        if (!cell.isBlocked()) {
            if((position[0]-1) >= 0) {
                neighbors.add(maze[position[0]-1][position[1]]);
            }
            if ((position[0]+1) < N) {
                neighbors.add(maze[position[0]+1][position[1]]);
            }
            if ((position[1]-1) >= 0 && (position[0]-1) >= 0) {
                neighbors.add(maze[position[0]-1][position[1]-1]);
            }
            if((position[1]+1) < N) {
                neighbors.add(maze[position[0]][position[1]+1]);
            }
            if((position[1]-1) >= 0) {
                neighbors.add(maze[position[0]][position[1]-1]);
            }
            if((position[0]+1) < N && (position[1] +1) < N) {
                neighbors.add(maze[position[0]+1][position[1]+1]);
            }
            if((position[0]+1) < N && (position[1]-1) >= 0) {
                neighbors.add(maze[position[0]+1][position[1]-1]);
            }
            if((position[0]-1) >= 0 && (position[1]+1) < N) {
                neighbors.add(maze[position[0]-1][position[1]+1]);
            }
        }
        return neighbors;
    }
    private boolean isBlocked() { //pairnoume enan tixaio arithmo gia kathe keli kai an einai mikrotero tis pithnotitas exei empodio
        float random_number = rand.nextFloat();
        if(random_number < p) {
            return true;
        }
        else {
            return false; 
        }
    }
    public Cell getCell(int x, int y) { //accessor method gia na paroume ena keli
        return maze[x][y];
    }
    private int calculateValue() { //dinei mia tixaia timi apo 0 eos 4 sto kathe kelli
        return (rand.nextInt(4 - 1) + 1);
    }
    public String toString() { //tiponei  to kathe keli
        String stringMaze = "";
        for(int i=0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stringMaze += maze[i][j].toString()+" ";
            }
            stringMaze += "\n";
        }
        return stringMaze;
    }
    public String graphicToString() { //tiponei to grid
        String stringMaze = "";
        for(int i=0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!maze[i][j].isBlocked()){
                    stringMaze +=  maze[i][j].toString()+" ";
                }
                else {
                    stringMaze += "   #    ";
                }
            }
            stringMaze += "\n";
        }
        return stringMaze;
    }
    public String allNeighborsToString() { //tiponei tous geitones tou keliou
        String stringNeighbors = "";
        for(int i=0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!maze[i][j].isBlocked()) {
                    stringNeighbors += maze[i][j].neighborsToString()+"\n";
                }
            }
        }
        return stringNeighbors;

    }


}

