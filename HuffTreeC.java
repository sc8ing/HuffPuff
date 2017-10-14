public class HuffTreeC implements HuffTree {
	private HuffTree left;
	private HuffTree right;
	private char symbol;
	private int value; // values are essentially the frequency
	private int order;

	public HuffTreeC(HuffTree l, HuffTree r, char s, int f) {
		left = l;
		right = r;
		symbol = s;
		value = f;
	}
	public HuffTreeC(HuffTree l, HuffTree r) {
		left = l;
		right = r;
		value = 0; // need this for getWeight()
	}

	public HuffTree getLeft() { return left; }
	public HuffTree getRight() { return right; }
	public char getSymbol() { return symbol; }
	public int getValue() { return value; }
	public int getWeight() {
		int w = value;
		if (left != null) w += left.getWeight();
		if (right != null) w += right.getWeight();
		return w;
	}

	public void setLeft(HuffTree t) { left = t; }
	public void setRight(HuffTree t) { right = t; }
	public void setSymbol(char s) { symbol = s; }
	public void setValue(int f) { value = f; }

	public int compareTo(HuffTree oth) {
		if (getWeight() > oth.getWeight()) return 1;
		if (getWeight() < oth.getWeight()) return -1;
		return 0;
	}
	public String toString() {
		String s = "{" + ((value == 0) ? "innerNode" : symbol) + ", " + getWeight() + "}";
		if (getLeft() != null) s += "L(" + getWeight() + "):" + getLeft().toString();
		if (getRight() != null) s += "R(" + getWeight() + "):" + getRight().toString();
		return s;
	}
}
