
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		String t = str.substring(1);

		return t;
	}

	public static char head(String str) {
		char h = str.charAt(0);

		return h;
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int a = word1.length();
		int b = word2.length();
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}
		int delete = levenshtein(tail(word1), word2);
		int subst = levenshtein(word1, tail(word2));
		int replace = levenshtein(tail(word1), tail(word2));

		return (1 + Math.min(delete, Math.min(subst, replace)));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int min = threshold + 1;
		String correct = word;
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word, dictionary[i]);
			if (distance < min) {
				min = distance;
				correct = dictionary[i];

			}
		}
		return correct;
	}

}
