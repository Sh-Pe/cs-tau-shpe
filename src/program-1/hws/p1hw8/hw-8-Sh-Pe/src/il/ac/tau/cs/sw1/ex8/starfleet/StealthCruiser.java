package il.ac.tau.cs.sw1.ex8.starfleet;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {

	public static int totalCloakingDevices = 0;

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		totalCloakingDevices++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name, commissionYear, maximalSpeed, crewMembers, List.of(new Weapon("Laser Cannons", 10, 100)));
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost() + 50 * totalCloakingDevices;
	}
}
