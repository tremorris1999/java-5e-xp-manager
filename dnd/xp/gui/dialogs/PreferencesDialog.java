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
import dnd.xp.gui.events.PreferencesEvent;
import dnd.xp.gui.interfaces.PreferencesListener;

public class PreferencesDialog extends JDialog
{
	PreferencesListener preferencesListener;

	private JLabel inPathLabel;
	private JLabel outPathLabel;
	public JTextField inPathField;
	public JTextField outPathField;
	private JButton saveBtn;

	public PreferencesDialog(final MainFrame frame)
	{
		// init
		super(frame, "Preferences");
		inPathLabel = new JLabel("\tSet input file by path: ");
		inPathField = new JTextField(20);
		outPathLabel = new JLabel("\tSet output file by path: ");
		outPathField = new JTextField(20);
		saveBtn = new JButton("Save");

		// listener
		saveBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String in = inPathField.getText();
				String out = outPathField.getText();
				PreferencesEvent event = new PreferencesEvent(this, in, out);
				if (preferencesListener != null)
					preferencesListener.PreferenceEventOccured(event);
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
		add(inPathLabel, gc);

		// r1c2
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(inPathField, gc);

		// row 2
		// r2c1
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 10, 0, 0);
		add(outPathLabel, gc);

		// r2c2
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(outPathField, gc);

		// r3c1
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridx = 1;
		gc.gridy = 2;
		add(saveBtn, gc);


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

	public void updatePaths(final String pathIn, final String pathOut)
	{
		inPathField.setText(pathIn);
		outPathField.setText(pathOut);
	}

	public void setPreferencesListener(final PreferencesListener listener)
	{
		preferencesListener = listener;
	}
}
