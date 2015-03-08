package com.cs13.kruskarl;

public class Node {
	
	private Node parent;
	private String alias;
	private boolean isRoot = false;
	private boolean visited = false;
	
	/**
	 * Ein Knoten mit folgenden Funktionen/Eigenschaften:
	 * - kennt Elter-Knoten
	 * - kennt duch Iteration durch die Elterknoten den Wurzelknoten
	 * - kann entweder Root sein oder nicht
	 * - kann entweder schon besucht sein oder nicht
	 * @author Sebastian
	 */
	public Node(String name){
		alias = name;
		parent = this;
	}
	
	public Node getRootNode(){
		return null; // TODO Matthias finde durch rekursiven aufruf den Wurzelknoten
	}
	
	public void visit(){
		visited = true;
	}
	
	public void setParent(Node node){
		parent = node;
	}
	
	public void setRoot(boolean root){
		isRoot = root;
	}
	
	public String getName(){
		return alias;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public boolean isRoot(){
		return isRoot;
	}
	
	public boolean isVisited(){
		return visited;
	}

}
