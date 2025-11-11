package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StarfleetManagerTester {

	static int crewId=0; //used for generating unique names for crew members

	public static void main(String[] args) {

		System.out.println("*** STARFLEET COMMAND OFFICIAL REPORT ***\n");

		List<Spaceship> fleet = generateStarfleet(); //Generates fleet objects

		System.out.println("Fleet ships sorted by fire power and commission year:");
		for (String shipDescription : StarfleetManager.getShipDescriptionsSortedByFirePowerAndCommissionYear(fleet)) {
			System.out.println(shipDescription);
		}

		System.out.println();
		System.out.println("Ship counts by type:");
		Map<String, Integer> instancesNumberPerClass = StarfleetManager.getInstanceNumberPerClass(fleet);
		List<String> sortedNames = new ArrayList<>(instancesNumberPerClass.keySet());
		Collections.sort(sortedNames);
		for (String spaceshipType : sortedNames) {
			System.out.println("\t" + instancesNumberPerClass.get(spaceshipType) + "\t" + spaceshipType);
		}

		System.out.println();
		System.out.println("Weapon types:");
		SortedSet<String> weapons = new TreeSet<>(StarfleetManager.getFleetWeaponNames(fleet));
		for (String  weaponName: weapons) {
			System.out.println("\t" + weaponName);
		}

		System.out.println();
		System.out.println("Highest ranking officer per ship:");
		Map<Officer,Spaceship> officersToShipsMap = StarfleetManager.getHighestRankingOfficerPerShip(fleet); 
		SortedSet<Officer> sortedOfficers = new TreeSet<>(new Comparator<Officer>() {

			@Override
			public int compare(Officer o1, Officer o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		sortedOfficers.addAll(officersToShipsMap.keySet());
		for (Officer officer : sortedOfficers) {
			System.out.println("\t" + officer.getRank() + "\t" + officer.getName() + "\t" +  officersToShipsMap.get(officer) .getName());
		}


		System.out.println();
		System.out.println("Officer ranks sorted ascendingly by popularity:");
		for (Map.Entry<OfficerRank, Integer>  rankCountPair: StarfleetManager.getOfficerRanksSortedByPopularity(fleet)) {
			System.out.println("\t" + rankCountPair.getValue() + "\t" + rankCountPair.getKey());
		}

		System.out.printf("\nFleet Totals:\n");
		System.out.printf("\tTotal fleet crew members:\t\t\t%d\n", StarfleetManager.getTotalNumberOfFleetCrewMembers(fleet));
		System.out.printf("\tAverage age of fleet officers:\t\t\t%.2f\n", StarfleetManager.getAverageAgeOfFleetOfficers(fleet));
		System.out.printf("\tTotal annual maintenance cost:\t\t\t%d\n", StarfleetManager.getTotalMaintenanceCost(fleet));
	}

	// Generates a list of spaceship objects with synthesized data
	private static List<Spaceship> generateStarfleet() {
		List<Spaceship> fleet = new ArrayList<>();

		fleet.add(new TransportShip("USS Astral Queen", 2396, 5.1f, generateCrew (9,14), 2000,5000));
		fleet.add(new TransportShip("USS Lantree", 2457, 5.1f, generateCrew (4,5), 3000,10000));	

		List<Weapon> weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,110));
		weapons.add(new Weapon("Quantum Torpedoes",120,100));
		weapons.add(new Weapon("TAU Phasers",150,280));
		fleet.add(new Fighter("USS Defiant",2423,6f, generateCrew (20,110), weapons));

		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		weapons.add(new Weapon("Evaporator",30,300));
		fleet.add(new StealthCruiser("USS Galaxy",2370,9f, generateCrew (1,3), weapons));

		fleet.add(new StealthCruiser("USS Odyssey",2419,9f, generateCrew (1,3)));
		fleet.add(new StealthCruiser("USS Amsterdamer",2410,9.2f, generateCrew (1,2)));

		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,110));
		weapons.add(new Weapon("Photon Torpedoes",120,260));
		fleet.add(new Bomber("USS Yamaguchi ",2416,9.9f, generateCrew (21,212), weapons,5));
				
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		weapons.add(new Weapon("Evaporator",30,300));
		Set<Cylon> cylons = new HashSet<>();
		cylons.add(new Cylon("Sharon", 35, 35, 5));
		fleet.add(new CylonRaider("Raider 1", 2056, 3.5f, cylons, weapons));
		
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Laser Cannons",10,100));
		weapons.add(new Weapon("Evaporator",30,300));
		Set<CrewWoman> crewWomen = new HashSet<>();
		crewWomen.add(new Officer("Lee Adama", 28, 5, OfficerRank.Captain));
		fleet.add(new ColonialViper("Viper1", 2451,7.2f, crewWomen, weapons));

		crewWomen = new HashSet<>();
		crewWomen.add(new Officer("Cara Thrace", 28, 5, OfficerRank.Lieutenant));
		fleet.add(new ColonialViper("Viper2", 2451,7.2f, crewWomen, weapons));


		return fleet;
	}

	
	
	
	// Generates a set containing crew-member objects containing synthesized data
	private static Set<CrewMember> generateCrew (int numberOfOfficers, int numberOfCrewmen) {

		Set<CrewMember> crew = new HashSet<>();

		//Generating Officers
		for (int i=0; i< numberOfOfficers-1; i++) {
			crewId++;
			String name = generateName();
			Integer age = generateAge();
			Integer yearsInService = generateYearsInService();
			OfficerRank rank = generateRank();

			crew.add(new Officer(name,age,yearsInService,rank));
		}

		// Adding one captain
		crewId++;
		crew.add(new Officer(generateName(),generateAge(),generateYearsInService(),OfficerRank.Captain));

		//Generating Crewmen
		for (int i=0; i< numberOfCrewmen; i++) {
			crewId++;
			String name = generateName();
			Integer age = generateAge();
			Integer yearsInService = generateYearsInService();

			crew.add(new CrewWoman(age,yearsInService,name));
		}

		return crew;
	}

	private static String generateName() {
		final String[] nameRepository = new String[]{"Riker", "Kathryn", "Picard", "Archer", "Sisko","Troi","Crusher", "FitzRoy", "Sparrow", "Nemo", "America","Data"};
		return  nameRepository[(crewId % (nameRepository.length))] + " #" +crewId;
	}

	private static Integer generateAge() {	
		final Integer[] ageRepository = new Integer[]{31, 47, 22, 21, 57, 104, 28, 19, 35, 64};
		return ageRepository[crewId % (ageRepository.length)];
	}

	private static Integer generateYearsInService() {	
		final Integer[] yearsRepository =  new Integer[]{7, 2, 14, 6, 32, 16, 12, 2, 1, 17, 5};
		return yearsRepository[crewId % (yearsRepository.length)];
	}

	private static OfficerRank generateRank() {	
		final OfficerRank[] ranksRepository =  new OfficerRank[]{OfficerRank.Ensign, OfficerRank.Ensign, OfficerRank.Commander, OfficerRank.Lieutenant, OfficerRank.Ensign, OfficerRank.LieutenantCommander,  OfficerRank.Lieutenant,};
		return ranksRepository[crewId % (ranksRepository.length)];
	}


}
