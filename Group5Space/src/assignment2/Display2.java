package assignment2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * author: Tyler Storr
 */

public class Display2 {

	private ArrayList<String> players;
	private DefaultListModel<String> model;
	private JList<String> list;
	private JFrame displayFrame;
	private String selectedName;
	
	public Display2() throws Exception {
		displayFrame = new JFrame("Display 2");
		JPanel mainPanel = new JPanel();

		JButton testButton = new JButton("Test");
		mainPanel.add(testButton);
		mainPanel = initPopPanel();

		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setLocationRelativeTo(null);

		displayFrame.add(mainPanel);
		displayFrame.pack();
		displayFrame.setVisible(true);
	}

	private JPanel initPopPanel() throws Exception {
		/*
		 * Initialize variables
		 */
		JPanel tempPanel = new JPanel();
		players = new ArrayList<>();
		model = new DefaultListModel<>();

		/*
		 * Statement to return names
		 */
		Statement stmt = SpaceController.dbConnection.createStatement();
		String getNames = new String("CALL csc371_02.getPlayerNames();"); // StoredProcedure to retrieve player Names
		ResultSet rs = stmt.executeQuery(getNames);

		/*
		 * Populate array list with names retrieved
		 */
		while (rs.next()) {
			addPlayer(rs.getString(1));
		}
		
		
		for (int i = 0; i < players.size(); i++) {
			model.addElement(players.get(i));
		}

		list = new JList<String>(model);

		JScrollPane listScroller = new JScrollPane(list);

		/*
		 * Adding Buttons
		 */
		JButton accept = new JButton("Send Message");
		JButton cancel = cancelButton();

		/*
		 * Send message button
		 */
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedName = list.getSelectedValue();
				sendMessage(selectedName);

			}
		});

		/*
		 * Adding Elements to the panel
		 */
		tempPanel.add(listScroller);
		tempPanel.add(accept);
		tempPanel.add(cancel);
		return tempPanel;

	}

	private void sendMessage(String playerName) {
		JFrame messageFrame = new JFrame("Message");
		JPanel messagePanel = new JPanel();
		messageFrame.setLocationRelativeTo(null);
		messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton send = new JButton("Send Message");
		JTextArea textArea = new JTextArea("Enter Message...", 5, 1);
		textArea.setBounds(10, 30, 150, 300);
		textArea.setSize(150, 300);
		textArea.setLineWrap(true);

		/*
		 * Clear default text when user clicks in box
		 */
		textArea.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				textArea.setText("");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		/*
		 * Send Button smarts
		 */
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dbSendMessage(playerName, textArea.getText());
				messageFrame.dispose();

			}
		});

		messagePanel.add(textArea);
		messagePanel.add(send);
		messageFrame.add(messagePanel);
		messageFrame.pack();
		messageFrame.setVisible(true);
	}

	private void dbSendMessage(String playerName, String message) {
		Statement stmt2;
		try {
			stmt2 = SpaceController.dbConnection.createStatement();
			// String sendMessage = new String("INSERT INTO `csc371_02`.`MESSAGES`
			// (`P_Username`, `Message`) VALUES ('Player10', 'test');");
			String sendMessage = new String("CALL csc371_02.sendMessage('" + playerName + "', '" + message + "');"); // StoredProcedure
																														// to
																														// send
																														// Message
			System.out.println(sendMessage);
			int rowsAffected = stmt2.executeUpdate(sendMessage);

			if (rowsAffected == 1) {
				JFrame okFrame = new JFrame();
				JOptionPane.showMessageDialog(okFrame, "Message Sent");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JButton cancelButton() {
		JButton button = new JButton("Cancel");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayFrame.dispose();

			}
		});

		return button;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public void addPlayer(String name) {
		this.players.add(name);
	}
}