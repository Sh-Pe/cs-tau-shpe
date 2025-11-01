
import java.util.*;

record MyFile(String name, long size, long created) {
 public String toString() {
	return "'" + name + "' | " + size + " | " + created;
 }
}


public class Q2Main {

	public static SortedMap<String, List<MyFile>> createReport(List<MyFile> files, Comparator<MyFile> comp) {
		SortedMap<String, List<MyFile>> report = new TreeMap<>();
        
		//add your code here
		
		return report;
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

}



