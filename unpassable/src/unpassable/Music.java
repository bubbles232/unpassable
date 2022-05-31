package unpassable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class Music {
	 ArrayList <String> questions = new ArrayList<String>();
	 ArrayList <String> answers = new ArrayList<String>();
	 ArrayList <String> key = new ArrayList<String>();
	File myObj = new File("music history.txt");
	File aKey = new File ("music key.txt") ;  
	public void scan() {
	try {
	      
	      Scanner myReader = new Scanner(myObj);
	      Scanner myReader2 = new Scanner(aKey);
	      
	  
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
if(data.length()>1){
	      if(data.substring(0,1).toLowerCase().equals("a")||data.substring(0,1).toLowerCase().equals("b")||data.substring(0,1).toLowerCase().equals("c") ||data.substring(0,1).toLowerCase().equals("d")) {    	
	        answers.add(data);
	      }
	      
	      else{
	    	  questions.add(data);
	      }
	      }}
	      while (myReader2.hasNextLine()) {
		        String data2 = myReader2.nextLine();
         key.add(data2);
		      }
	      key.set(0, "a");
		   
		     
		      myReader.close();
		      } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }}
	      

public ArrayList <String> sortQs (){
	ArrayList <String> newQ=new ArrayList<String>();
	
		for(int j =0; j<questions.size();j++) {
			newQ.add( questions.get(j));
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


public ArrayList<String> getAnswers(){
	return answers;
}
public String getCorrectAnsIn(int questionNum) throws FileNotFoundException {
	String answer = "";
	for (int i = 0; i < 20; i++) {
		if (i == questionNum) {
			answer = key.get(i);
		}
	}
	return answer;
}
}
