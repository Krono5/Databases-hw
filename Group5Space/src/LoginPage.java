import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 
 * @author aa1184 aka MinaKindo
 *
 */
public class LoginPage extends JFrame {
  
  private JTextField usernameTF;     
  private JLabel usernameLabel;             
  private JTextField passwordTF;    
  private JLabel passwordLabel; 
  private JButton loginButton;
  
  //These three buttons will allow user to 
  //select the right choice
  private ButtonGroup selection;
  private JRadioButton playerRB;
  private JRadioButton adminRB;
  private JRadioButton newUserRB;
  
  /*
   * this UI will be the first screen a user will see 
   * and allows to get to the DB
   */
  public LoginPage() {
    // Display a title.
    setTitle("User Login");
    setSize(800, 800);
    setLocationRelativeTo(null);
    // Specify an action for the close button.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(3,1));
    //create login button
    loginButton = new JButton("Enter");
    loginButton.addActionListener(new LoginActionListener());
    add(loginFields());
    add(selectionRB());
    add(loginButton);
    
    pack();
    setVisible(true);
  }
  
  public JPanel loginFields() {
    JPanel panel = new JPanel();
    
    panel.setLayout(new GridLayout(2,2));
    // Create a label to display instructions.
    usernameLabel = new JLabel("Username: ");
    usernameTF = new JTextField(5);
    
    // Create a label to display instructions.
    passwordLabel = new JLabel("Password: ");
    passwordTF = new JTextField(5);
    
    //add components to panel
    panel.add(usernameLabel);
    panel.add(usernameTF);
    panel.add(passwordLabel);
    panel.add(passwordTF);
    
    return panel;  
  }
  
  public JPanel selectionRB() {
    JPanel panel = new JPanel();
    
    panel.setLayout(new GridLayout(3,1));
    
    //Create Radio Button   
    playerRB = new JRadioButton("I am a PLAYER", true);
    newUserRB = new JRadioButton("I am an ADMIN");
    adminRB = new JRadioButton("NEW USER");
    selection = new ButtonGroup();
    
    selection.add(playerRB);
    selection.add(adminRB);
    selection.add(newUserRB);
    
    //add components to panel
    panel.add(playerRB);
    panel.add(adminRB);
    panel.add(newUserRB);
    
    return panel;  
  }
  
  private class LoginActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

    }

  }

}
