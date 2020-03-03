package dnd.xp.gui;

import java.io.IOException;

public class GUI implements Runnable
{
	@Override
	public void run()
	{
		try { new MainFrame(); } catch (IOException e) { e.printStackTrace();
		}
	}
}
