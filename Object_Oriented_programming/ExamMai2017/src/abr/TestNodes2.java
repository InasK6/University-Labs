package abr;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class TestNodes2 {

	@Test
	public void testNodes () {
		// arbre 1 : b, a, c
		Node node = new Node("b");
		node.add("a");
		node.add("c");
		
		node.print(System.out);		

		OutputStream baos = new ByteArrayOutputStream();
		node.print(new PrintStream(baos));
		assertEquals(baos.toString(), "a b c ");
		
		node = new Node("a");
		node.add("c");
		node.add("b");

		baos = new ByteArrayOutputStream();
		node.print(new PrintStream(baos));		
		assertEquals("a b c", baos.toString().trim());
	}
}
