package stringtree;

public class Node {

	private final String value;

	private Node left;

	private Node right;

	Node(String value) {
		this.value = value;
		right = null;
		left = null;
	}

	public String getValue() {
		return value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
