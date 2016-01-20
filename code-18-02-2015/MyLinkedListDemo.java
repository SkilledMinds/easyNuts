
import java.util.Iterator;

public class MyLinkedListDemo {

	public static void main(String args[]) {
		LinkedList lnlst = new LinkedList();
		lnlst.add("11");
		lnlst.add("12");
		lnlst.add("13");
		lnlst.add("14");
		lnlst.add("15");

		lnlst.displayLinkedList();

	}

}

class LinkedList implements Iterator<LinkedList.Node>, Iterable<LinkedList.Node> {

	private Node head;
	private Node lastVisited;
	private Node OpsNode;
	private int size = 0;

	class Node {

		private Object objVal;
		private Node next;

		public Node() {
			super();
		}

		public Node(Object o) {
			this.objVal = o;
			this.next = null;
		}

		@Override
		public String toString() {

			return this.objVal.toString();
		}
	}

	public void add(Object o) {
		Node newNode = new Node(o);
		if (this.head == null) {
			this.head = newNode;
			this.OpsNode = this.head;
		} else {
			this.OpsNode.next = newNode;
			this.OpsNode = newNode;
		}
		size++;
		System.out.println("total added item :" + size + " and Node is :" + newNode.toString());
	}

	private int visited = 0;

	@Override
	public boolean hasNext() {
		boolean hasNext = true;
		if (visited++ < size)
			return hasNext;
		else
			return !hasNext;
	}

	@Override
	public Node next() {
		lastVisited = OpsNode;
		OpsNode = OpsNode.next;
		return lastVisited;
	}

	@Override
	public Iterator<Node> iterator() {
		OpsNode = head;
		return this;
	}

	public void displayLinkedList() {
		Iterator<Node> it = iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
