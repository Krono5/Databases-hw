import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;



public class Display3 {

	
	JButton textButton, imageButton;
	static JLabel textLabel;
	JLabel imageLabel;
	private JFrame displayFrame;
	public static String username;
	


	public Display3() throws Exception {
		
		
		displayFrame = new JFrame("Display 3");
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);
		
		displayFrame.setLayout(new BorderLayout());
		//setting Plant Name
		
		//setting userName
		displayFrame.dataTable();
	
	
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(dataTable);
        displayFrame.add(scrollPane);
        
        displayFrame.add(radioButtons);
	

		
	
		displayFrame.setVisible(true);
		
	}   
	
	public static String getUserName() {
		  try { 
		        //Statement to return names
				Statement stmt = SpaceController.dbConnection.createStatement();
				String getNames = new String("CALL csc371_02.getPlayerNames();"); // StoredProcedure to retrieve player Names
				ResultSet rs = stmt.executeQuery(getNames);
				username = rs.getString(1);
				        
              	} catch (SQLException e1) {
		        e1.printStackTrace();
		        }
		  return username;
	}
	
	public static JTable dataTable() {
		String [] columnNames = {"Resources", "Factories", "Baubles", "Shipyards", "Cargo Ships", "Crusier Ships"};
		//write out the call to get all the info to fill the JTable
		Object [][] data = { getResources(), getFactories(), getBaubles(), GetShipyards(), getCargoShips(), getCrusierShips()};
		//Creating dataTable to show off how many of what a player and planet has
		JTable dataTable = new JTable(data, columnNames);
		dataTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        dataTable.setFillsViewportHeight(true);
	}
	/*
	 * split window to house the radio buttons 
	 */
	public void selectAndBuyWindow() {
		
	}
	
	/*
	 * being able to pick which things to buy 
	 */
	public static JFrame radioButtons() {
		String shipStrng = "Ships";
		String cargoStrng = "Cargo Ships";
		String crusierStrng = "Crusier Ships";
		String baublesStrng = "Baubles";
		
		JRadioButton shipButton = new JRadioButton(shipStrng);
		shipButton.setMnemonic(KeyEvent.VK_S);
	    shipButton.setActionCommand(shipStrng);
	    shipButton.setSelected(true);
		
		JRadioButton cargoButton = new JRadioButton(cargoStrng);
		cargoButton.setMnemonic(KeyEvent.VK_C);
	    cargoButton.setActionCommand(cargoStrng);
	    cargoButton.setSelected(true);
    
		JRadioButton cruiserButton = new JRadioButton(crusierStrng);
		cruiserButton.setMnemonic(KeyEvent.VK_R);
	    cruiserButton.setActionCommand(crusierStrng);
	    cruiserButton.setSelected(true);
	    
		JRadioButton baublesButton = new JRadioButton(baublesStrng);
		baublesButton.setMnemonic(KeyEvent.VK_B);
	    baublesButton.setActionCommand(baublesStrng);
	    baublesButton.setSelected(true);
		
	    
	    
	    
	    return buttons;
		
		
	}
	
	/*
	 *buy and update buttons for the split window 
	 */
	public static JButton buyShipsButton() {
		JButton buyShips = new JButton();
		
		return buyShips;
		
	}
	
	/*
	 * 
	 */
	public static JButton  buyCargoButton() {
		JButton buyCargoShips = new JButton();
		
		
		return buyCargoShips;
		
	}
	 
	/*
	 * 
	 */
	public static JButton buyCrusiersButton() {
		JButton buyCrusiers = new JButton();
		
		return buyCrusiers;
	}
	
	/*
	 * 
	 */
	public static JButton buyBaublesButton() {
		JButton buyBaubles = new JButton();
		
		return buyBaubles;
	}

}
