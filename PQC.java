import java.util.*;

public class PQC<Item extends Comparable<Item>> implements PQ<Item> {
	private int size;
	private Node first;
	private Node last;

	public PQC() {
		first = new Node();
		last = new Node();
		first.next = last;
		last.previous = first;
		size = 0;
	}
	private class Node {
		Item item;
		Node previous;
		Node next;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	public void add(Item item) {
		if (isEmpty()) {
			pushLeft(item);
			return;
		}
		Node node = last.previous; // "last" & "first" nodes are just helper nodes
		if (node.item.compareTo(item) <= 0) { // new last node
			pushRight(item);
			return;
		}
		while (!node.previous.equals(first) && node.previous.item.compareTo(item) > 0) {
			node = node.previous;
		}
		 if (node.previous.equals(first)) { // new first node
		 	pushLeft(item);
			return;
		 }
		// adding a node to node's left
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = node;
		newNode.previous = node.previous;
		node.previous.next = newNode;
		node.previous = newNode;
		size++;
	}
	public Item remove() {
		return popLeft();
	}
	public void pushRight(Item item) {
	//add to the end of the list (push it in on the right)
		Node node = new Node();
		node.item = item;
		node.previous = last.previous;
		node.previous.next = node;
		last.previous = node;
		node.next = last;
		size++;
	}
	public void pushLeft(Item item) {
	// add to the beginning of the list (push it in on the left)
		Node node = new Node();
		node.item = item;
		node.previous = first;
		node.next = first.next;
		node.next.previous = node;
		first.next = node;
		size++;
	}
	public Item popLeft() {
	// move the list left (return the first)
		Item item = first.next.item;
		first.next = first.next.next;
		first.next.previous = first;
		size--;
		return item;
	}
	public Item popRight() {
	// move the list right (return the last one)
		Item item = last.previous.item;
		last.previous = last.previous.previous;
		last.previous.next = last;
		size--;
		return item;
	}
	public String toString() {
		String string = "";
		int i = 0;
		for (Node n=first.next; n.next!=null; n=n.next) {
			string += "Item " + i + ": " + n.item + "\n";
			i++;
		}
		return string;
	}
}
