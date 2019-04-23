import javax.swing.JFrame;

public class Display1 {
	
	
	public Display1() {
		JFrame displayFrame = new JFrame("Display 1");
		
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.setSize(800, 800);
		displayFrame.setLocationRelativeTo(null);
		
		
		displayFrame.setVisible(true);
	}
}
