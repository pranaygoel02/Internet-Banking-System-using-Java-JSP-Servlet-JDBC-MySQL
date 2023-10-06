package Model;

public class Link {
	public final String label;
	public final String link;
	public final int min_blockingLevel;
	public final int max_blockingLevel;

	// TODO: check min and max blocking levels to protect certain routes

	Link(String label, String link, int min_blockingLevel, int max_blockingLevel) {
		this.label = label;
		this.link = link;
		this.min_blockingLevel = min_blockingLevel;
		this.max_blockingLevel = max_blockingLevel;
	}
}