package dnd.xp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dnd.xp.LinkedList.Node;
import dnd.xp.gui.events.OperationsEvent;
import dnd.xp.gui.interfaces.OperationsListener;

public class OperationsPanel extends JPanel
{
	private TrackerPanel tp;
	private OperationsListener opListener;
	private dnd.xp.LinkedList cList;
	private JLabel xpLabel;
	private JTextField xpField;
	private JButton addBtn;
	@SuppressWarnings("rawtypes")
	private JList selectionList;
	@SuppressWarnings("rawtypes")
	private DefaultListModel charMod;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OperationsPanel()
	{
		// init
		xpLabel = new JLabel("Add XP: ");
		xpField = new JTextField(10);
		addBtn = new JButton("Add");
		selectionList = new JList();

		charMod = new DefaultListModel();
		charMod.addElement("All");

		// listener
		addBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e ) {
				List selection = new ArrayList();
				double xp = 0;
				try {xp = Double.parseDouble(xpField.getText()); selection = selectionList.getSelectedValuesList(); } catch (Exception err) {xp = 0; selection.add("All"); tp.log("Failed to add xp.");}
				OperationsEvent event = new OperationsEvent(this, selection, xp);
				if (opListener != null)
					opListener.operationsEventOccured(event);
			}
		});

		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// row 1
		// r1c1
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(xpLabel, gc);

		// r1c2
		gc.weightx = 1;
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(xpField, gc);

		// r2c1
		gc.weighty = 0.5;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(selectionList, gc);

		// r3c1
		gc.weighty = 3;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addBtn, gc);


		// aesthetics
		Border outer = BorderFactory.createBevelBorder(1, Color.WHITE,  Color.BLACK);
		Border inner = BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 1, 5), new TitledBorder(null, "Operation:", 0, 0, new Font("Arial", Font.PLAIN, 12), Color.BLACK));
		setBorder(BorderFactory.createCompoundBorder(outer, inner));
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);

	}

	public void setCharacterList(final dnd.xp.LinkedList list)
	{
		cList = list;
	}

	public void setOperationsListener(final OperationsListener listener)
	{
		this.opListener = listener;
	}

	@SuppressWarnings("unchecked")
	public void fillList()
	{
		int i = 0;
		for (Node cur = cList.head; cur != null; cur = cur.next)
		{
			if (cur.character != null)
				charMod.addElement(cur.toString());
			i++;
		}
		selectionList.setModel(charMod);
		selectionList.setPreferredSize(new Dimension(150, 18 * i));
		selectionList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		selectionList.setSelectedIndex(0);
		selectionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	public void setTrackerPanel(final TrackerPanel tp)
	{
		this.tp = tp;
	}

	@SuppressWarnings("unchecked")
	public void update()
	{
		xpField.setText("");
		charMod.removeAllElements();
		charMod.addElement("All");
		fillList();
	}
}
