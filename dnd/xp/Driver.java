package dnd.xp;

import java.io.File;

import javax.swing.SwingUtilities;

import dnd.xp.gui.GUI;

public class Driver
{
	public static void main(final String[] args) throws Exception
	{
		GUI gui = new GUI();
		FileToolbox.defaultPathSetup();
		File inFile = new File(FileToolbox.getPrefPathIn());
		inFile.createNewFile();
		FileToolbox.generateReadMe();
		SwingUtilities.invokeLater(gui);
	}
}
