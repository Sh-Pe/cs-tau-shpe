package il.ac.tau.cs.sw1.ex8.starfleet;

public class Weapon {
	private String name;
	private int firePower;
	private int annualMaintenanceCost;
	public Weapon(String name, int firePower, int annualMaintenanceCost){
		this.name = name;
		this.firePower = firePower;
		this.annualMaintenanceCost = annualMaintenanceCost;
	}
	
	public String getName(){
		return name;
	}
	
	public int getFirePower(){
		return firePower;
	}
	
	public int getAnnualMaintenanceCost(){
		return annualMaintenanceCost;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Weapon [");
		sb.append("name=").append(this.name).append(", ");
		sb.append("firePower=").append(this.getFirePower()).append(", ");
		sb.append("annualMaintenanceCost=").append(this.getAnnualMaintenanceCost()).append("]");
		return sb.toString();
	}
}
