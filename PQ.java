public interface PQ<Item> {
	public boolean isEmpty();
	public int size();
	public void pushLeft(Item item);
	public void pushRight(Item item);
	public Item popLeft();
	public Item popRight();
	public String toString();
	public void add(Item item);
	public Item remove();
}
