package dnd.xp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import dnd.xp.FileToolbox;
import dnd.xp.LinkedList;
import dnd.xp.LinkedList.Node;
import dnd.xp.gui.dialogs.AddCharacterDialog;
import dnd.xp.gui.dialogs.PreferencesDialog;
import dnd.xp.gui.dialogs.RemoveCharacterDialog;
import dnd.xp.gui.events.AddCharacterEvent;
import dnd.xp.gui.events.OperationsEvent;
import dnd.xp.gui.events.PreferencesEvent;
import dnd.xp.gui.events.RemoveCharacterEvent;
import dnd.xp.gui.events.ToolbarEvent;
import dnd.xp.gui.interfaces.AddCharacterListener;
import dnd.xp.gui.interfaces.OperationsListener;
import dnd.xp.gui.interfaces.PreferencesListener;
import dnd.xp.gui.interfaces.RemoveCharacterListener;
import dnd.xp.gui.interfaces.ToolbarListener;

public class MainFrame extends JFrame
{
	// files
	public static String prefPath;
	public static String prefPathOut;

	// globals
	private LinkedList characterList;
	private TrackerPanel trackerPanel;
	private ToolbarPanel toolbarPanel;
	private OperationsPanel operationsPanel;
	private PreferencesDialog preferences;
	private AddCharacterDialog addCharacter;
	private RemoveCharacterDialog removeCharacter;

	public MainFrame() throws IOException
	{
		// init
		super("5e XP Manager");
		prefPath = FileToolbox.getPrefPathIn();
		prefPathOut = FileToolbox.getPrefPathOut();
		try { characterList = FileToolbox.buildCharacterList(new File(prefPath));} catch (FileNotFoundException e1) { e1.printStackTrace(); }
		trackerPanel = new TrackerPanel();
		toolbarPanel = new ToolbarPanel();
		operationsPanel = new OperationsPanel();
		operationsPanel.setCharacterList(characterList);
		operationsPanel.fillList();
		preferences = new PreferencesDialog(this);
		addCharacter = new AddCharacterDialog(this);
		removeCharacter = new RemoveCharacterDialog(this);
		removeCharacter.setCharList(characterList);
		removeCharacter.update();
		trackerPanel.update(characterList);

		// listeners
		toolbarPanel.setToolbarListener(new ToolbarListener() {
			@Override public void ToolbarEventOccured(final ToolbarEvent e) {
				String command = e.getCommand();
				// menu 1
				if (command.trim().equalsIgnoreCase("Save"))
				{
					trackerPanel.log("Saving character list.\n");
					try {
						FileToolbox.save(new File(prefPath), characterList);
						FileToolbox.save(new File(prefPathOut), characterList);
					}
					catch (Exception error) { trackerPanel.log("Failed to save\n"); }
				}
				if (command.trim().equalsIgnoreCase("Open"))
				{
					trackerPanel.log("Opening a different character list (.txt): ");
					LinkedList newList = characterList;
					try { newList = FileToolbox.buildCharacterList(FileToolbox.openFile(MainFrame.this)); } catch (FileNotFoundException e1) { e1.printStackTrace(); }
					characterList = newList;
					trackerPanel.update(characterList);
				}
				if (command.trim().equalsIgnoreCase("Exit"))
				{
					MainFrame.this.dispatchEvent(new WindowEvent(MainFrame.this, WindowEvent.WINDOW_CLOSING));
				}
				// menu 2
				if (command.trim().equalsIgnoreCase("Add Character"))
				{
					addCharacter.setVisible(true);
				}
				if (command.trim().equalsIgnoreCase("Remove Character"))
				{
					removeCharacter.setVisible(true);
				}
				if (command.trim().equalsIgnoreCase("Preferences"))
				{
					preferences.setVisible(true);
					preferences.updatePaths(prefPath, prefPathOut);
				}
			}
		});

		preferences.setPreferencesListener(new PreferencesListener() {
			@Override public void PreferenceEventOccured(final PreferencesEvent e) {
				preferences.setVisible(false);
				try {
					FileToolbox.setPrefPathIn(e.getIn());
					FileToolbox.setPrefPathOut(e.getOut());
					prefPath = FileToolbox.getPrefPathIn();
					prefPathOut = FileToolbox.getPrefPathOut();
				} catch (IOException e1) { e1.printStackTrace(); }
			}
		});

		addCharacter.setAddCharacterListener(new AddCharacterListener() {
			@Override public void addCharacterEventOccured(final AddCharacterEvent e) {
				addCharacter.setVisible(false);
				characterList.addFirst(new dnd.xp.characters.Character(e.getName(), e.getLevel(), e.getXp()));
				trackerPanel.update(characterList);
				addCharacter.clearFields();
				operationsPanel.update();
			}
		});

		operationsPanel.setOperationsListener(new OperationsListener() {
			@Override public void operationsEventOccured(final OperationsEvent e) {
				List<String> selection = e.getSelected();
				if (characterList.getSize() != 0)
				{
					if (selection.get(0) == "All")
					{
						for (Node cur = characterList.head.next; cur != null; cur = cur.next)
						{
							cur.character.addXP(e.getXp());
						}
					}
					else
					{
						for(Node cur = characterList.head.next; cur != null; cur = cur.next)
						{
							for (String s : selection)
							{
								if(cur.character.getName() == s)
								{
									cur.character.addXP(e.getXp());
								}
							}
						}
					}
				}
				trackerPanel.update(characterList);
				operationsPanel.update();
			}
		});

		removeCharacter.setRemoveCharacterListener(new RemoveCharacterListener() {
			@Override public void removeCharacterEventOccured(final RemoveCharacterEvent e) {
				String name = e.getDelName();
				if (characterList.getSize() != 0)
				{
					for (Node cur = characterList.head.next; cur != null; cur = cur.next)
					{
						if (cur.character.getName() == name)
						{
							characterList.remove(cur);
						}
					}
				}
				trackerPanel.update(characterList);
				operationsPanel.update();
				removeCharacter.update();
			}
		});

		// layout
		setLayout(new BorderLayout());

		// adds
		add(trackerPanel, BorderLayout.CENTER);
		add(toolbarPanel, BorderLayout.NORTH);
		add(operationsPanel, BorderLayout.WEST);

		// aesthetics
		getContentPane().setBackground(Color.BLACK);

		// pre-run
		setLocation(300, 200);
		setSize(960, 540);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public TrackerPanel getTrackerPanel()
	{
		return this.trackerPanel;
	}

	public LinkedList getCharacterList()
	{
		return characterList;
	}
}
