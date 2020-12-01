package presentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import command.acid.AcidUpdateCommand;
import command.chemical.ChemicalFilterCommand;
import command.metal.MetalFilterCommand;
import model.Acid;
import model.Base;
import model.Chemical;
import model.DomainModelException;
import model.Metal;

public class AcidDetailsFrame extends JFrame{
	
	Acid acid;
	GridBagConstraints gbc = new GridBagConstraints();
	JButton update = new JButton("Update");
	Chemical selectedBase = null;
	JLabel selectedLabel = null;
	List<Metal> selectedMetals = new ArrayList<Metal>();
	JScrollPane solute = new JScrollPane();
	JScrollPane metals = new JScrollPane();

	
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
		JTextField id = new JTextField("" + acid.getID());
		
		
			
		gbc.fill = GridBagConstraints.BOTH;
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
		solute.setViewportView(buildLabelsBase());
		solute.setVerticalScrollBar(solute.createVerticalScrollBar());
		add(solute,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		//add(dissolves,gbc);
		
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					new AcidUpdateCommand(new Acid(acid.getID(), name.getText(), Double.parseDouble(inventory.getText()), selectedMetals, selectedBase)).execute();
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
	
	private JPanel buildLabelsMetal() {
		JPanel labels = new JPanel();
		List<Metal> metalList = new ArrayList<Metal>();
		try {
			metalList = new MetalFilterCommand("12").execute();
		} catch (DomainModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		labels.setLayout(new GridLayout(metalList.size(), 1));
		
		for(int i = 0; i < metalList.size(); i++) {
		      final int x = i;
		      final Metal m = metalList.get(i);
		      JLabel label = new JLabel(buildHtml(m));
		      label.setOpaque(true);
		      
		      label.setBackground(new Color(30, 30, 30));
		      
		      label.addMouseListener( new MouseAdapter() {
		          @Override
		          public void mouseClicked(MouseEvent e) {
		              if(selectedMetals.contains(m)) {
		            	  selectedMetals.remove(m);
		            	  label.setBackground(new Color(30, 30, 30));
		              }
		              else {
		            	  selectedMetals.add(m);
		            	  label.setBackground(new Color(234, 201, 55));
		              }
		          }
		      }); 
		      labels.add(label, i, 0);
		    }

		return labels;
	}
    
	private String buildHtml(Metal metal) {
		return "<html><p style=\"color:white;\">" + metal.getName() + "</p></html>";
	}
	private JPanel buildLabelsBase() {
		JPanel labels = new JPanel();
		List<Chemical> baseList = new ArrayList<Chemical>();
		try {
			baseList = (List<Chemical>) new ChemicalFilterCommand("4").execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		labels.setLayout(new GridLayout(baseList.size(), 1));
		
		for(int i = 0; i < baseList.size(); i++) {
		      final int x = i;
		      final Chemical b = baseList.get(x);
		      JLabel label = new JLabel(buildHtml(baseList.get(i)));
		      label.setOpaque(true);
		      
		      label.setBackground(new Color(30, 30, 30));
		      
		      label.addMouseListener( new MouseAdapter() {
		          @Override
		          public void mouseClicked(MouseEvent e) {
		        	  if(selectedBase != null)
		        		  selectedLabel.setBackground(new Color(30, 30, 30));
		              label.setBackground(new Color(234, 201, 55));
		              selectedLabel = label;
		              selectedBase = b;
		          }
		      }); 
		      labels.add(label, i, 0);
		    }

		return labels;
	}
    
	private String buildHtml(Chemical base) {
		return "<html><p style=\"color:white;\">" + base.getName() + "</p></html>";
	}
	
}
