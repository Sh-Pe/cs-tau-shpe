package il.ac.tau.cs.sw1.ex6.collections;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class CollectionsExercise {

	/* @pre: mapsList.size() > 0 */
	public static Map<Character, Integer> processDicts(List<Map<Character, Set<String>>> mapsList) {
		HashMap<Character, HashMap<String, Integer>> counts = new HashMap<>();
		for (Map<Character, Set<String>> mapping : mapsList) {
			for (Character character : mapping.keySet()) {
				Set<String> keys = mapping.get(character);
				Map<String, Integer> entry = counts.get(character);
				if (entry == null) {
					counts.put(character, new HashMap<>());
					entry = counts.get(character);
				}
				for (String key : keys) {
					Integer currentCount = entry.get(key);
					if (currentCount == null) {
						counts.get(character).put(key, 0);
						currentCount = 0;
					}
					counts.get(character).put(key, currentCount + 1);
				}
			}
		}
		HashMap<Character, Integer> output = new HashMap<>();
		for (Character character : counts.keySet()) {
			int max = 0;
			for (Integer val : counts.get(character).values()) {
				if (max < val) {
					max = val;
				}
			}
			output.put(character, max);
		}
		return output;
	}
	
	
	/* @pre p is prime */
	public static List<Integer> weirdSort(List<Integer> lst, int p) {
		Function<Integer, Double> value = (i) -> Math.cos(((double) i) % p);
		return lst.stream().sorted(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				System.out.println(o1 + " v " + o2);
				if (Objects.equals(value.apply(o1), value.apply(o2))) { return 0; }
				int out = value.apply(o1) < value.apply(o2) ? -1 : 1;
				System.out.println(out);
				return out;
			}
		}).toList();
	}
}
