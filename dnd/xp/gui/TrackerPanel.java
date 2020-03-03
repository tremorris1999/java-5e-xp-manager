package dnd.xp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dnd.xp.LinkedList.Node;

public class TrackerPanel extends JPanel
{
	private JTextArea textArea;
	public JTextArea textArea2;

	public TrackerPanel()
	{
		// text area
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane JSP = new JScrollPane(textArea);
		JSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//text area 2
		textArea2 = new JTextArea();
		textArea2.setEditable(false);
		JScrollPane JSP2 = new JScrollPane(textArea2);
		JSP2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JSP2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// aesthetics
		Border outer = BorderFactory.createBevelBorder(1, Color.WHITE, Color.BLACK);
		Border head1 = BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 5), BorderFactory.createTitledBorder("Character Tracker:"));
		Border head2 = BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 1, 5), new TitledBorder(null, "Log:", 0, 0, new Font("Arial", Font.PLAIN, 12), Color.WHITE));
		textArea.setBorder(BorderFactory.createCompoundBorder(outer, head1));
		textArea2.setBorder(BorderFactory.createCompoundBorder(outer, head2));
		Dimension dim = getPreferredSize();
		dim.height = 100;
		JSP2.setPreferredSize(dim);


		// layout
		setLayout(new BorderLayout());

		// added to layout
		add(JSP, BorderLayout.CENTER);
		add(JSP2, BorderLayout.SOUTH);

		// aesthetics
		Color gray = new Color(160, 160, 160);
		textArea.setBackground(gray);
		textArea2.setForeground(Color.WHITE);
		textArea2.setBackground(Color.BLACK);

	}

	public void write(final String text)
	{
		textArea.append(text);
	}

	public void log(final String text)
	{
		textArea2.append(text);
	}

	public void clear()
	{
		textArea.setText("");
	}

	public void update(final dnd.xp.LinkedList characters)
	{
		clear();
		if (characters.getSize() == 0)
		{
			return;
		}
		Node cur;
		for (cur = characters.head; cur.next != null; cur = cur.next){}
		for (; cur != characters.head; cur = cur.prev)
		{
			textArea.setFont(new Font("Arial", Font.BOLD, 16));
			write(cur.character.getName() + "\t-\tLevel " + cur.character.getLevel() + "\t-\t" + cur.character.getXp() + " XP\r\n\n");
		}
	}
}
