/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    // 对每个方法进行测试
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    // Testing strategy
    // 测试图中不含待加入点、含有待加入点的情况
    // 在不含待加入点情况中，还要检测点是否加入成功
    @Test
    public void addTest() {
    	Graph<String> graph = emptyInstance();
    	assertEquals(true, graph.add("a"));
    	assertEquals(true, graph.vertices().contains("a"));
    	assertEquals(false, graph.add("a"));
    	assertEquals(false, graph.add(null));
    }
    // Testing strategy
    // 分别测试不存在待加入边以及其端点、存在端点但需要修改权值、
    // 存在端点但需要将权值改为0、不存在端点且权值要求为0、权值要求为负数的情况
    @Test
    public void setTest() {
    	Graph<String> graph = emptyInstance();
    	assertEquals(0, graph.set("a", "b", 1));
    	assertEquals(0, graph.set("b", "c", 2));
    	assertEquals(0, graph.set("d", "e", 2));
    	assertEquals(1, graph.set("a", "b", 3));
    	assertEquals(3, graph.set("a", "b", 0));
    	assertEquals(0, graph.set("a", "c", 4));
    	assertEquals(0, graph.set("f", "g", 0));
    	assertEquals(-1, graph.set("a", "b", -2));
    }
    // Testing strategy
    // 测试待移除的点在图中和不在图中的情况
    @Test
    public void removeTest() {
    	Graph<String> graph = emptyInstance();
    	graph.set("a", "b", 1);
    	graph.set("b", "c", 2);
    	assertEquals(true, graph.remove("b"));
    	assertEquals(false, graph.remove("b"));
    }
    // Testing strategy
    // 测试包含a和b两个点的图
    @Test
    public void verticesTest() {
    	Graph<String> graph = emptyInstance();
    	Set<String> vertices = new HashSet<>();
    	graph.add("a");
    	graph.add("b");
    	vertices.add("a");
    	vertices.add("b");
    	assertEquals(vertices, graph.vertices());
    }
    // Testing strategy
    // 测试包含边a->b(1)和b->c(2)的图中b的源
    @Test
    public void sourcesTest() {
    	Graph<String> graph = emptyInstance();
    	Map<String, Integer> bs = new HashMap<>();
    	graph.set("a", "b", 1);
    	graph.set("b", "c", 2);
    	bs.put("a", 1);
		assertEquals(bs, graph.sources("b"));
    }
    // Testing strategy
    // 测试包含边a->b(1)和b->c(2)的图中b的目标
    @Test
    public void targetsTest() {
    	Graph<String> graph = emptyInstance();
		Map<String, Integer> bt = new HashMap<>();
    	graph.set("a", "b", 1);
    	graph.set("b", "c", 2);
		bt.put("c", 2);
		assertEquals(bt, graph.targets("b"));
    }
}
