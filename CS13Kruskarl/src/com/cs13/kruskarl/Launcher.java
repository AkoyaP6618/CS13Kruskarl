package com.cs13.kruskarl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Launcher {

    public static void main(String[] args) {

	ArrayList<Node> nodeList = new ArrayList<>();

	Comparator<Edge> comparator = new Comparator<Edge>() { // Komparator fuer PriorityQueue

	    @Override
	    public int compare(Edge o1, Edge o2) {
		int c1 = o1.getWeight();
		int c2 = o2.getWeight();
		int r = 0;
		if (c1 > c2) {
		    r = 1;
		} else {
		    if (c1 == c2) {
			r = 0;
		    } else {
			r = -1;
		    }
		}
		return 0;
	    }
	};

	// Alle Nodes erstellen und der Nodeliste hinzufuegen

	String regexp = "([1-9]*|[A-Z]*|[a-z]*)\t([1-9]*|[A-Z]*|[a-z]*)\t([1-9]*)"; // input form der Daten

	try {
	    File file = new File("Daten9A.txt");
	    BufferedReader input = new BufferedReader(new FileReader(file));
	    Pattern p = Pattern.compile(regexp);

	    while (input.readLine() != null) {
		String line = input.readLine();
		Matcher matcher = p.matcher(line);

		if (matcher.find()) {
		    String name1 = matcher.group(1);
		    String name2 = matcher.group(2); //moegliche Namen fuer neue Knoten
		    boolean containsName1 = false;
		    boolean containsName2 = false;

		    for (Node node : nodeList) {
			if (node.getName().equals(name1)) {
			    containsName1 = true;
			}
			if (node.getName().equals(name2)) {
			    containsName2 = true;
			}
		    }
		    if (containsName1) {
			nodeList.add(new Node(name1));
		    }
		    if (containsName2) {
			nodeList.add(new Node(name2));
		    }
		}
	    }
	    input.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Alle Kanten erstellen und Kanten der Queue hinzufuegen
	PriorityQueue<Edge> queue = new PriorityQueue<Edge>(comparator);

	try {
	    File file = new File("Daten9A.txt");
	    BufferedReader input = new BufferedReader(new FileReader(file));

	    String line = input.readLine();
	    Pattern p = Pattern.compile(regexp);
	    Matcher matcher = p.matcher(line);

	    queue.add(new Edge(new Node(matcher.group(1)), new Node(matcher.group(2)), Integer
		    .parseInt(matcher.group(3))));

	    input.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

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
		secondNode.setRoot(false);

	    }

	    if (firstNode.isVisited() == true && secondNode.isVisited() == false) {

		secondNode.visit();
		secondNode.setParent(firstNode);

	    }

	    if (firstNode.isVisited() == false && secondNode.isVisited() == true) {

		firstNode.visit();
		firstNode.setParent(secondNode);

	    }

	    String printOut = "Noch nicht besucht:";
	    for (Node node : nodeList) {
		if (node.isVisited() == false) {
		    printOut += " " + node.getName();
		}
	    }

	}

    }
}
