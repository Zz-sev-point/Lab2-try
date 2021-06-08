/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
	
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}
	// Testing strategy
	// 测试语料为空的情况（empty.txt)
	// 测试语料一行的情况（mugar-omni-theater.txt）
	// 测试语料多行的情况（mugar-omni-theater-1.txt）
	// 测试图中边权值不全为1的情况（mugar-omni-theater-2.txt）
	// TODO tests
	@Test
	public void GraphPoetTest() throws IOException {
		final GraphPoet poet1 = new GraphPoet
				(new File("test/P1/poet/txt/empty.txt"));
		final String input1 = "Test the system";
		assertEquals("Test the system", poet1.poem(input1));
		final GraphPoet poet2 = new GraphPoet
				(new File("test/P1/poet/txt/mugar-omni-theater.txt"));
		final String input2 = "Test the system";
		assertEquals("Test of the system", poet2.poem(input2));
		final GraphPoet poet3 = new GraphPoet
				(new File("test/P1/poet/txt/mugar-omni-theater-1.txt"));
		final String input3 = "Test the system";
		assertEquals("Test of the system", poet3.poem(input3));
		final GraphPoet poet4 = new GraphPoet
				(new File("test/P1/poet/txt/mugar-omni-theater-2.txt"));
		final String input4 = "Test the system";
		assertEquals("Test and the system", poet4.poem(input4));
	}
	// Testing strategy
	// 利用所给样例“Hello, HELLO, hello, goodbye!”（hello.txt)
	// 测试函数生成边权值不为1的图以及toString方法
	@Test
	public void toStringTest() throws IOException {
		final GraphPoet poet = new GraphPoet(new File("test/P1/poet/txt/hello.txt"));
    	String str = "graph:\n" + "hello,->hello,: 2\n" + "hello,->goodbye!: 1\n" ;
    	assertEquals(str, poet.toString());
	}
}
