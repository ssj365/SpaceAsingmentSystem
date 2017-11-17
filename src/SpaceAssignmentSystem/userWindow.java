package SpaceAssignmentSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class userWindow extends JPanel {
	public userWindow(){
	// Dumb data for rendering.  TO DO
	String[] columnNames = { "Room", "Start Time", "End Time", "Status"};

	String[][] data = {{"Room 1", "Start Time", "End Time", "Approved"}, 
						{"Room 2", "Start Time", "End Time", "Pending"},
						{"Room 3", "Start Time", "End Time", "Denied"}};
	//Build all panes + the table	
	JPanel mainPane = new JPanel(new BorderLayout());
	JPanel toolBarPane = new JPanel(new BorderLayout());
	JTable table = new JTable(data, columnNames);
	JScrollPane requestPane = new JScrollPane(table);		
	
	// Build cancel button with handler. 
	JButton cancleButton = new JButton("cancle Request");
	cancleButton.addActionListener( new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e)  {
	    	JOptionPane.showMessageDialog(null, "To Do");
	    }
	});
	
	// Add elements to panes and panes to frames.
	toolBarPane.add(cancleButton, BorderLayout.LINE_END);
	requestPane.setPreferredSize(new Dimension(400, 250));
	mainPane.add(toolBarPane, BorderLayout.NORTH);
	mainPane.add(requestPane);
	mainPane.setPreferredSize(new Dimension(400, 300));
	add(mainPane);
	}

	
	
	public static void renderUserWindow() {
		//Build frame for panes and rendering. 
		JFrame frame = new JFrame("Request Window");	
		userWindow newContentPane = new userWindow();
		frame.setContentPane(newContentPane);
		// Set the label.
		frame.pack();
		// Display the window.
		frame.setVisible(true);	
	}
	
}



