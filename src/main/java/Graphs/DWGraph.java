package Graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DWGraph {

	private class AdjNode {
		int v;
		int wt;
		AdjNode(int vertex, int weight){
			this.v = vertex;
			this.wt = weight;
		}
	}

	private int V;
	private LinkedList<AdjNode> adjList[];

	DWGraph(int noOfVertices){
		this.V = noOfVertices;
		adjList = new LinkedList[V];
		for(int i = 0; i < V; i++){
			adjList[i] = new LinkedList<AdjNode>();
		}
	}

	public void addEdge(int src, int des, int weight){
		AdjNode node = new AdjNode(des, weight);
		adjList[src].add(node);
		return;
	}

	private Stack<Integer> getTopologicalSortedWithSource(int src){

		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++){
			if(!visited[i]) topoDfsUtil(src, stack, visited);
		}
		return stack;
	}

	private void topoDfsUtil(int src, Stack<Integer> topoStack, boolean[] visited){

		visited[src] = true;
		Iterator<AdjNode> iterator = adjList[src].iterator();
		while(iterator.hasNext()){
			AdjNode next = iterator.next();
			if(!visited[next.v]) topoDfsUtil(next.v, topoStack, visited);
		}
		topoStack.push(src);
		return;
	}

	public void printShortestDistances(int src){
		Stack<Integer> topologicalSort = getTopologicalSortedWithSource(src);
		int[] dis = new int[V];
		for(int i = 0; i < V; i++){
			dis[i] = Integer.MAX_VALUE;
		}
		dis[src] = 0;
		while (!topologicalSort.isEmpty()){
			int u = topologicalSort.pop();
			Iterator<AdjNode> iterator = adjList[u].iterator();
			while(iterator.hasNext()){
				AdjNode next = iterator.next();
				if(dis[u] != Integer.MAX_VALUE && dis[u] + next.wt < dis[next.v]){
					dis[next.v] = dis[u] + next.wt;
				}
			}
		}
		for(int i : dis){
			if(i == Integer.MAX_VALUE){
				System.out.println("INF");
			}else {
				System.out.println(i);
			}
		}
	}

	public DWGraph getTransPose(){
		DWGraph transpose = new DWGraph(V);
		for(int i = 0; i < V; i++){
			Iterator<AdjNode> iterator = adjList[i].iterator();
			while(iterator.hasNext()){
				AdjNode next = iterator.next();
				transpose.addEdge(next.v, i, next.wt);
			}
		}
		return transpose;
	}
}
