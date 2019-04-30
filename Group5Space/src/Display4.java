/* Imports */
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/* 
 * Display 4
 * Kevin Roberts
 * GUI that shows the lists of tables in the database and gives a user the ability to
 * either insert a row, update a row, or delete a row in any table. 
 */

public class Display4 {
	
	/* Instance variable declarations */
	JFrame displayFrame;
	private ArrayList<String> tables;
	private JList<String> tableList;
	private DefaultListModel<String> model;
	private String[][] rows;
	private JTable rowTable;
	private DefaultTableModel rowModel;
	private String selectedTable;
	private String selectedRowValue;
	private String[] colNames;
	private Statement stmt = SpaceController.dbConnection.createStatement();

	/* Main display */
	public Display4() throws SQLException {
		displayFrame = new JFrame("Display 4");
		JPanel panel = new JPanel(new FlowLayout());


		panel = showTables(); // Panel to show all tables
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setLocationRelativeTo(null);


		displayFrame.add(panel);
		displayFrame.pack();
		displayFrame.setVisible(true);
	}
	
	/* Method to show all tables in DB */	
	private JPanel showTables() throws SQLException {
		/* Variables */
		JPanel temp = new JPanel();
		tables = new ArrayList<String>();
		model = new DefaultListModel<String>();
		
		/* Getting the tables using a stored procedure */
		String getTables = new String("CALL csc371_02.getTables();");
		ResultSet rs = stmt.executeQuery(getTables);
		
		/* Adding tables to the list */
		while(rs.next()) {
			addTable(rs.getString(1));
		}
		
		/* Adding tables to the model */
		for (int i = 0; i < tables.size(); i++) {
			model.addElement(tables.get(i));
		}
		
		/* Inserting the model into a list */
		tableList = new JList<String>(model);
		
		/* Scroll pane for list (if there are a lot of tables in DB) */
		JScrollPane listScroller = new JScrollPane(tableList);

		JButton select = new JButton("Select");
		
		/* Select button action */
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTable = tableList.getSelectedValue(); // Gets the value of table selected
				try {
					selectRows(selectedTable); // Putting the selected value through the selectRows parameter
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		temp.add(listScroller);
		temp.add(select);
		return temp;
	}
	
	/* Selects all the rows from the table chosen from the showTables() method */
	private void selectRows(String tableName) throws SQLException {
		/* Variables */
		JFrame tempFrame = new JFrame("TABLE: " + tableName);
		JPanel temp1 = new JPanel();
		int colNum = getColNum(tableName);
		int rowNum = getRowNum(tableName);
		rows = new String[rowNum][colNum];
		colNames = getColNames(tableName);
		

		
		/* Selects the rows from the table chosen by the user */
		String selectTables = new String("SELECT * FROM csc371_02." + tableName + ";");
		ResultSet rs = stmt.executeQuery(selectTables);
		
		/* Adding all of the rows of the table into a 2D Array */
		int i = 0;	
		while(rs.next()) {
				for(int j=0; j<colNum;j++) {
					rows[i][j] = rs.getString(j+1);
				}
			i++;
		}
		
		/* Creating a DefaultTableModel that has both the rows in the table and the column names of the table */
		rowModel = new DefaultTableModel(rows, colNames);
		
		/* Placing the model into the JTable */
		rowTable = new JTable(rowModel);
		
		/* Adding a scroll pane for rows with a lot of columns */
		JScrollPane tablePane = new JScrollPane(rowTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		/* Initializing the insert, update, and delete buttons */
		JButton insert = new JButton("Insert");
		JButton update = new JButton("Update");
		JButton delete = new JButton("Delete");
		
		/* Insert button action */
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					insertRow(tableName, colNames, colNum); // Passes the table name, all of the column names, and the amount of columns through the insertRow() method
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		/* Update button action */
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowNum = rowTable.getSelectedRow(); // Getting the row the user wants to update
				selectedRowValue = rowTable.getModel().getValueAt(rowNum, 0).toString(); // Converts the row's value in the first column into a string
				updateRow(tableName, colNames, colNum, rows[rowNum]); // Passing the table's name, name of the columns, the number of columns, and the row of data chosen		
			}
		});
		
		/* Delete button action */
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowNum = rowTable.getSelectedRow(); // Getting the row the user wants to delete
				selectedRowValue = rowTable.getModel().getValueAt(rowNum, 0).toString(); // Converts the row's value in the first column into a string
				try {
					deleteRow(tableName, colNames, selectedRowValue); //Passes the table name, column names, and the row chosen to be deleted by the user
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		temp1.add(tablePane);
		temp1.add(insert);
		temp1.add(update);
		temp1.add(delete);
		tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tempFrame.setLocationRelativeTo(null);
		tempFrame.add(temp1);
		tempFrame.pack();
		tempFrame.setVisible(true);
	}
	
