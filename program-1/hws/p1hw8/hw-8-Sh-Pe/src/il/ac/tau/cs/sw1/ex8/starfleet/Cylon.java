package il.ac.tau.cs.sw1.ex8.starfleet;

public class Cylon extends MyAbstractCrewMember {

	private final int modelNumber;

	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(age, yearsInService, name);
		this.modelNumber = modelNumber;
	}

	public int getModelNumber() {
		return modelNumber;
	}

}
