package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter {

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 3500 + getWeaponsMaintenanceCost() + 500 * getCrewMembers().size() + (int) (1200 * getMaximalSpeed());
	}

}