	/* Inserts the a new row into the table chosen by the user */
	private void insertRow(String tableName, String[] colNames, int colNum) throws SQLException {
		/* Variables */
		JFrame tempFrame = new JFrame("INSERTING TO TABLE: " + tableName);
		JPanel temp1 = new JPanel();
		String[] newRow = new String[colNum];
		StringBuilder stringBuilder = new StringBuilder();
		JTextArea[] textFields = new JTextArea[colNum];
		JLabel[] labels = new JLabel[colNum];
		JButton insert = new JButton("Insert Data");
		
		/* Adding the labels and text-fields required to input a new row of data into the table */
		for(int i=0;i<colNum;i++) {
			labels[i] = new JLabel(colNames[i]);
			textFields[i] = new JTextArea(1,15);
			temp1.add(labels[i]);
			temp1.add(textFields[i]);
		}
		
		/* Insert button action */
		insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Getting the user's input from the text-fields and placing it into a 1D array */
				for(int i=0;i<colNum;i++) {
					newRow[i] = textFields[i].getText();
				}
				
				/* 
				 * Shitty way of taking the data that was input by the user. Basically it is
				 * appending all the data into a string-builder where later a string variable
				 * is used in an insert statement. Very vunerable to SQL Injection, but it
				 * gets the job done for this project.
				 */
				for(int j=0;j<colNum;j++) {
					if(j == colNum-1) {
						stringBuilder.append("'" + newRow[j] + "'");
					} else {
						stringBuilder.append("'" + newRow[j] + "', ");
					}
				}
				
				/* Putting the string-builder into a string variable */
				String insertStmt = stringBuilder.toString();				
				
				/* Insert statement */
				String selectTables = new String("INSERT INTO csc371_02." + tableName + " VALUES (" + insertStmt + ");");
				try {
					stmt.executeUpdate(selectTables); // Inserts new row into DB
					JOptionPane.showMessageDialog(null, "Row Inserted"); // Gives a message confirmed the user the row has been inserted
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		temp1.setLayout(new GridLayout(0,1));
		tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tempFrame.setLocationRelativeTo(null);
		temp1.add(insert);
		tempFrame.add(temp1);
		tempFrame.pack();
		tempFrame.setVisible(true);
	}
	
	/* Updates the row that was chosen by the user */
	private void updateRow(String tableName, String[] colNames, int colNum, String[] row) {
		/* Variables */
		JFrame tempFrame = new JFrame("UPDATING TO TABLE: " + tableName);
		JPanel temp1 = new JPanel();
		String[] newRow = new String[colNum];
		StringBuilder stringBuilder = new StringBuilder();
		JTextField[] textFields = new JTextField[colNum];
		JLabel[] labels = new JLabel[colNum];
		JButton insert = new JButton("Update Data");
		
		/* Pretty similar to the for loop in the insert method, however
		 * instead of having blank text-fields, it takes the data already
		 * in the row and places it in the text-fields.
		 */
		for(int i=0;i<colNum;i++) {
			labels[i] = new JLabel(colNames[i]);
			textFields[i] = new JTextField(row[i]);
			temp1.add(labels[i]);
			temp1.add(textFields[i]);
		}
		
		/* Insert button action */
		insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Getting the user's input from the text-fields and placing it into a 1D array */
				for(int i=0;i<colNum;i++) {
					newRow[i] = textFields[i].getText();
				}

				/* Another shitty way of making a statement for the SQL statement */
				for(int j=0;j<colNum;j++) {
					if(j == colNum-1) {
						stringBuilder.append(colNames[j] + " = '" + newRow[j] + "'");
					} else {
						stringBuilder.append(colNames[j] + " = '" + newRow[j] + "', ");
					}
				}
				
				/* Putting the string-builder into a string variable */
				String updateStmt = stringBuilder.toString();
				
				/* Update statement */
				String selectTables = new String("UPDATE csc371_02." + tableName + " SET " + updateStmt + " WHERE " + colNames[0] + " = '" + row[0] + "';");
				
				try {
					stmt.executeUpdate(selectTables); // Updates the selected row
					JOptionPane.showMessageDialog(null, "Row Updated"); // Gives the user a confirmation message
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tempFrame.setVisible(false);
			}
		});
		
		temp1.setLayout(new GridLayout(0,1));
		tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tempFrame.setLocationRelativeTo(null);
		temp1.add(insert);
		tempFrame.add(temp1);
		tempFrame.pack();
		tempFrame.setVisible(true);
	}
	
	/* Deletes the row chosen by the user */
	private void deleteRow(String tableName, String[] colNames, String selectedRowValue) throws SQLException {
		/* SQL statement to delete the row */
		String deleteRow = new String("DELETE FROM  csc371_02." + tableName + " WHERE " + colNames[0] + " = '" + selectedRowValue + "';");
		
		try {
			stmt.executeUpdate(deleteRow); // Deletes the row
			JOptionPane.showMessageDialog(null, "Row Deleted"); // Confirmation message
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/* Adds a table to the list of tables */
	private void addTable(String tableName) {
		this.tables.add(tableName);
	}
	
	/* Gets the number of columns from a given table */
	private int getColNum(String tableName) throws SQLException {
		/* Variables */
		int colNum = 0;
		
		/* Gets the number of columns from a table */
		String getColNum = new String("SELECT count(*) FROM information_schema.columns WHERE table_name = '" + tableName + "' AND table_schema = 'csc371_02';");
		ResultSet rs = stmt.executeQuery(getColNum);
		
		while(rs.next()) {
			colNum = rs.getInt(1);
		}
		
		return colNum;
	}
	
	/* Gets the number of rows from a given table */
	private int getRowNum(String tableName) throws SQLException {
		/* Variables */
		int rowNum = 0;
		
		/* Gets the row number */
		String getRowNum = new String("SELECT count(*) FROM csc371_02." + tableName + ";");
		ResultSet rs = stmt.executeQuery(getRowNum);
		
		while(rs.next()) {
			rowNum = rs.getInt(1);
		}
		
		return rowNum;
	}
	
	/* Gets all the column names from a given table */
	private String[] getColNames(String tableName) throws SQLException {
		/* Variables */
		int colNum = getColNum(tableName);
		colNames = new String[colNum];
		
		/* Gets all the column names from a table */
		String getColNames = new String("SELECT * FROM information_schema.columns WHERE table_name = '" + tableName + "' AND table_schema = 'csc371_02';");
		ResultSet rs = stmt.executeQuery(getColNames);
	
		int i=0;
		
		while(rs.next()) {
			colNames[i] = rs.getString(4);
			i++;
		}

		return colNames;
	}
}
