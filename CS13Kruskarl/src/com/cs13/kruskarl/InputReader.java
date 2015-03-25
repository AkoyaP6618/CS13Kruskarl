package com.cs13.kruskarl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Liest die Knoten und Kanten eines ungerichteten ungerichteten Graphen aus
 * einer Datei aus und fuegt sie automatisch zu einer Liste der Knoten und
 * PriorityQueue der Kanten hinzu.
 * 
 * @author Matthias Thurow
 */
public class InputReader {

    private File file;

    public InputReader(File file) {
	this.file = file;
    }

    /**
     * erstellt Liste aller vorhandenen Knoten
     * 
     * @return nodeList
     */
    public ArrayList<Node> readNodes() {
	ArrayList<Node> nodeList = new ArrayList<>();

	try {
	    BufferedReader input = new BufferedReader(new FileReader(file));

	    while (input.ready()) {
		String line = input.readLine();
		String[] parameters = line.split("\t", 3); // getrennt werden die Werte durch Tabulatoren

		String name1 = parameters[0];
		String name2 = parameters[1]; //moegliche Namen fuer neue Knoten
		boolean containsName1 = false;
		boolean containsName2 = false;

		// prueft, ob die Knoten irgendwo in der Liste vorhanden sind
		for (Node node : nodeList) {
		    if (node.getName().equals(name1)) {
			containsName1 = true;
		    }
		    if (node.getName().equals(name2)) {
			containsName2 = true;
		    }
		}
		// falls ein Knoten nicht vorhanden ist, wird er hier hinzugefuegt
		if (!containsName1) {
		    nodeList.add(new Node(name1));
		}
		if (!containsName2) {
		    nodeList.add(new Node(name2));
		}
	    }
	    input.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return nodeList;
    }

    /**
     * erstellt PriorityQueue fuer die Kanten des Graphen. Diese werden
     * automatisch nach Gewicht sortiert
     * 
     * @param nodeList
     * @return priorityQueue
     */
    public PriorityQueue<Edge> readEdges(ArrayList<Node> nodeList) {

	Comparator<Edge> comparator = new Comparator<Edge>() { // Komparator fuer PriorityQueue

	    @Override
	    public int compare(Edge edge1, Edge edge2) {
		int cost1 = edge1.getWeight();
		int cost2 = edge2.getWeight();
		int r = 0;
		// erkennt welche Kante mehr kostet, damit die Queue sich spaeter selbst sortieren kann
		if (cost1 > cost2) {
		    r = 1;
		} else {
		    if (cost1 == cost2) {
			r = 0;
		    } else {
			r = -1;
		    }
		}
		return r;
	    }
	};
	PriorityQueue<Edge> queue = new PriorityQueue<Edge>(comparator);
	Node node1 = null;
	Node node2 = null;

	try {
	    BufferedReader input = new BufferedReader(new FileReader(file));

	    while (input.ready()) {
		String line = input.readLine();
		String[] parameters = line.split("\t");
		for (Node node : nodeList) { // sucht Knoten der Kante in der nodeList, um eine Referenz darauf zu verwenden
		    if (node.getName().equals(parameters[0])) {
			node1 = node;
		    }
		    if (node.getName().equals(parameters[1])) {
			node2 = node;
		    }
		}
		queue.add(new Edge(node1, node2, Integer.parseInt(parameters[2])));

	    }

	    input.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return queue;

    }

}
