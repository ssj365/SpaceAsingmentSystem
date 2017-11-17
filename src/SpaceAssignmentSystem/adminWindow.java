package SpaceAssignmentSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class adminWindow extends JPanel {
	public adminWindow(){
	// Dumb data for rendering.  TO DO
	String[] columnNames = {"Name", "Room", "Start Time", "End Time", "Status"};

	String[][] data = {{"John Doe","Room 1", "Start Time", "End Time", "Approved"}, 
						{"Jane Doe","Room 2", "Start Time", "End Time", "Pending"},
						{"Fake Name", "Room 3", "Start Time", "End Time", "Denied"}};
	//Build all panes + the table	
	JPanel mainPane = new JPanel(new BorderLayout());
	JPanel toolBarPane = new JPanel(new FlowLayout());
	JTable table = new JTable(data, columnNames);
	JScrollPane requestPane = new JScrollPane(table);		
	
	// Build cancel button with handler. 
	JButton rejectButton = new JButton("Reject Request");
	rejectButton.addActionListener( new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e)  {
	    	JOptionPane.showMessageDialog(null, "To Do");
	    }
	});
	JButton approveButton = new JButton("Approve Request");
	approveButton.addActionListener( new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e)  {
	    	JOptionPane.showMessageDialog(null, "To Do");
	    }
	});

	
	// Add elements to panes and panes to frames.
	toolBarPane.add(approveButton);
	toolBarPane.add(rejectButton);
	requestPane.setPreferredSize(new Dimension(400, 275));
	mainPane.add(toolBarPane, BorderLayout.NORTH);
	mainPane.add(requestPane);
	mainPane.setPreferredSize(new Dimension(400, 300));
	add(mainPane);
	
	
	}
	
	
	public static void renderAdminWindow() {
		//Build frame for panes and rendering. 
		JFrame frame = new JFrame("Admin Window");	
		adminWindow newContentPane = new adminWindow();
		frame.setContentPane(newContentPane);
		// Set the label.
		frame.pack();
		// Display the window.
		frame.setVisible(true);	
	}
	
}



