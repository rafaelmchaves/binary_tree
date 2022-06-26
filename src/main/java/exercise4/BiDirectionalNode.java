package exercise4;

public class BiDirectionalNode {

	private final Integer value;

	private BiDirectionalNode left;

	private BiDirectionalNode right;

	private BiDirectionalNode leftNeighbor;

	private BiDirectionalNode rightNeighbor;

	BiDirectionalNode(int value) {
		this.value = value;
		this.right = null;
		this.left = null;
		this.leftNeighbor = null;
		this.rightNeighbor = null;
	}

	public BiDirectionalNode getLeftNeighbor() {
		return leftNeighbor;
	}

	public void setLeftNeighbor(BiDirectionalNode leftNeighbor) {
		this.leftNeighbor = leftNeighbor;
	}

	public BiDirectionalNode getRightNeighbor() {
		return rightNeighbor;
	}

	public void setRightNeighbor(BiDirectionalNode rightNeighbor) {
		this.rightNeighbor = rightNeighbor;
	}

	public Integer getValue() {
		return value;
	}

	public BiDirectionalNode getLeft() {
		return left;
	}

	public void setLeft(BiDirectionalNode left) {
		this.left = left;
	}

	public BiDirectionalNode getRight() {
		return right;
	}

	public void setRight(BiDirectionalNode right) {
		this.right = right;
	}
}
