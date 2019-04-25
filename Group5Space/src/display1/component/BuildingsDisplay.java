package display1.component;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author aa1184 aka Mina Kindo ;)
 *
 */

public class BuildingsDisplay extends JFrame  {
  
  //buttons for buildings
  private static String  username;
  private BuildingsPanel buildings; 
  private static JButton playerMoneyButton;

  
  
  public BuildingsDisplay(String username) {
    
    BuildingsDisplay.username = username;
    JFrame displayFrame = new JFrame("Display 1");
    
    displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    displayFrame.setSize(800, 800);
    displayFrame.setLocationRelativeTo(null);
    
    // Create a BorderLayout manager.
    displayFrame.setLayout(new BorderLayout());
    buildings = new BuildingsPanel();
    displayFrame.add(buildings, BorderLayout.CENTER);
    displayFrame.add(playerPanel(), BorderLayout.PAGE_START);
  
    displayFrame.setVisible(true);
    
    
    displayFrame.setVisible(true);
  }
  
  
  
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
  static String getPlayerName() {
    return username;
  }
  
  /**
   * This method should be used to get player money from DB
   * @return
   */
  public static String getPlayerMoney() {
    String money = null;
    try {
      String sql = "SELECT Money FROM PLAYER WHERE Username = ?";
      String username = getPlayerName();
      PreparedStatement stmt;
      DBconnect.connector();
      stmt = DBconnect.dbConn.prepareStatement(sql);
      
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next())
      {
      // You can access values from a ResultSet either by column number - not advised:
      money = rs.getString("Money");
      }
      
      
      System.out.println(money);
      
    } catch (SQLException e1) {
      e1.printStackTrace();
      
    }
    return money;
  }
  
  public static void updateButton() {
    playerMoneyButton.setText(getPlayerMoney());
    
  }
  
  public JPanel planetDropDown() {
    JPanel panel = new JPanel();
    
    return panel; 
  }
}
