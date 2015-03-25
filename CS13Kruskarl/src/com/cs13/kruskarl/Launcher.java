package com.cs13.kruskarl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Launcher {

    public static void main(String[] args) {

	// Sebastian Wolff

	String output = "";

	// Einlesen der Daten, Benutzer waehlt die Datei
	File file = new File("Daten9A.txt");

	int i = JOptionPane
		.showConfirmDialog(
			null,
			"Bitte geben sie eine Datei mit Kanteneintraegen an.\nStandardmaessig wird die \"Daten9A.txt\" benutzt, falls keine Datei ausgewaehlt wurde.",
			"Hinweis", JOptionPane.CANCEL_OPTION);
	if (i == JOptionPane.CANCEL_OPTION) {
	    System.exit(0);
	}

	JFileChooser fileChooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	fileChooser.setFileFilter(filter);
	int returnVal = fileChooser.showOpenDialog(null);

	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    file = fileChooser.getSelectedFile();
	}

	output += "-- Es wurde die Datei " + file.getName() + " verwendet. --\n\n";

	InputReader reader = new InputReader(file);

	// Matthias Thurow

	ArrayList<Node> nodeList;
	PriorityQueue<Edge> queue;
	ArrayList<Edge> tree = new ArrayList<Edge>(); // Liste der Kanten des Minimalgeruests

	// Alle Nodes erstellen und der Nodeliste hinzufuegen

	nodeList = reader.readNodes();
	Collections.sort(nodeList, new SortList());
	// Alle Kanten erstellen und Kanten der Queue hinzufuegen

	queue = reader.readEdges(nodeList);

	while (!queue.isEmpty()) {

	    Edge entry = queue.poll();

	    // Sebastian Wolff

	    Node firstNode = entry.getFirstNode();
	    Node secondNode = entry.getSecondNode();

	    // beide Knoten unbesucht
	    if (!firstNode.isVisited() && !secondNode.isVisited()) {

		firstNode.visit();
		secondNode.visit();
		firstNode.setRoot(true);
		firstNode.setRootNode(firstNode);
		secondNode.addParent(firstNode);
		secondNode.removeParent(secondNode);
		secondNode.setRootNode(firstNode);
		tree.add(entry);

		output += "added edge " + firstNode.getName() + " - " + secondNode.getName()
			+ " Gewicht: " + entry.getWeight() + "\n";
	    }

	    // beide Knoten besucht, aber unterschiedliche Wurzel
	    if (firstNode.isVisited() && secondNode.isVisited()
		    && !firstNode.getRootNode().equals(secondNode.getRootNode())) {

		secondNode.addParent(firstNode);
		// Muesste ein Fehler sein
		// soll -> secondNode.getRootNode().setRoot(false);
		secondNode.getRootNode().setRoot(false);
		secondNode.setRootNode(firstNode.getRootNode());

		tree.add(entry);

		output += "added edge " + firstNode.getName() + " - " + secondNode.getName()
			+ " Gewicht: " + entry.getWeight() + "\n";
	    }

	    // zweiter Knoten unbesucht
	    if (firstNode.isVisited() && !secondNode.isVisited()) {

		secondNode.visit();
		secondNode.addParent(firstNode);
		secondNode.removeParent(secondNode);
		secondNode.setRootNode(firstNode.getRootNode());
		tree.add(entry);

		output += "added edge " + firstNode.getName() + " - " + secondNode.getName()
			+ " Gewicht: " + entry.getWeight() + "\n";
	    }

	    // erster Knoten unbesucht
	    if (!firstNode.isVisited() && secondNode.isVisited()) {

		firstNode.visit();
		firstNode.addParent(secondNode);
		firstNode.removeParent(firstNode);
		firstNode.setRootNode(secondNode.getRootNode());
		tree.add(entry);

		output += "added edge " + firstNode.getName() + " - " + secondNode.getName()
			+ " Gewicht: " + entry.getWeight() + "\n";
	    }

	    // --> wenn beide besucht mit gleicher Wurzel passiert nichts

	    // Ausgabe der noch nicht besuchten Knoten
	    String printOut = "Noch nicht besucht:";
	    String liste = "";
	    for (Node node : nodeList) {
		if (node.isVisited() == false) {
		    liste += " " + node.getName();
		}
	    }
	    if (liste != "") {
		output += printOut + liste + "\n";
	    }

	    output += "Rootknoten:";
	    for (Node node : nodeList) {
		if (node.isRoot()) {
		    output += " " + node.getName();
		}
	    }
	    output += "\n";
	}

	output += "\n-- Alle Knoten wurden besucht! --\n";

	// Matthias Thurow

	output += "\n-- Alle Kanten des Minimalgeruests im Ueberblick --\n";
	for (Edge edge : tree) { // Ausgabe des Minimalgeruests
	    output += "{ " + edge.getFirstNode().getName() + " , " + edge.getSecondNode().getName()
		    + " , " + edge.getWeight() + " }" + "\n";
	}

	// Sebastian Wolff

	// Ausgabe in einem extra-Fenster
	JFrame frame = new JFrame("Minimalgeruest");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 800);
	frame.setLocationRelativeTo(null);

	JTextArea area = new JTextArea();
	area.setEditable(false);
	area.setText(output);

	JScrollPane scrollPane = new JScrollPane(area);

	frame.add(scrollPane);
	frame.setVisible(true);
    }

}
