package dnd.xp.gui.interfaces;

import java.util.EventListener;

import dnd.xp.gui.events.ToolbarEvent;

public interface ToolbarListener extends EventListener
{
	public void ToolbarEventOccured(ToolbarEvent e);
}
