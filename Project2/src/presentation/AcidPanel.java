package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AcidPanel extends JPanel{

	JScrollPane chemicals = new JScrollPane(); 
	GridBagConstraints gbc = new GridBagConstraints(); 
	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");
	JButton filterButton = new JButton("Filter");
	JLabel selected = null;

	public AcidPanel() {
		setButtons();
	}
	
	
	
	private void setButtons() {
		addButton.addActionListener( new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent ae) {
		        addAcid();
		      }
		    });
		deleteButton.addActionListener( new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent ae) {
		        deleteAcid();
		      }
		    });
		filterButton.addActionListener( new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent ae) {
		        filterAcid();
		      }
		    });
		JPanel buttons = new JPanel(new GridBagLayout());
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    buttons.add(addButton, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    buttons.add(deleteButton, gbc);
	    
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    buttons.add(filterButton, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.NORTHWEST;
	    gbc.weighty = 1;
	    buttons.setBackground(Color.GRAY);
	    add(buttons, gbc);
	}
	
	private void addAcid() {
		new AddAcidFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				//reset the view
			}
		});
	}
	
	private void deleteAcid() {
		if(selected != null) {
			//deletes selected acid
		}
	}
	
	private void filterAcid() {
		if(selected != null) {
			//brings up new window based on selected acid
			new FilterAcidFrame().addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent arg0) {
					//reset the view
				}
			});
		}
	}
}
