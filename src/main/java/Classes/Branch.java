package Classes;

public class Branch {
	public final String id;
	private final String name;
	private final String minBal;

	public Branch(String id, String name, String min_bal) {
		this.id = id;
		this.name = name;
		this.minBal = min_bal;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMinBal() {
		return minBal;
	}

}
