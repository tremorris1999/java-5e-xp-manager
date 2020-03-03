package dnd.xp.gui.interfaces;

import java.util.EventListener;

import dnd.xp.gui.events.AddCharacterEvent;

public interface AddCharacterListener extends EventListener
{
	public void addCharacterEventOccured(AddCharacterEvent e);
}
