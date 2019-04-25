package display1.component;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author aa1184
 *
 */
public class BuildingsPanel extends JPanel {
  
  
  public BuildingsPanel() {
      
      // Create a GridLayout manager with
      // 2 rows and 2 columns to contain the buttons.
      setLayout(new GridLayout(2, 2));
      setBorder(BorderFactory.createLineBorder(Color.black));
      
      //create buttons for each building
      JButton factoryButton = new JButton("<html> <center> <h2> Factory<br>1000</h2></center></html>");
      JButton mineButton = new JButton("<html> <center> <h2> Mine<br>3000</h2></center></html>");
      JButton researchButton = new JButton("<html> <center> <h2> Research Center<br>5000</h2></center></html>");
      JButton shipyardButton = new JButton("<html> <center> <h2> ShipYard<br>2500</h2></center></html>");
      
      factoryButton.addActionListener(new FactoryListener());
      mineButton.addActionListener(new MineListener());
      researchButton.addActionListener(new ResearchListener());
      shipyardButton.addActionListener(new ShipyardListener());
      
      //add buttons to the panel
      add(factoryButton);
      add(mineButton);
      add(researchButton);
      add(shipyardButton);
      setVisible(true);
    }

  private class FactoryListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String updateFactory = "UPDATE PLANET SET Num_Factories = Num_Factories + 1 WHERE P_Username = ? AND PLANET_ID = 'MINA0001'";
        String updateMoney = "UPDATE PLAYER SET Money = Money - 1000 WHERE Username = ?";
        String username = BuildingsDisplay.getPlayerName();
        PreparedStatement stmt1, stmt2;
        DBconnect.connector();
        stmt1 = DBconnect.dbConn.prepareStatement(updateFactory);
        stmt2 = DBconnect.dbConn.prepareStatement(updateMoney);
        
        stmt1.setString(1, username);
        stmt1.executeUpdate();
        
        stmt2.setString(1, username);
        stmt2.executeUpdate();
        
        BuildingsDisplay.updateButton();
        
        
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }
  
  private class MineListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String updateFactory = "UPDATE PLANET SET Num_Mines = Num_Mines + 1 WHERE P_Username = ? AND PLANET_ID = 'MINA0001'";
        String updateMoney = "UPDATE PLAYER SET Money = Money - 3000 WHERE Username = ?";
        String username = BuildingsDisplay.getPlayerName();
        PreparedStatement stmt1, stmt2;
        DBconnect.connector();
        stmt1 = DBconnect.dbConn.prepareStatement(updateFactory);
        stmt2 = DBconnect.dbConn.prepareStatement(updateMoney);
        
        stmt1.setString(1, username);
        stmt1.executeUpdate();
        
        stmt2.setString(1, username);
        stmt2.executeUpdate();
        
        BuildingsDisplay.updateButton();
               
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  private class ResearchListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String updateFactory = "UPDATE PLANET SET Num_Research = Num_Research + 1 WHERE P_Username = ? AND PLANET_ID = 'MINA0001'";
        String updateMoney = "UPDATE PLAYER SET Money = Money - 5000 WHERE Username = ?";
        String username = BuildingsDisplay.getPlayerName();
        PreparedStatement stmt1, stmt2;
        DBconnect.connector();
        stmt1 = DBconnect.dbConn.prepareStatement(updateFactory);
        stmt2 = DBconnect.dbConn.prepareStatement(updateMoney);
        
        stmt1.setString(1, username);
        stmt1.executeUpdate();
        
        stmt2.setString(1, username);
        stmt2.executeUpdate();
        
        BuildingsDisplay.updateButton();
               
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }
  
  private class ShipyardListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String updateFactory = "UPDATE PLANET SET Num_Shipyards = Num_Shipyards + 1 WHERE P_Username = ? AND PLANET_ID = 'MINA0001'";
        String updateMoney = "UPDATE PLAYER SET Money = Money - 2500 WHERE Username = ?";
        String username = BuildingsDisplay.getPlayerName();
        PreparedStatement stmt1, stmt2;
        DBconnect.connector();
        stmt1 = DBconnect.dbConn.prepareStatement(updateFactory);
        stmt2 = DBconnect.dbConn.prepareStatement(updateMoney);
        
        stmt1.setString(1, username);
        stmt1.executeUpdate();
        
        stmt2.setString(1, username);
        stmt2.executeUpdate();
        
        BuildingsDisplay.updateButton();
               
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }
}
