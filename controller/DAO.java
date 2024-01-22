package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Employee;

public class DAO {

	private Connection conn;

	public DAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://localhost:1433;" + "database=QLLLLL_Employee;" + "user=sa;"
					+ "password=123456;" + "encrypt=true;" + "trustServerCertificate=true;";

			conn = DriverManager.getConnection(connectionURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Lấy Danh Sách Nhân Viên từ CSDL

	public ArrayList<Employee> getListEmployee() {
		ArrayList<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM Employee";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee s = new Employee();
				s.setID(rs.getString("ID"));
				s.setName(rs.getString("name"));
				s.setDob(rs.getDate("dob"));
				s.setAddress(rs.getString("address"));
				s.setPhone(rs.getString("phone"));
				s.setEmail(rs.getString("email"));
				s.setWage(rs.getDouble("wage"));
				s.setDaysWorked(rs.getInt("daysWorked"));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Thêm Nhân Viên

	public boolean addEmployee(Employee s) {
		String sql = "INSERT INTO Employee(ID, name, dob, address, phone, email, wage, daysWorked) "
				+ "VALUES(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getID());
			ps.setString(2, s.getName());
			ps.setDate(3, new Date(s.getDob().getTime()));
			ps.setString(4, s.getAddress());
			ps.setString(5, s.getPhone());
			ps.setString(6, s.getEmail());
			ps.setDouble(7, s.getWage());
			ps.setInt(8, s.getDaysWorked());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean testConnection() {
		return conn != null;
	}

	// Cập Nhật Thông Tin Nhân Viên

	public boolean updateEmployee(Employee s) {
		String sql = "UPDATE Employee SET name=?, dob=?, address=?, phone=?, email=?, wage=?,daysWorked=? WHERE ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getName()); // lấy từ sql
			ps.setDate(2, new Date(s.getDob().getTime()));
			ps.setString(3, s.getAddress());
			ps.setString(4, s.getPhone());
			ps.setString(5, s.getEmail());
			ps.setDouble(6, s.getWage());
			ps.setInt(7, s.getDaysWorked());
			ps.setString(8, s.getID());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Xóa Nhân Viên

	public boolean deleteEmployee(String studentID) {
		String sql = "DELETE FROM Employee WHERE ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, studentID);

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// tìm kiếm employee theo id
	public Employee getEmployeeById(String EmployeeID) {
		String sql = "SELECT * FROM Employee WHERE ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, EmployeeID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee s = new Employee();
				s.setID(rs.getString("ID")); // set : cho nó 1 giá <- get : lấy
				s.setName(rs.getString("name"));
				s.setDob(rs.getDate("dob"));
				s.setAddress(rs.getString("address"));
				s.setPhone(rs.getString("phone"));
				s.setEmail(rs.getString("email"));
				s.setWage(rs.getDouble("wage"));
				s.setDaysWorked(rs.getInt("daysWorked"));
				return s;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		new DAO();

	}

}
