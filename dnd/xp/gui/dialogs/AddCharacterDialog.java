package dnd.xp.gui.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dnd.xp.gui.MainFrame;
import dnd.xp.gui.events.AddCharacterEvent;
import dnd.xp.gui.interfaces.AddCharacterListener;

public class AddCharacterDialog extends JDialog
{
	AddCharacterListener addCharacterListener;

	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel levelLabel;
	private JTextField levelField;
	private JLabel xpLabel;
	private JTextField xpField;
	private JButton addBtn;

	public AddCharacterDialog(final MainFrame frame)
	{
		// init
		super(frame, "Add Character");
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(10);
		levelLabel = new JLabel("Level");
		levelField = new JTextField(10);
		xpLabel = new JLabel("XP:");
		xpField = new JTextField(10);
		addBtn = new JButton("Add");

		// listener
		addBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String name = nameField.getText();
				try {
					int level = Integer.parseInt(levelField.getText());
					double xp = Double.parseDouble(xpField.getText());
					AddCharacterEvent event = new AddCharacterEvent(this, name, level, xp);
					if (addCharacterListener != null)
						addCharacterListener.addCharacterEventOccured(event);
				} catch (Exception error) { frame.getTrackerPanel().log("Failed to create character.\n"); AddCharacterDialog.this.setVisible(false); AddCharacterDialog.this.clearFields();}

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
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(nameLabel, gc);

		// r1c2
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// row 2
		// r2c1
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 10, 0, 0);
		add(levelLabel, gc);

		// r2c2
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(levelField, gc);

		// row 3
		// r3c1
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 10, 0, 0);
		add(xpLabel, gc);

		// r3c2
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(xpField, gc);

		// r4c1
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridx = 1;
		gc.gridy = 3;
		add(addBtn, gc);

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

	public void setAddCharacterListener(final AddCharacterListener listener)
	{
		this.addCharacterListener = listener;
	}

	public void clearFields()
	{
		nameField.setText("");
		levelField.setText("");
		xpField.setText("");
	}
}
