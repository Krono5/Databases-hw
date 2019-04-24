import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	private DefaultListModel<String> model;
	private JList<String> list;
	private JFrame displayFrame;

	public Display2() throws Exception {
		displayFrame = new JFrame("Display 2");
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
		JButton accept = acceptButton();
		JButton cancel = cancelButton();

		/*
		 * Adding Elements to the panel
		 */
		tempPanel.add(listScroller);
		tempPanel.add(accept);
		tempPanel.add(cancel);
		return tempPanel;

	}

	private JButton acceptButton() {
		JButton button = new JButton("Send Message");

		return button;
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
