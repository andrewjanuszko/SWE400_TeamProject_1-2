package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import command.base.BaseCreateCommand;
import model.Acid;
import model.DomainModelException;

public class AddBaseFrame extends JFrame{
	GridBagConstraints gbc = new GridBagConstraints(); 
	int height = 450, width = 300;
	Acid selectedAcid = null;
	JScrollPane acids = new JScrollPane();
	
	public AddBaseFrame() {
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
		gbc.gridy = 2;
		JLabel soluteLabel = new JLabel("Solute: ");
		add(soluteLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		JTextField jtfSolute = new JTextField("Solute");
		add(jtfSolute,gbc);
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id;
				double inventory;
				int solute;
				String name;
				try {
					//id = Integer.parseInt(jtfId.getText());
					inventory = Double.parseDouble(jtfInventory.getText());
					solute = Integer.parseInt(jtfSolute.getText());
					name = jtfName.getText();
					//System.out.println(id + "\n" + name + "\n" + inventory + "\n" + solute);
					dispose();
					try {
						new BaseCreateCommand(name, inventory, selectedAcid).execute();
					} catch (DomainModelException e1) {
						e1.printStackTrace();
					}
				} catch (NumberFormatException e1) {
					new FailureFrame("Failed to create Base");
				}
						
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		
		add(add, gbc);
	}
}
