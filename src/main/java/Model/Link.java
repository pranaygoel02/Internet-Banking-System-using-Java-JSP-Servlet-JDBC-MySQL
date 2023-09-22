package Model;

public class Link {
	public final String label;
	public final String link;
	public final int blockingLevel;
	Link(String label, String link, int blockingLevel) {
		this.label = label;
		this.link = link;
		this.blockingLevel = blockingLevel;
	}
}