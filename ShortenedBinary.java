public class ShortenedBinary {
	private int value, length;

	public ShortenedBinary(int val, int len) {
		value = val;
		length = len;
	}

	public int getValue() { return value; }
	public int getLength() { return length; }

	public ShortenedBinary add(boolean val) {
		int newVal = val ? (value*2)|1 : value*2;
		return new ShortenedBinary(newVal, length + 1);
	}
}
