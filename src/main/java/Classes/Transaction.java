package Classes;

public class Transaction {

	public final String acc, amount, id, doc, type, name;

	public Transaction(String id, String acc, String amount, String doc, String type, String name) {
		this.acc = acc;
		this.amount = amount;
		this.id = id;
		this.doc = doc;
		this.type = type;
		this.name = name;
	}

	public String getAcc() {
		return acc;
	}

	public String getAmount() {
		return amount;
	}

	public String getId() {
		return id;
	}

	public String getDoc() {
		return doc;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}