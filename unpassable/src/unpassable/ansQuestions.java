package unpassable;

public class ansQuestions {
Chemistry chem = new Chemistry();
int ansChoice;
String subject;
public ansQuestions(int ansChoice, String subject) {
	this.ansChoice=ansChoice;
	this.subject=subject;
}

public boolean isCorrect(int questionNum) {
	if(subject.equals("Chemistry")) {
		chem.scan();
		if(chem.getCorrectAnsIn(questionNum)==ansChoice) {
			return true;
		}
	}
	return false;
}
}
