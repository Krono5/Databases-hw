package display1.component;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author aa1184 aka MinaKindo
 *
 */
public class Display1 extends JFrame {
  

  private JTextField usernameTF;     
  
  private JButton loginButton;
  
  //These three buttons will allow user to 
  //select the right choice
  
  
  /*
   * this UI will be the first screen a user will see 
   * and allows to get to the DB
   */
  public Display1() {
    // Display a title.
    setTitle("User Login");
    setPreferredSize(new Dimension(400, 400));
    setLocationRelativeTo(null);
    // Specify an action for the close button.
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(3,1));
    //create login button
    loginButton = new JButton("Enter");
    loginButton.addActionListener(new LoginActionListener());
    add(loginFields());
    add(loginButton);
    
    pack();
    setVisible(true);
  }
  
  public JPanel loginFields() {
    
    JLabel usernameLabel;             
    JPanel panel = new JPanel();
    
    panel.setLayout(new GridLayout(2,2));
    // Create a label to display instructions.
    usernameLabel = new JLabel("Username: ");
    usernameTF = new JTextField(5);
    
    //add components to panel
    panel.add(usernameLabel);
    panel.add(usernameTF);
    
    return panel;  
  }
  
  
  private class LoginActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      
      
      try {
        String sql = "SELECT Username FROM PLAYER WHERE Username = ?";
        String username = usernameTF.getText();
        PreparedStatement stmt;
        DBconnect.connector();
        stmt = DBconnect.dbConn.prepareStatement(sql);
        
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next())
        {
        // You can access values from a ResultSet either by column number - not advised:
        username = rs.getString("Username");
        }
        
        
        System.out.println(username);
        new BuildingsDisplay(username);
        
      } catch (SQLException e1) {
        e1.printStackTrace();
        JOptionPane.showMessageDialog(null, usernameTF.getText() + " does not exist ");
      }
        
    }

  }

}
