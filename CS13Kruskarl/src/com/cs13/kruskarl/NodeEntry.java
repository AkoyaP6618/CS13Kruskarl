package com.cs13.kruskarl;

public class NodeEntry {

	private Node firstNode;
	private Node secondNode;
	private int weight;
	
	/**
	 * Ein Kanteneintrag mit den jeweiligen Knoten und dem Gewicht
	 * @author Sebastian
	 */
	public NodeEntry(Node first, Node second, int weight){
		this.firstNode = first;
		this.secondNode = second;
		this.weight = weight;
	}

	public Node getFirstNode() {
		return firstNode;
	}

	public Node getSecondNode() {
		return secondNode;
	}

	public int getWeight() {
		return weight;
	}
	
	
	
}
