package assignment2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;



public class Display3 {

	
 
	private JButton textButton, imageButton;
	private static JLabel textLabel, imageLabel;
	private JFrame displayFrame;
	private JPanel  statsFrame, buyFrame;
	

	public static String username;
	
	static JTable dataTable;
	static JScrollPane header;

	public Display3 () throws Exception {
		
		
		displayFrame = new JFrame("Display 3");
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel southPanel = new JPanel();
		header = new JScrollPane();
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		displayFrame.setLocationRelativeTo(null);
		refreshValues();
		
		//JLabel userName = new JLabel(getUserName());
		
		//mainPanel.add("North", userName);
		mainPanel.add("Center",header);
		mainPanel.add("South",southPanel);
		southPanel.add(buyShipsButton());
		southPanel.add(buyCargoButton());
		southPanel.add(buyCrusiersButton());
		southPanel.add(buyBaublesButton());
	    displayFrame.add(mainPanel);
	    displayFrame.pack();
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
	
	private static JTable dataTable( int resources , int factories , int baubles , int shipyards , int cargos , int cruisers ) {
		Vector<String> columnNames = new Vector<String>();
		 columnNames.addElement("Resources");
		 columnNames.addElement("Factories");
		 columnNames.addElement("Baubles");
		 columnNames.addElement("Shipyards");
		 columnNames.addElement("Cargo Ships");
		 columnNames.addElement("Crusier Ships");
		 
		 Vector<Vector> data = new Vector<Vector>();
		 Vector<Integer> row = new Vector<Integer>();
		 row.addElement( resources );
		 row.addElement(factories);
		 row.addElement(baubles);
		 row.addElement(shipyards);
		 row.addElement(cargos);
		 row.addElement(cruisers);
		 data.addElement( row );
		
		
		JTable dataTable = new JTable(data, columnNames);
		
		dataTable.setPreferredScrollableViewportSize(new Dimension(150, 70));
        dataTable.setFillsViewportHeight(false);
        dataTable.setVisible(true);
        
        return dataTable;
	}

	public static void refreshValues() {
		int resourceValue = -1;
		int factoriesValue = -1;
		int baublesValue = -1;
		int shipyardValue = -1;
		int cargoValue = -1;
		int crusierValue = -1;
		Statement stmt;
		try {
			stmt = SpaceController.dbConnection.createStatement();
			String getValues= new String("CALL csc371_02.getValues();"); // StoredProcedure to retrieve player Names
			ResultSet rs = stmt.executeQuery(getValues);
			while (rs.next()) {
				resourceValue = rs.getInt(1);
				factoriesValue= rs.getInt(2);
				baublesValue= rs.getInt(3);
				shipyardValue= rs.getInt(4);
				cargoValue= rs.getInt(5);
				crusierValue= rs.getInt(6);
				
			}
			dataTable = dataTable( resourceValue , factoriesValue, baublesValue, shipyardValue, cargoValue, crusierValue);
			header.setViewportView( dataTable );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	/*
	 *buy and update buttons for the split window 
	 */
	public static JButton buyShipsButton() {
		JButton buyShips = new JButton("Buy a Ship");
		
		  buyShips.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent e) { 
			  Statement stmt;
			  try {
			  stmt = SpaceController.dbConnection.createStatement();
				String buyCargo = new String("CALL csc371_02.buyCargo();"); // StoredProcedure to retrieve player Names
				stmt.execute(buyCargo);
			  refreshValues();
		  	} catch (SQLException el){
		  		el.printStackTrace();
		  	}
		  }
		  });
		 
		return buyShips;
		
	}
	
	/*
	 * 
	 */
	public static JButton  buyCargoButton() {
		JButton buyCargoShips = new JButton("Buy a Cargo Ship");
		
		
/*		  buyCargoShips.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent e) { selectedName =
		  list.getSelectedValue(); sendMessage(selectedName);
		  
		  }
		 */
		return buyCargoShips;
		
	}
	 
	/*
	 * 
	 */
	public static JButton buyCrusiersButton() {
		JButton buyCrusiers = new JButton("Buy a Cruiser Ship");
		
		
/*		  buyCrusiers.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent e) { selectedName =
		  list.getSelectedValue(); sendMessage(selectedName);
		  
		  }*/
		 
		return buyCrusiers;
	}
	
	/*
	 * 
	 */
	public static JButton buyBaublesButton() {
		JButton buyBaubles = new JButton("Buy more Baubles");
		
/*		  buyBaubles.addActionListener(new ActionListener() {
	  
		  @Override public void actionPerformed(ActionEvent e) { selectedName =
		  list.getSelectedValue(); sendMessage(selectedName);
		  
		  }
		 */
		return buyBaubles;
	}
		  
	}


