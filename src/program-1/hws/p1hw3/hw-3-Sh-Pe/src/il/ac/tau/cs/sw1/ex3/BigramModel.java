package il.ac.tau.cs.sw1.ex3;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	private static final String ENGLISH_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		File fromFile = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
		String line;
		HashSet<String> seen = new HashSet<>();
		ArrayList<String> output = new ArrayList();
		while ((line = bufferedReader.readLine()) != null && seen.size() <= MAX_VOCABULARY_SIZE) {
			String[] words = line.trim().toLowerCase().split(" ");
			for (String word : words) {
				String valid = toValid(word);
				if (valid != null) {
					if (!seen.contains(valid)) {
						output.add(valid);
					}
					seen.add(valid);
				}
			}
		}
		bufferedReader.close();
		return output.toArray(new String[]{});
	}

	private static String toValid(String word) {
		if (word.chars().anyMatch((int c) -> ENGLISH_LETTERS.contains(String.valueOf((char) c)))) {
			return word;
		} else if (word.chars().allMatch((int c) -> Character.isDigit((char) c))){
			return "some_num";
		}
		return null;
	}


	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		Hashtable<String, Integer> fastVoc = new Hashtable<>();
		int vocSize = vocabulary.length;
		for (int i = 0; i < vocSize; i++) {
			fastVoc.put(vocabulary[i], i);
		}
		int[][] output = new int[vocSize][vocSize];

		File fromFile = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
		String line;

//		System.out.println(fastVoc);

		while ((line = bufferedReader.readLine()) != null) {
			String[] words = line.trim().toLowerCase().split(" ");
			for (int wi = 0; wi < words.length - 1; wi++) {
				String cur = toValid(words[wi]);
				String next = toValid(words[wi + 1]);
				if (cur == null || next == null) {
//					System.out.println(cur + ", " + next + "null");
					continue;
				} else if (!fastVoc.containsKey(cur) || !fastVoc.containsKey(next)) {
//					System.out.println(cur + ", " + next + "nothere");
					continue;
				}
//				System.out.println(cur + ", " + next + "added");
				output[fastVoc.get(cur)][fastVoc.get(next)] += 1;
			}
		}

		bufferedReader.close();
		return output;
	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		saveVoc(fileName);
		saveCounts(fileName);
	}

	private void saveVoc(String filename) throws IOException {
		filename += VOC_FILE_SUFFIX;
		File fromFile = new File(filename);
		final StringBuilder fileContent = new StringBuilder();
		fileContent.append(mVocabulary.length).append(" words");
		int length = mVocabulary.length;
		for (int i = 0; i < length; i++) {
			fileContent.append("\r\n");
			fileContent.append(i).append(",").append(mVocabulary[i]);
		}
		FileWriter writer = new FileWriter(fromFile);
		writer.write(fileContent.toString());
		writer.close();
	}
	
	private void saveCounts(String filename) throws IOException {
		filename += COUNTS_FILE_SUFFIX;
		File fromFile = new File(filename);
		final StringBuilder fileContent = new StringBuilder();
		for (int i = 0; i < mBigramCounts.length; i++) {
			int[] col = mBigramCounts[i];
			for (int j = 0; j < col.length; j++) {
				int value = mBigramCounts[i][j];
				if (value == 0) {
					continue;
				}
				fileContent.append(i).append(",").append(j).append(":").append(value);
				fileContent.append("\r\n");
			}
		}

		// delete the latest newline
		fileContent.deleteCharAt(fileContent.length() - 1);
		fileContent.deleteCharAt(fileContent.length() - 1);

		FileWriter writer = new FileWriter(fromFile);
		writer.write(fileContent.toString());
		writer.close();
	}
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException { // Q - 4
		loadVoc(fileName);
		loadCounts(fileName);
	}

	private void loadVoc(String filename) throws IOException {
		filename += VOC_FILE_SUFFIX;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		reader.readLine();
		ArrayList<String> output  = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			int i = 0;
			while ((line.charAt(i++)) != ',') {}
			output.add(line.substring(i));
		}
//		System.out.println(Arrays.toString(mVocabulary));
		mVocabulary = output.toArray(new String[0]);
	}

	private void loadCounts(String filename) throws IOException {
		filename += COUNTS_FILE_SUFFIX;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		int len = mVocabulary.length;
		mBigramCounts = new int[len][len];
		String line;
		while ((line = reader.readLine()) != null) {
			int i = 0;
			while ((line.charAt(i++)) != ',') {}
			int firstNum = Integer.parseInt(line.substring(0, i - 1));
			int j = i;
			while ((line.charAt(j++)) != ':') {}
			int secondNum = Integer.parseInt(line.substring(i, j - 1));

			mBigramCounts[firstNum][secondNum] = Integer.parseInt(line.substring(j));
		}
//		System.out.println(Arrays.deepToString(mBigramCounts));
	}
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		for (int i = 0; i < mVocabulary.length; i++) {
			if (word.equals(mVocabulary[i])) {
				return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int word1Index = getWordIndex(word1);
		int word2Index = getWordIndex(word2);
		if (word1Index == ELEMENT_NOT_FOUND || word2Index == ELEMENT_NOT_FOUND) {
			return 0;
		}
		return mBigramCounts[word1Index][word2Index];
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int maxVal = -1;
		int maxInd = -1;
		int wordInd = getWordIndex(word);
		if (wordInd == -1) { return null; }
		for (int i = 0; i < mVocabulary.length; i++) {
			int val = mBigramCounts[wordInd][i];
			if (val > maxVal) {
				maxVal = val;
				maxInd = i;
			}
		}
		return mVocabulary[maxInd];
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String[] words = sentence.split(" ");
		int lenMinusOne = words.length - 1;
		for (int i = 0; i < lenMinusOne; i++) {
			if (getBigramCount(words[i], words[i + 1]) == 0) {
				return false;
			}
		}
		return !(getWordIndex(words[lenMinusOne]) == ELEMENT_NOT_FOUND);
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		int innerProd = 0;
		int ASizeSquared = 0;
		int BSizeSquared = 0;
		int n = arr1.length;
		for (int i = 0; i < n; i++) {
			innerProd += arr1[i] * arr2[i];
			ASizeSquared += arr1[i] * arr1[i];
			BSizeSquared += arr2[i] * arr2[i];
		}
		if (ASizeSquared == 0  || BSizeSquared == 0) {
			return -1;
		}
		return innerProd / (Math.sqrt(ASizeSquared) * Math.sqrt(BSizeSquared));
	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		double maxCosine = -1.0D;
		String maxCosineWordValue = "";
		int wordIndex = getWordIndex(word);
		int[] vec1 = mBigramCounts[wordIndex];
		for (int i = 0; i < vec1.length; i++) {
			if (i == wordIndex) { continue; }
			int[] vec2 = mBigramCounts[i];
			double change = calcCosineSim(vec1, vec2);
			String currentWord = mVocabulary[i];
			if (change > maxCosine) {
				maxCosine = change;
				maxCosineWordValue = currentWord;

			}
		}
		return maxCosineWordValue;
	}

	
}
