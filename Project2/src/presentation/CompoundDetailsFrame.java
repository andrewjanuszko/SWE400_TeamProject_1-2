package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Compound;
import model.Element;
import model.Metal;

public class CompoundDetailsFrame extends JFrame{
	Compound compound;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public CompoundDetailsFrame(Compound a) {
		compound = a;
		setLayout(new GridBagLayout());
    	setBackground(Color.BLACK);
    	setUp();
    	setSize(300, 450);
    	setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUp() {
		JLabel name = new JLabel(compound.getName());
		JLabel inventory = new JLabel("" + compound.getInventory());
		
		
		JLabel madeOf = new JLabel();
		
		List<Element> elementList = compound.getMadeOf();
		if(elementList != null)
			elementList.forEach(x -> madeOf.setText(madeOf.getText() + " " + x.getName()));
		else 
			madeOf.setText("nothing");
			
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(name,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(inventory, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(madeOf,gbc);
		
	}
}
