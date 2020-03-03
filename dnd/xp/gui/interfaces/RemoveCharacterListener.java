package dnd.xp.gui.interfaces;

import java.util.EventListener;

import dnd.xp.gui.events.RemoveCharacterEvent;

public interface RemoveCharacterListener extends EventListener
{
	public void removeCharacterEventOccured(RemoveCharacterEvent e);
}