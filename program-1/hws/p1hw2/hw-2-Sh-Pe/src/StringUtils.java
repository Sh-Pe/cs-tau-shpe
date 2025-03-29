import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtils {

	public static String findSortedSequence(String str) {
		if (str.isEmpty()) {
			return "";
		}
		String[] words = str.split(" ");
		System.out.println(Arrays.toString(words));
		ArrayList<Integer> wordCounts = new ArrayList<>(List.of(1));
		ArrayList<String> options = new ArrayList<>(List.of(words[0]));
		int max = 0;
		int maxIndex = 0;
		for (int i = 0; i < words.length; i++) {
//			System.out.println("max: " + max + " maxIndex: " + maxIndex + " options: " + options + " wordCounts: " + wordCounts);
			boolean mode;
			boolean isNotLast = i != words.length - 1;
			if (isNotLast) mode = !isSecondsGreaterThanFirst(words[i], words[i + 1]);
			else mode = false;

			if (mode) {
				wordCounts.set(options.size() - 1, wordCounts.getLast() + 1);
				options.set(options.size() - 1, options.getLast() + " " + words[i + 1]);
			} else {
				int newMax = wordCounts.getLast();
//				System.out.println("false -> " + newMax);
				if (newMax >= max) {
					max = newMax;
					maxIndex = options.size() - 1;
				}
				wordCounts.add(1);
				if (isNotLast) options.add(words[i + 1]);
			}
		}
//		System.out.println("max: " + max + " maxIndex: " + maxIndex + " options: " + options + " wordCounts: " + wordCounts);

		return options.get(maxIndex);
	}

	private static boolean isSecondsGreaterThanFirst(String w1, String w2) {
		for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {
			if ((int) w1.charAt(i) < (int) w2.charAt(i)) {
				return false;
			} if ((int) w1.charAt(i) > (int) w2.charAt(i)) {
				return true;
			}
		}
		return w1.length() < w2.length();
	}

	
	public static boolean isEditDistanceOne(String a, String b) {
		if (a.length() == b.length()) {
			int disagreement = 0;
			for (int i = 0; i < a.length(); i++) {
				disagreement += a.charAt(i) == b.charAt(i) ? 0 : 1;
			}
			return disagreement <= 1;
		} else if (a.length() == b.length() + 1) {
//			System.out.println("c1");
			return canAddToA(b, a);
		} else if (a.length() + 1 == b.length()) {
//			System.out.println("c2");
			return canAddToA(a, b);
		} else {
			return false;
		}
	}

	private static boolean canAddToA(String a, String b) {
//		System.out.println(a + " " + b);
		int agreeTill = -1;
		int agreeAfter = 0;
		for (int i = 0; i < a.length(); i++) {
//			System.out.println("i" + i + ", a[i]: " + a.charAt(i) + ", b[i]: " + b.charAt(i) + ", a:" + agreeAfter + ", t:" + agreeTill);
			if (a.charAt(i) == b.charAt(i) && agreeTill == i - 1) {
				agreeTill++;
			} else if (a.charAt(i) == b.charAt(i + 1) && agreeAfter + agreeTill == i - 1) {
				agreeAfter++;
			}
		}
//		System.out.println(agreeAfter + ", " + agreeTill);
		return agreeTill + agreeAfter == a.length() - 1;
	}
	
}
