package assignment2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;



public class Display3 {

	
	private static final Component radioScrollPane = null;
	private static final Component buttonScrollPane = null;
	JButton textButton, imageButton;
	static JLabel textLabel;
	JLabel imageLabel;
	private JFrame displayFrame, mainFrame;
	JSplitPane splitPane;

	public static String username;
	


	public Display3 () throws Exception {
		
		
		displayFrame = new JFrame("Display 3");
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);
		
		displayFrame.setLayout(new BorderLayout());
		mainFrame = new JFrame("Player Stats");
		mainFrame.setLayout(new BorderLayout());
		
		//setting Plant Name
		
	    mainFrame.add(dataTable(), BorderLayout.CENTER);   
	    mainFrame.add(buyShipsButton(), BorderLayout.EAST);
	    mainFrame.add(buyCargoButton(), BorderLayout.EAST);
	    mainFrame.add(buyCrusiersButton(), BorderLayout.EAST);
	    mainFrame.add(buyBaublesButton(), BorderLayout.EAST);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(dataTable());
        
        mainFrame.add(scrollPane);
        mainFrame.pack();
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, radioScrollPane, buttonScrollPane);
        Dimension minimumSize = new Dimension(100, 50);
        radioScrollPane.setMinimumSize(minimumSize);
        buttonScrollPane.setMinimumSize(minimumSize);
        
        displayFrame.add(radioButtons(), BorderLayout.SOUTH);
        
     
    
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
		//Object [][] data = { getResources(), getFactories(), getBaubles(), GetShipyards(), getCargoShips(), getCrusierShips()};
		Object [][] data = { null, null, null, null, null, null,};
		//Creating dataTable to show off how many of what a player and planet has
		JTable dataTable = new JTable(data, columnNames);
		dataTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        dataTable.setFillsViewportHeight(true);
        
        return dataTable;
	}
	/*
	 * split window to house the radio buttons 
	 */
	public void selectAndBuyWindow() {
		
	}
	
	/*
	 * being able to pick which things to buy 
	 */
	public static JPanel radioButtons() {
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
		
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(shipButton);
	    group.add(cargoButton);
	    group.add(cruiserButton);
	    group.add(baublesButton);
	 
	    //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(shipButton);
        radioPanel.add(cargoButton);
        radioPanel.add(cruiserButton);
        radioPanel.add(baublesButton);
     

       
        
	    return radioPanel;
		
		
	}
	
	/*
	 *buy and update buttons for the split window 
	 */
	public static JButton buyShipsButton() {
		JButton buyShips = new JButton("Buy a Ship");
		/*
		 * buyShips.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { selectedName =
		 * list.getSelectedValue(); sendMessage(selectedName);
		 * 
		 * }
		 */
		return buyShips;
		
	}
	
	/*
	 * 
	 */
	public static JButton  buyCargoButton() {
		JButton buyCargoShips = new JButton("Buy a Cargo Ship");
		
		/*
		 * buyCargoShips.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { selectedName =
		 * list.getSelectedValue(); sendMessage(selectedName);
		 * 
		 * }
		 */
		return buyCargoShips;
		
	}
	 
	/*
	 * 
	 */
	public static JButton buyCrusiersButton() {
		JButton buyCrusiers = new JButton("Buy a Cruiser Ship");
		
		/*
		 * buyCrusiers.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { selectedName =
		 * list.getSelectedValue(); sendMessage(selectedName);
		 * 
		 * }
		 */
		return buyCrusiers;
	}
	
	/*
	 * 
	 */
	public static JButton buyBaublesButton() {
		JButton buyBaubles = new JButton("Buy more Baubles");
		/*
		 * buyBaubles.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { selectedName =
		 * list.getSelectedValue(); sendMessage(selectedName);
		 * 
		 * }
		 */
		return buyBaubles;
	}

}
