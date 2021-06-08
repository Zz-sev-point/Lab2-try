/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	// AF(vertices) = 图中所有点
	// Representation invariant:
	// vertices中没有重复的点
	// 起点和终点相同的边只有一条
	// 边的权值大于0
	// Safety from rep exposure:
	// 将vertices设置为private final
	// vertices方法使用返回vertices转换得到的一个Set而非其本身
	
	// TODO constructor
	public ConcreteVerticesGraph() {
	}

	// TODO checkRep
	public void checkRep() {
		Set<Vertex> verticeset = new HashSet<>();
		verticeset.addAll(vertices);
		assert verticeset.size() == vertices.size();
	}

	@Override
	public boolean add(L vertex) {
		if(vertex == null)
			return false;
		for (Vertex i : vertices) {
			if (i.getName() == vertex) {
				System.out.println("The vertex has existed!");
				return false;
			}
		}
		Vertex<L> v = new Vertex(vertex);
		vertices.add(v);
		checkRep();
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		int returndata = 0;
		this.add(source);
		this.add(target);
		for (Vertex<L> i : vertices) {
			if (i.getName().equals(source))
				returndata = i.addTarget(target, weight);
			if (i.getName().equals(target))
				returndata = i.addSource(source, weight);
		}
		checkRep();
		return returndata;
	}

	@Override
	public boolean remove(L vertex) {
		boolean flag = false;
		Iterator<Vertex<L>> iv = vertices.iterator();
		while (iv.hasNext()) {
			Vertex<L> v = iv.next();
			if (v.getName().equals(vertex)) {
				iv.remove();
				flag = true;
			} else {
				if (v.getSources().containsKey(vertex))
					v.removeSource(vertex);
				if (v.getTargets().containsKey(vertex))
					v.removeTarget(vertex);
			}
		}
		return flag;
	}

	@Override
	public Set<L> vertices() {
		Set<L> verticeset = new HashSet<>();
		L vertex = null;
		for (Vertex<L> i : vertices) {
			vertex = i.getName();
			verticeset.add(vertex);
		}
		checkRep();
		return verticeset;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> sou = new HashMap<>();
		for (Vertex<L> i : vertices) {
			if (i.getName().equals(target))
				sou = i.getSources();
		}
		return sou;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> tar = new HashMap<>();
		for (Vertex<L> i : vertices) {
			if (i.getName().equals(source))
				tar = i.getTargets();
		}
		return tar;
	}

	// TODO toString()
	public String toString() {
		String str = "";
		for (Vertex<L> i : vertices)
			str += i.toString();
		return str;
	}

}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	private final L name;
	private final Map<L, Integer> sources;
	private final Map<L, Integer> targets;
	// Abstraction function:
	// AF（name) = 点的名字/标签
	// AF（sources) = 以该点为终点的边的起点到边权值映射的集合
	// AF（targets) = 以该点为起点的边的终点到边权值映射的集合
	// Representation invariant:
	// 以该点为某一端点的边的权值都为正数
	// Safety from rep exposure:
	// 将name、sources、targets设置为private final
	//getSources和getTargets方法采用了defensive copy策略

	// TODO constructor
	public Vertex(L name) {
		this.name = name;
		this.sources = new HashMap<>();
		this.targets = new HashMap<>();
	}

	// TODO checkRep
	public void checkRep() {
		Set<L> sourceset = sources.keySet();
		if (sourceset != null) {
			Iterator<L> il = sourceset.iterator();
			while (il.hasNext()) {
				L iname = il.next();
				Integer weight = sources.get(iname);
				assert weight > 0;
			}
		}
		Set<L> targetset = targets.keySet();
		if (targetset != null) {
			Iterator<L> il = targetset.iterator();
			while (il.hasNext()) {
				L iname = il.next();
				Integer weight = targets.get(iname);
				assert weight > 0;
			}
		}
	}

	// TODO methods
	public L getName() {
		checkRep();
		return name;
	}

	public Map<L, Integer> getSources() {
		return new HashMap<>(sources);
	}

	public Map<L, Integer> getTargets() {
		return new HashMap<>(targets);
	}

	public int addSource(L source, int weight) {
		if (weight < 0) {
			System.out.println("Weight meaningless!");
			return -1;
		}
		Integer returndata = 0;
		if (weight == 0)
			returndata = this.removeSource(source);
		if (weight > 0) {
			returndata = sources.put(source, weight);
			if (returndata == null)
				returndata = 0;
		}
		checkRep();
		return returndata;
	}

	public int addTarget(L target, int weight) {
		if (weight < 0) {
			System.out.println("Weight meaningless!");
			return -1;
		}
		Integer returndata = 0;
		if (weight == 0)
			returndata = this.removeTarget(target);
		if (weight > 0) {
			returndata = targets.put(target, weight);
			if (returndata == null)
				returndata = 0;
		}
		checkRep();
		return returndata;
	}

	public int removeSource(L source) {
		Integer returndata = sources.remove(source);
		if (returndata == null)
			returndata = 0;
		checkRep();
		return returndata;
	}

	public int removeTarget(L target) {
		Integer returndata = targets.remove(target);
		if (returndata == null)
			returndata = 0;
		checkRep();
		return returndata;
	}

	// TODO toString()
	public String toString() {
		return this.name.toString() + "\nsource:\n" + this.sources.toString() + "\ntarget:\n" + this.targets.toString() + "\n";
	}
}
