package com.cs13.kruskarl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class InputReader {

    private File file;

    public InputReader(File file) {
	this.file = file;
    }

    public ArrayList<Node> readNodes() {
	ArrayList<Node> nodeList = new ArrayList<>();

	try {
	    BufferedReader input = new BufferedReader(new FileReader(file));

	 
	    while (input.ready()) {
		String line = input.readLine();
		String[] parameters = line.split("\t", 3);

		String name1 = parameters[0];
		String name2 = parameters[1]; //moegliche Namen fuer neue Knoten
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

    public PriorityQueue<Edge> readEdges() {

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
		return r;
	    }
	};
	PriorityQueue<Edge> queue = new PriorityQueue<Edge>(comparator);

	try {
	    BufferedReader input = new BufferedReader(new FileReader(file));

	    while (input.ready()) {
		String line = input.readLine();
		String[] parameters = line.split("\t");
		queue.add(new Edge(new Node(parameters[0]), new Node(parameters[1]), Integer
			.parseInt(parameters[2])));

	    }

	    input.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return queue;

    }

}
