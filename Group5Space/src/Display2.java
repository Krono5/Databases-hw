import java.awt.Dimension;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * 
 */

public class Display2 {

	
	private ArrayList<String> players;
	
	public Display2() throws Exception {
		JFrame displayFrame = new JFrame("Display 2");
		JPanel mainPanel = new JPanel();
	
		JButton testButton = new JButton("Test");
		mainPanel.add(testButton);
		mainPanel = initPopPanel();
		

		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);

		displayFrame.add(mainPanel);
		displayFrame.setVisible(true);
	}

	private JPanel initPopPanel() throws Exception {
		
		JPanel tempPanel = new JPanel();

		Statement stmt = SpaceController.dbConnection.createStatement();
		String getNames = new String("SELECT * FROM csc371_02.PLAYER;");
		ResultSet rs = stmt.executeQuery(getNames);
		
		while (rs.next()) {
			players.add(rs.getString(1));
		}
		
		DefaultListModel<String> playerListModel = new DefaultListModel<>();
		
		for (int i = 0; i < players.size(); i++) {
			playerListModel.addElement(players.get(i));
		}
		JList<String> jlist = new JList<String>(playerListModel);
		
		JScrollPane listScroller = new JScrollPane(jlist);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		
		
		
		tempPanel.add(listScroller);
		return tempPanel;

	}
}
