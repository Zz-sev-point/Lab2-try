/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// AF(vertices) = 图中所有点
	// AF（edges) = 图中所有边
	// Representation invariant:
	// 每条边的点必须在vertices当中
	// 每条边的权值必须大于0
	// 没有重复的点
	// 起点终点相同的边只有一条
	// Safety from rep exposure:
	// 将vertices和edges设置为private final
	// 返回vertices时进行了defensive copy

	// TODO constructor
	public ConcreteEdgesGraph() {
	}

	// TODO checkRep
	public void checkRep() {
		for (Edge<L> i : edges) {
			assert vertices.contains(i.getSource());
			assert vertices.contains(i.getTarget());
			assert i.getWeight() > 0;
		}
	}

	@Override
	public boolean add(L vertex) {
		if(vertex==null) {
			System.out.println("Null vertex!");
			return false;
		}
		if (vertices.contains(vertex)) {
			System.out.println("The vertex has existed!");
			return false;
		}
		vertices.add(vertex);
		checkRep();
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		int returndata = 0;
		if (weight < 0) {
			System.out.println("Error!");
			return -1;
		} else {
			boolean flag = false;
			Iterator<Edge<L>> ie = edges.iterator();
			while (ie.hasNext()) {
				Edge<L> i = ie.next();
				if (i.getSource().equals(source) && i.getTarget().equals(target)) {
					ie.remove();
					flag = true;
					returndata = i.getWeight();
					break;
				}
			}
			if (flag) {
				if (weight != 0) {
					Edge<L> newedge = new Edge<L>(source, target, weight);
					vertices.add(source);
					vertices.add(target);
					edges.add(newedge);
				}
			} else {
				if (weight != 0) {
					Edge<L> newedge = new Edge<L>(source, target, weight);
					vertices.add(source);
					vertices.add(target);
					edges.add(newedge);
				} else {
					vertices.add(source);
					vertices.add(target);
				}
			}
			checkRep();
			return returndata;
		}
	}

	@Override
	public boolean remove(L vertex) {
		if (vertices.contains(vertex)) {
			vertices.remove(vertex);
			Iterator<Edge<L>> ie = edges.iterator();
			while (ie.hasNext()) {
				Edge<L> i = ie.next();
				if (i.getSource().equals(vertex) || i.getTarget().equals(vertex))
					ie.remove();
			}
		} else {
			checkRep();
			return false;
		}
		checkRep();
		return true;
	}

	@Override
	public Set<L> vertices() {
		return new HashSet<>(vertices);
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> sou = new HashMap<>();
		for (Edge<L> i : edges) {
			if (i.getTarget().equals(target))
				sou.put(i.getSource(), i.getWeight());
		}
		checkRep();
		return sou;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> tar = new HashMap<>();
		for (Edge<L> i : edges) {
			if (i.getSource().equals(source))
				tar.put(i.getTarget(), i.getWeight());
		}
		checkRep();
		return tar;
	}

	// TODO toString()
	public String toString() {
		String str = "";
		for (Edge<L> i : edges)
			str += i.toString();
		return str;
	}

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	// TODO fields
	private final L source, target;
	private int weight;
	// Abstraction function:
	// AF（source) = 边的起点
	// AF（target) = 边的终点
	// AF（weight) = 边的权值
	// Representation invariant:
	// 起点和终点均不为空，权值大于0
	// Safety from rep exposure:
	// 将source、target设置为private final
	// 将weight设置为private

	// TODO constructor
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	// TODO checkRep
	private void checkRep() {
		assert source != null;
		assert target != null;
		assert weight > 0;
	}

	// TODO methods
	public L getSource() {
		checkRep();
		return source;
	}

	public L getTarget() {
		checkRep();
		return target;
	}

	public int getWeight() {
		checkRep();
		return weight;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Edge) {
			Edge e = (Edge)obj;
			return e.source == source && e.target == target && e.weight == weight;
		}
		return false;
	}

	// TODO toString()
	public String toString() {
		checkRep();
		String info = this.source + "->" + this.target + ": " + Integer.toString(this.weight) + "\n";
		return info;
	}
}
