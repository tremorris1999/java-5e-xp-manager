package dnd.xp.gui.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import dnd.xp.LinkedList;
import dnd.xp.LinkedList.Node;
import dnd.xp.gui.MainFrame;
import dnd.xp.gui.events.RemoveCharacterEvent;
import dnd.xp.gui.interfaces.RemoveCharacterListener;

public class RemoveCharacterDialog extends JDialog
{
	RemoveCharacterListener remListener;

	private LinkedList charList;
	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel charModel;
	private JLabel nameLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox charCombo;
	private JButton delBtn;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RemoveCharacterDialog(final MainFrame frame)
	{
		// init
		super(frame, "Remove Character");
		nameLabel = new JLabel("Character:");
		delBtn = new JButton("Delete");
		charCombo = new JComboBox();

		charModel = new DefaultComboBoxModel();
		charCombo.setModel(charModel);

		// listener
		delBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String name = "";
				try { name = (String) charCombo.getSelectedItem(); } catch (Exception err) { frame.getTrackerPanel().log("Failed to remove character."); }
				RemoveCharacterEvent event = new RemoveCharacterEvent(this, name);
				if (remListener != null)
					remListener.removeCharacterEventOccured(event);
			}
		});

		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// row 1
		// r1c1
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 20, 0, 0);
		add(nameLabel, gc);

		// r1c2
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(charCombo, gc);

		// row 2
		// r2c1
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(delBtn, gc);

		// pre-run
		setSize(400, 300);
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(saveAndClose());
		setVisible(false);
	}

	public int saveAndClose()
	{
		setVisible(false);
		return 1;
	}

	public void setRemoveCharacterListener(final RemoveCharacterListener listener)
	{
		this.remListener = listener;
	}

	public void setCharList(final LinkedList list)
	{
		this.charList = list;
	}

	@SuppressWarnings("unchecked")
	public void update()
	{
		charModel.removeAllElements();
		if (charList.getSize() != 0);
		{
			for (Node cur = charList.head.next; cur != null; cur = cur.next)
			{
				charModel.addElement(cur.character.getName());
			}
		}
		charCombo.setModel(charModel);
	}
}
