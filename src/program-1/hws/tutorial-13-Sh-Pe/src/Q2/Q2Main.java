package Q2;

import java.util.*;

record MyFile(String name, long size, long created) {
 public String toString() {
	return "'" + name + "' | " + size + " | " + created;
 }
}


public class Q2Main {

	public static SortedMap<String, List<MyFile>> createReport(List<MyFile> files, Comparator<MyFile> comp) {
		SortedMap<String, List<MyFile>> report = new TreeMap<>();
		for (MyFile file : files) {
			report.put(file.name().split("\\.")[1], List.of());
		}
		return new TreeMap<String, List<MyFile>>(Map.ofEntries((AbstractMap.SimpleEntry[]) report.entrySet().stream().map(entry -> new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue().stream().sorted())).toArray()));
    }
	
	public static void main(String[] args) {
		String [] filenames = {"b.java","hello.java", "a.java", "a.class","b.class", "emma.txt", "hello.jar"};
		int idx1 = 1273;
		int idx2 = 2466;
		List<MyFile> files = new ArrayList<>();
		for (String f: filenames) {
			files.add(new MyFile(f, idx1, idx2));
			idx1 += 17;
			idx2 += 191;
		}
		
		SortedMap<String, List<MyFile>> report = createReport(files, (x, y) -> Long.compare(x.created(), y.created()));
		for (String type : report.keySet()) { // Returns a set, but sorted
			System.out.println("*." + type);
			for (MyFile f : report.get(type)) {
				System.out.println("\t" + f.toString());
			}
		}
	}
/*
	public SortedMap createReport(Collection<) {
		Collections
	}

	public static class SortedMapImpl<K, V> implements SortedMap<K, V> {

		Map<K, V> innerMap = new HashMap<>();

		@Override
		public Collection<V> values() {
			return innerMap.values().stream().sorted().toList();
		}
		@Override
		public Set<Map.Entry<K, V>> entrySet() {
			return innerMap.entrySet().stream.sorted((Entry x, Entry y) ->
				K.compareTo(x.getKey(), y.getKey()).toList();
			)
		}
		@Override
		public Boolean containsKey(Object key) {
			returns innter.containsKey(key);
		}
		@Override
		public V put(K key, V value) {
			return innerMap.put(K, V);
		}
		@Override
		public V get(Object key) {
			return innerMap.get(key);
		}
		@Override
		public Set<K> keySet() {
			return innerMap.keySet().stream().sorted().toList();
		}

	}*/

}



