package presentation;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Acid;

public class AcidDetailsFrame extends JFrame{
	
	Acid acid;
	
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
		
		List<Acid> dissolvesList = new ArrayList<Acid>();
		
		dissolvesList.forEach(x -> dissolves.setText(dissolves.getText() + " " + x));
	}
	
}
