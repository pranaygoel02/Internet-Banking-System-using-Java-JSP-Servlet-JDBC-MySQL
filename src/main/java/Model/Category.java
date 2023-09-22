package Model;

public enum Category {
	ADMIN("admin",
            "admin_id",
            "admin_pin",
            "Choose any of the following operation:\n1 to see all admins\n2 to add a new employee\n3 to see all employees\n4 to see all customers\n5 to add new admin\n6 to logout",
            1
    ),
    CUSTOMER("customer",
            "customer_id",
            "customer_pin",
            "Choose any of the following operation:\n1 to see all admins\n2 to add a new employee\n3 to see all employees\n4 to see all customers\n5 to logout",
            3
    ),
    EMPLOYEE("employee",
            "emp_id",
            "emp_pin",
            "Choose any of the following operation:\n1 to see all admins\n2 to add a new employee\n3 to see all employees\n4 to see all customers\n5 to logout",
            2
    );
	
//	Link HomeLink = new Link("Home", "home", 3);		
//	Link SeeBranches = new Link("See Branches", "branches", new int[] {1,2,3});
//	Link SeeEmployees = new Link("See Employees", "all-employees");
//	Link SeeCustomers = new Link()
	

    private final String table_name;
    private final String primaryKey;
    private final String pin;
    private final String select_prompt;
    private final int level;
    
    String getTableName() {
        return  this.table_name;
    }
    String getPrimaryKey() {
        return  this.primaryKey;
    }
    String getPin() { return this.pin; }
    String getSelect_prompt() { return this.select_prompt; }
    public int getLevel() { return this.level; } 
    Category(String table_name, String primaryKey, String pin, String select_prompt, int level) {
        this.table_name = table_name;
        this.primaryKey = primaryKey;
        this.pin = pin;
        this.select_prompt = select_prompt;
        this.level = level;
    }
}
