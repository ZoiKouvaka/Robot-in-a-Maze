//Vasiliki Katogianni AM 4696 Zoi Kouvaka AM 4706 Ioannis Tsironis AM 4908 
import java.util.Collections;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.lang.*;
public class Robot { //klasi pou afora to robot kai exei tous algorithmous ucs kai A*
    private Cell currentPosition; //keli pou vrisketai to robot
    private Cell finalPosition1; //proti teliki katastasi 
    private Cell finalPosition2;  //deuteri teliki katastasi 
    private Map<Cell,Double> metwpo=new HashMap<Cell,Double>();//lexiko pou krataei to keli me to kostos gn diladi to metopo anazitisis


    public Robot(Cell starting_position, Cell finalPosition1, Cell finalPosition2) { //constructor
        currentPosition = starting_position; 
        this.finalPosition1 = finalPosition1;
        this.finalPosition2 = finalPosition2;
    }
    
    public Cell getCurrentPosition() { //accessor method gia na paroume to pedio currentPosition
        return currentPosition;
    }
    public Cell getFinalPosition1() { //accessor method gia na paroume to pedio finalPosition1
        return finalPosition1;
    }
    public Cell getFinalPosition2() { //accessor method gia na paroume to pedio finalPosition2
        return finalPosition2;
    }
    public ArrayList<Cell> ucs(Cell startpos,Cell finalpos1,Cell finalpos2) //algorithmos ucs. Pairnei os orisma tin arxiki katastasi kai tis 2 telikes
    {
         ArrayList<Cell> path=new ArrayList<Cell>(); //to monopati pou tha epistrepsei
		 int epektaseis=0; //arxikopoioume ton arithmo ton epektaseon
         metwpo.put(startpos,(double)0);//vazoume sto metopo arxika tin arxiki katastasi
         startpos.setGn(0); //to gn apo tin riza einai 0 ara to thetoume edo sto 0
         startpos.setColor(true); //thetoume true oste na min ton valoume xana sto metopo anazitisis
         Cell finalCell;  //teliki katastasi
         double gn=0; //arxikopoio to kostos metakinisis tou keliou pou vrisketai stin arxiki katastasi apo tin riza sto 0
        while(true)
         {
            boolean diakopths=true;
            double min=0;
            for (Cell cell:metwpo.keySet()) //gia kathe keli pou vrisketai ston komvo anazitisis
            {   
                if(diakopths) //
                {
                    diakopths=false;
                    min=metwpo.get(cell);
                    continue;
                }
                if(metwpo.get(cell)<min)
                {
                    min=metwpo.get(cell);
                }
            }
			if(metwpo.isEmpty()==true){ //an den iparxei monopati
				System.out.println("Path not found!!!");
				System.exit(0);
			}	
            for(Cell key:metwpo.keySet()) //gia kathe cell pou exw sto metwpo
            {
                if(metwpo.get(key).equals(min))//otan vreis poio kleidi-cell htan auto pou eixe to elaxisto gn,pleon to key einai o komvos pou tha svisw apo to metwpo
                {
                    if(!(key.equals(finalPosition1)||key.equals(finalPosition2)))//an to key einai iso me mia apo tis dio telikes katastaseis
                    {         
                        for(Cell neighbor: (key.getNeighborCosts()).keySet())//gia kathe neighbor
                        {
                            if(neighbor.isColored()&&((key.getNeighborCosts().get(neighbor)+key.getGn())<neighbor.getGn()))//an vreis tropo na pas me mikrotero gn se kapoio geitona
                            {
                                neighbor.setGn(key.getNeighborCosts().get(neighbor)+key.getGn()); //theto to kostos tou geitona iso me to kostos metakinisis sin to gn
                                neighbor.setParent(key); //theto ton parent tou geitona
                                if(metwpo.get(neighbor)!=null) 
                                {
                                    metwpo.remove(neighbor); //svino ton geitona apo to metopo
                                }
                                metwpo.put(neighbor,(key.getNeighborCosts().get(neighbor)+key.getGn()));
                            }
                            if(!(neighbor.isBlocked()||neighbor.isColored())) //an o geitonas den exei empodio /den exei xanampei sto metopo
                            {    
                                neighbor.setColor(true); //to theto true
                                neighbor.setParent(key);//kane ton komvo pou svhnetai gonea tou
                                gn=key.getNeighborCosts().get(neighbor)+key.getGn();//gn tou paidiou=kostos metakinhshs+gn tou gonea
                                neighbor.setGn(gn); //theto to gn tou geitona
                                metwpo.put(neighbor,gn); //prostheto ton geitona sto metopo
                            }
                        }
                        metwpo.remove(key); //afairo to key apo to metopo
						epektaseis=epektaseis+1; //auxano ton arithmo epektaseon
                        break;
                    }
                    else
                    {
                        finalCell=key; //exoume  ftasei se teliki katastasi
                        path.add(finalCell); //prosthetoume to keli sto monopati
						System.out.println("Cost of path is "+finalCell.getGn());
						System.out.println("Number of extensions: "+epektaseis);
                        while(!(finalCell.equals(currentPosition)))//oso den eisai sth riza
                        {
                            path.add(finalCell.getParent()); //prosthese ton gonea sto monopati
                            finalCell=finalCell.getParent();
                        }
                        return path;
                    }    
                }
            }
        }
    }
	public ArrayList<Cell> alfaAstro(Cell startpos,Cell finalpos1,Cell finalpos2) //algorithmos A*. Pairnei os orisma tin arxiki katastasi kai tis 2 telikes
    {	
		 ArrayList<Cell> metwpo = new ArrayList<>(); //metopo anazitisis
		 ArrayList<Cell> closedSet = new ArrayList<>(); //kleisto sinolo
         ArrayList<Cell> path = new ArrayList<>(); //monopati
         double minCost = 0; //arxika theto to elaxisto kostos se 0
		 int epektaseis=0; //arxika theto to arithmo epektaseon se 0
         metwpo.add(startpos); //prostheto sto metopo tin arxiki katastasi
         startpos.setGn(0); //theto to gn tis arxikis 0 epeidi einai stin riza
         Cell current = new Cell(0,0,false); //to keli pou tha vrisketai kathe fora to robot
         int i;
         boolean diakoptis = true;
         double cost = 0; //to kostos kathe keliou
         ArrayList<Double> costs = new ArrayList<>(); //lista me ta kosti
         
        while(true)
         {
        	costs.clear(); //arxika einai adeia i lista
        	diakoptis = true;
        	// ------------------ Find min cell in metwpo ----------------------
            for (Cell cell: metwpo)
            {   
            	cost = cell.getGn() + cell.getH(finalpos1,finalpos2); //kostos=g(n) + h(n)
                if(diakoptis) {
                	minCost = cost; 
                	current = cell;
                	diakoptis = false;
                	continue;
                }
                if(cost < minCost) { //an iparxei mikrotero kostos 
                	minCost = cost; 
                	current = cell; //ginetai auto current
                }
            }
            metwpo.remove(current); //vgazo to current apo to metopo
            closedSet.add(current); //to prostheto sto kleisto sinolo
            //--------------- End State ---------------------------
            if(current == finalpos1 || current == finalpos2) //an exoume ftasei se teliki katastasi
            {
                path.add(current); //prostheto sto monopati to current
				System.out.println("Cost of path is " + current.getGn());
				System.out.println("Number of extensions: "+epektaseis);
                while(!(current.equals(startpos)))//oso den eisai sth riza
                {
                    path.add(current.getParent()); //prosthese ton gonea sto monopati
                    current=current.getParent(); //kano current ton gonea tou current
                }
                return path;
            }
            
         // ------------------------- Runing State -------------------
            else {
            	epektaseis ++; //auxano ton arithmo epektaseon
            	for(Cell neighbor: current.getNeighborCosts().keySet()) { //gia kathe geitona
            		if(neighbor.isBlocked() || closedSet.contains(neighbor)) { //an o geitonas exei empodio /to kleisto sinolo ton periexei
            			continue;
            		}
            		if((!metwpo.contains(neighbor)) || (current.getNeighborCosts().get(neighbor) + current.getGn() < neighbor.getGn())) {
            			neighbor.setGn(current.getNeighborCosts().get(neighbor) + current.getGn());
            			neighbor.setParent(current);
            			if(!metwpo.contains(neighbor)) { //an to metopo den periexei ton geitona
            				metwpo.add(neighbor); //ton prostheto
            			}
            		}
            	}
            }
            
         // ---------------- Exit No Path ------------------
            if(metwpo.isEmpty() == true){ //an einai adeio tote den iparxei monopati
				System.out.println("Path not found!!!");
				System.exit(0);
			}
            
        }
    }
}

