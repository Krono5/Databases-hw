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
  
  
  public BuildingsPanel() {
      
      // Create a GridLayout manager with
      // 2 rows and 2 columns to contain the buttons.
      setLayout(new GridLayout(2, 2));
      setBorder(BorderFactory.createLineBorder(Color.black));
      
      String label = "<html> <center> <h2> Factory<br>1000</h2></center></html>";
      //create buttons for each building
      JButton factoryButton = new JButton("<html> <center> <h2> Factory<br>1000</h2></center></html>");
      JButton mineButton = new JButton("<html> <center> <h2> Mine<br>1000</h2></center></html>");
      JButton researchButton = new JButton("<html> <center> <h2> Research Center<br>1000</h2></center></html>");
      JButton shipyardButton = new JButton("<html> <center> <h2> ShipYard<br>1000</h2></center></html>");
      
      //add buttons to the panel
      add(factoryButton);
      add(mineButton);
      add(researchButton);
      add(shipyardButton);
      setVisible(true);
    }

  private class FactoryListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String sql =
          "INSERT INTO table(column1, column2,...)\n" + 
          "VALUES (value1, value2,...)";
      Statement stmt;
      
      try {
        DBconnect.connector();
        stmt = DBconnect.m_dbConn.createStatement();
        stmt.executeUpdate(sql);
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  

}
