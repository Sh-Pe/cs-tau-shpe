package il.ac.tau.cs.software1.pacman;

import java.io.*;
import java.util.*;

public class PacmanSceneReader {

	public static List<PacmanGameObject> readScene(String filepath) {
		List<PacmanGameObject> objects = new ArrayList<>();
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filepath));
			String line = reader.readLine();
			int row = 0;
			while (line != null) {
				PacmanSceneReader.parseLine(line, row, objects);
				line = reader.readLine();
				row++;
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return objects;
	}
	
	private static void parseLine(String line, int row, List<PacmanGameObject> objects) {
		for (int col = 0; col < line.length(); col++)
			PacmanSceneReader.parseChar(line.charAt(col), row, col, objects);
	}
	
	private static void parseChar(char c, int row, int col, List<PacmanGameObject> objects) {
		PacmanGridComponent location = new PacmanGridComponent(col, PacmanConstants.gridHeight - 1 - row);
		
		if (c == 'x')
			objects.add(PacmanObjectFactory.spawnObstacle(location));
		else if (c == 'P')
			objects.add(PacmanObjectFactory.spawnPlayer(location));
		else if (c == '+')
			objects.add(PacmanObjectFactory.spawnBonusDot(location));
		else if (c == '#')
			objects.add(PacmanObjectFactory.spawnBonusPotion(location));
		else if (c == 'G')
			objects.add(PacmanObjectFactory.spawnGhost(location));
	};

}
