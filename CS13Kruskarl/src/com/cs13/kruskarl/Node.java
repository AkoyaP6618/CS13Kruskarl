package com.cs13.kruskarl;

import java.util.ArrayList;

public class Node {

    private ArrayList<Node> parent = new ArrayList<Node>();
    private String alias;
    private Node root;
    private boolean isRoot = false;
    private boolean visited = false;

    /**
     * Ein Knoten mit folgenden Funktionen/Eigenschaften: - kennt Elter-Knoten -
     * kennt duch Iteration durch die Elterknoten den Wurzelknoten - kann
     * entweder Root sein oder nicht - kann entweder schon besucht sein oder
     * nicht
     * 
     * @author Sebastian Wolff
     */
    public Node(String name) {
	alias = name;
	parent.add(this);
    }

    public Node getRootNode() {
	return this.root;
    }

    public void setRootNode(Node node) {
	this.root = node;
    }

    public void visit() {
	visited = true;
    }

    public void addParent(Node node) {
	parent.add(node);
    }

    public void removeParent(Node node) {
	parent.remove(node);
    }

    public void setRoot(boolean root) {
	isRoot = root;
    }

    public String getName() {
	return alias;
    }

    public ArrayList<Node> getParents() {
	return parent;
    }

    public boolean isRoot() {
	return isRoot;
    }

    public boolean isVisited() {
	return visited;
    }

}
