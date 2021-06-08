/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		return new ConcreteVerticesGraph();
	}

	/*
	 * Testing ConcreteVerticesGraph...
	 */

	// Testing strategy for ConcreteVerticesGraph.toString()
	// 测试三个点三条边的图

	// TODO tests for ConcreteVerticesGraph.toString()
	@Test
	public void ConcreteVerticesGraphToStringTest() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("b");
		graph.add("c");
		graph.set("a", "b", 1);
		graph.set("b", "c", 2);
		graph.set("c", "a", 3);
		String str = "a\nsource:\n{c=3}\ntarget:\n{b=1}\n" 
					+ "b\nsource:\n{a=1}\ntarget:\n{c=2}\n"
					+ "c\nsource:\n{b=2}\ntarget:\n{a=3}\n";
		assertEquals(str, graph.toString());
	}
	/*
	 * Testing Vertex...
	 */

	// Testing strategy for Vertex
	// 分别测试Vertex的每个方法

	// TODO tests for operations of Vertex
	// 生成一个名为a的节点，比较a和getName
	@Test
	public void getNameTest() {
		Vertex<String> vertex = new Vertex("a");
		assertEquals("a", vertex.getName());
	}
	// 添加两条边b->a、c->a，权值分别为1,2
	@Test
	public void getSourceTest() {
		Vertex<String> vertex = new Vertex("a");
		Map<String, Integer> sou = new HashMap<>();
		vertex.addSource("b", 1);
		vertex.addSource("c", 2);
		sou.put("b", 1);
		sou.put("c", 2);
		assertEquals(sou, vertex.getSources());
	}
	// 添加两条边a->b、a->c，权值分别为1,2
	@Test
	public void getTargetTest() {
		Vertex<String> vertex = new Vertex("a");
		Map<String, Integer> tar = new HashMap<>();
		vertex.addTarget("b", 1);
		vertex.addTarget("c", 2);
		tar.put("b", 1);
		tar.put("c", 2);
		assertEquals(tar, vertex.getTargets());
	}
	// 分别测试不存在边、存在边需要修改权值、存在边需要将权值改为0、
	// 不存在边需要加入权值为0的边以及加入边权值为负数的情况
	@Test
	public void addSourceTest() {
		Vertex<String> vertex = new Vertex("a");
		assertEquals(0, vertex.addSource("b", 1));
		assertEquals(1, vertex.addSource("b", 2));
		assertEquals(2, vertex.addSource("b", 0));
		assertEquals(0, vertex.addSource("c", 0));
		assertEquals(-1, vertex.addSource("d", -2));
	}
	// 分别测试不存在边、存在边需要修改权值、存在边需要将权值改为0、
	// 不存在边需要加入权值为0的边以及加入边权值为负数的情况
	@Test
	public void addTargetTest() {
		Vertex<String> vertex = new Vertex("a");
		assertEquals(0, vertex.addTarget("b", 1));
		assertEquals(1, vertex.addTarget("b", 2));
		assertEquals(2, vertex.addTarget("b", 0));
		assertEquals(0, vertex.addTarget("c", 0));
		assertEquals(-1, vertex.addTarget("d", -2));
	}
	// 给a添加b->a(1)和c->a(2)两条边，再删除后者
	@Test
	public void removeSourceTest() {
		Vertex<String> vertex = new Vertex("a");
		vertex.addSource("b", 1);
		vertex.addSource("c", 2);
		assertEquals(2, vertex.removeSource("c"));
	}
	// 给a添加a->b(1)和a->c(2)两条边，再删除后者
	@Test
	public void removeTargetTest() {
		Vertex<String> vertex = new Vertex("a");
		vertex.addTarget("b", 1);
		vertex.addTarget("c", 2);
		assertEquals(2, vertex.removeTarget("c"));
	}
	// 测试有边b->a(1)和a->c(2)的情况生成的String
	@Test
	public void toStringTest() {
		Vertex<String> vertex = new Vertex("a");
		Map<String, Integer> sou = new HashMap<>();
		Map<String, Integer> tar = new HashMap<>();
		vertex.addSource("b", 1);
		vertex.addTarget("c", 2);
		sou.put("b", 1);
		tar.put("c", 2);
		String str = "a\nsource:\n" + sou.toString() + "\ntarget:\n" + tar.toString() + "\n";
		assertEquals(str, vertex.toString());
	}
}
