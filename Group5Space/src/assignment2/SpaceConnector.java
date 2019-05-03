package assignment2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * author: Tyler Storr
 */
public class SpaceConnector{
	
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_02";
	public static final String LOGIN_NAME = "csc371_02";
	public static final String PASSWORD = "Password02";
	private Connection con = null;

	public SpaceConnector() {
		try {
			con = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
			con.setAutoCommit(true); // FOUND ISSUES WHEN NOT USING THIS
			System.out.println("Connected to DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		return this.con;
	}
}
