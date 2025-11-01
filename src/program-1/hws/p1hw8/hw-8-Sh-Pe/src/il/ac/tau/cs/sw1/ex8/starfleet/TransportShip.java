package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.Set;

public class TransportShip extends MyAbstractSpaceship {

	private final int cargoCapacity;
	private final int passengerCapacity;

	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 3000 + 5 * cargoCapacity + 3 * passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	@Override
	public String toString() {
		return super.toString()
			+ "\n\tCargoCapacity=" + cargoCapacity
			+ "\n\tPassengerCapacity=" + passengerCapacity;
	}
}
