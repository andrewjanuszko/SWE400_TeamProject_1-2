package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import model.AcidDataMapper;
import model.BaseDataMapper;
import model.Base;
import model.BaseDataMapper;

public class BasePanel extends JPanel {

  JScrollPane bases = new JScrollPane();
  GridBagConstraints gbc = new GridBagConstraints();
  JLabel selected = null;
  Base selectedBase = null;
  List<Base> baseList;
  JPanel basePanel = null;

  public BasePanel() {
    setBackground(Color.DARK_GRAY);
    setLayout(new GridLayout(1, 2)); 
    
    basePanel = genBasePanel(); 
    add(basePanel); 
  }
  
  private JPanel genBasePanel() {
    JPanel panel = new JPanel(); // Entire panel, holding header, panel of bases, buttons
    panel.setBackground(Color.DARK_GRAY);
    panel.setLayout(new BorderLayout()); // Borderlayout bc im lazy
    
    // set dimensions
    Dimension d = new Dimension(450, 650);
    panel.setMinimumSize(d);
    panel.setPreferredSize(d);
    panel.setMaximumSize(d);
    
    // scrollbar
    JScrollPane scroll = new JScrollPane(); 
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.add(scroll.createVerticalScrollBar());
    scroll.getVerticalScrollBar().setUnitIncrement(20);
    
    // Panel of bases
    JPanel bases = new JPanel();
    bases.setBackground(Color.LIGHT_GRAY);
    bases.setLayout(new GridLayout(baseList.size(), 1));
    
    // refresh list
    baseList = new BaseDataMapper().getAll(); 
    
    // header
    panel.add(new JLabel("Bases: ")); 
    
    // add all labels of bases
    for(int i = 0; i < baseList.size(); i++) {
      final int x = i; 
      
      JLabel label = new JLabel(baseList.get(i).getName(), SwingConstants.CENTER);
      label.setOpaque(true);
      label.setBackground(Color.LIGHT_GRAY);
      label.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          removeSelectedBackground(); // Remove old selected
          label.setBackground(Color.yellow); // Indiciate it was selected
          selected = label; // Store JLabel for changing later
          selectedBase = baseList.get(x); // Store to pass into functions
          refresh(); // show changes
        }
      });
      bases.add(label);
    }
    
    // Add scrollbar
    scroll.setViewportView(bases);
    panel.add(bases, BorderLayout.CENTER);
//    panel.add(addButtons(), BorderLayout.SOUTH);
   
    return null;
  }
  
  private void refresh() {
    
  }


  private void removeSelectedBackground() {
    if (selected != null)
      selected.setBackground(Color.LIGHT_GRAY);
  }

}
