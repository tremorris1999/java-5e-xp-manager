package dnd.xp.gui.events;

import java.util.EventObject;

public class ToolbarEvent extends EventObject
{
	private String command;

	public ToolbarEvent(final Object source)
	{
		super(source);
	}

	public ToolbarEvent(final Object source, final String command)
	{
		super(source);
		this.command = command;
	}

	public String getCommand()
	{
		return command;
	}

	public void setCommand(final String command)
	{
		this.command = command;
	}
}
