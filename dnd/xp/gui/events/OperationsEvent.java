package dnd.xp.gui.events;

import java.util.EventObject;
import java.util.List;

public class OperationsEvent extends EventObject
{
	private List<String> selected;
	private double xp;

	public OperationsEvent(final Object source)
	{
		super(source);
	}

	public OperationsEvent(final Object source, final List<String> selected, final double xp)
	{
		super(source);
		this.selected = selected;
		this.xp = xp;
	}

	public List<String> getSelected()
	{
		return this.selected;
	}

	public double getXp()
	{
		return this.xp;
	}
}
