import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceController {

  
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Space Control");
		mainFrame.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 800);
		mainFrame.setLocationRelativeTo(null);

		JButton display1 = display1();
		JButton display2 = display2();
		JButton display3 = display3();
		JButton display4 = display4();
		
		mainPanel.add(display1);
		mainPanel.add(display2);
		mainPanel.add(display3);
		mainPanel.add(display4);

		
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		//mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private static JButton display1() {
		JButton display1 = new JButton("Display 1");
		display1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Display1 display1 = new Display1();
				
			}
		});
		
		
		return display1;
	}

	private static JButton display2() {
		JButton display2 = new JButton("Display 2");
		display2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Display2 display = new Display2();
				
			}
		});
		return display2;
	}

	private static JButton display3() {
		JButton display3 = new JButton("Display 3");
		display3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Display3 display = new Display3();
				
			}
		});
		return display3;
	}
	
	private static JButton display4() {
		JButton display4 = new JButton("Display 4");
		display4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Display4 display = new Display4();
				
			}
		});
		return display4;
	}

}
