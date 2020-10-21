package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FilterAcidFrame extends JFrame{
	
	int filterType;
	JRadioButton idFilter = new JRadioButton();          //filterType 1
	JRadioButton nameFilter = new JRadioButton();        //filterType 2
	JRadioButton soluteFilter = new JRadioButton();      //filterType 3
	JRadioButton inventoryFilter = new JRadioButton();   //filterType 4
	GridBagConstraints gbc = new GridBagConstraints();
	JButton filterButton = new JButton("Filter");
	JButton clearButton = new JButton("Clear Filter");
	JTextField jtfName;
	JTextField jtfSolute;
	JTextField jtfInventory;
	
	public FilterAcidFrame() {
		setLayout(new GridBagLayout());
    	setBackground(Color.BLACK);
    	setUp();
    	setSize(300, 450);
    	setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		/*
		idFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		        filterType = 1;
		      }
		});
		*/
		nameFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		        filterType = 1;
		      }
		});
		soluteFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		        filterType = 2;
		      }
		});
		inventoryFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		        filterType = 3;
		      }
		});
		
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(idFilter, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JLabel idLabel = new JLabel("Id Filter: ");
		add(idLabel,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		JTextField jtfid1 = new JTextField("Range 1");
		add(jtfid1,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		JTextField jtfid2 = new JTextField("Range 2");
		add(jtfid2,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(nameFilter, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		JLabel nameLabel = new JLabel("Name Filter: ");
		add(nameLabel,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(soluteFilter, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		JLabel soluteLabel = new JLabel("Solute Filter: ");
		add(soluteLabel,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(inventoryFilter, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		JLabel inventoryLabel = new JLabel("Inventory Filter: ");
		add(inventoryLabel,gbc);
		
		
		gbc.gridwidth = 2;
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		jtfName = new JTextField("Name");
		add(jtfName,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		jtfSolute = new JTextField("Solute");
		add(jtfSolute,gbc);
		

		gbc.gridx = 2;
		gbc.gridy = 3;
		jtfInventory = new JTextField("Inventory");
		add(jtfInventory,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		add(filterButton, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		
		add(clearButton, gbc);
		
	}
	
	public String getFilter() {
		String filter = "" + filterType;
		try {
			switch(filterType) {
				case 0:
					filter = null;
					break;
				case 1:
					filter = filter + " " + jtfName.getText();
					break;
				case 2:
					filter = filter + " " + Integer.parseInt(jtfSolute.getText());
					break;
				case 3:
					filter = filter + " " + Double.parseDouble(jtfInventory.getText()); 
			}
		} catch(NumberFormatException e) {
			new FailureFrame("Could not Filter Acid");
			return null;
		}
		return filter;
	}
}
