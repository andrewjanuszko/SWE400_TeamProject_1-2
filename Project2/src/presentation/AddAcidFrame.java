package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		JLabel idLabel = new JLabel("Id: ");
		add(idLabel,gbc);
		JTextField jtf = new JTextField("Id");
		add(jtf,gbc);
	}
}
