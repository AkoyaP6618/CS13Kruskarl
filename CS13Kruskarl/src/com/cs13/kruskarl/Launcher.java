package com.cs13.kruskarl;

import java.util.ArrayList;

public class Launcher {

	public static void main(String[] args) {

		ArrayList<Node> nodeList = new ArrayList<>();
		//irgendeineQueue erstellen mit Nodeentries
		
		// TODO MATTHIAS die unteren beiden Kommentare
		// Alle Nodes erstellen und der Nodeliste hinzufügen
		// Alle Kanten erstellen und Kanten der Queue hinzufügen

		// while(!IrgendeineQueue.isEmpty()){
		while(true){
			
			// NodeEntry entry = IrgendeineQueue.remove();
			NodeEntry entry = new NodeEntry(null, null, 0);
			
			Node firstNode = entry.getFirstNode();
			Node secondNode = entry.getSecondNode();
			
			if( firstNode.isVisited() == false
				&& secondNode.isVisited() == false){
				
				firstNode.visit();
				secondNode.visit();
				firstNode.setRoot(true);
				secondNode.setParent(firstNode);
				
			}
			
			if( firstNode.isVisited() == true
					&& secondNode.isVisited() == true
					&& !firstNode.getRootNode().equals(secondNode.getRootNode())){
					
					secondNode.setParent(firstNode);
					secondNode.setRoot(false);
					
				}
			
			if( firstNode.isVisited() == true
					&& secondNode.isVisited() == false){
					
					secondNode.visit();
					secondNode.setParent(firstNode);
					
				}
			
			if( firstNode.isVisited() == false
					&& secondNode.isVisited() == true){
					
					firstNode.visit();
					firstNode.setParent(secondNode);
					
				}
			
			
			String printOut = "Noch nicht besucht:";
			for (Node node : nodeList) {
				if(node.isVisited() == false){
					printOut += " " + node.getName();
				}
			}
			
		}
		
	}

}
