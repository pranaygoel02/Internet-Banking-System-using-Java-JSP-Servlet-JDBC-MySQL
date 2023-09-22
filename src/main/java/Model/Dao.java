package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Classes.Branch;
import Classes.CategoryClass;
import Connection.Conn;

public class Dao {
	
	public static ResultSet getEmployees() throws ClassNotFoundException, SQLException {		
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select * from employee");
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet getCustomers() throws ClassNotFoundException, SQLException {		
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement(" select customer.customer_id, customer_name, customer_email,count(account_no) as accounts,customer_addr,customer_phone,customer_adhaar,customer_pan,customer_dob from customer left join account on account.customer_id=customer.customer_id group by customer_id;");
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet getAccounts(String selectString) throws ClassNotFoundException, SQLException {		
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from account");
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet getBranches(String selectString) throws ClassNotFoundException, SQLException {		
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString  + " from branch");
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	
	public static boolean updateAccountStatus(String status, String accountNo) throws ClassNotFoundException, SQLException {
		PreparedStatement updateAccount = Conn.getConnectionObj().prepareStatement("update account set status=? where account_no=?");
		updateAccount.setString(1, status);
		updateAccount.setString(2, accountNo);
		int insertCount = updateAccount.executeUpdate();
        System.out.println(insertCount);
		return insertCount > 0;
	}
	
	public static String getUserName(Category category, String userId) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		switch(category) {
			case EMPLOYEE:
				rs = getEmployeeDetails(userId, "emp_name");
				break;
			case CUSTOMER:
				rs = getCustomerDetails(userId, "customer_name");
				break;
			default:
				break;
		}
		if(rs != null && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
	public static ResultSet getCustomerDetails(String customerId, String selectString) throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from customer where customer_id=?");
		ps.setString(1, customerId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet getEmployeeDetails(String empId, String selectString) throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from employee where emp_id=?");
		ps.setString(1, empId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet getAccountDetails(String accId, String selectString) throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from account where account_no=?");
		ps.setString(1, accId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static ResultSet getBranchDetails(String branchId, String selectString) throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from branch where branch_id=?");
		ps.setString(1, branchId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public static boolean updateEmployeeDetails(String name, String email, String address, Long phone, Long adhaar, String pan, String dob, String city, String branch, String role, String empId) throws SQLException, ClassNotFoundException {
		PreparedStatement addNewEmployee = Conn.getConnectionObj().prepareStatement("update employee set emp_name=?, emp_email=?, emp_addr=?, emp_phone=?, emp_adhaar=?, emp_pan=?, emp_dob=?, emp_city=?, branch_id=?, eMP_role=? where emp_id=?");
        addNewEmployee.setString(1, name);
        addNewEmployee.setString(2, email);
        addNewEmployee.setString(3,address);
        addNewEmployee.setLong(4,phone);
        addNewEmployee.setLong(5,adhaar);
        addNewEmployee.setString(6, pan);
        addNewEmployee.setString(7, dob);
        addNewEmployee.setString(8, city);
        addNewEmployee.setString(9, branch);
        addNewEmployee.setString(10, role);
        addNewEmployee.setString(11, empId);
        int insertCount = addNewEmployee.executeUpdate();
        System.out.println(insertCount);
        return insertCount > 0;
	}
	
	public static boolean updateCustomerDetails(String name, String email, String address, Long phone, Long adhaar, String pan, String dob, String empId) throws SQLException, ClassNotFoundException {
		PreparedStatement addNewCustomer = Conn.getConnectionObj().prepareStatement("update customer set customer_name=?, customer_email=?, customer_addr=?, customer_phone=?, customer_adhaar=?, customer_pan=?, customer_dob=? where customer_id=?");
		addNewCustomer.setString(1, name);
        addNewCustomer.setString(2, email);
        addNewCustomer.setString(3,address);
        addNewCustomer.setLong(4,phone);
        addNewCustomer.setLong(5,adhaar);
        addNewCustomer.setString(6, pan);
        addNewCustomer.setString(7, dob);
        addNewCustomer.setString(8, empId);
        int insertCount = addNewCustomer.executeUpdate();
        System.out.println(insertCount);
        return insertCount > 0;
	}
	
	public static boolean updateBranchDetails(String name, String email, Long phone, String city, String address, int pincode, double min_balance, String branchId) throws SQLException, ClassNotFoundException {
		PreparedStatement addNewBranch = Conn.getConnectionObj().prepareStatement("update branch set branch_name=?,branch_email=?,branch_phone=?,branch_city=?,branch_addr=?,branch_pincode=?,min_balance=? where branch_id=?");
		addNewBranch.setString(1, name);
        addNewBranch.setString(2, email);
        addNewBranch.setLong(3,phone);
        addNewBranch.setString(4,city);
        addNewBranch.setString(5,address);
        addNewBranch.setInt(6,pincode);
        addNewBranch.setDouble(7,min_balance);
        addNewBranch.setString(8,branchId);
        int insertCount = addNewBranch.executeUpdate();
        return insertCount > 0;
	}
	
	public static List<CategoryClass> list() throws SQLException, ClassNotFoundException {
        List<CategoryClass> listCategory = new ArrayList<>();
         
        try {
            String sql = "SELECT * FROM category ORDER BY name";
            Statement statement = Conn.getConnectionObj().createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                int id = result.getInt("category_id");
                String name = result.getString("name");
                CategoryClass category = new CategoryClass(id, name);
                     
                listCategory.add(category);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return listCategory;
    }
	
	public static List<Branch> getBranchList(String selectString) throws ClassNotFoundException, SQLException {		
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString  + " from branch");
		ResultSet rs = ps.executeQuery();
		List<Branch> branchList = new ArrayList<>();
		while(rs.next()) {
			String id = rs.getString("branch_id");
			String name = rs.getString("branch_name");
			System.out.println(id + " " + name);
			branchList.add(new Branch(id, name));
		}
		return branchList;
	}
	
	public static List<String> getRoles() {
		List<String> roles = new ArrayList<String>();
		roles.add("BRANCH MANAGER");
		roles.add("WORKER");
		return roles;
	}
	
}
