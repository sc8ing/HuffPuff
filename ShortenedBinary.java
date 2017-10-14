public class ShortenedBinary {
  private int value, length;

  public ShortenedBinary(int val, int len) { value = val; length = len; }
  public int getValue() { return value; }
  public int getLength() { return length; }
  public void setValue(int val) { value = val; }
  public void setLength(int len) { length = len; }
  
  public ShortenedBinary addZero() {
    int val = value * 2;
    int len = length + 1;
    return new ShortenedBinary(val, len);
  }
  public ShortenedBinary addOne() {
    int val = (value * 2) | 1;
    int len = length + 1;
    return new ShortenedBinary(val, len);
  }
}
