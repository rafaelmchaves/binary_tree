import exercise4.BiDirectionBinaryTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BiDirectionBinaryTreeTest {

	/**
	 * Checking the values of nodes in the tree below
	 * 				                5
	 *
	 * 				3								7
	 *
	 * 		2				4				6				8
	 *
	 * 	1		 		 		 		 		 		 		9
	 */
	@Test
	public void insert_addNodes_valuesTreeCorrect () {
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

		assertAll( () -> {
			assertEquals(5, bt.getNode().getValue());
			assertEquals(7, bt.getNode().getRight().getValue());
			assertEquals(3, bt.getNode().getLeft().getValue());

			assertEquals(8, bt.getNode().getRight().getRight().getValue());
			assertEquals(6, bt.getNode().getRight().getLeft().getValue());
			assertEquals(2, bt.getNode().getLeft().getLeft().getValue());
			assertEquals(4, bt.getNode().getLeft().getRight().getValue());

			assertEquals(9, bt.getNode().getRight().getRight().getRight().getValue());
			assertNull(bt.getNode().getRight().getRight().getLeft());

			assertEquals(1, bt.getNode().getLeft().getLeft().getLeft().getValue());
			assertNull(bt.getNode().getLeft().getLeft().getRight());

		});
	}


	/**
	 *
	 * Checking the bidirection links in the tree below
	 * 				                5
	 *
	 * 				3								7
	 *
	 * 		2				4				6				8
	 *
	 * 	1		 		 		 		 		 		 		9
	 */
	@Test
	public void insert_addNodes_bidirectionalCorrect () {
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

		assertAll( () -> {

			assertNull(bt.getNode().getLeftNeighbor());
			assertNull(bt.getNode().getRightNeighbor());

			//checking node 7 neighbors
			assertEquals(3, bt.getNode().getRight().getLeftNeighbor().getValue());
			assertNull( bt.getNode().getRight().getRightNeighbor());

			//checking node 3 neighbors
			assertEquals(7, bt.getNode().getLeft().getRightNeighbor().getValue());
			assertNull( bt.getNode().getLeft().getLeftNeighbor());

			//checking node 2 neighbors
			assertEquals(4, bt.getNode().getLeft().getLeft().getRightNeighbor().getValue());
			assertNull( bt.getNode().getLeft().getLeft().getLeftNeighbor());

			//checking node 4 neighbors
			assertEquals(2, bt.getNode().getLeft().getRight().getLeftNeighbor().getValue());
			assertEquals(6, bt.getNode().getLeft().getRight().getRightNeighbor().getValue());

			//checking node 6 neighbors
			assertEquals(4, bt.getNode().getRight().getLeft().getLeftNeighbor().getValue());
			assertEquals(8, bt.getNode().getRight().getLeft().getRightNeighbor().getValue());

			//checking node 8 neighbors
			assertEquals(6, bt.getNode().getRight().getRight().getLeftNeighbor().getValue());
			assertNull(bt.getNode().getRight().getRight().getRightNeighbor());

			//checking node 9 neighbors
			assertNull(bt.getNode().getRight().getRight().getRight().getRightNeighbor());
			assertNull(bt.getNode().getRight().getRight().getRight().getLeftNeighbor());

			//checking node 1 neighbors
			assertNull(bt.getNode().getLeft().getLeft().getLeft().getRightNeighbor());
			assertNull(bt.getNode().getLeft().getLeft().getLeft().getLeftNeighbor());

		});
	}
}
