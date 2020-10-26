package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Acid;
import model.Metal;

public class AcidDetailsFrame extends JFrame{
	
	Acid acid;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public AcidDetailsFrame(Acid a) {
		acid = a;
		setLayout(new GridBagLayout());
    	setBackground(Color.BLACK);
    	setUp();
    	setSize(300, 450);
    	setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		JLabel name = new JLabel(acid.getName());
		JLabel inventory = new JLabel("" + acid.getInventory());
		JLabel solute = new JLabel("" + acid.getSolute());
		
		JLabel dissolves = new JLabel();
		
		List<Metal> dissolvesList = acid.getDissolves();
		if(dissolvesList != null && !dissolvesList.isEmpty())
			dissolvesList.forEach(x -> dissolves.setText(dissolves.getText() + " " + x.getName()));
		else 
			dissolves.setText("nothing");
			
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
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(dissolves,gbc);
		
	}
	
}
