package display1.component;

import javax.swing.*;

import assignment2.SpaceController;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author aa1184 
 *This class is for getting a username in order to display 
 *the interface to add buildings
 */
public class Display1 extends JFrame {
  

  private JTextField usernameTF;     
  
  private JButton loginButton;
  
  
  /*
   * this UI will be the first screen a user will see 
   * and allows to get to the different buildings
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
  /**
   * 
   * @return a p
   */
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
  
  /**
   * 
   * Inner class for login button listener
   *
   */
  private class LoginActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {   
      try {    
        //if empty will stop
        while(usernameTF.getText().trim().length() == 0) {
          JOptionPane.showMessageDialog(null, "please enter a value");
          return;
          }
        
        String username = usernameTF.getText();
        String sql = "SELECT Username FROM PLAYER WHERE Username = ?";
        
        //connect to DB
        PreparedStatement stmt = SpaceController.dbConnection.prepareStatement(sql);
              
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        
        //if username entered is not in DB stop
        while(!rs.next()) {
          JOptionPane.showMessageDialog(null, usernameTF.getText() + " does not exist ");
          return;
          }
        
        //username is in DB 
        while (rs.next()) {
          username = rs.getString("Username");
        }
        
        new BuildingsDisplay(username);
         
      } catch (SQLException e1) {
        e1.printStackTrace();
        JOptionPane.showMessageDialog(null, "Something bad happenned");
      }
    }
  }
}
