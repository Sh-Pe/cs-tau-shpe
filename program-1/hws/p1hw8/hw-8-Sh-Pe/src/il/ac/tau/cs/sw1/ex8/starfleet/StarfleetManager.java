package il.ac.tau.cs.sw1.ex8.starfleet;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		return fleet.stream().sorted().map(Spaceship::toString).toList();
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> output = new HashMap<>();
		fleet.forEach(ship -> output.put(ship.getClass().getSimpleName(), ship.getNumberOfInstances()));
		return output;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost(Collection<Spaceship> fleet) {
		return fleet.stream().mapToInt(Spaceship::getAnnualMaintenanceCost).sum();

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		return fleet.stream()
			.filter(ship -> ship instanceof MyWeaponizedSpaceShip)
			.flatMap(ship -> ((MyWeaponizedSpaceShip) ship).getWeapons().stream())
			.map(Weapon::getName)
			.collect(Collectors.toSet());

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		return fleet.stream().mapToInt(spaceship -> spaceship.getCrewMembers().size()).sum();

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		return (float) fleet.stream()
			.mapToInt(
				spaceship ->
					spaceship.getCrewMembers()
					.stream()
					.mapToInt(CrewMember::getAge)
					.sum()
			).sum()
			/ getTotalNumberOfFleetCrewMembers(fleet);
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		return fleet.stream().filter(spaceship -> !spaceship.getCrewMembers().stream().filter(crewMember -> crewMember.getClass().equals(Officer.class)).toList().isEmpty())
			.collect(Collectors.toMap(
				spaceship -> (Officer) (
					spaceship.getCrewMembers().stream()
						.filter(crewMember -> crewMember.getClass().equals(Officer.class))
						.max(Comparator.comparing((CrewMember crewMember) -> ((Officer) crewMember).getRank())).get()
				),
				spaceship -> spaceship
		));
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> output = new HashMap<>();
		fleet.stream()
			.flatMap(
				spaceship -> spaceship.getCrewMembers()
					.stream()
					.filter(crewMember -> crewMember.getClass() == Officer.class)
					.map(crewMember -> ((Officer) crewMember).getRank())
			)
			.forEach(rank -> {
			if (!output.containsKey(rank)) {
				output.put(rank, 0);
			}
			output.put(rank, output.get(rank) + 1);
		});
		return output.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
	}

}
