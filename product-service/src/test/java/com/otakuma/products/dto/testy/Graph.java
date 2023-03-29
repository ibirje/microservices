package com.otakuma.products.dto.testy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Graph {

    private Map<String, Set<String>> adj_nodes;
    
    
    
    
    
    public Graph() {
    	adj_nodes = new HashMap<String, Set<String>>();
    }

    
	public void link(String node1, String node2) {

		putInAdj(node1, node2);
		putInAdj(node2, node1);
    }

	public void unlink(String node1, String node2) {
		removeFromAdj(node1,node2);
		removeFromAdj(node2,node1);
	}
	
	public void removeFromAdj(String node1, String node2) {
		if(adj_nodes.containsKey(node1)) {
			Set<String> nodes1 = adj_nodes.get(node1);
			nodes1.remove(node2);
		}
	}
	
	public void putInAdj(String node1, String node2) {

    	Set<String> nodes1 = adj_nodes.containsKey(node1) ? adj_nodes.get(node1) : new HashSet<>();
    	nodes1.add(node2);
    	adj_nodes.put(node1, nodes1);
	}
	
	public static boolean hasLink(Map<String, Set<String>> adj_nodes, String node1, String node2) {
		return (adj_nodes.containsKey(node1) && adj_nodes.get(node1).contains(node2)) || 
				adj_nodes.containsKey(node2) && adj_nodes.get(node1).contains(node2);
    }
	// 1 > 2 > 3 .    	1
	public static Set<List<String>> shotestPaths = new HashSet<>();
	public static Integer distance(Map<String, Set<String>> adj_nodes, String node1, String node2) {
		if(hasLink(adj_nodes, node1,node2)) 
			return 1;
		Integer dst = null, minDst = null;
		
		for(String node : adj_nodes.get(node1))
		{
			dst = distance(adj_nodes, node, node2);
			if(dst != null)
			minDst = (minDst > dst) ? dst+ 1 : minDst;
		}
		
		return minDst;
	}
	
	public static Integer minPath = null;
	
	public static void computePaths(Map<String, Set<String>> adj_nodes, String node1, String node2) {

		Set<String> closedList = new HashSet<>();
		Map<String,Integer> weightList = new HashMap<>();

		weightList.put(node1, 0);
		
		refreshWeight(adj_nodes, node1, closedList,weightList);
	}

	public static void refreshWeight(Map<String, Set<String>> adj_nodes, String start, Set<String> closedList, Map<String,Integer> weightList) {
		if(closedList.contains(start))
			return;
		closedList.add(start);
		Integer oldweight = null;
		for(String node : adj_nodes.get(start))
		{
			if(weightList.containsKey(node))
			{
				oldweight = weightList.get(node);
			}
			if(oldweight == null || oldweight > (weightList.get(start) + 1))
				weightList.put(node, (weightList.get(start) + 1));
			oldweight = null;
			
			refreshWeight(adj_nodes, start, closedList, weightList);
		}
	}
	
	
	public static String getClosestCommonLink(Map<String,Integer> weightlist, Map<String,Integer> gatewayWeightList) {
		
		String pt1 = null;
		Integer minweight = null;
		for(Entry<String, Integer> entry : weightlist.entrySet())
		{
			if(gatewayWeightList.containsKey(entry.getKey()) &&
					(minweight == null || entry.getValue() + gatewayWeightList.get(entry.getKey()) < minweight ))
			{
				pt1 = entry.getKey();
				minweight = entry.getValue() + gatewayWeightList.get(entry.getKey());
			}
		}
		return pt1;
	}
	
}
