package presentation;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MetalPanel extends JPanel{

	JScrollPane metals = new JScrollPane(); 
	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");
	JButton filterButton = new JButton("Filter");
	
	public MetalPanel() {
		setBackground(Color.red);
	}
}
