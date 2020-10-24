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

import model.Acid;
import model.BaseDataMapper;
import model.Chemical;

public class ChemicalPanel extends JPanel{
	
  JScrollPane chemicals = new JScrollPane();
  GridBagConstraints gbc = new GridBagConstraints();
  JButton filterButton = new JButton("Filter");
  JButton detailsButton = new JButton("Details");
  JLabel selected = null;
  Color labelColor = new Color(30, 30, 30);
  List<Chemical> chemicallList;
	
  public ChemicalPanel() {
    this.setLayout(new GridBagLayout());
    addScrollPane();
    setButtons();
  }

  private void addScrollPane() {
    chemicals.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    chemicals.add(chemicals.createVerticalScrollBar());

    chemicals.setViewportView(Labels());
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = Integer.MAX_VALUE;
    gbc.fill = GridBagConstraints.BOTH;
    add(chemicals, gbc);
  }

  private JLabel Labels() {
    JLabel label = new JLabel();
    label.setBackground(Color.WHITE);
    label.setOpaque(true);
    label.setPreferredSize(new Dimension(100, 20));
    return label;
  }

  private void setButtons() {
    filterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        filterAcid();
      }
    });
    detailsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        getDetailAcid();
      }
    });
    JPanel buttons = new JPanel(new GridBagLayout());
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0;
    gbc.gridy = 0;

    gbc.gridx = 0;
    buttons.add(filterButton, gbc);

    gbc.gridx = 1;
    buttons.add(detailsButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.SOUTHWEST;
    gbc.weighty = 1;
    buttons.setBackground(Color.GRAY);

    add(buttons, gbc);

  }

  private void filterAcid() {
    if (selected != null) {
      // brings up new window based on selected metal
      new FilterMetalFrame().addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent arg0) {
          // reset the view
        }
      });
    }
  }

  private void getDetailAcid() {
    if (selected == null) {
      return;
    }
  }

  private void removeSelectedBackground() {
    if (selected != null)
      selected.setBackground(labelColor);
  }

  private JPanel buildLabels() {
    JPanel labels = new JPanel();

    return labels;
  }
}
