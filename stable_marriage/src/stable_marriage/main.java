package stable_marriage;

public class main {

//	public static char[][] proposingArray= {
//			{'A','F','E','G','H'},
//			{'B','G','H','F','E'},
//			{'C','G','F','H','E'},
//			{'D','E','H','G','F'}
//	};
	
	public static char[][] proposingArray= {
			{'A','O','M','N','L','P'},
			{'B','P','M','N','L','O'},
			{'C','M','P','L','O','N'},
			{'D','P','M','O','N','L'},
			{'E','O','L','M','N','P'}
	};
	
//	public static char[][] pretendersArray= {
//			{'E','C','D','B','A'},
//			{'F','C','B','A','D'},
//			{'G','A','B','C','D'},
//			{'H','D','C','B','A'},
//	};
	
	public static char[][] pretendersArray= {
			{'L','D','E','B','C','A'},
			{'M','B','A','D','C','E'},
			{'N','A','C','E','D','B'},
			{'O','D','A','C','B','E'},
			{'P','B','E','A','C','D'}
	};
	
	public static Proposer[] proposingMatches;
	
	public static Pretender[] pretenderMatches;
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.out.println("stable marriage");
		
		//initialize both sides
		proposingMatches = initializeProposer();
		
		pretenderMatches = initializePretender();
		
		
		//run the algorithm while there are at least one man unmatched

		// loop
		
		while(oneUmaried()) {
		
			for(int i=0;i<proposingMatches.length;i++) {
				
				for(int j=0;j<pretenderMatches.length;j++) {
					
					//find the link between the proposer and the pretender that he prefer  
					
					if(proposingMatches[i].pret == pretenderMatches[j].prop) {
						
						
						//if the most preferred pretender of the man is unmatched
						
						if(!pretenderMatches[j].married) {
							matchCouple(pretenderMatches[j].prop,proposingMatches[i].prop);
						}
						else {
							prefer(pretenderMatches[j].prop,proposingMatches[i].prop);
						}
						
					}
				}
			}
		}
		
		System.out.println(" ");
		
		dispProposerArray(proposingMatches);
		
		dispMarriage();
		
		
		
	}
	
	
	
	/* Function who initialize Proposer side
	 * return Marriage[]
	 */
	public static Proposer[] initializeProposer() {
		Proposer[] returnArray;
		
		returnArray = new Proposer[proposingArray.length];
		
		for(int i=0; i<proposingArray.length;i++) {
			returnArray[i] = new Proposer(proposingArray[i][0], //proposer
										  proposingArray[i][1], //pretender
										  1,		            //pretender position
										  false);               //matched status
		}
		
		return returnArray;
	}
	
	/* Function who initialize Pretender side
	 * return Pretender[]
	 */
	public static Pretender[] initializePretender() {
		Pretender[] returnArray;
		
		returnArray = new Pretender[pretendersArray.length];
		
		for(int i=0; i<pretendersArray.length;i++) {
			returnArray[i] = new Pretender(pretendersArray[i][0], //proposer
										  ' ', 					  //pretender
										  false);                 //matched status
		}
		
		return returnArray;
	}
	
	
	/*Function who determinate if all the proposers are married
	 * return boolean
	 */
	public static boolean oneUmaried() {
		
		int nbUnmmaried = 0; //number of unmarried people
		
		for(int i=0;i<proposingMatches.length;i++) {
			if(!proposingMatches[i].married) {
				nbUnmmaried ++;
			}
		}
		
		if(nbUnmmaried > 0) { //there is at least one unmarried proposer
			return true;
		}else {
			return false;	//All proposers are married 
		}
	}
	
	/* Sub program who run through a Proposer array
	 * @param1 Proposer[] array 
	 */
	
	public static void dispProposerArray(Proposer[] array) {
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i].prop);
			System.out.print(":");
			System.out.print(array[i].pret);
			System.out.print(":");
			System.out.print(array[i].pos);
			System.out.print(":");
			System.out.print(array[i].married);
			System.out.println(" ");
		}
	}
	
	/* Sub program who run through a Pretender array
	 * @param1 Pretender[] array 
	 */
	
	public static void dispPretenderArray(Pretender[] array) {
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i].prop);
			System.out.print(":");
			System.out.print(array[i].pret);
			System.out.print(":");
			System.out.print(array[i].married);
			System.out.println(" ");
		}
	}
	
	
	
	/* Sub program who matches a couple
	 * @param1 char W
	 * @param2 char M
	 */
	
	public static void matchCouple(char W,char M) {
		
		for(int i=0;i<proposingMatches.length;i++) {
			if(proposingMatches[i].prop == M) {
				proposingMatches[i].married = true;
			}
		}
		
		for(int j=0;j<pretenderMatches.length;j++) {
			if(pretenderMatches[j].prop == W) {
				pretenderMatches[j].married = true;
				pretenderMatches[j].pret = M;
			}
		}
	}
	
	/* Sub program who unmatches a couple
	 * @param1 char W
	 * @param2 char M
	 */
	
	public static void unMatchCouple(char W,char M) {
		
		for(int i=0;i<proposingMatches.length;i++) {
			if(proposingMatches[i].prop == M) {
				proposingMatches[i].pret = getNextPretender(M, (proposingMatches[i].pos + 1));
				proposingMatches[i].pos += 1;
				proposingMatches[i].married = false;
			}
		}
		
		for(int j=0;j<pretenderMatches.length;j++) {
			if(pretenderMatches[j].prop == W) {
				pretenderMatches[j].married = false;
				pretenderMatches[j].pret = ' ';
			}
		}
	}
	
	/* Sub program who determine the most preferred pretender's proposer
	 * @param1 char W
	 * @param2 char M
	 */
	
	public static void prefer(char W, char M) {
		
		int pos1 = 0; //new M position
		int pos2 = 0; //old M position
		
		for(int i=0;i<pretenderMatches.length;i++) {
			
			if (pretenderMatches[i].prop == W) {
				
				
				// check if W most preferrer the new M than the old
				
				for(int j=0;j<pretendersArray.length;j++) {
					if(pretendersArray[j][0] == W) {
						for(int k=0; k<pretendersArray[j].length;k++) {
							if(pretendersArray[j][k] == M) {
								pos1 = k;
							}
							if(pretendersArray[j][k] == pretenderMatches[i].pret) {
								pos2 = k;
							}
						}
					}
				}
			
				
				//if W prefer the new M
				if(pos1 < pos2) {
					//unmatch W and the old M
					unMatchCouple(W, pretenderMatches[i].pret);				
					//match W and the new M
					matchCouple(W, M);
				}
				
				//if W prefer the old M
				if(pos1 > pos2) {
					// The proposer ask this next preferer 
					for(i=0;i<proposingMatches.length;i++) {
						if(proposingMatches[i].prop == M) {
							proposingMatches[i].pret = getNextPretender(M, (proposingMatches[i].pos + 1) );
							proposingMatches[i].pos += 1;
						}
					}
				}
			}
		}
	}
	
	public static char getNextPretender(char M, int position) {
		
		for(int i=0; i<proposingArray.length;i++) {
			if(proposingArray[i][0] == M) {
				return proposingArray[i][position];
			}
		}
		
		//case no results
		return ' ';
	}
	
	public static void dispMarriage() {
		for(int i=0;i<proposingMatches.length;i++) {
			System.out.println(proposingMatches[i].prop + " is married with " + proposingMatches[i].pret);
		}
	}
}
