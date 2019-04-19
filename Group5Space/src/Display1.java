import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author aa1184 aka Mina Kindo ;)
 *
 */

public class Display1 extends JPanel {
  
  //buttons for buildings
  private JButton factoryButton; 
  private JButton mineButton; 
  private JButton researchButton; 
  private JButton shipyardButton; 
	
  //buttons for player and actions
  private JButton playerInfoButton;
  private JButton playerMoneyButton;
  
	public Display1() {
		JFrame displayFrame = new JFrame("Display 1");
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);
		// Create a BorderLayout manager.
    setLayout(new BorderLayout());
		add(buildingsPanels(), BorderLayout.SOUTH);
		add(playerPanel(), BorderLayout.NORTH);
		
    setVisible(true);
		
	}
	
	public JPanel buildingsPanels() {
	  
	  JPanel panel = new JPanel();
	  // Create a GridLayout manager with
    // 2 rows and 2 columns to contain the buttons.
    setLayout(new GridLayout(2, 2));
    
    //create buttons for each building
	  factoryButton = new JButton("Factory\n1000");
	  mineButton = new JButton("Mine\n1000");
	  researchButton = new JButton("Research Center\n1000");
	  shipyardButton = new JButton("Shipyard\n1000");
	  
	  //add buttons to the panel
	  panel.add(factoryButton);
	  panel.add(mineButton);
	  panel.add(researchButton);
	  panel.add(shipyardButton);
    return panel;
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
