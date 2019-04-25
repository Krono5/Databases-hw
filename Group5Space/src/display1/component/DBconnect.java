package display1.component;

import java.sql.Connection;
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
    protected static Connection dbConn = null;
    
    public static void connector() throws SQLException {
  /** 
   * Creates a connection to the database that you can then send commands to.
   */
   dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
   

  /**
   * To get the meta data for the DB.
   */
     dbConn.getMetaData();

   }
}
