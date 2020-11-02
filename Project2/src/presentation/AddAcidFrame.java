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
import command.acid.AcidCreateCommand;
import model.DomainModelException;

public class AddAcidFrame extends JFrame{
	
	GridBagConstraints gbc = new GridBagConstraints(); 
	int height = 450, width = 300;
	
	public AddAcidFrame() {
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
		gbc.gridy = 3;
		JLabel idLabel = new JLabel("Dissolves Id: ");
		add(idLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		JTextField jtfId = new JTextField("Dissolves Ids");
		add(jtfId,gbc);
		
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
				String id;
				int solute = 0;
				double inventory = 0;
				String name = "";
				List<Integer> ids = new ArrayList<Integer>();
				try {
					id = jtfId.getText();
					inventory = Double.parseDouble(jtfInventory.getText());
					solute = Integer.parseInt(jtfSolute.getText());
					name = jtfName.getText();
					String[] stringIds = id.split(" ");
					System.out.println(stringIds[0]);
					if(!stringIds[0].equals(""))
						for(String s: stringIds) {
							ids.add(Integer.parseInt(s));
						}
						
						try {
							new AcidCreateCommand(name, inventory, ids, solute).execute();
						} catch (DomainModelException e1) {
							e1.printStackTrace();
						}
						
					dispose();
				} catch (NumberFormatException e1) {
					new FailureFrame("Failed to create Acid");
				}

			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		
		add(add, gbc);
	}
}
