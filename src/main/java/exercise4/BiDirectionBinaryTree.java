package exercise4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BiDirectionBinaryTree {

	public static final int MAX_HEIGHT = 2;
	private BiDirectionalNode node;

	public void insert(int value) {

		node = insert(node, node, value);

		if (!isBalanced()) {
			System.out.println("Not Balanced");
			rebalance();
		} else {
			System.out.println("Balanced");
		}

	}

	private BiDirectionalNode insert(BiDirectionalNode parent, BiDirectionalNode current, int value) {

		if (current == null) {
			current = new BiDirectionalNode(value);
			insertBidirection(parent, current, value);

			return current;
		}

		if (value < current.getValue()) {
			current.setLeft(insert(current, current.getLeft(), value));
		} else if (value > current.getValue()) {
			current.setRight(insert(current, current.getRight(), value));
		} else {
			return current;
		}

		return current;
	}

	/**
	 * After the balancing process, it is necessary to recreate the bidirectional pointer of all nodes.
	 *
	 * @param bidirectionalNode
	 * @return
	 */
	private BiDirectionalNode insertBidirectionAfterRebalanced(BiDirectionalNode bidirectionalNode) {

		if(bidirectionalNode.getLeft() == null || bidirectionalNode.getRight() == null) {
			return bidirectionalNode;
		}

		insertBidirection(bidirectionalNode, insertBidirectionAfterRebalanced(bidirectionalNode.getLeft()), bidirectionalNode.getLeft().getValue());
		insertBidirection(bidirectionalNode, insertBidirectionAfterRebalanced(bidirectionalNode.getRight()), bidirectionalNode.getRight().getValue());

		return bidirectionalNode;

	}

	/**
	 * Inserting bidirection pointers in the current neighbors.
	 * If the current is the right child node, then we need to connect with the left node if exists.
	 * We need to check another neighbors from the neighbor children of the parent.
	 *
	 * @param parent
	 * @param current
	 * @param value
	 */
	private void insertBidirection(BiDirectionalNode parent, BiDirectionalNode current, int value) {
		if (parent != null && value > parent.getValue()) {
			insertBiDirectionFromTheLeftNode(parent, current);
		} else if (parent != null && value < parent.getValue()) {
			insertBidirectionFromTheRightNode(parent, current);
		}
	}

	private void insertBiDirectionFromTheLeftNode(BiDirectionalNode parent, BiDirectionalNode current) {
		if (parent.getLeft() != null) {
			current.setLeftNeighbor(parent.getLeft());
			parent.getLeft().setRightNeighbor(current);
		}
		if (parent.getRightNeighbor() != null && parent.getRightNeighbor().getLeft() != null) {
			parent.getRightNeighbor().getLeft().setLeftNeighbor(current);
			current.setRightNeighbor(parent.getRightNeighbor().getLeft());
		}
	}

	private void insertBidirectionFromTheRightNode(BiDirectionalNode parent, BiDirectionalNode current) {
		if(parent.getRight() != null) {
			current.setRightNeighbor(parent.getRight());
			parent.getRight().setLeftNeighbor(current);
		}
		if (parent.getLeftNeighbor() != null && parent.getLeftNeighbor().getRight() != null) {
			parent.getLeftNeighbor().getRight().setRightNeighbor(current);
			current.setLeftNeighbor(parent.getLeftNeighbor().getRight());
		}
	}

	private Boolean isBalanced() {
		return isBalanced(node, new HeightCount());
	}

	private Boolean isBalanced(BiDirectionalNode node, HeightCount heightCount) {

		if (node == null) {
			heightCount.setCount(0L);
			return true;
		}

		HeightCount leftHeightCount = new HeightCount();
		HeightCount rightHeightCount = new HeightCount();

		boolean isLeftBalanced = isBalanced(node.getLeft(), leftHeightCount);
		boolean isRightBalanced = isBalanced(node.getRight(), rightHeightCount);

		long leftHeight = leftHeightCount.getCount();
		long rightHeight = rightHeightCount.getCount();

		heightCount.setCount((Math.max(leftHeight, rightHeight)) + 1L);

		if ((leftHeight - rightHeight >= MAX_HEIGHT) || (rightHeight - leftHeight >= MAX_HEIGHT)) {
			return false;
		} else {
			return isLeftBalanced && isRightBalanced;
		}
	}
	private void rebalance() {

		final List<BiDirectionalNode> nodes = new ArrayList<>();
		insertTreeToList(node, nodes);

		nodes.sort(Comparator.comparing(BiDirectionalNode::getValue));

		buildTree(nodes, 0, nodes.size() - 1);

		insertBidirectionAfterRebalanced(node);

	}

	public BiDirectionalNode buildTree(List<BiDirectionalNode> nodes, Integer start, Integer end) {

		if (start > end) {
			return null;
		}

		int mid = (start + end)/2;

		var node = nodes.get(mid);

		node.setLeft(buildTree(nodes, start, mid - 1));
		node.setRight(buildTree(nodes, mid + 1, end));

		return node;

	}

	private void insertTreeToList (BiDirectionalNode node, List<BiDirectionalNode> nodes) {

		if (node == null) {
			return;
		}

		nodes.add(node);
		insertTreeToList(node.getLeft(), nodes);
		insertTreeToList(node.getRight(), nodes);

	}

	public BiDirectionalNode getNode() {
		return node;
	}

}
