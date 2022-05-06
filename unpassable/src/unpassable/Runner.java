package unpassable;

import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Runner {
	public static void main(String[] args) {
		Chemistry c=new Chemistry();
		c.scan();
	System.out.println(c.getCorrectAnsIn(0));
	Psych p = new Psych();
	p.scan();
	System.out.println(p.sortAs().length);
	System.out.println(p.sortQs().size());
	for(String[] row: p.sortAs())
		for(String col :row) {
	System.out.println(col);}
	for (String s : p.sortQs()) {
		System.out.println(s);
		
	}
	}
	

}