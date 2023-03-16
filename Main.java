//Vasiliki Katogianni AM 4696 Zoi Kouvaka AM 4706 Ioannis Tsironis AM 4908 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
public class Main { //klasi main

    public static void main(String[] args) { 
        int N = 70; //thetoume to megethhos tou lavirinthou
        double p = 0.4; //thetoume tin pithanotita na exei empodia
        Grid maze = new Grid(N, p); //ftiaxnoume ena antikeimeno maze
		System.out.println(maze.graphicToString()); //tipono to grid
		Scanner input = new Scanner(System.in); //ftiaxno antikeimeno scanner gia na dinei o xristis times
		System.out.println("Give the x axis of the S cell : ");
		int xofs= input.nextInt(); //o xristis dinei tin x thesi tou keliou pou tha vrisketai to robot arxika
		System.out.println("Give the y axis of the S cell : ");
		int yofs= input.nextInt(); //o xristis dinei tin y thesi tou keliou pou tha vrisketai to robot arxika
		System.out.println("Give the x axis of the G1 cell : ");
		int xofg1= input.nextInt(); //o xristis dinei tin x thesi tou keliou tis protis telikis katastasis
		System.out.println("Give the y axis of the G1 cell : ");
		int yofg1= input.nextInt(); //o xristis dinei tin y thesi tou keliou tis protis telikis katastasis
		System.out.println("Give the x axis of the G2 cell : ");
		int xofg2= input.nextInt(); //o xristis dinei tin x thesi tou keliou tis deuteris telikis katastasis
		System.out.println("Give the y axis of the G2 cell : ");
		int yofg2= input.nextInt();  //o xristis dinei tin y thesi tou keliou tis deuteris telikis katastasis
	
        Cell startingPosition = maze.getmaze()[xofs][yofs]; //dimiourgeitai to keli tis arxikis katastasis
        Cell finalPosition1 = maze.getmaze()[xofg1][yofg1]; //dimiourgeitai to keli tis protis telikis katastasis
        Cell finalPosition2 =maze.getmaze()[xofg2][yofg2]; //dimiourgeitai to keli tis deuteris telikis katastasis
        System.out.println("startingPosition"+ startingPosition);
        System.out.println("finalPosition1"+ finalPosition1);
        System.out.println("finalPosition2"+ finalPosition2);
        Robot rb = new Robot(startingPosition, finalPosition1 ,finalPosition2);	//ftiaxnoume antikeimeno tis klasis robot
        System.out.println("----- A* --------");
        ArrayList<Cell> pathA = rb.alfaAstro(startingPosition,finalPosition1,finalPosition2); //to monopati tpu A*
		Collections.reverse(pathA); //antistrefo to monopati
		System.out.print("PathA is :");
		for(Cell komvos:pathA) //tipono to monopati
		{
			System.out.print(komvos+"  ");
		}
		System.out.println();
		System.out.println("----- UCS --------");
		ArrayList<Cell> pathUCS = rb.ucs(startingPosition,finalPosition1,finalPosition2); //to monopati tpu ucs
		Collections.reverse(pathUCS); //antistrefo to monopati
		System.out.print("PathUCS is :");
		for(Cell komvos:pathUCS) //tipono to monopati
		{
			System.out.print(komvos+"  ");
		}
        
    }
}
