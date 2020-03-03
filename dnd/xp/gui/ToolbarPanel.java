package dnd.xp.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dnd.xp.gui.events.ToolbarEvent;
import dnd.xp.gui.interfaces.ToolbarListener;

public class ToolbarPanel extends JPanel
{
	ToolbarListener toolbarListener;

	JMenuBar menuBar;

	JMenu menu1;
	JMenuItem menuItem1_1;
	JMenuItem menuItem1_2;
	JMenuItem menuItem1_3;

	JMenu menu2;
	JMenuItem menuItem2_1;
	JMenuItem menuItem2_2;
	JMenuItem menuItem2_3;

	public ToolbarPanel()
	{
		// init
		menuBar = new JMenuBar();

		// menu 1
		menu1 = new JMenu("File");
		menuItem1_1 = new JMenuItem("Save");
		menuItem1_2 = new JMenuItem("Open");
		menuItem1_3 = new JMenuItem("Exit");

		// menu 2
		menu2 = new JMenu("Edit");
		menuItem2_1 = new JMenuItem("Add Character");
		menuItem2_2 = new JMenuItem("Remove Character");
		menuItem2_3 = new JMenuItem("Preferences");

		// layout
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);

		// adds
		add(menuBar);

		// menu management
		menuBar.add(menu1);
		menuBar.add(menu2);
		// menu 1
		menu1.add(menuItem1_1);
		menu1.add(menuItem1_2);
		menu1.add(menuItem1_3);
		// menu 2
		menu2.add(menuItem2_1);
		menu2.add(menuItem2_2);
		menu2.add(menuItem2_3);

		// listeners
		menuItem1_1.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String command = menuItem1_1.getText();
				ToolbarEvent event = new ToolbarEvent(this, command);
				if (toolbarListener != null)
					toolbarListener.ToolbarEventOccured(event);
			}
		});
		menuItem1_2.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String command = menuItem1_2.getText();
				ToolbarEvent event = new ToolbarEvent(this, command);
				if (toolbarListener != null)
					toolbarListener.ToolbarEventOccured(event);
			}
		});
		menuItem1_3.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String command = menuItem1_3.getText();
				ToolbarEvent event = new ToolbarEvent(this, command);
				if (toolbarListener != null)
					toolbarListener.ToolbarEventOccured(event);
			}
		});
		menuItem2_1.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String command = menuItem2_1.getText();
				ToolbarEvent event = new ToolbarEvent(this, command);
				if (toolbarListener != null)
					toolbarListener.ToolbarEventOccured(event);
			}
		});
		menuItem2_2.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String command = menuItem2_2.getText();
				ToolbarEvent event = new ToolbarEvent(this, command);
				if (toolbarListener != null)
					toolbarListener.ToolbarEventOccured(event);
			}
		});
		menuItem2_3.addActionListener(new ActionListener() {
			@Override public void actionPerformed(final ActionEvent e) {
				String command = menuItem2_3.getText();
				ToolbarEvent event = new ToolbarEvent(this, command);
				if (toolbarListener != null)
					toolbarListener.ToolbarEventOccured(event);
			}
		});
	}

	public void setToolbarListener(final ToolbarListener listener)
	{
		this.toolbarListener = listener;
	}
}