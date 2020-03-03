package dnd.xp;

public class LinkedList
{
	public class Node
	{
		public dnd.xp.characters.Character character;
		public Node next;
		public Node prev;

		private Node(final dnd.xp.characters.Character character)
		{
			this.character = character;
			this.next = null;
			this.prev = null;
		}

		@Override
		public String toString()
		{
			if (this.character != null)
			{
				return this.character.toString();
			}
			else
			{
				return "null";
			}
		}
	}

	public Node head;
	private int size;

	public LinkedList()
	{
		this.head = new Node(null);
	}

	public void addFirst(final dnd.xp.characters.Character character)
	{
		Node nn = new Node(character);
		if (this.size == 0)
		{
			this.head.next = nn;
			nn.prev = this.head;
			this.size++;
		}
		else
		{
			nn.next = this.head.next;
			nn.prev = this.head;
			this.head.next.prev = nn;
			this.head.next = nn;
			this.size++;
		}
	}

	public int getSize()
	{
		return this.size;
	}

	public Node findNode (final String name)
	{
		Node cur;
		for (cur = head; cur.next != null; cur = cur.next)
		{
			if (cur.character.getName() == name)
			{
				return cur;
			}
		}
		return null;
	}

	public void remove(Node d)
	{
		if (size == 0)
			return;
		else if (size == 1)
		{
			head.next = null;
		}
		else
		{
			d.prev.next = d.next;
			d.next.prev = d.prev;
			d = null;
			size--;
		}
	}
}
