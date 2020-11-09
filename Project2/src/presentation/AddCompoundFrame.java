package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import command.compound.CompoundCreateCommand;
import model.DomainModelException;

public class AddCompoundFrame extends JFrame{
	GridBagConstraints gbc = new GridBagConstraints(); 
	int height = 450, width = 300;
	
	public AddCompoundFrame() {
		setLayout(new GridBagLayout());
	    setBackground(Color.BLACK);
	    setUp();
	    setSize(width, height);
	    setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		/*
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel idLabel = new JLabel("Id: ");
		add(idLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JTextField jtfId = new JTextField("Id");
		add(jtfId,gbc);
		*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel nameLabel = new JLabel("Name: ");
		add(nameLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JTextField jtfName = new JTextField("Name");
		add(jtfName,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel inventoryLabel = new JLabel("Inventory: ");
		add(inventoryLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		JTextField jtfInventory = new JTextField("Inventory");
		add(jtfInventory,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel MadeOfLabel = new JLabel("Made Of: ");
		add(MadeOfLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		JTextField jtfElements = new JTextField("Elements");
		add(jtfElements,gbc);
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id;
				double inventory;
				int solute;
				String name;
				List<Integer> ids = new ArrayList<Integer>();
				try {
					id = jtfElements.getText();
					inventory = Double.parseDouble(jtfInventory.getText());
					name = jtfName.getText();
					String[] stringIds = id.split(" ");
					System.out.println(stringIds[0]);
					if(!stringIds[0].equals(""))
						for(String s: stringIds) {
							ids.add(Integer.parseInt(s));
						}
						
					dispose();
					try {
						new CompoundCreateCommand(name, inventory, ids).execute();
					} catch (DomainModelException e1) {
						e1.printStackTrace();
					}
				} catch (NumberFormatException e1) {
					new FailureFrame("Failed to create Compound");
				}
						
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		
		add(add, gbc);
	}
}
