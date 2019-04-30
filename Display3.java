import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display3  extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO 
	//  or instead of doing this all, under BorderLayout, Gridlayout would be better. 
	//North: Resources as TextLabel, Plant maybe as a text label with buttons around it or a user input
	//Center: Factories and Shipyards title as an imageLabel. Adding ships, babules, crusiers and cargo on ethier side
	//as imageButtons with actions.  Display table for them with textLabes but bufferedImages that arrive upon creation of items
	//East: Resources and Baubles amount as textLabels and then  something to display their values as they are used and updated
	JButton textButton, imageButton;
	static JLabel textLabel;
	JLabel imageLabel;
	public static final String[] shipColumns = { "Cargoships", "Cruisers"};
	public static final String[] factoryColumns = {"Resources", "Baubles"};
	Object[][] factoryData = { 5, 5};
	Object[][] shipyardData = { 9,9};

	public static void main(String[] args) {
		
		
		JFrame displayFrame = new JFrame("Display 3");
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);
		
		displayFrame.setLayout(new BorderLayout());
		//TODO: turn into 2 nested panels one set up from North Center and South, then a border pane for Player and stats
	
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel borderPanel = new JPanel(new BorderLayout());
		
		mainPanel.add("North", northPanel());
		mainPanel.add("Center", centerPanel());	
		mainPanel.add("South",southPanel());
		borderPanel.add(playerStats());
	
		
		displayFrame.add(mainPanel);
		displayFrame.add("East", borderPanel);
	
		displayFrame.setVisible(true);
		
	}

	
	/*
	 * TODO: Turn into a table rather than a grid
	 */
	public static JPanel playerStats() {
		JPanel psPanel = new JPanel(new GridLayout(2,2));
		JLabel[][] labelArray = new JLabel[2][2];
		labelArray[0][0] = new JLabel("Resources");
		labelArray[0][1] = new JLabel("Baubles");
		labelArray[1][0] = new JLabel("$$$###");
		labelArray[1][1] = new JLabel("$$$###");
		for (int r=0;r<2;r++) {
			for (int c=0;c<2;c++) {
				psPanel.add(labelArray[r][c]);
			}
		}
		
		return psPanel;
	}
	/*
	 * Basically just a header panel for the display 
	 * Maybe there is an easier way to make a header
	 */
	public static JPanel northPanel() {
		JPanel nPanel = new JPanel(new GridLayout(2,1));
		JLabel[][] labelArray = new JLabel[2][1];
		labelArray[0][0] = new JLabel("Planet");
		labelArray[1][0] = new JLabel("Resources");
		nPanel.add(labelArray[0][0]);
		nPanel.add(labelArray[1][0]);
		return nPanel;
		
	}
	
	/*
	 * TODO Change Center to do just the Factory 
	 * TODO Change the south to do just the Shipyard
	 */
	public static JPanel centerPanel() {
		JPanel centerPanel = new JPanel(new GridLayout(9,3));
		JLabel[][] labelArray = new JLabel[5][3];
		for (int r=0;r<5;r++) {
			for (int c=0;c<3;c++) {
				labelArray[r][c] = new JLabel("(" + r + ":" + c + ")");
				centerPanel.add(labelArray[r][c]);
			}
		}
	
		return centerPanel;
	}
	/*
	 * TODO Change Center to do just the Factory 
	 * TODO Change the south to do just the Shipyard
	 */
	public static JPanel southPanel() {
		JPanel southPanel = new JPanel(new GridLayout(9,3));
		JLabel[][] labelArray = new JLabel[5][3];
		for (int r=0;r<5;r++) {
			for (int c=0;c<3;c++) {
				labelArray[r][c] = new JLabel("(" + r + ":" + c + ")");
				southPanel.add(labelArray[r][c]);
			}
		}
	
		return southPanel;
	}
}
