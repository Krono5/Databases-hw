package display1Componnent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {

  public boolean activateJDBC()
  {
      try
      {
          DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      }
      catch (SQLException sqle)
      {
          sqle.printStackTrace();
      }
  
      return true;
  }

    public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_02";
    public static final String LOGIN_NAME = "csc371_02";
    public static final String PASSWORD = "Password02";
    // Make sure and use the java.sql imports.
    protected static Connection m_dbConn = null;
    
    public static void connector() throws SQLException {
  /** 
   * Creates a connection to the database that you can then send commands to.
   */
   m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
   

  /**
   * To get the meta data for the DB.
   */
   DatabaseMetaData meta = m_dbConn.getMetaData();

   }
}
