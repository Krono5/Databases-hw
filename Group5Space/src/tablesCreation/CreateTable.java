package tablesCreation;

import java.sql.*;
/**
 * 
 * @author aa1184 aka MinaKindo
 *
 */
public class CreateTable {
  
//For a more in-depth tutorial see: https://www.tutorialspoint.com/jdbc/index.htm

  // IMPORTANT: Make sure all imports for the SQL stuff use java.sql !!!

  /**
   * This is the recommended way to activate the JDBC drivers, but is
   * only setup to work with one specific driver.  Setup to work with
   * a MySQL JDBC driver.
   *
   * If the JDBC Jar file is not in your build path this will not work.
   * I have the Jar file posted in D2L.
   * 
   * @return Returns true if it successfully sets up the driver.
   */
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
  
 /**
  * You MUST change these values based on the DB you are assigned to work with.
  */
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
  
 
  public static void main(String[] args) throws Exception {
    
    
    String sqlStmt[] = { "INSERT INTO PLANET(P_Username, Planet_ID, Raw_resources, Base_Max_Resources, " + 
                                            "Total_Max_Resources, Bauble, Factory_Technology, Hull_Technology, "
                                            + "Mining_Technology, Engine_Technology, Weapon_Technology, "
                                            + "Num_Factories, Factory_init_cost, Factory_maint_cost, Num_Mines, "
                                            + "Mine_init_cost, Mine_maint_cost, Num_Shipyards, Shipyard_init_cost, "
                                            + "Shipyard_maint_cost, Num_Research, Research_init_cost, Research_maint_cost)" + 
                          "VALUES ('MinaKindo', 'MINA0001', 200000, 200000, "
                                  + "500000, 10000, 1, 1, 1, 1, 1, 0, 1000, 250, 0,"
                                  + " 3000, 750, 0, 2500, 625, 0, 5000, 1250)"};         
                        
    for(int i=0; i < sqlStmt.length; i++) {   
      createTable(sqlStmt[i]);
      System.out.println("Success " + (i+1) );
    }
  }
 /**
  * To execute an SQL statement that is not a SELECT statement.
  */
  public static void createTable(String sql) throws Exception
  {
      Statement stmt;
      connector();
      stmt = m_dbConn.createStatement();
      stmt.executeUpdate(sql);
  }

 /**
  * To execute an SQL statement that is a SELECT statement.
 * @throws SQLException 
  */
  public void testSelectStatements() throws SQLException
  {
       String selectData = new String("SELECT * FROM JOBS");
       PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
       ResultSet rs = stmt.executeQuery(selectData);
       while (rs.next())
       {
       // You can access values from a ResultSet either by column number - not advised:
       String data = rs.getString(1);
       System.out.print(data+" : ");
       // Or by column name - advised:
       data = rs.getString("Fname");
       System.out.println(data);
       }
   }

}
