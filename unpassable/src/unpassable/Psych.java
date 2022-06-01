package unpassable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Psych {
	 ArrayList <String> questions = new ArrayList<String>();
	 ArrayList <String> answers = new ArrayList<String>();
	 ArrayList <String> key = new ArrayList<String>();
	File myObj = new File("psychology.txt");
	File aKey = new File ("psych key.txt") ;  
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
		   key.set(0, "b");
		     
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
	String[][]newA=new String[sortQs().size()][4];
	for(int i=0; i<newA.length;i++) {
		for(int j=0;j<newA[i].length;j++) {
			newA[i][j]=answers.get(j+(i*4) );
		}
	}
	return newA;
}
public int getCorrectAnsIn(int questionNum)  {
	int answer=0;
	for(int i=0; i<sortAs()[questionNum].length;i++) {
		if(sortAs()[questionNum][i].substring(1,2).equals(key.get(questionNum).trim())) {
		answer=i;
	}}
	return answer;
}
}
