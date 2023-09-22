package Classes;

public class Branch {
	public final String id;	
	private final String name;
	
	public Branch(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
