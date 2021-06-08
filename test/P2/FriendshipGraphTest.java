package P2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import P1.graph.ConcreteEdgesGraph;
import P2.FriendshipGraph;
import P2.Person;

public class FriendshipGraphTest {
	//Testing strategy
	//在图中添加一个Person"abc"
	@Test
	public void addVertexTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person p = new Person("abc");
		Set<Person> people = new HashSet<>();
		graph.addVertex(p);
		people.add(p);
		assertEquals(people, graph.getGraph().vertices());
	}
	//Testing strategy
	//在图中添加Person"a""b""c"，添加边a->b(1)、c->b(1)、a->c(1)
	@Test
	public void addEdgeTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person p1 = new Person("a");
		Person p2 = new Person("b");
		Person p3 = new Person("c");
		Map<Person, Integer> m1 = new HashMap<>();
		Map<Person, Integer> m2 = new HashMap<>();
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		graph.addEdge(p1, p2);
		graph.addEdge(p3, p2);
		graph.addEdge(p1, p3);
		m1.put(p2, 1);
		m1.put(p3, 1);
		m2.put(p1, 1);
		m2.put(p3, 1);
		assertEquals(m1, graph.getGraph().targets(p1));
		assertEquals(m2, graph.getGraph().sources(p2));
	}
	//Testing strategy
	//在图中添加Person"a""b""c""d""e"，添加无向边ab、ac、bc、bd、cd
	@Test
	public void getDistanceTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person p1 = new Person("a");
		Person p2 = new Person("b");
		Person p3 = new Person("c");
		Person p4 = new Person("d");
		Person p5 = new Person("e");
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		graph.addVertex(p4);
		graph.addVertex(p5);
		graph.addEdge(p1, p2);
		graph.addEdge(p2, p1);
		graph.addEdge(p1, p3);
		graph.addEdge(p3, p1);
		graph.addEdge(p2, p3);
		graph.addEdge(p3, p2);
		graph.addEdge(p2, p4);
		graph.addEdge(p4, p2);
		graph.addEdge(p3, p4);
		graph.addEdge(p4, p3);
		assertEquals(1, graph.getDistance(p1, p2));
		assertEquals(1, graph.getDistance(p1, p3));
		assertEquals(2, graph.getDistance(p1, p4));
		assertEquals(-1, graph.getDistance(p1, p5));
		assertEquals(1, graph.getDistance(p2, p3));
		assertEquals(1, graph.getDistance(p2, p4));
		assertEquals(-1, graph.getDistance(p2, p5));
		assertEquals(1, graph.getDistance(p3, p4));
		assertEquals(-1, graph.getDistance(p3, p5));
		assertEquals(-1, graph.getDistance(p4, p5));
	}
	//Testing strategy
	//在图中添加Person"a"和"b"，并在添加边a->b(1)
	@Test
	public void getGraphTest() {
		FriendshipGraph g = new FriendshipGraph();
		ConcreteEdgesGraph<Person> g1 = new ConcreteEdgesGraph<>();
		Person p1 = new Person("a");
		Person p2 = new Person("b");
		g.addVertex(p1);
		g.addVertex(p2);
		g.addEdge(p1, p2);
		g1.set(p1, p2, 1);
		assertEquals(g1.toString(), g.getGraph().toString());
	}
}
