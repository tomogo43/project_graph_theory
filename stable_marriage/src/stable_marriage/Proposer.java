package stable_marriage;

public class Proposer {
//	proposing id
	char prop;
//	pretender id
	char pret;
//	position pretenders
	int pos;
//	is already married?
	boolean married;
	
//	default constructor
	public Proposer() {
		System.out.println("Array constructor");
		prop = ' ';
		pret = ' ';
		pos = 0;
		married = false;
	}
	
//	Constructor with parameters
	public Proposer(char pProp, char pPret,int pPos, boolean pMarried) {
		prop = pProp;
		pret = pPret;
		pos = pPos;
		married = pMarried;
	}
}
