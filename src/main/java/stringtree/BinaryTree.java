package stringtree;

public class BinaryTree {

	private Node node;

	public void insert(String value) {

		node = insert(node, value);

	}

	private Node insert(Node current, String value) {

		if (current == null) {
			return new Node(value);
		}

		if (value.compareTo(current.getValue()) <= -1) {
			current.setLeft(insert(current.getLeft(), value));
		} else if (value.compareTo(current.getValue()) >= 1) {
			current.setRight(insert(current.getRight(), value));
		} else {
			return current;
		}

		return current;
	}

	public Node find(String value) {
		return find(node, value);
	}

	private Node find(Node node, String value) {

		if (node == null || value == null) {
			return null;
		}

		if(node.getValue().contains(value)) {
			return node;
		} else if (value.compareTo(node.getValue()) <= -1) {
			return find(node.getLeft(), value);
		} else {
			return find(node.getRight(), value);
		}

	}

}
