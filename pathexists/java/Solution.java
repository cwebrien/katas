/**
 * Instructions to candidate:
 *  1) Run this code in the REPL to observe its behaviour. The 
 *     execution entry point is main().
 *  2) Consider adding some more tests.
 *  3) Implement pathExists() correctly.
 *  4) If time permits, discuss possible extensions.
 */


import java.util.*;

public class Solution
{
	/** 
	 * In this problem, a graph is a collection of directed edges 
	 * between vertices stored as key:value pairs in a map. For 
	 * vertex 1, for example, g[1] contains the list of * vertex IDs
	 * to which vertex 1 is connected.
	 */
	
		
	/**
	 * Determines whether there exists a path between from vertex
	 * v1 to vertex v2 in directed graph g. For simplicity, we assume
	 * that there always exists a path between a vertex and itself.
	 * Return true if there exists a path, and false otherwise.
	 */
	public static boolean pathExists(Map<Integer, List<Integer>> graph, Integer v1, Integer v2)
	{
		// Quick sanity check
		if(v1 == null || v2 == null)
		{
			return false;
		}
		
		// Data structures for searching
		Queue<Integer> toExplore = new LinkedList<Integer>();
		Set<Integer> explored    = new HashSet<Integer>();
		toExplore.add(v1);
		
		// Keep searching while there are new nodes to explore
		while(!toExplore.isEmpty())
		{
			// Get the current vertex
			Integer currentVertex = toExplore.remove();
			
			// Check if we found a path
			if(currentVertex.equals(v2))
			{
				return true;
			}
			
			// Don't consider vertices that we have already seen
			// to avoid infinite loops in cycles
			if(!explored.contains(currentVertex))
			{
				// Get the edges from current vertex
				if(graph.containsKey(currentVertex))
				{
					toExplore.addAll(graph.get(currentVertex));
				}
			
				// Mark that we've seen this vertex and get next
				explored.add(currentVertex);
			}
		} 
		
		return false;
	}
	
	/**
	 * Generates a directed graph to use in our testing. Some quick
	 * notes on the graph:
	 *  i)   1 is connected to 2 (adjacent)
	 *  ii)  1 is connected to 3 (via 2)
	 *	iii) 2 is not connected to 1
	 *	iv)  3 is connected to 2 (adjacent)
	 *  v)   3 is connected to 4 (adjacent)
	 *	vi)  1 is NOT connected to 11
	 *	vii) there exists a directed cycle 11->12->13->11->...
	 */
	public static Map<Integer, List<Integer>> generateTestGraph()
	{		
		// Create our edges
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(2);
		list3.add(4);
		List<Integer> list11 = new ArrayList<Integer>();
		list11.add(12);
		List<Integer> list12 = new ArrayList<Integer>();
		list12.add(13);
		List<Integer> list13 = new ArrayList<Integer>();
		list13.add(11);
		
		// Create graph
		HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		graph.put(1, list1);
		graph.put(2, list2);
		graph.put(3, list3);
		graph.put(11, list11);
		graph.put(12, list12);
		graph.put(13, list13);
		
		// Print the graph to make things more obvious
		System.out.println("Graph:");
		for(Integer vertex : graph.keySet())
		{
			System.out.println(" " + vertex + ": " + graph.get(vertex));
		}
		
		return graph;
	}
	
	/**
	 * Runs some tests. You should feel free to add some other sensible
	 * test cases.
	 */
	public static void doTestsPass()
	{
		// Build a test graph
		Map<Integer, List<Integer>> graph = generateTestGraph();
		boolean result = true;
	
		// Some connections that exist
		if(!pathExists(graph, 1, 2)) System.out.println("1->2 broken");
		result = result && pathExists(graph, 1, 1);
		result = result && pathExists(graph, 1, 2);
		
		// Some connections that do not exist
		result = result && !pathExists(graph, 2, 1);
		result = result && !pathExists(graph, 1, 11);	
	
		// todo: Please add some more test cases below
		result = result && pathExists(graph, 1, 3);
		result = result && pathExists(graph, 1, 4);
		result = result && pathExists(graph, 3, 2);
		result = result && pathExists(graph, 3, 4);
		result = result && pathExists(graph, 11, 13);
		result = result && pathExists(graph, 12, 11);
		result = result && !pathExists(graph, 1, 50);
		
		// Check test results
		if(result)
		{
			System.out.println("All tests pass!");
		}
		else
		{
			System.out.println("There are test failures.");
		}
	}
	
	/**
	 * Execution entry point.
	 */		
	public static void main(String args[])
	{
		doTestsPass();
	}
}