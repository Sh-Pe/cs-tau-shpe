package il.ac.tau.cs.sw1.ex8.starfleet;

public class Officer extends CrewWoman {

	private final OfficerRank rank;

	public Officer(String name, int age, int yearsInService, OfficerRank rank) {
		super(age, yearsInService, name);
		this.rank = rank;
	}

	public OfficerRank getRank() {
		return rank;
	}
	
}
