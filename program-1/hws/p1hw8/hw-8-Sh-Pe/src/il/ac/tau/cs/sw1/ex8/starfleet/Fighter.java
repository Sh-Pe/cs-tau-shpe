package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MyWeaponizedSpaceShip {


	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 2500 + super.getWeaponsMaintenanceCost() + (int) Math.floor(getMaximalSpeed() * 1000);
	}

}
