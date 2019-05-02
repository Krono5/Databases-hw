package assignment2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;



public class Display3 {



	public static String username;
	
	static JTable dataTable;
	static JScrollPane header;	
	private ArrayList<String> players;
	private DefaultListModel<String> model;
	private JList<String> list;
	private static JFrame displayFrame;

	JFrame showData;

	private static JPanel southPanel;
	private String selectedName;

	public Display3 () throws Exception {
		
		
		displayFrame = new JFrame("Display 3");
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		displayFrame.setLocationRelativeTo(null);
		
		
		
		
		mainPanel.add(initPopPanel());

	    displayFrame.add(mainPanel);
	    displayFrame.pack();
		displayFrame.setVisible(true);
	
		
	}   
	private JPanel initPopPanel() throws Exception {
		
		JPanel tempPanel = new JPanel();
		players = new ArrayList<>();
		model = new DefaultListModel<>();

		Statement stmt = SpaceController.dbConnection.createStatement();
		String getNames = new String("CALL csc371_02.getPlayerNames();"); // StoredProcedure to retrieve player Names
		ResultSet rs = stmt.executeQuery(getNames);

		while (rs.next()) {
			addPlayer(rs.getString(1));
		}
		
		
		for (int i = 0; i < players.size(); i++) {
			model.addElement(players.get(i));
		}

		list = new JList<String>(model);

		JScrollPane listScroller = new JScrollPane(list);

		JButton select = new JButton("Select");
			select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedName = list.getSelectedValue();
				showData(selectedName);
			

			}
		});

		tempPanel.add(listScroller);
		tempPanel.add(select);
		
		return tempPanel;

	}
	
	public ArrayList<String> getPlayers() {
		return players;
	}

	public void addPlayer(String name) {
		this.players.add(name);
	}

	
	public static JFrame showData(String selectedName) {
		
		JFrame showData = new JFrame("Player Data");
		JPanel dataPanel = new JPanel(new BorderLayout());
		JLabel playerName = new JLabel(selectedName);
		JPanel southPanel = new JPanel();
		header = new JScrollPane();
		refreshValues(selectedName);
		dataPanel.add("North", playerName);
		dataPanel.add("Center",header);
		dataPanel.add("South",southPanel);

		southPanel.add(buyCargoButton(selectedName));
		southPanel.add(buyCrusiersButton(selectedName));
		southPanel.add(buyBaublesButton(selectedName));
		showData.add(dataPanel);
		showData.pack();
		showData.setVisible(true);
		return showData;
	}
	
	
	public static String getUserName() {
		  try { 
		        //Statement to return names
				Statement stmt = SpaceController.dbConnection.createStatement();
				String getNames = new String("CALL csc371_02.getPlayerNames();"); // StoredProcedure to retrieve player Names
				ResultSet rs = stmt.executeQuery(getNames);
				
				while(rs.next()) {
				username = rs.getString(1);
				break;
				        
              	}
		  }catch (SQLException e1) {
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

	public static void refreshValues(String selectedName) {
		int resourceValue = -1;
		int factoriesValue = -1;
		int baublesValue = -1;
		int shipyardValue = -1;
		int cargoValue = -1;
		int crusierValue = -1;
		Statement stmt;
		try {
			stmt = SpaceController.dbConnection.createStatement();
			String getValues= new String("CALL csc371_02.getValues('" + selectedName +"');"); // StoredProcedure to retrieve player Names
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
	 * 
	 */
	public static JButton  buyCargoButton(String selectedName) {
		JButton buyCargoShips = new JButton("Buy a Cargo Ship");
		
		  buyCargoShips.addActionListener(new ActionListener() {
			  
			  @Override public void actionPerformed(ActionEvent e) { 
				  Statement stmt;
				  try {
				  stmt = SpaceController.dbConnection.createStatement();
					String buyCargo = new String("CALL csc371_02.buyCargo('" + selectedName + "');"); // StoredProcedure to retrieve player Names
					stmt.execute(buyCargo);
				  refreshValues(selectedName);
			  	} catch (SQLException el){
			  		el.printStackTrace();
			  	}
			  };
		  });  
			 
		return buyCargoShips;
		
	}
	 
	/*
	 * 
	 */
	public static JButton buyCrusiersButton(String selectedName) {
		JButton buyCrusiers = new JButton("Buy a Cruiser Ship");
		
		
	  buyCrusiers.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent e) { 
			  Statement stmt;
			  try {
				  stmt = SpaceController.dbConnection.createStatement();
					String buyCrusiers = new String("CALL csc371_02.buyCrusiers('" + selectedName + "');"); // StoredProcedure to retrieve player Names
					stmt.execute(buyCrusiers);
				  refreshValues(selectedName);				  
			  }catch (SQLException el){
				  el.printStackTrace();
				  }
		  }
	  });
		 
		return buyCrusiers;
	}
	
	/*
	 * 
	 */
	public static JButton buyBaublesButton(String selectedName) {
		JButton buyBaubles = new JButton("Buy Baubles");
		
		  buyBaubles.addActionListener(new ActionListener() {
			  
			  @Override public void actionPerformed(ActionEvent e) { 
				  Statement stmt;
				  try {
					  stmt = SpaceController.dbConnection.createStatement();
						String buyBaubles = new String("CALL csc371_02.buyBaubles('" + selectedName +"');"); // StoredProcedure to retrieve player Names
						stmt.execute(buyBaubles);
					  refreshValues(selectedName);				  
				  }catch (SQLException el){
					  el.printStackTrace();
					  }
			  }
		  });
		 
		return buyBaubles;
	}
		  
	}


