/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

	private final Graph<String> graph = Graph.empty();

	// Abstraction function:
	// AF（graph) = 所给文章中所有单词构成的图
	// Representation invariant:
	// 图是从语料文件中生成的
	// Safety from rep exposure:
	// 将graph设置为private final，且没有mutator

	/**
	 * Create a new poet with the graph from corpus (as described above).
	 * 
	 * @param corpus text file from which to derive the poet's affinity graph
	 * @throws IOException if the corpus file cannot be found or read
	 */
	public GraphPoet(File corpus) throws IOException {
		try {
			BufferedReader breader = new BufferedReader(new FileReader(corpus));
			String reader = null;
			String[] strs = null;
			List<String> words = new ArrayList<>();
			Map<String, Integer> pair = new HashMap<>();
			while ((reader = breader.readLine()) != null) {
				strs = reader.split(" ");
				words.addAll(Arrays.asList(strs));
			} // 将读入字符串分成单词
			breader.close();
			int len = words.size();
			for (int i = 0; i < len - 1; i++) {
				int weight = 0;
				String current = words.get(i).toLowerCase();
				String next = words.get(i + 1).toLowerCase();
				String wordpair = current + next;
				if (pair.containsKey(wordpair))
					weight = pair.get(wordpair);
				pair.put(wordpair, weight + 1);
				graph.set(current, next, weight + 1);
			}
			checkRep();
		} catch (IOException e) {
			System.out.println("File error!");
		}
	}

	// TODO checkRep
	public void checkRep() {
		assert graph != null;
	}

	/**
	 * Generate a poem.
	 * 
	 * @param input string from which to create the poem
	 * @return poem (as described above)
	 */
	public String poem(String input) {
		StringBuilder poem = new StringBuilder();
		List<String> in = new ArrayList<>();
		String[] inputstring = input.split(" ");
		in.addAll(Arrays.asList(inputstring));
		Map<String, Integer> sou = new HashMap<>();
		Map<String, Integer> tar = new HashMap<>();
		int len = in.size();
		for(int i=0;i<len-1;i++) {
			int max = 0;
			String word = "";
			poem.append(in.get(i)).append(" ");
			String source = in.get(i).toLowerCase();//统一变为小写
			String target = in.get(i+1).toLowerCase();//统一变为小写
			sou = graph.sources(target);
			tar = graph.targets(source);
			for(String istr: sou.keySet()) {
				if(tar.containsKey(istr)&&tar.get(istr)+sou.get(istr)>max) {
					word = istr;
					max = tar.get(istr)+sou.get(istr);
				}
			}
			if(max!=0) 
				poem.append(word+" ");
		}
		poem.append(in.get(len-1));//加入最后一个单词
		return poem.toString();
	}

	// TODO toString()
	public String toString() {
		return "graph:\n" + graph.toString();
	}
}
