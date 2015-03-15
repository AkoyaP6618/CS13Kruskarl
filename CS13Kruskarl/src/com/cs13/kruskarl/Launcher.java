package com.cs13.kruskarl;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Launcher {

    public static void main(String[] args) {

	File file = new File("Daten9A.txt");
	InputReader reader = new InputReader(file);

	ArrayList<Node> nodeList;
	PriorityQueue<Edge> queue;

	// Alle Nodes erstellen und der Nodeliste hinzufuegen

	nodeList = reader.readNodes();

	// Alle Kanten erstellen und Kanten der Queue hinzufuegen

	queue = reader.readEdges(nodeList);

	// while(!IrgendeineQueue.isEmpty()){
	while (!queue.isEmpty()) {

	    // NodeEntry entry = IrgendeineQueue.remove();
	    Edge entry = queue.poll();

	    Node firstNode = entry.getFirstNode();
	    Node secondNode = entry.getSecondNode();

	    if (firstNode.isVisited() == false && secondNode.isVisited() == false) {

		firstNode.visit();
		secondNode.visit();
		firstNode.setRoot(true);
		secondNode.setParent(firstNode);
	    }

	    if (firstNode.isVisited() == true && secondNode.isVisited() == true
		    && !firstNode.getRootNode().equals(secondNode.getRootNode())) {

		secondNode.setParent(firstNode);

	    }

	    if (firstNode.isVisited() == true && secondNode.isVisited() == false) {

		secondNode.visit();
		secondNode.setParent(firstNode);

	    }

	    if (firstNode.isVisited() == false && secondNode.isVisited() == true) {

		firstNode.visit();
		firstNode.setParent(secondNode);

	    }

	    // Ausgabe der noch nicht besuchten Knoten
	    String printOut = "Noch nicht besucht:";
	    String liste = "";
	    for (Node node : nodeList) {
		if (node.isVisited() == false) {
		    liste += " " + node.getName();
		}
	    }
	    if(liste != ""){
	    	System.out.println(printOut + liste);
	    }
	}
	
	System.out.println("Alle Knoten wurden besucht.");

	for (Node node : nodeList) {
	    System.out.println("I am " + node.getName() + " my Parent is "
		    + node.getParent().getName());
	}

    }
}
