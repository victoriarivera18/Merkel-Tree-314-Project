
public class Node<T> {
	//Used to determine if Node as children of not;
	//Simular to left = null and right = null
	private Boolean leftchildren;
	private Boolean rightchildren;
	//Index is value of node
	private T value;
	private Node<T> left;
	private Node<T> right;

	public Node() {}
	//default constuctor

	public Node(T val, Node<T> l, Node<T> r) {
		//constuctor which takes in value, left and right nodes
		this.leftchildren = true;
		this.rightchildren = true;
		this.value = val;
		this.left = l;
		this.right = r;
	}

	public Node(T val, Node<T> x, String Side) {
		//constuctor takes in only value and one node
		if (Side == "LEFT") {
			leftchildren = true;
			rightchildren = false;
			left = x;
		}
		else if (Side == "RIGHT") {
			leftchildren = false;
			rightchildren = true;
			right = x;
		}
		else {
			this.leftchildren = false;
			this.rightchildren = false;
			this.value = val;
		}
	}

	public Node(T val) {
		//constuctor takes in only one vaule; assuming no children nodes
		this.leftchildren = false;
		this.rightchildren = false;
		this.value = val;
	}

	public T getvalue() {
		return value;
	}

	public Node<T> getleft() {
		//returns left node
		return left;
	}

	public Node<T> getright() {
		//returns right node
		return right;
	}

	public Boolean HasLeft() {
		//true if node has left child else false
		return leftchildren;
	}

	public Boolean HasRight() {
		//true if node has right child else false
		return rightchildren;
	}

	public String toString() {
		String temp;
		temp = "(Node Value: " + value + ")";
		return temp;
	}
}
