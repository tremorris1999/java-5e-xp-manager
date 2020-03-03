package dnd.xp.gui.interfaces;

import java.util.EventListener;

import dnd.xp.gui.events.OperationsEvent;

public interface OperationsListener extends EventListener
{
	public void operationsEventOccured(OperationsEvent e);
}
