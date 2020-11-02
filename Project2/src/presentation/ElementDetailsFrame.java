package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Element;
import model.Element;


public class ElementDetailsFrame extends JFrame{
	Element element;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public ElementDetailsFrame(Element a) {
		element = a;
		setLayout(new GridBagLayout());
    	setBackground(Color.BLACK);
    	setUp();
    	setSize(300, 450);
    	setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		JLabel name = new JLabel(element.getName());
		JLabel inventory = new JLabel("" + element.getInventory());
		JLabel atomicNumber = new JLabel("" + element.getAtomicNumber());
		JLabel atomicMass = new JLabel("" + element.getAtomicMass());
		
		
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
		
	}
}
