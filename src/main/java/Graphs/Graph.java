package Graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {

	private int V;
	private LinkedList<Integer> adj[];


	Graph(int v){
		this.V = v;
		this.adj = new LinkedList[v];
		for(int i = 0; i < v; i++){
			adj[i] = new LinkedList<>();
		}
	}
	public void addEdge(int u, int v){
		if (u > V || v > V)  return;
		adj[u].add(v);
	}

	private void DFSUtil(int v, boolean[] visited){
		System.out.println(v);
		visited[v] = true;
		Iterator<Integer> iterator = adj[v].iterator();
		while(iterator.hasNext()){
			int next = iterator.next();
			if(!visited[next]) DFSUtil(next, visited);
		}
	}

	public void DFS(int v){
		boolean visited[] = new boolean[V];
		DFSUtil(v, visited);
	}

	public void DFSComplete(){
		boolean visited[] = new boolean[V];
		for(int i = 0; i < V; i++){
			if(!visited[i]) DFSUtil(i, visited);
		}
	}
}
