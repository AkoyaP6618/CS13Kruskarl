package com.cs13.kruskarl;

public class Edge {

    private Node firstNode;
    private Node secondNode;
    private int weight;

    /**
     * Ein Kanteneintrag mit den jeweiligen Knoten und dem Gewicht
     * 
     * @author Sebastian
     */
    public Edge(Node first, Node second, int weight) {
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
