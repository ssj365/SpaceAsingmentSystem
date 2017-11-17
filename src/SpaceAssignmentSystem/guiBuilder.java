package SpaceAssignmentSystem;

import javax.swing.*;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;
import org.jdatepicker.impl.*;
import SpaceAssignmentSystem.guiBuilder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

// Main GUI class, handles building SWING elements for rendering.
public class guiBuilder extends JPanel {
	public guiBuilder() {
		// load dummy data for rapid prototyping
		String[] columnNames = { "Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6", "Room 7" };
		Object[][] data = buildDay();
		
		// Create the table for displaying rooms:		
		JTable table = new JTable(data, columnNames);
		JComboBox<String> roomBox = new JComboBox<String>(columnNames);
		
		// Set the cell rendering to center for all cells.
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() ;
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		 for(int x=0;x<table.getColumnCount();x++){
	         table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
	        }
		table.setEnabled(false);
		JTable rowTable = new RowNumberTable(table);
		
		// Create all other swing elements.
		JLabel startL = new JLabel("Start:");
		JLabel endL = new JLabel("End: ");
		
		// create all the swing panes and tables
		JPanel mainPane = new JPanel(new BorderLayout());
		JPanel toolBarPane = new JPanel(new BorderLayout());
		JPanel dropDownPane = new JPanel();
		JPanel buttonPane = new JPanel();
		JScrollPane calenderPane = new JScrollPane(table);		
		
		// Build the JSpinner to enter in time of day for booking, both start and end.	
		Date startDate = new Date();
		Date endDate = new Date();
		SpinnerModel startSpinnerModel = new SpinnerDateModel(startDate, null, null, Calendar.MINUTE);
		SpinnerModel endSpinnerModel = new SpinnerDateModel(endDate, null, null, Calendar.MINUTE);
        JSpinner startSpinner = new JSpinner(startSpinnerModel);
        JSpinner endSpinner = new JSpinner(endSpinnerModel);
        JSpinner.DateEditor se = new JSpinner.DateEditor(startSpinner, "hh:mm a");
        JSpinner.DateEditor ee = new JSpinner.DateEditor(endSpinner, "hh:mm a");
        startSpinner.setEditor(se);
        endSpinner.setEditor(ee);
      
        
		JButton submit = new JButton("submit");
		// Proof of concept button listener, rebuild later. 
		submit.addActionListener( new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)  {
		    	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm a");
		    	String startString = DATE_FORMAT.format((startSpinner.getValue()));
		    	String endString = DATE_FORMAT.format((endSpinner.getValue()));
		    	JOptionPane.showMessageDialog(null, "Booked, Starting: " + startString + " Ending: " + endString + " In " + roomBox.getSelectedItem() , "TO-DO message box", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
	
		
		JButton requests = new JButton("requests");
		
		requests.addActionListener( new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)  {
		    	if (user.getUser().equals("Admin")) {
		    		adminWindow.renderAdminWindow();
				}
		    	else {
					userWindow.renderUserWindow();
		    	};
		   
		    }
			
		});
		
		
		JButton BOButton = new JButton("Black Out");
		BOButton.addActionListener( new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e)  {
		    	JOptionPane.showMessageDialog(null, "To Do");
		    }
		});
		
		
		// Set up parameters and objects for JDatePicker swing elements 
		Date now = Calendar.getInstance().getTime();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, yyy");
		String today = DATE_FORMAT.format(now);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");		
		UtilDateModel model = new UtilDateModel();		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.getJFormattedTextField().setText(today);
		
		
		// Add buttons, add requests button iff user is Admin.
		if (user.getUser().equals("Admin")) {
			buttonPane.add(BOButton);
		}
		else {
			buttonPane.add(submit);
		}

		buttonPane.add(requests);
	
		
		// Add elements to sub panes:
	    dropDownPane.add(datePicker);	   
		dropDownPane.add(startL);
		dropDownPane.add(startSpinner);
		dropDownPane.add(endL);	
		dropDownPane.add(endSpinner);
		dropDownPane.add(roomBox);
	
		// Set all the layouts and add all the elements to the appropriate frames.
		calenderPane.setPreferredSize(new Dimension(800, 400));
		calenderPane.setRowHeaderView(rowTable);
		calenderPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, rowTable.getTableHeader());
		toolBarPane.setPreferredSize(new Dimension(600, 50));
		toolBarPane.add(buttonPane, BorderLayout.LINE_END);
		toolBarPane.add(dropDownPane, BorderLayout.LINE_START);
		mainPane.add(toolBarPane, BorderLayout.NORTH);
		mainPane.add(calenderPane, BorderLayout.SOUTH);
		add(mainPane);
	};

	public static void renderGUI() {
		//Create the frame for the scheduler
		JFrame frame = new JFrame(user.getUser());
		// Create the schedule table pane and attach it to the frame.
		guiBuilder newContentPane = new guiBuilder();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		// Set the frame to exit operations on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the label.
		frame.pack();
		// Display the window.
		frame.setVisible(true);
	}

	// A simple function to build the day delimiter, not final implementation.
	public Object[][] buildDay() {
		Object[][] data = new Object[24][7];
		for (int j = 0; j < 24; j++) {
			for (int i = 0; i < 7; i++) {
				data[j][i] = "Blank";
			}
		}
		return data;
	}

}