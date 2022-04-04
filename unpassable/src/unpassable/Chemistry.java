package unpassable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Chemistry {
	 ArrayList <String> questions = new ArrayList<String>();
	 ArrayList <String> answers = new ArrayList<String>();
	File myObj = new File("chemistry.txt");
	
   
	public  void scan() {
	try {
	      
	      Scanner myReader = new Scanner(myObj);
	      int counter=0;
	  
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();

	      if(data.contains("(")) {    	
	        answers.add(data);
	      }
	      //lol
	      else{
	    	  questions.add(data);
	      }
	      }
	      
	      System.out.println(questions);
	      myReader.close();
	      } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }


}}
