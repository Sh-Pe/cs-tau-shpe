package il.ac.tau.cs.software1.sokoban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import il.ac.tau.cs.software1.pacman.PacmanGridComponent;


public class SokobanSceneReader {
	public static List<SokobanGameObject> readScene(String filepath) {
		List<SokobanGameObject> objects = new ArrayList<>();
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filepath));
			String line = reader.readLine();
			int row = 0;
			while (line != null) {
				SokobanSceneReader.parseLine(line, row, objects);
				line = reader.readLine();
				row++;
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return objects;
	}
	
	private static void parseLine(String line, int row, List<SokobanGameObject> objects) {
		for (int col = 0; col < line.length(); col++)
			SokobanSceneReader.parseChar(line.charAt(col), row, col, objects);
	}
	
	private static void parseChar(char c, int row, int col, List<SokobanGameObject> objects) {
		PacmanGridComponent location = new PacmanGridComponent(col, SokobanConstants.gridHeight - 1 - row);
		
		if (c == 'x')
			objects.add(SokobanObjectFactory.spawnWall(location));
		else if (c == 'W')
			objects.add(SokobanObjectFactory.spawnWorker(location));
		else if (c == '+')
			objects.add(SokobanObjectFactory.spawnBox(location));
		else if (c == 'O')
			objects.add(SokobanObjectFactory.spawnTarget(location));
		
	};
}
