package unpassable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class Chemistry {
	 ArrayList <String> questions = new ArrayList<String>();
	 ArrayList <String> answers = new ArrayList<String>();
	 ArrayList <String> key = new ArrayList<String>();
	File myObj = new File("chemistry.txt");
	File aKey = new File ("Chem ans.txt") ;  
	public void scan() {
	try {
	      
	      Scanner myReader = new Scanner(myObj);
	      Scanner myReader2 = new Scanner(aKey);
	      
	  
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();

	      if(data.substring(0,1).equals("(")) {    	
	        answers.add(data);
	      }
	      
	      else{
	    	  questions.add(data);
	      }
	      }
	      while (myReader2.hasNextLine()) {
		        String data2 = myReader2.nextLine();
         key.add(data2);
		      }
		   key.set(0, key.get(0).substring(3));
		     
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
			newA[i][j]=answers.get(j+(i*5) );
		}
	}
	return newA;
}
public int getCorrectAnsIn(int questionNum) {
	int in=0;
		for (int i = 0; i < sortAs()[questionNum].length; i++) {
			if(sortAs()[questionNum][i].substring(0,3).equals(key.get(questionNum).trim())) {
				in =i;
		}
	}
		return in;
}
}
