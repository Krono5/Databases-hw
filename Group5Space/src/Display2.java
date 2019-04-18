import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 
 */

public class Display2 {
	public Display2() {
		JFrame displayFrame = new JFrame("Display 2");
		JPanel mainPanel = new JPanel();
		mainPanel = initPopPanel();
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);
		
		
		displayFrame.add(mainPanel);
		displayFrame.setVisible(true);
	}
	
	private JPanel initPopPanel() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://db.cs.ship.edu:3306", "csc371_02", "Password02");	// Connection
			con.setAutoCommit(false);	// FOUND ISSUES WHEN NOT USING THIS
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel tempPanel = new JPanel();
		
		
		
		
		return tempPanel;
		
	}
}
