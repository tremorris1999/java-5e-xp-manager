package dnd.xp.gui.events;

import java.util.EventObject;

public class RemoveCharacterEvent extends EventObject
{
	private String name;

	public RemoveCharacterEvent (final Object source)
	{
		super(source);
	}

	public RemoveCharacterEvent (final Object source, final String name)
	{
		super(source);
		this.name = name;
	}

	public String getDelName()
	{
		return name;
	}
}
