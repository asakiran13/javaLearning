package algorithms;


public class StringMatching {

	private static final int NO_OF_CHARS = 256;

	private static final int PRIME = 251;

	private static final int ALPHABET = 256;

	private static int[] prefixFunction(String pattern){
		int m = pattern.length(),k=0;
		int[] prefix = new int[m + 1];
		prefix[0] = prefix[1] = 0;
		for(int j = 2; j <= m; j++){
			while(k > 0 && pattern.charAt(k) != pattern.charAt(j-1)){
				k = prefix[k];
			}
			if(pattern.charAt(j-1) == pattern.charAt(k)) k = k + 1;
			prefix[j] = k;
		}
		return prefix;
	}

	//Prints all indexes where the given pattern is present
	public static void KMPMatching(String text, String pattern){
		int n,m,k=0;
		int[] prefix = prefixFunction(pattern);
		n = text.length();
		m = pattern.length();
		for(int i = 0; i < n; i++){
			while(k > 0 && pattern.charAt(k) != text.charAt(i))k = prefix[k];
			if(pattern.charAt(k) == text.charAt(i)) k = k+1;
			if(k == m){
				System.out.println("Pattern occurs at index: " + (i-m+1));
				k = prefix[k];
			}
		}
		return;
	}

	private static int calculateHighestPower(int m){
		int result = 1;
		for(int i = 1; i < m; i++){
			result = (result*ALPHABET) % PRIME;
		}
		return result % PRIME;
	}

	public static void RabinKarpMatching(String text, String pattern){

		int pvalue, tvalue, temp, n, m, h;
		n = text.length();
		m = pattern.length();
		h = calculateHighestPower(m);
		pvalue = tvalue = 0;

		for(int i = 0; i < m; i++){
			pvalue = (pvalue*ALPHABET + pattern.charAt(i)) % PRIME;
			tvalue = (tvalue*ALPHABET + text.charAt(i)) % PRIME;
		}

		pvalue = pvalue % PRIME;
		tvalue = tvalue % PRIME;

		for(int s = 0; s <= n-m; s++){

			if(tvalue == pvalue){
				int matchingChars = 0;
				while(matchingChars < m && pattern.charAt(matchingChars) == text.charAt(s+matchingChars)) matchingChars++;
				if(matchingChars == m) System.out.print("The pattern occurs at index: " + s);;
			}
			if(s < n-m){
				tvalue = (ALPHABET * (tvalue - h * text.charAt(s)) + text.charAt(s+m)) % PRIME;
				if(tvalue < 0) tvalue += PRIME;
			}
		}
		return;
	}

	private static int[][] buildFiniteAutomata(String pattern){
		int m = pattern.length();
		int[][] FA = new int[m + 1][NO_OF_CHARS];
		FA[0][pattern.charAt(0)] = 1;
		int lps = 0;
		for(int state = 1; state < m; state++){
			for(int i = 0; i < NO_OF_CHARS; i++){
				FA[state][i] = FA[lps][i];
			}
			FA[state][pattern.charAt(state)] = state + 1;
			if(state < m)
				lps = FA[lps][pattern.charAt(state)];
		}
		return FA;
	}

	public static void finiteAutomataStringMatching(String text, String pattern){

		int m = pattern.length();
		int n = text.length();
		int[][] FA = buildFiniteAutomata(pattern);
		int curState = 0;
		for(int i = 0; i < n; i++){
			curState = FA[curState][text.charAt(i)];
			if(curState == m){
				System.out.println("Pattern found at index: " + (i-m+1));
			}
		}
		return;
	}


	public static void main(String[] args){
		String text = "asdniasdopwerasd";
		String pattern = "asd";
		finiteAutomataStringMatching(text, pattern);
	}
}
