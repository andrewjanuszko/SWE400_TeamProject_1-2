package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Metal;

public class MetalDetailsFrame extends JFrame{
	Metal metal;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public MetalDetailsFrame(Metal a) {
		metal = a;
		setLayout(new GridBagLayout());
    	setBackground(Color.BLACK);
    	setUp();
    	setSize(300, 450);
    	setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		JLabel name = new JLabel(metal.getName());
		JLabel inventory = new JLabel("" + metal.getInventory());
		JLabel atomicNumber = new JLabel("" + metal.getAtomicNumber());
		JLabel atomicMass = new JLabel("" + metal.getAtomicMass());
		JLabel dissolvedBy = new JLabel("" + metal.getAcidAmount());
		
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
		add(atomicNumber,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(atomicMass,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(dissolvedBy,gbc);
	}
}
