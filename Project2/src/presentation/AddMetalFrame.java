package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddMetalFrame extends JFrame{

	GridBagConstraints gbc = new GridBagConstraints(); 
	int height = 450, width = 300;
	
	
	public AddMetalFrame() {
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
		gbc.gridy = 2;
		JLabel atomNumLabel = new JLabel("Atomic Number: ");
		add(atomNumLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		JTextField jtfAtomNum = new JTextField("Number");
		add(jtfAtomNum,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		JLabel atomMassLabel = new JLabel("Atomic Mass: ");
		add(atomMassLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		JTextField jtfAtomMass = new JTextField("Mass");
		add(jtfAtomMass,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		JLabel DissByLabel = new JLabel("Dissolved By: ");
		add(DissByLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		JTextField jtfDissBy = new JTextField("Dissolved By");
		add(jtfDissBy,gbc);
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double inventory, atomicMass;
				String name;
				int solute, atomicNumber, dissolvedBy;
				
				
				try {
					
					inventory = Double.parseDouble(jtfInventory.getText());
					name = jtfName.getText();
					atomicNumber = Integer.parseInt(jtfAtomNum.getText());
					atomicMass = Double.parseDouble(jtfAtomMass.getText());
					dissolvedBy = Integer.parseInt(jtfDissBy.getText());
					
					System.out.println("\n" + name + "\n" + inventory + "\n" + atomicNumber + "\n" + atomicMass + "\n" + dissolvedBy);
					dispose();
				} catch (NumberFormatException e1) {
					new FailureFrame("Failed to create Metal");
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		
		add(add, gbc);
	}
}
