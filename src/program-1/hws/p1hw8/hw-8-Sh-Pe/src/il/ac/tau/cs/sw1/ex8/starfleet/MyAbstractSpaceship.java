package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.*;

public abstract class MyAbstractSpaceship implements Spaceship, Comparable<MyAbstractSpaceship> {

	private static final HashMap<String, Integer> instancesCounts = new HashMap<>();

	private final String name;
	private final int commissionYear;
	private final float maximalSpeed;
	private final int firePower;
	private final Set<? extends CrewMember> crewMembers;

	public MyAbstractSpaceship(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
		this.name = name;
		this.commissionYear = commissionYear;
		this.maximalSpeed = maximalSpeed;
		this.firePower = 10;
		this.crewMembers = crewMembers;

		String instanceName = getClass().getSimpleName();
		if (!instancesCounts.containsKey(instanceName)) {
			instancesCounts.put(instanceName, 0);
		}
		instancesCounts.put(instanceName, instancesCounts.get(instanceName) + 1);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getMaximalSpeed() {
		return maximalSpeed;
	}

	@Override
	public int getFirePower() {
		return firePower;
	}

	@Override
	public abstract int getAnnualMaintenanceCost();

	@Override
	public int getCommissionYear() {
		return commissionYear;
	}

	@Override
	public Set<? extends CrewMember> getCrewMembers() {
		return crewMembers;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()
			+ "\n\tName=" + getName()
			+ "\n\tCommissionYear=" + getCommissionYear()
			+ "\n\tMaximalSpeed=" + getMaximalSpeed()
			+ "\n\tFirePower=" + getFirePower()
			+ "\n\tCrewMembers=" + getCrewMembers().size()
			+ "\n\tAnnualMaintenanceCost=" + getAnnualMaintenanceCost();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MyAbstractSpaceship that)) return false;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public int compareTo(MyAbstractSpaceship o) {
		int compareFirePower = Integer.compare(o.getFirePower(), this.getFirePower());
		int compareYear = Integer.compare(o.getCommissionYear(), this.getCommissionYear());
		int compareName = this.getName().compareTo(o.getName());
		return compareFirePower == 0 ? (compareYear == 0 ? compareName : compareYear) : compareFirePower;
	}

	@Override
	public int getNumberOfInstances() {
		return instancesCounts.get(getClass().getSimpleName());
	}

}
