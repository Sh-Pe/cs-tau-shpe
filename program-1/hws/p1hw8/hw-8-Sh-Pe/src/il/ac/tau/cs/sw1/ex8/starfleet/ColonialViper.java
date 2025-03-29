package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter {

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 4000 + getWeaponsMaintenanceCost() + 500 * getCrewMembers().size() + (int) (500 * getMaximalSpeed());
	}

}
