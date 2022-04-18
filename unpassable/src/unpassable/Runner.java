package unpassable;
public class Runner {
	public static void main(String[] args) {
		Chemistry c=new Chemistry();
		c.scan();
		System.out.println(c.sortAs()[2][0]);
		System.out.println(" ");
		for(int i=0; i<c.answers.size();i++) {
		System.out.println(c.answers.get(i));}
		System.out.println(c.sortQs().size());
	}

}