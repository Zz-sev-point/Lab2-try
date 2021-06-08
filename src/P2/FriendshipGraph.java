package P2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import P1.graph.*;
import P2.Person;

public class FriendshipGraph {

	private final ConcreteEdgesGraph<Person> graph;
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben));
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		//should print -1
	}
	
	//Constructor
	public FriendshipGraph() {
		graph = new ConcreteEdgesGraph<>();
	}
	
	public boolean addVertex(Person p) {
		return graph.add(p);
	}
	
	public void addEdge(Person p1, Person p2) {
		graph.set(p1, p2, 1);
	}
	
	public int getDistance(Person p1, Person p2) {
		if (p1 == p2)
			return 0;
		Map<Person, Integer> dis = new HashMap<>();
		Queue<Person> q = new LinkedList<>();
		q.offer(p1);
		dis.put(p1, 0);
		while (!q.isEmpty()) {
			Person p = q.poll();
			Set<Person> Friends = graph.targets(p).keySet();
			for (Person i : Friends) {
				if (!dis.containsKey(i)) {
					if (i == p2)
						return dis.get(p) + 1;
					q.offer(i);
					dis.put(i, dis.get(p) + 1);
				}
			}
		}
		return -1;
	}
	
	public ConcreteEdgesGraph<Person> getGraph(){
		return graph;
	}
}
