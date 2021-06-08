/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		return new ConcreteEdgesGraph();
	}

	/*
	 * Testing ConcreteEdgesGraph...
	 */

	// Testing strategy for ConcreteEdgesGraph.toString()
	// 测试3条边的图
	// TODO tests for ConcreteEdgesGraph.toString()
	@Test
	public void concreteEdgesGraphToStringTest() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("b");
		graph.add("c");
		graph.add("d");
		graph.set("a", "b", 1);
		graph.set("b", "c", 2);
		graph.set("c", "d", 3);
		assertEquals("a->b: 1\nb->c: 2\nc->d: 3\n", graph.toString());
	}
	/*
	 * Testing Edge...
	 */

	// Testing strategy for Edge
	// 测试Edge中的每个方法
	
	// TODO tests for operations of Edge
	// 生成一条a到b权值为5的边，比较起点和a
	@Test
	public void getSourceTest() {
		Edge<String> edge = new Edge("a", "b", 5);
		assertEquals("a", edge.getSource());
	}
	// 生成一条a到b权值为5的边，比较终点和b
	@Test
	public void getTargetTest() {
		Edge<String> edge = new Edge("a", "b", 5);
		assertEquals("b", edge.getTarget());
	}
	// 生成一条a到b权值为5的边，比较权重是否等于5
	@Test
	public void getWeightTest() {
		Edge<String> edge = new Edge("a", "b", 5);
		assertEquals(5, edge.getWeight());
	}
	// 生成edge1、edge2、edge3三条边，1和2不同，1和3相同
	// 分别测试1和2比较以及1和3比较的结果
	@Test
	public void equalsTest() {
		Edge<String> edge1 = new Edge("a", "b", 5);
		Edge<String> edge2 = new Edge("a", "b", 4);
		Edge<String> edge3 = new Edge("a", "b", 5);
		assertEquals(false, edge1.equals(edge2));
		assertEquals(true, edge1.equals(edge3));
	}
	// 生成一条a到b权值为5的边，比较toString方法生成的字符串与期望的字符串
	@Test
	public void toStringTest() {
		Edge<String> edge = new Edge("a", "b", 5);
		assertEquals("a->b: 5\n", edge.toString());
	}
}
