package dnd.xp.gui.events;

import java.util.EventObject;

public class AddCharacterEvent extends EventObject
{
	private String name;
	private int level;
	private double xp;

	public AddCharacterEvent(final Object source)
	{
		super(source);
	}

	public AddCharacterEvent(final Object source, final String name, final int level, final double xp)
	{
		super(source);
		this.name = name;
		this.level = level;
		this.xp = xp;
	}

	public String getName()
	{
		return name;
	}

	public int getLevel()
	{
		return level;
	}

	public double getXp()
	{
		return xp;
	}
}
