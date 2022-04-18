package unpassable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class Chemistry {
	 ArrayList <String> questions = new ArrayList<String>();
	 ArrayList <String> answers = new ArrayList<String>();
	File myObj = new File("chemistry.txt");
	
   
	public void scan() {
	try {
	      
	      Scanner myReader = new Scanner(myObj);
	      int counter=0;
	  
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();

	      if(data.substring(0,1).equals("(")) {    	
	        answers.add(data);
	      }
	      //lol
	      else{
	    	  questions.add(data);
	      }
	      }
	      
	     
	      myReader.close();
	      } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }}

public ArrayList <String> sortQs (){
	ArrayList <String> newQ=new ArrayList<String>();
	
		for(int j =0; j<questions.size()-1;j+=2) {
			newQ.add( questions.get(j)+ questions.get(j+1));
	}
	return newQ;
}
public String [][] sortAs (){
	String[][]newA=new String[sortQs().size()][5];
	for(int i=0; i<newA.length;i++) {
		for(int j=0;j<newA[i].length;j++) {
			newA[i][j]=answers.get(j+(i*5));
		}
	}
	return newA;
}
}
