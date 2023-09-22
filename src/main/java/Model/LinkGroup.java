package Model;

import java.util.List;

public class LinkGroup {
	public final String label;
	public final List<Link> links;
	public final int blockingLevel;
	public LinkGroup(String label, List<Link> links, int blockingLevel) {
		this.label = label;
		this.links = links;
		this.blockingLevel = blockingLevel;
	}
}
