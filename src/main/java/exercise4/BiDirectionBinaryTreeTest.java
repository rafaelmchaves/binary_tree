package exercise4;

import java.util.LinkedList;

public class BiDirectionBinaryTreeTest {

	public static void main(String[] args) {

		var bt = createUnbalancedBinaryTree();

		printBinaryTree(bt.getNode());

	}

	private static BiDirectionBinaryTree createUnbalancedBinaryTree() {
		BiDirectionBinaryTree bt = new BiDirectionBinaryTree();

		bt.insert(5);
		bt.insert(6);
		bt.insert(4);
		bt.insert(7);
		bt.insert(3);
		bt.insert(8);
		bt.insert(2);
		bt.insert(9);
		bt.insert(1);

//		bt.insert(9);
//		bt.insert(8);
//		bt.insert(7);
//		bt.insert(6);
//		bt.insert(5);
//		bt.insert(4);
//		bt.insert(3);
//		bt.insert(2);
//		bt.insert(1);

		return bt;
	}

	private static BiDirectionBinaryTree createBalancedBinaryTree() {
		BiDirectionBinaryTree bt = new BiDirectionBinaryTree();

		bt.insert(6);
		bt.insert(4);
		bt.insert(8);
		bt.insert(3);
		bt.insert(5);
		bt.insert(7);
		bt.insert(9);

		return bt;
	}

	public static void printBinaryTree(BiDirectionalNode root) {
		LinkedList<BiDirectionalNode> treeLevel = new LinkedList<>();
		treeLevel.add(root);
		LinkedList<BiDirectionalNode> temp = new LinkedList<>();

		int counter = 0;
		int height = heightOfTree(root)-1;
		double numberOfElements = (Math.pow(2 , (height + 1)) - 1);

		while (counter <= height) {
			BiDirectionalNode removed = treeLevel.removeFirst();
			if (temp.isEmpty()) {
				printSpace(numberOfElements / Math.pow(2 , counter + 1), removed);
			} else {
				printSpace(numberOfElements / Math.pow(2 , counter), removed);
			}
			if (removed == null) {
				temp.add(null);
				temp.add(null);
			} else {
				temp.add(removed.getLeft());
				temp.add(removed.getRight());
			}

			if (treeLevel.isEmpty()) {
				System.out.println("");
				System.out.println("");
				treeLevel = temp;
				temp = new LinkedList<>();
				counter++;
			}

		}
	}

	public static void printSpace(double n, BiDirectionalNode removed){
		for(;n>0;n--) {
			System.out.print("\t");
		}
		if(removed == null){
			System.out.print(" ");
		}
		else {
			System.out.print(removed.getValue());
		}
	}

	public static int heightOfTree(BiDirectionalNode root){
		if(root==null){
			return 0;
		}
		return 1+ Math.max(heightOfTree(root.getLeft()),heightOfTree(root.getRight()));
	}
}
