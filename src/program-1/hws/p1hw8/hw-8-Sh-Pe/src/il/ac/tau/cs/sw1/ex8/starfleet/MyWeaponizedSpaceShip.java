package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.List;
import java.util.Set;

public abstract class MyWeaponizedSpaceShip extends MyAbstractSpaceship {

	private final List<Weapon> weapons;

	public MyWeaponizedSpaceShip(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	@Override
	public int getFirePower() {
		return super.getFirePower() + getWeaponsFirePower();
	}

	protected int getWeaponsMaintenanceCost() {
		return weapons.stream().mapToInt(Weapon::getAnnualMaintenanceCost).sum();
	}

	protected int getWeaponsFirePower() {
		return weapons.stream().mapToInt(Weapon::getFirePower).sum();
	}

	@Override
	public String toString() {
		return super.toString()
			+ "\n\tWeaponArray=" + weapons.toString();
	}

}
