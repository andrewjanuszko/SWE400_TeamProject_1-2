package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Base;
import model.Metal;

public class BaseDetailsFrame extends JFrame{

	Base base;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public BaseDetailsFrame(Base a) {
		base = a;
		setLayout(new GridBagLayout());
    	setBackground(Color.BLACK);
    	setUp();
    	setSize(300, 450);
    	setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		JLabel name = new JLabel(base.getName());
		JLabel inventory = new JLabel("" + base.getInventory());
		JLabel solute = new JLabel("" + base.getSolute());
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(name,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(inventory, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(solute,gbc);

		
	}
	
}
