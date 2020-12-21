public abstract class BasicTree<T> {
	protected Node<T> Head;
	protected int height;

	public abstract void display();

	public abstract void display(String my_file);

	public int getheight() {
		//return number of nodes in three
		return height;
	}

	public Node<T> gethead() {
		//return head
		return Head;
	}

	public T getheadvalue() {
		//return head value
		return Head.getvalue();
	}

	public void display_prefix() {
		display_helper(Head);
	}

	private void display_helper(Node<T> curr) {
		if ((curr.HasLeft() == false) && (curr.HasRight() == false)) {
			System.out.print(curr);
			return;
		}
		else {
			System.out.print(curr);
			if (curr.HasLeft() == true) {
				display_helper(curr.getleft());
			}
			if (curr.HasRight() == true) {
				display_helper(curr.getright());
			}
		}
	}
}
