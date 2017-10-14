public interface HuffTree extends Comparable<HuffTree> {
	public HuffTree getLeft();
	public HuffTree getRight();
	public char getSymbol();
	public int getValue();
	public int getWeight();

	public void setLeft(HuffTree t);
	public void setRight(HuffTree t);
	public void setSymbol(char s);
	public void setValue(int v);

	public int compareTo(HuffTree oth);
	public String toString();
}
