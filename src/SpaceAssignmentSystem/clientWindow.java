package SpaceAssignmentSystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SpaceAssignmentSystem.guiBuilder;
import SpaceAssignmentSystem.user;
import javax.swing.*;

public class clientWindow extends JPanel{
	
	String[] userList = user.getUsers();
	static JFrame frame = new JFrame("Client Window");
	public clientWindow() {
		// Build elements for client window. 
		
		JPanel mainPane = new JPanel(new FlowLayout());
		JComboBox<String> userBox= new JComboBox<String>(userList);
		JLabel userL = new JLabel("User: ");
		
		// Build submit button with action handlers. 		
		JButton enter = new JButton("enter");
		enter.addActionListener( new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)  {
		    	frame.dispose();
		    	user.setUser(userBox.getSelectedItem().toString());
		    	JOptionPane.showMessageDialog(null, "welcome " + user.getUser());
		    	guiBuilder.renderGUI();		
		    }
		});
		
		// Add  all the elements to the Pane.
		mainPane.add(userL);
		mainPane.add(userBox);
		mainPane.add(enter);
		mainPane.setPreferredSize(new Dimension(200, 50));
		add(mainPane);
		
	}
	public static void renderClientWindow() {	
		
		clientWindow newContentPane = new clientWindow();
		frame.setContentPane(newContentPane);
		// Set the frame to exit operations on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the label.
		frame.pack();
		// Display the window.
		frame.setVisible(true);			
	}	
}
