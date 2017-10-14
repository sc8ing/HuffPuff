import java.util.*;
import java.io.*;

public class CharFreqTable implements SymbolTable<Character, Integer> {
	private Map<Character, Integer> fT;

	public CharFreqTable(FileReader file) {
		fT = new HashMap<Character, Integer>();

		Integer curFreq;
		int ch;
		char c;
		try {
			while ((ch = file.read()) != -1) {
				c = (char) ch;
				curFreq = get(c);
				curFreq = (curFreq == null) ? 1 : curFreq + 1;
				put(c, curFreq);
			}
		} catch (IOException e) {
			System.out.println("IOException initializing frequency table. " + e);
		}
	}
	public void put(Character k, Integer v) { fT.put(k, v); }
	public Integer get(Character k) { return fT.get(k); }
        public Integer remove(Character k) { return fT.remove(k); }
	public int size() { return fT.size(); }
	public boolean isEmpty() { return fT.isEmpty(); }
	public Set<Character> keySet() { return fT.keySet(); }
}
