import java.util.*;

public class ArrayUtils {

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		int n = array.length;
		if (direction == 'L') {
			move = -move;
		} else if (direction != 'R'){
			move = 0;
		}

		int[] output = new int[n];
		for (int i = 0; i < n; i++) {
			output[((move + i) % n + n) % n] = array[i];
		}

		System.arraycopy(output, 0, array, 0, n);

		return array;
	}


	private static int findShortestPathRec(int[][] m, int i, int j, HashSet<Integer> notVisited, int indention) {
//		System.out.println("  ".repeat(indention) + "performing " + i + " -> " + j + " on notVisited=" + notVisited);
		if (i == j) {
			return 0;
		} else if (indention > 10){
			return 0;
		}

		int n = m.length;

		ArrayList<Integer> toVisit = new ArrayList<>();
		for (int index = 0; index < n; index++) {
			int i1 = m[i][index];
//			System.out.println("  ".repeat(indention) + " check inx " + index + " got " + i1);
			if (notVisited.contains(index) && i1 == 1) {
				toVisit.add(index);
			}
		}
//		System.out.println("constructed " + toVisit);

		int latgestPossibleValue = (int) Math.pow(n, 2) + 1;
		int min = latgestPossibleValue;
		for (int node : toVisit) {
			HashSet newNotVisited = new HashSet<>(notVisited);
			newNotVisited.remove(node);
			int shortestPath = findShortestPathRec(m, node, j, newNotVisited, indention + 1);
			if (shortestPath != -1) {
				min = Math.min(shortestPath, min);
			}
		}
		if (min == latgestPossibleValue) {
			return -1;
		}

		return min + 1;
	}

	public static int findShortestPath(int[][] m, int i, int j) {
		HashSet<Integer> notVisited = new HashSet<>();
		for (int index = 0; index < m.length; index++) {
			notVisited.add(index);
		}
		return findShortestPathRec(m, i, j, notVisited, 0);
	}

}
