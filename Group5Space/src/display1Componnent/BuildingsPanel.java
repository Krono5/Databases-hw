package display1Componnent;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BuildingsPanel extends JPanel {
  
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
  public BuildingsPanel() {
      
      // Create a GridLayout manager with
      // 2 rows and 2 columns to contain the buttons.
      setLayout(new GridLayout(2, 2));
      setBorder(BorderFactory.createLineBorder(Color.black));
      
      String label = "<html> <center> <h2> Factory<br>1000</h2></center></html>";
      //create buttons for each building
      JButton factoryButton = new JButton(label);
      JButton mineButton = new JButton("Mine\n1000");
      JButton researchButton = new JButton("Research Center\n1000");
      JButton shipyardButton = new JButton("Shipyard\n1000");
      
      //add buttons to the panel
      add(factoryButton);
      add(mineButton);
      add(researchButton);
      add(shipyardButton);
      setVisible(true);
    }

  private class FactoryListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String addFactory =
          "INSERT INTO table(column1, column2,...)\n" + 
          "VALUES (value1, value2,...)";
      Statement stmt;
      connector();
      stmt = m_dbConn.createStatement();
      stmt.executeUpdate(sql);
    }
  }

  

}
