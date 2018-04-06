public interface HuffTree<T> extends Comparable<HuffTree<T>> {
	public HuffTree<T> getLeft();
	public HuffTree<T> getRight();

	public T getSymbol();
	public int getWeight();

	public boolean isLeaf();
	public int compareTo(HuffTree<T> oth);
	public String toString();
}
