package Classes;

public class Account {
	private final String no;	
	private final String type;
	private final String status;
	private final String branch_id;
	private final String branch_name;
	private final String balance;
	
	public Account(String no, String type, String status, String branch_id, String branch_name, String balance) {
		this.no = no;
		this.type = type;
		this.status = status;
		this.branch_id = branch_id;
		this.branch_name = branch_name;
		this.balance = balance;
	}
	
	public String getNo() {
		return no;
	}
	
	public String getType() {
		return type;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getBranchName() {
		return branch_name;
	}
	
	public String getBranchId() {
		return branch_id;
	}
	
	public String getBalance() {
		return balance;
	}
}
