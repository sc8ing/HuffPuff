public interface PQ<T extends Comparable<T>> {
	public boolean isEmpty();
	public int size();
	public String toString();
	public void add(T item);
	public T remove();
}
