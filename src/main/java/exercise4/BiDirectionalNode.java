package exercise4;

public class BiDirectionalNode {

	private final Integer value;

	private BiDirectionalNode left;

	private BiDirectionalNode right;

	private BiDirectionalNode leftRelative;

	private BiDirectionalNode rightRelative;

	BiDirectionalNode(int value) {
		this.value = value;
		this.right = null;
		this.left = null;
		this.leftRelative = null;
		this.rightRelative = null;
	}

	public BiDirectionalNode getLeftRelative() {
		return leftRelative;
	}

	public void setLeftRelative(BiDirectionalNode leftRelative) {
		this.leftRelative = leftRelative;
	}

	public BiDirectionalNode getRightRelative() {
		return rightRelative;
	}

	public void setRightRelative(BiDirectionalNode rightRelative) {
		this.rightRelative = rightRelative;
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
