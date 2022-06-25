package exercise3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree {

	public static final int MAX_HEIGHT = 2;
	private Node node;

	public void insert(int value) {

		node = insert(node, value);

		if (!isBalanced()) {
			rebalance();
			System.out.println("Not Balanced");
		} else {
			System.out.println("Balanced");
		}

	}

	private Node insert(Node current, int value) {

		if (current == null) {
			return new Node(value);
		}

		if (value < current.getValue()) {
			current.setLeft(insert(current.getLeft(), value));
		} else if (value > current.getValue()) {
			current.setRight(insert(current.getRight(), value));
		} else {
			return current;
		}

		return current;
	}

	private Boolean isBalanced() {
		return isBalanced(node, new HeightCount());
	}

	private Boolean isBalanced(Node node, HeightCount heightCount) {

		if (node == null) {
			heightCount.setCount(0L);
			return true;
		}

		HeightCount leftHeighteight = new HeightCount();
		HeightCount rightHeighteight = new HeightCount();

		boolean isLeftBalanced = isBalanced(node.getLeft(), leftHeighteight);
		boolean isRightBalanced = isBalanced(node.getRight(), rightHeighteight);

		long leftHeight = leftHeighteight.getCount();
		long rightHeight = rightHeighteight.getCount();

		heightCount.setCount((Math.max(leftHeight, rightHeight)) + 1L);

		if ((leftHeight - rightHeight >= MAX_HEIGHT) || (rightHeight - leftHeight >= MAX_HEIGHT)) {
			return false;
		} else {
			return isLeftBalanced && isRightBalanced;
		}
	}
	private void rebalance() {

		final List<Node> nodes = new ArrayList<>();
		insertTreeToList(node, nodes);

		nodes.sort(Comparator.comparing(Node::getValue));

		buildTree(nodes, 0, nodes.size() - 1);

	}

	public Node buildTree(List<Node> nodes, Integer start, Integer end) {

		if (start > end) {
			return null;
		}

		int mid = (start + end)/2;

		var node = nodes.get(mid);

		node.setLeft(buildTree(nodes, start, mid - 1));
		node.setRight(buildTree(nodes, mid + 1, end));

		return node;

	}

	private void insertTreeToList (Node node, List<Node> nodes) {

		if (node == null)
			return;

		nodes.add(node);
		insertTreeToList(node.getLeft(), nodes);
		insertTreeToList(node.getRight(), nodes);

	}

	public Node getNode() {
		return node;
	}
}
