package Classes;

public class Transaction {

	public final String acc, amount, id, doc, type;

	public Transaction(String id, String acc, String amount, String doc, String type) {
		this.acc = acc;
		this.amount = amount;
		this.id = id;
		this.doc = doc;
		this.type = type;
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

}