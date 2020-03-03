package dnd.xp.gui.interfaces;

import java.util.EventListener;

import dnd.xp.gui.events.PreferencesEvent;

public interface PreferencesListener extends EventListener
{
	public void PreferenceEventOccured(PreferencesEvent e);
}
