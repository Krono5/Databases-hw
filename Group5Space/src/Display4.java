import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Display4 {
	JFrame displayFrame;
	private ArrayList<String> tables;
	private JList<String> tableList;
	private DefaultListModel<String> model;
	private String[][] rows;
	private JList<String> rowList;
	private DefaultListModel<String> rowModel;
	private String selectedTable;
	private String selectedRow;
	private String[] colNames;

	
	public Display4() throws SQLException {
		displayFrame = new JFrame("Display 4");
		JPanel panel = new JPanel(new FlowLayout());


		panel = showTables();
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setLocationRelativeTo(null);


		displayFrame.add(panel);
		displayFrame.pack();
		displayFrame.setVisible(true);
	}
	
	private JPanel showTables() throws SQLException {
		JPanel temp = new JPanel();
		tables = new ArrayList<String>();
		model = new DefaultListModel<String>();
		
		
		Statement stmt = SpaceController.dbConnection.createStatement();
//		String useDB = new String("USE CSC371_02;");
//		stmt.executeQuery(useDB);
		
		/* GET TABLE NAMES */
		String getTables = new String("CALL csc371_02.getTables();");
		ResultSet rs = stmt.executeQuery(getTables);
		
		while(rs.next()) {
			addTable(rs.getString(1));
		}
		
		for (int i = 0; i < tables.size(); i++) {
			model.addElement(tables.get(i));
		}
		

		
		tableList = new JList<String>(model);
		
		JScrollPane listScroller = new JScrollPane(tableList);

		JButton select = new JButton("Select");
		
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTable = tableList.getSelectedValue();
				try {
					selectRows(selectedTable);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		temp.add(listScroller);
		temp.add(select);
		return temp;
	}
	
	/********** WORKING HERE *************/
	private void selectRows(String tableName) throws SQLException {
		JFrame tempFrame = new JFrame("TABLE: " + tableName);
		JPanel temp1 = new JPanel();
		int colNum = getColNum(tableName);
		int rowNum = getRowNum(tableName);
		rows = new String[colNum][rowNum];
		rowModel = new DefaultListModel<String>();
		colNames = getColNames(tableName);
		
		Statement stmt2 = SpaceController.dbConnection.createStatement();
		/* SELECT TABLES */
		String selectTables = new String("SELECT * FROM " + tableName + ";");
		ResultSet rs = stmt2.executeQuery(selectTables);
		
		while(rs.next()) {
			int i = 0;
				for(int j=0; j<rowNum;j++) {
					rows[i][j] = rs.getString(j+1);
				}
			i++;
		}
		
//		for (int i = 0; i < rows.size(); i++) {
//			rowModel.addElement(rows.get(i));
//		}
		
		rowList = new JList<String>(rowModel);
		
		JTable table = new JTable(rows, colNames);

		JButton insert = new JButton("Insert");
		JButton update = new JButton("Update");
		JButton delete = new JButton("Delete");
		
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					insertRow(tableName);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow = rowList.getSelectedValue();
					updateRow(tableName, selectedRow);
			}
		});
		
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow = rowList.getSelectedValue();
					try {
						deleteRow(tableName, selectedRow);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		temp1.add(table);
		temp1.add(insert);
		temp1.add(update);
		temp1.add(delete);

		
		tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tempFrame.setLocationRelativeTo(null);

		tempFrame.add(temp1);
		tempFrame.pack();
		tempFrame.setVisible(true);
	}
	
	private void insertRow(String tableName) throws SQLException {
		
		JFrame tempFrame = new JFrame("TABLE: " + tableName);
		JPanel temp1 = new JPanel();
//		rows = new ArrayList<String>();
		rowModel = new DefaultListModel<String>();
		
		
		
		
		Statement stmt2 = SpaceController.dbConnection.createStatement();
		/* SELECT TABLES */
		String selectTables = new String("INSERT " + tableName + " ");
		ResultSet rs = stmt2.executeQuery(selectTables);
		
		
		
		
	}
	
	private void updateRow(String tableName, String row) {
		System.out.print("Table Name: " + tableName + ".\n");
		System.out.print("Row: " + row + ".");
	}
	
	private void deleteRow(String tableName, String row) throws SQLException {
		Statement stmtDEL = SpaceController.dbConnection.createStatement();
		/* DELETING A ROW */
		String deleteRow = new String("DELETE FROM  " + tableName + " WHERE col1='" + row + "';");
		ResultSet rs = stmtDEL.executeQuery(deleteRow);
	}
	
	private void addTable(String tableName) {
		this.tables.add(tableName);
	}
	
//	private void addRow(String tableName) {
//		this.rows.add(tableName);
//	}
	
	private int getColNum(String tableName) throws SQLException {
		Statement stmt = SpaceController.dbConnection.createStatement();
		/* DELETING A ROW */
		String deleteRow = new String("SELECT count(*)\r\n" + 
				"FROM information_schema.columns\r\n" + 
				"WHERE table_name = '" + tableName + "';");
		ResultSet rs = stmt.executeQuery(deleteRow);
		
		int colNum = rs.getInt(1);
		
		return colNum;
	}
	
	private int getRowNum(String tableName) throws SQLException {
		Statement stmtDEL = SpaceController.dbConnection.createStatement();
		/* DELETING A ROW */
		String deleteRow = new String("SELECT count(*)\r\n" + 
				"FROM" + tableName + "';");
		ResultSet rs = stmtDEL.executeQuery(deleteRow);
		
		int rowNum = rs.getInt(1);
		return rowNum;
	}
	
	private String[] getColNames(String tableName) throws SQLException {
		int colNum = getColNum(tableName);
		colNames = new String[colNum];
		
		Statement stmtDEL = SpaceController.dbConnection.createStatement();
		/* DELETING A ROW */
		String stmt = new String("SELECT *\r\n" + 
				"FROM information_schema.columns\r\n" + 
				"WHERE table_name = '" + tableName + "';");
		ResultSet rs = stmtDEL.executeQuery(stmt);
		
			while(rs.next()) {
				int i=0;
				colNames[i] = rs.getString(4);
				i++;
			}

		return colNames;
	}
}
