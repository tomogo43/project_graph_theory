package stable_marriage;

public class Pretender {
//	proposing id
	char prop;
//	pretender id
	char pret;
//	is already married?
	boolean married;
	
//	default constructor
	public Pretender() {
		System.out.println("Array constructor");
		prop = ' ';
		pret = ' ';
		married = false;
	}
	
//	Constructor with parameters
	public Pretender(char pProp, char pPret, boolean pMarried) {
		prop = pProp;
		pret = pPret;
		married = pMarried;
	}
}

