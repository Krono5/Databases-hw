import javax.swing.*;

import display1Componnent.BuildingsPanel;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author aa1184 aka Mina Kindo ;)
 *
 */

public class Display1 extends JFrame  {
  
  //buttons for buildings
  private BuildingsPanel buildings; 

  //buttons for player and actions
  private JButton playerInfoButton;
  private JButton playerMoneyButton;
  
	public Display1() {
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
		
	}
	
	
	
	public JPanel playerPanel() {
	  
	  JPanel panel = new JPanel();
	  // Create a GridLayout manager with
    // 1 rows and 2 columns to contain the buttons.
    setLayout(new GridLayout(1, 2));
    
    //create buttons for each building
    playerInfoButton = new JButton(getPlayerInfo());
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
  private String getPlayerInfo() {
    // TODO Auto-generated method stub
    return "playerMina";
  }
  
  /**
   * This method should be used to get player money from DB
   * @return
   */
  private String getPlayerMoney() {
    // TODO Auto-generated method stub
    return "12345";
  }
}
