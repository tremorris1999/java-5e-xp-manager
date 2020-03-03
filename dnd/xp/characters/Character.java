package dnd.xp.characters;

public class Character
{
	public String name;
	public int level;
	public double xp;
	public static final double[] thresholds = {Double.NEGATIVE_INFINITY, (0 - Double.MAX_VALUE), 0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000, 100000, 120000, 140000, 165000, 195000, 225000, 265000, 305000, 355000, Double.MAX_VALUE, Double.POSITIVE_INFINITY};

	public Character() //DVC
	{
		this.name = null;
		this.level = 1;
		this.xp = 0;
	}

	public Character(final String name, final int level, final double xp) //EVC
	{
		this.name = name;
		this.level = level;
		this.xp = xp;
	}

	public void addXP(final double xp)
	{
		double newXP = this.xp + xp;
		if (newXP >= Character.thresholds[this.level + 1] && this.xp != Double.POSITIVE_INFINITY)
		{
			this.level++;
		}
		this.setXp(newXP);
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public double getXp()
	{
		return xp;
	}

	public void setXp(final double xp)
	{
		this.xp = xp;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
}
