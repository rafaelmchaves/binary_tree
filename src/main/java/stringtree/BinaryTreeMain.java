package stringtree;

public class BinaryTreeMain {

	public static void main(String[] args) {

		var bt = createUnbalancedBinaryTree();

		final var node = bt.find("fea");

		System.out.println(node != null ? node.getValue() : "Not found");
	}

	private static BinaryTree createUnbalancedBinaryTree() {
		BinaryTree bt = new BinaryTree();
		bt.insert("att");
		bt.insert("kea");
		bt.insert("feasd");
		bt.insert("attualization");

		return bt;
	}
}
