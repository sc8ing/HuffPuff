public class HuffTreeC<T> implements HuffTree<T> {
	private HuffTree left;
	private HuffTree right;
	private T symbol;
	private int weight;

	public HuffTreeC(HuffTree l, HuffTree r, T s, int w) {
		left = l;
		right = r;
		symbol = s;
		weight = w;
	}
	// for non-leaf nodes
	public HuffTreeC(HuffTree l, HuffTree r) {
		left = l;
		right = r;
		weight = l.getWeight() + r.getWeight();
	}

	public HuffTree getLeft() { return left; }
	public HuffTree getRight() { return right; }
	public T getSymbol() { return symbol; }
	public int getWeight() { return weight; }

	public boolean isLeaf() { return getLeft() == null && getRight() == null; }
	public int compareTo(HuffTree oth) {
		if (getWeight() > oth.getWeight()) return 1;
		if (getWeight() < oth.getWeight()) return -1;
		return 0;
	}
	public String toString() {
		if (isLeaf())
			return new StringBuilder("{" + getSymbol() + ", " + getWeight() + "}").toString();

		return new StringBuilder("{" + "[inner node], " + getWeight() +
								 "L(" + getLeft() + ") R(" + getRight() + ")").toString();
	}
}
