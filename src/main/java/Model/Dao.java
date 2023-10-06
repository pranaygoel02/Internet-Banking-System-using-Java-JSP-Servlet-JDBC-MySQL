package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Classes.Account;
import Classes.Branch;
import Classes.CategoryClass;
import Classes.Transaction;
import Connection.Conn;
import Util.FormatCurrency;

public class Dao {

	public static ResultSet getEmployees() throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select * from employee");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static ResultSet getCustomers() throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement(
				" select customer.customer_id, customer_name, customer_email,count(account_no) as accounts,customer_addr,customer_phone,customer_adhaar,customer_pan,customer_dob from customer left join account on account.customer_id=customer.customer_id group by customer_id;");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static ResultSet getAccounts(String selectString) throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from account");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	private static List<Account> getUserAccountsHandler(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.executeQuery();
		List<Account> accountList = new ArrayList<>();
		while (rs.next()) {
			String ac_no = rs.getString("account_no");
			String ac_type = rs.getString("account_type");
			String ac_status = rs.getString("status");
			String balance = FormatCurrency.getFormatted(rs.getDouble("balance"));
			String branch_name = "", branch_id = "", doc = "";
			try {
				branch_name = rs.getString("branch_name");
				branch_id = rs.getString("branch_id");
				doc = rs.getString("doc");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			accountList.add(new Account(ac_no, ac_type, ac_status, branch_id, branch_name, balance, doc));
		}
		return accountList;
	}

	public static List<Account> getUserAccounts(String user_id, String selectString)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString
				+ " from account join branch on branch.branch_id=account.branch_id where customer_id=?");
		ps.setString(1, user_id);
		return getUserAccountsHandler(ps);
	}

	public static List<Account> getUserAccounts(String user_id, String selectString, String acc_type, String status)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString
				+ " from account join branch on branch.branch_id=account.branch_id where customer_id=? and account_type=? and status=?");
		ps.setString(1, user_id);
		ps.setString(2, acc_type);
		ps.setString(3, status);
		return getUserAccountsHandler(ps);
	}

	public static ResultSet getBranches(String selectString) throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from branch");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static boolean updateAccountStatus(String status, String accountNo)
			throws ClassNotFoundException, SQLException {
		PreparedStatement updateAccount = Conn.getConnectionObj()
				.prepareStatement("update account set status=? where account_no=?");
		updateAccount.setString(1, status);
		updateAccount.setString(2, accountNo);
		int insertCount = updateAccount.executeUpdate();
		System.out.println(insertCount);
		return insertCount > 0;
	}

	public static String getUserName(Category category, String userId) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		switch (category) {
		case EMPLOYEE:
			rs = getEmployeeDetails(userId, "emp_name");
			break;
		case CUSTOMER:
			rs = getCustomerDetails(userId, "customer_name");
			break;
		default:
			break;
		}
		if (rs != null && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public static ResultSet getCustomerDetails(String customerId, String selectString)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from customer where customer_id=?");
		ps.setString(1, customerId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static ResultSet getEmployeeDetails(String empId, String selectString)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from employee where emp_id=?");
		ps.setString(1, empId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static ResultSet getAccountDetails(String accId, String selectString)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from account where account_no=?");
		ps.setString(1, accId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static Account getAccountDetails(String accId, String customer_id, String selectString)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString
				+ " from account join branch on branch.branch_id=account.branch_id where customer_id=? and account_no=?");
		ps.setString(1, customer_id);
		ps.setString(2, accId);
		ResultSet rs = ps.executeQuery();
		Account ac = null;
		while (rs.next()) {
			String ac_no = rs.getString("account_no");
			String ac_type = rs.getString("account_type");
			String ac_status = rs.getString("status");
			String branch_name = rs.getString("branch_name");
			String branch_id = rs.getString("branch_id");
			String balance = FormatCurrency.getFormatted(rs.getDouble("balance"));
			Date doc = rs.getDate("doc");
			ac = new Account(ac_no, ac_type, ac_status, branch_id, branch_name, balance, doc.toString());
		}
		return ac;
	}

	public static int getTotalTransactionCount(String acc_no) throws ClassNotFoundException {
		int cnt = 0;
		try {
			String sql = "SELECT count(t.transaction_id) FROM transaction t WHERE t.account_no_from = ? OR t.account_no_to = ?";
			PreparedStatement statement = Conn.getConnectionObj().prepareStatement(sql);
			statement.setString(1, acc_no);
			statement.setString(2, acc_no);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cnt = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public static List<Transaction> getTransactions(String acc_no, int offset, int pageSize)
			throws ClassNotFoundException {
		List<Transaction> listTransaction = new ArrayList<>();

		try {
			String sql = "SELECT\r\n" + "  t.transaction_id,\r\n" + "  CASE\r\n"
					+ "    WHEN t.account_no_from = ? THEN t.account_no_to\r\n" + "    ELSE t.account_no_from\r\n"
					+ "  END AS account,\r\n" + "  (\r\n" + "    SELECT c.customer_name\r\n" + "    FROM customer c\r\n"
					+ "    WHERE c.customer_id = (\r\n" + "      SELECT a.customer_id\r\n" + "      FROM account a\r\n"
					+ "      WHERE a.account_no = (\r\n" + "        CASE\r\n"
					+ "          WHEN t.account_no_from = ? THEN t.account_no_to\r\n"
					+ "          ELSE t.account_no_from\r\n" + "        END\r\n" + "      )\r\n" + "    )\r\n"
					+ "  ) AS name,\r\n" + "  amount,\r\n" + "  t.doc,\r\n" + "  CASE\r\n"
					+ "    WHEN t.account_no_from = ? THEN 'Send'\r\n" + "    ELSE 'Received'\r\n" + "  END AS type\r\n"
					+ "FROM transaction t\r\n" + "WHERE t.account_no_from = ? OR t.account_no_to = ? \r\n"
					+ "ORDER BY t.doc DESC\r\n" + "LIMIT ? OFFSET ?";
			PreparedStatement statement = Conn.getConnectionObj().prepareStatement(sql);
			statement.setString(1, acc_no);
			statement.setString(2, acc_no);
			statement.setString(3, acc_no);
			statement.setString(4, acc_no);
			statement.setString(5, acc_no);
			statement.setInt(6, pageSize);
			statement.setInt(7, offset);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				String id = result.getString("transaction_id");
				String acc = result.getString("account");
				String name = result.getString("name");
				String amount = FormatCurrency.getFormatted(result.getDouble("amount"));
				String doc = result.getString("doc");
				String type = result.getString("type");
				Transaction t = new Transaction(id, acc, amount, doc, type, name);
				listTransaction.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTransaction;
	}

	public static ResultSet getBranchDetails(String branchId, String selectString)
			throws ClassNotFoundException, SQLException {
		Connection conn = Conn.getConnectionObj();
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from branch where branch_id=?");
		ps.setString(1, branchId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static boolean updateEmployeeDetails(String name, String email, String address, Long phone, Long adhaar,
			String pan, String dob, String city, String branch, String role, String empId)
			throws SQLException, ClassNotFoundException {
		PreparedStatement addNewEmployee = Conn.getConnectionObj().prepareStatement(
				"update employee set emp_name=?, emp_email=?, emp_addr=?, emp_phone=?, emp_adhaar=?, emp_pan=?, emp_dob=?, emp_city=?, branch_id=?, eMP_role=? where emp_id=?");
		addNewEmployee.setString(1, name);
		addNewEmployee.setString(2, email);
		addNewEmployee.setString(3, address);
		addNewEmployee.setLong(4, phone);
		addNewEmployee.setLong(5, adhaar);
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

	public static boolean updateCustomerDetails(String name, String email, String address, Long phone, Long adhaar,
			String pan, String dob, String empId) throws SQLException, ClassNotFoundException {
		PreparedStatement addNewCustomer = Conn.getConnectionObj().prepareStatement(
				"update customer set customer_name=?, customer_email=?, customer_addr=?, customer_phone=?, customer_adhaar=?, customer_pan=?, customer_dob=? where customer_id=?");
		addNewCustomer.setString(1, name);
		addNewCustomer.setString(2, email);
		addNewCustomer.setString(3, address);
		addNewCustomer.setLong(4, phone);
		addNewCustomer.setLong(5, adhaar);
		addNewCustomer.setString(6, pan);
		addNewCustomer.setString(7, dob);
		addNewCustomer.setString(8, empId);
		int insertCount = addNewCustomer.executeUpdate();
		System.out.println(insertCount);
		return insertCount > 0;
	}

	public static boolean updateBranchDetails(String name, String email, Long phone, String city, String address,
			int pincode, double min_balance, String branchId) throws SQLException, ClassNotFoundException {
		PreparedStatement addNewBranch = Conn.getConnectionObj().prepareStatement(
				"update branch set branch_name=?,branch_email=?,branch_phone=?,branch_city=?,branch_addr=?,branch_pincode=?,min_balance=? where branch_id=?");
		addNewBranch.setString(1, name);
		addNewBranch.setString(2, email);
		addNewBranch.setLong(3, phone);
		addNewBranch.setString(4, city);
		addNewBranch.setString(5, address);
		addNewBranch.setInt(6, pincode);
		addNewBranch.setDouble(7, min_balance);
		addNewBranch.setString(8, branchId);
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
		PreparedStatement ps = conn.prepareStatement("select " + selectString + " from branch");
		ResultSet rs = ps.executeQuery();
		List<Branch> branchList = new ArrayList<>();
		while (rs.next()) {
			String id = rs.getString("branch_id");
			String name = rs.getString("branch_name");
			String min_bal = FormatCurrency.getFormatted(rs.getDouble("min_balance"));
			branchList.add(new Branch(id, name, min_bal));
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
