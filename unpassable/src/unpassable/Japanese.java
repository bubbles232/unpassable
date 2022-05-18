package unpassable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Japanese {
	ArrayList <String> questions = new ArrayList<String>();
	 ArrayList <String> answers = new ArrayList<String>();
	 ArrayList <String> key = new ArrayList<String>();
	
	File aKey = new File ("psych key.txt") ;  
	File m = new File("50 jpn q (1).txt");
	public void scan() {
	try {
		FileInputStream myObj = new FileInputStream(m);
		InputStreamReader read = new InputStreamReader(myObj,"UTF-8" );
	      Scanner myReader = new Scanner(myObj);
	      Scanner myReader2 = new Scanner(aKey);
	    int  count =0;
	  
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        
System.out.println(data);
	      if(count%5==0) {    	
	        questions.add(data);
	      }
	      
	      else{
	    	  answers.add(data);
	      }
	      count ++;
	      }
	      while (myReader2.hasNextLine()) {
		        String data2 = myReader2.nextLine();
       key.add(data2);
		      }
		   key.set(0, key.get(0).substring(3));
		     
		      myReader.close();
//		      } catch (FileNotFoundException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
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

