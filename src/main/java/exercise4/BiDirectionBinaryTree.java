package exercise4;

import exercise3.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

	private BiDirectionalNode insert(BiDirectionalNode father, BiDirectionalNode current, int value) {

		if (current == null) {
			current = new BiDirectionalNode(value);
			insertBidirection(father, current, value);

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

	private BiDirectionalNode insertBidirectionAfterRebalanced(BiDirectionalNode node2) {

		if(node2.getLeft() == null || node2.getRight() == null) {
			return node2;
		}

		insertBidirection(node2, insertBidirectionAfterRebalanced(node2.getLeft()), node2.getLeft().getValue());
		insertBidirection(node2, insertBidirectionAfterRebalanced(node2.getRight()), node2.getRight().getValue());

		return node2;

	}
	private void insertBidirection(BiDirectionalNode father, BiDirectionalNode current, int value) {
		if (father != null && value > father.getValue()) {
			if (father.getLeft() != null) {
				current.setLeftRelative(father.getLeft());
				father.getLeft().setRightRelative(current);
			}
			if (father.getRightRelative() != null && father.getRightRelative().getLeft() != null) {
				father.getRightRelative().getLeft().setLeftRelative(current);
				current.setRightRelative(father.getRightRelative().getLeft());
			}

		} else if (father != null && value < father.getValue()) {
			if(father.getRight() != null) {
				current.setRightRelative(father.getRight());
				father.getRight().setLeftRelative(current);
			}
			if (father.getLeftRelative() != null && father.getLeftRelative().getRight() != null) {
				father.getLeftRelative().getRight().setRightRelative(current);
				current.setLeftRelative(father.getLeftRelative().getRight());
			}
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

		if (node == null)
			return;

		nodes.add(node);
		insertTreeToList(node.getLeft(), nodes);
		insertTreeToList(node.getRight(), nodes);

	}

	public BiDirectionalNode getNode() {
		return node;
	}

}
