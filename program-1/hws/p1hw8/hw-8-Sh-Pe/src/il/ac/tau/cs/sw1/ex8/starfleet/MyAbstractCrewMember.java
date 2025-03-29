package il.ac.tau.cs.sw1.ex8.starfleet;

import java.util.Objects;

public abstract class MyAbstractCrewMember implements CrewMember {

	private final int age;
	private final int yearsInService;
	private final String name;

	public MyAbstractCrewMember(int age, int yearsInService, String name){
		this.age = age;
		this.yearsInService = yearsInService;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public int getYearsInService() {
		return yearsInService;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MyAbstractCrewMember that)) return false;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

}
