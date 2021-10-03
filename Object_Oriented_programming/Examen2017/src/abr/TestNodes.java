package abr;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNodes {

	@Test
	public void testNodes () {
		// arbre 1 : b, a, c
		Node node = new Node("b");
		node.add("a");
		node.add("c");
		
		assertTrue(node.getValue().equals("b"));
		assertTrue(node.getLeft() != null);
		assertTrue(node.getLeft().getValue().equals("a"));
		assertTrue(node.getLeft().getLeft() == null);
		assertTrue(node.getLeft().getRight() == null);
		assertTrue(node.getRight() != null);
		assertTrue(node.getRight().getValue().equals("c"));
		assertTrue(node.getRight().getLeft() == null);
		assertTrue(node.getRight().getRight() == null);
		
		node = new Node("a");
		node.add("c");
		node.add("b");
		
		assertTrue(node.getValue().equals("a"));
		assertTrue(node.getLeft() == null);
		assertTrue(node.getRight() != null);
		assertTrue(node.getRight().getValue().equals("c"));
		assertTrue(node.getRight().getLeft() != null);
		assertTrue(node.getRight().getRight() == null);
		assertTrue(node.getRight().getLeft().getValue().equals("b"));
	}
}
