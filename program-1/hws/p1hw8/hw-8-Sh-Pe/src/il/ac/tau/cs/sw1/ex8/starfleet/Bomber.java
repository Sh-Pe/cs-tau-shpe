package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MyWeaponizedSpaceShip {

	private final int numberOfTechnicians;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.numberOfTechnicians = numberOfTechnicians;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 5000 + (int) ((getWeaponsMaintenanceCost()) * (100.0 - 10.0 * numberOfTechnicians) / 100.0);
	}

	public int getNumberOfTechnicians() {
		return numberOfTechnicians;
	}

	@Override
	public String toString() {
		return super.toString()
			+ "\n\tNumberOfTechnicians=" + 5
		;
	}

}
