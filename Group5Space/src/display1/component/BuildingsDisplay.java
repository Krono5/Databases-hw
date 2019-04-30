package display1.component;

import javax.swing.*;

import assignment2.SpaceController;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author aa1184
 * Display of buildings mine, factory, research center, shipyard,
 * player username and money
 *
 */

public class BuildingsDisplay extends JFrame  {
  
  //buttons for buildings
  private String username;
  private JButton playerMoneyButton;

  public final String mineColumn = "Num_Mines";
  public final String factoryColumn = "Num_Factories";
  public final String researchColumn = "Num_Research";
  public final String shipyardColumn = "Num_Shipyards";
  
  public final int minePrice = 3000;
  public final int factoryPrice = 1000;
  public final int researchPrice = 5000;
  public final int shipyardPrice = 2500;
  
  /**
   * 
   * @param username
   */
  public BuildingsDisplay(String username) {
    
    this.username = username;
    JFrame displayFrame = new JFrame("Display 1");
    
    displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    displayFrame.setSize(800, 800);
    displayFrame.setLocationRelativeTo(null);
    
    // Create a BorderLayout manager.
    displayFrame.setLayout(new BorderLayout());
    displayFrame.add(buildingsPanel(), BorderLayout.CENTER);
    displayFrame.add(playerPanel(), BorderLayout.PAGE_START);
  
    displayFrame.setVisible(true);
  }
  
  
  public JPanel buildingsPanel() {
    JPanel panel = new JPanel();
    // Create a GridLayout manager with
    // 2 rows and 2 columns to contain the buttons.
    panel.setLayout(new GridLayout(2, 2));
    
    //create buttons for each building
    JButton factoryButton = new JButton("<html> <center> <h2> Factory<br>1000</h2></center></html>");
    JButton mineButton = new JButton("<html> <center> <h2> Mine<br>3000</h2></center></html>");
    JButton researchButton = new JButton("<html> <center> <h2> Research Center<br>5000</h2></center></html>");
    JButton shipyardButton = new JButton("<html> <center> <h2> ShipYard<br>2500</h2></center></html>");
    
    factoryButton.addActionListener(e -> buttonClick(factoryColumn, factoryPrice));
    mineButton.addActionListener(e -> buttonClick(mineColumn, minePrice));
    researchButton.addActionListener(e -> buttonClick(researchColumn, researchPrice));
    shipyardButton.addActionListener(e -> buttonClick(shipyardColumn, shipyardPrice));
    
    //add buttons to the panel
    panel.add(factoryButton);
    panel.add(mineButton);
    panel.add(researchButton);
    panel.add(shipyardButton);
    
    return panel;
  }
  
  /**
   * Button click listener
   * @param column
   * @param price
   */
  public void buttonClick(String column, int price) {
    int moneyAvailable =  Integer.parseInt(getPlayerMoney());
    try {
      if (moneyAvailable >= price) {
        String updateFactory = "UPDATE PLANET SET " + column + " = " + column + " + 1 WHERE P_Username = ? AND PLANET_ID = ?";
        String updateMoney = "UPDATE PLAYER SET Money = Money - ? WHERE Username = ?";
       
        PreparedStatement stmt1, stmt2;
        stmt1 = SpaceController.dbConnection.prepareStatement(updateFactory);
        stmt2 = SpaceController.dbConnection.prepareStatement(updateMoney);
        
        stmt1.setString(1, username);
        stmt1.setString(2, getPlayerPlanet());
        stmt1.executeUpdate();
        
        //second sql statement
        stmt2.setInt(1, price);
        stmt2.setString(2, username);
        stmt2.executeUpdate();
        
        updateButton();
        JOptionPane.showMessageDialog(null, "You now have " + column + " = " + getNumBuilding(column));
      } else {
        JOptionPane.showMessageDialog(null, "You do not have enough money");
      }
             
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }
  
  /**
   * 
   * @return a panel containing the username and the 
   * remaining money
   */
  public JPanel playerPanel() {
    
  //buttons for player and actions
    JButton playerInfoButton;
    
    JPanel panel = new JPanel();
    // Create a GridLayout manager with
    // 1 rows and 2 columns to contain the buttons.
    setLayout(new GridLayout(1, 2));
    
    //create buttons for each building
    playerInfoButton = new JButton(getPlayerName());
    playerMoneyButton = new JButton(getPlayerMoney());
    
    panel.add(playerInfoButton);
    panel.add(playerMoneyButton);
   
    setPreferredSize( new Dimension( 640, 480 ) );
    setResizable( false );
    
    return panel;
  }
  

  /* 
   * This method should be used to get player info from DB
   */
  public String getPlayerName() {
    return username;
  }
  
  /**
   * This method should be used to get player money from DB
   * @return
   */
  public String getPlayerMoney() {
    String money = null;
    try {
      String sql = "SELECT Money FROM PLAYER WHERE Username = ?";
      String username = getPlayerName();
      PreparedStatement stmt;
      stmt = SpaceController.dbConnection.prepareStatement(sql);
      
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()){
        money = rs.getString("Money");
      }
      
    } catch (SQLException e1) {
      e1.printStackTrace();
      
    }
    return money;
  }
  
  /**
   * This method should be used to get player planet
   * @return
   */
  public String getPlayerPlanet() {
    String planet = null;
    try {
      String sql = "SELECT Planet_ID FROM PLANET WHERE P_Username = ?";
      String username = getPlayerName();
      PreparedStatement stmt;
      stmt = SpaceController.dbConnection.prepareStatement(sql);
      
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()){
        planet = rs.getString("Planet_ID");
      }
      
    } catch (SQLException e1) {
      e1.printStackTrace();
      
    }
    return planet;
  }
  
  /*
   * to get the number of buildings
   */
  public String getNumBuilding(String column) {
    String num = null;
    try {
      String sql = "SELECT " + column + " FROM PLANET WHERE P_Username = ?";
      String username = getPlayerName();
      PreparedStatement stmt;
      stmt = SpaceController.dbConnection.prepareStatement(sql);
      
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()){
        num = rs.getString(column);
      }
      
    } catch (SQLException e1) {
      e1.printStackTrace();
      
    }
    return num;
  }
  
  /*
   * this method will update money displayed when a building is added
   */
  public void updateButton() {
    playerMoneyButton.setText(getPlayerMoney());
  }
}
