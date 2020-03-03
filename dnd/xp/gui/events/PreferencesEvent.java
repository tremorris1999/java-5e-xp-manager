package dnd.xp.gui.events;

import java.util.EventObject;

public class PreferencesEvent extends EventObject
{
	private String in;
	private String out;

	public PreferencesEvent(final Object source)
	{
		super(source);
	}

	public PreferencesEvent(final Object source, final String in, final String out)
	{
		super(source);
		this.in = in;
		this.out = out;
	}

	public String getIn()
	{
		return this.in;
	}

	public String getOut()
	{
		return this.out;
	}

}
