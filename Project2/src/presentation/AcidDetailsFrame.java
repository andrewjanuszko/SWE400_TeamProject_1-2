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

import command.acid.AcidUpdateCommand;
import model.Acid;
import model.DomainModelException;
import model.Metal;

public class AcidDetailsFrame extends JFrame{
	
	Acid acid;
	GridBagConstraints gbc = new GridBagConstraints();
	JButton update = new JButton("Update");
	
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
		JTextField name = new JTextField(acid.getName());
		JTextField inventory = new JTextField("" + acid.getInventory());
		JTextField solute = new JTextField("" + acid.getSolute());
		JTextField id = new JTextField("" + acid.getID());
		
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
		add(id, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(name,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(inventory, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(solute,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(dissolves,gbc);
		
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] strings = dissolves.getText().split(" ");
				for(String s: strings) {
					//dissolvesList.add(new Metal(Integer.parseInt(s)));
				}
				try {
					new AcidUpdateCommand(new Acid(acid.getID(), name.getText(), Double.parseDouble(inventory.getText()), dissolvesList, Integer.parseInt(solute.getText()))).execute();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DomainModelException e1) {
					new FailureFrame("Could not Update Acid");
				}
			}
		});
				
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(update,gbc);
	}
	
}
