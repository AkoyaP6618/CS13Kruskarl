package com.cs13.kruskarl;

import java.util.Comparator;

public class SortList implements Comparator<Node>{
 

@Override
public int compare(Node node1, Node node2) {
	return node1.getName().compareTo(node2.getName());
}
}
