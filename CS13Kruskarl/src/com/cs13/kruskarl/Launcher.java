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
	String kanonischeAusgabe = "";

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

	boolean somethingHappened = false;
	
	while (!queue.isEmpty()) {

		somethingHappened = false;
		
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

		secondNode.removeParent(secondNode);
		secondNode.addParent(firstNode);
		secondNode.setRootNode(firstNode);
		tree.add(entry);

		output += "added edge " + firstNode.getName() + " - " + secondNode.getName()
			+ " Gewicht: " + entry.getWeight() + "\n";
		somethingHappened = true;
	    }

	    // beide Knoten besucht, aber unterschiedliche Wurzel
	    if (firstNode.isVisited() && secondNode.isVisited()
		    && !firstNode.getRootNode().equals(secondNode.getRootNode())) {

		Node firstRoot = firstNode.getRootNode();
		Node secondRoot = secondNode.getRootNode();

		for (Node node : nodeList) {
		    if (node.isVisited() && node.getRootNode().equals(secondRoot)) {
			node.setRootNode(firstRoot);
		    }
		}
		secondRoot.setRoot(false);
		secondNode.addParent(firstNode);

		tree.add(entry);

		output += "added edge " + firstNode.getName() + " - " + secondNode.getName()
			+ " Gewicht: " + entry.getWeight() + "\n";
		somethingHappened = true;
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
		somethingHappened = true;
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
		somethingHappened = true;
	    }

	    // --> wenn beide besucht mit gleicher Wurzel passiert nichts

	    // Hier wird lediglich die Ausgabe von Informationen vorbereitet und formatiert
	    
	    if(somethingHappened){
	    String printOut = "Noch nicht besucht:";
	    String liste = "";
	    for (Node node : nodeList) {
		if (node.isVisited() == false) {
		    liste += " " + node.getName();
		}
	    }
	    if(liste != ""){
	    	output += printOut + liste + "\n";
	    }else{
	    	output += "Alle Knoten wurden besucht.\n";
	    }
	    
	    output += "Rootknoten:";
	    for (Node node : nodeList) {
		if (node.isRoot()) {
		    output += " " + node.getName();
		}
	    }
	    output += "\n\n";

	    // kanonische element ausgabe
	    kanonischeAusgabe += "\n\nRoot:\t";
	    for (Node node : nodeList) {
		if (node.getRootNode() != null) {
			if(node.getRootNode().getName().length() < node.getName().length()){
				kanonischeAusgabe += node.getRootNode().getName() + "_ ";
			}else{
				kanonischeAusgabe += node.getRootNode().getName() + " ";
			}
		} else {
		    kanonischeAusgabe += node.getName() + " ";
		}
	    }

	    kanonischeAusgabe += "\nNode:\t";
	    for (Node node : nodeList) {
	    	if(node.getRootNode() != null && node.getName().length() < node.getRootNode().getName().length()){
	    		kanonischeAusgabe += node.getName() + "_ ";
			}else{
				kanonischeAusgabe += node.getName() + " ";
			}
	    }}
	}

	// Matthias Thurow

	output += "\n*Hinweis, die \"_\" dienen nur zur Einrueckung der einstelligen Zahlen.*\nKanonische Ausgabe:\n" + kanonischeAusgabe;
	output += "\n\n\t-- Alle Kanten des Minimalgeruests im Ueberblick --\n";
	for (Edge edge : tree) { // Ausgabe des Minimalgeruests
	    output += "\t\t{ " + edge.getFirstNode().getName() + " , " + edge.getSecondNode().getName()
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
