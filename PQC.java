import java.util.*;

public class PQC<T extends Comparable<T>> implements PQ<T> {
	private int size;
	private Node first;
	private Node last;

	private class Node {
		T item;
		Node previous;
		Node next;
	}

	public PQC() {
		first = new Node();
		last = new Node();
		first.next = last;
		last.previous = first;
		size = 0;
	}
	public boolean isEmpty() { return size == 0; }
	public int size() { return size; }
	public T remove() { return popLeft(); }

	public void add(T item) {
		if (isEmpty()) { pushLeft(item); return; }

		Node node = last.previous; // "last" & "first" nodes are just helper nodes to deal w/ null

		if (node.item.compareTo(item) <= 0) { pushRight(item); return; }

		while (!node.previous.equals(first) && node.previous.item.compareTo(item) > 0)
			node = node.previous;

		if (node.previous.equals(first)) { pushLeft(item); return; }

		// add a new node to node's left
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = node;
		newNode.previous = node.previous;
		node.previous.next = newNode;
		node.previous = newNode;
		size++;
	}
	private void pushRight(T item) {
		// add to the end of the list (push it in on the right)
		Node node = new Node();
		node.item = item;
		node.previous = last.previous;
		node.previous.next = node;
		last.previous = node;
		node.next = last;
		size++;
	}
	private void pushLeft(T item) {
		// add to the beginning of the list (push it in on the left)
		Node node = new Node();
		node.item = item;
		node.previous = first;
		node.next = first.next;
		node.next.previous = node;
		first.next = node;
		size++;
	}
	private T popLeft() {
		// move the list left (return the first)
		T item = first.next.item;
		first.next = first.next.next;
		first.next.previous = first;
		size--;
		return item;
	}
	public String toString() {
		StringBuilder string = new StringBuilder("");
		int i = 0;
		for (Node n=first.next; n.next!=null; n=n.next) {
			string.append("T " + i + ": " + n.item + "\n");
			i++;
		}
		return string.toString();
	}
}
