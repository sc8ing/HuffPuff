import java.io.*;
import java.util.*;

public class Huff {
	private static final short SIGNATURE = 0x0BC0; // code put at beginning of output file to identify as a huffed file

	private static void walkTreeFormulateBinCodes(HuffTree<Character> tree, Map<Character, ShortenedBinary> map) {
		walkTreeFormulateBinCodes(tree, map, new ShortenedBinary(0, 0));
	}
	private static void walkTreeFormulateBinCodes(HuffTree<Character> tree, Map<Character, ShortenedBinary> map, ShortenedBinary binPath) {
		// left -> add 0 to bin code, left -> add 1
		if (tree.isLeaf()) map.put(tree.getSymbol(), binPath);
		else {
			walkTreeFormulateBinCodes(tree.getLeft(), map, binPath.add(false));
			walkTreeFormulateBinCodes(tree.getRight(), map, binPath.add(true));
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Syntax: java Huff [textfilename]");
			return;
		}

		FileIO io = new FileIOC();
		FileReader inputFile;

		// build the frequency table
		Map<Character, Integer> fT = new HashMap<>();
		int c;
		try {
			inputFile = io.openInputFile(args[0]);
			while ((c = inputFile.read()) != -1) {
				char ch = (char) c;
				fT.put(ch, fT.containsKey(ch) ? fT.get(ch) + 1 : 1);
			}
			inputFile.close();
		} catch (IOException e) { System.out.println("error reading input"); }

		// make the priority queue
		// note: this custom PQ is used rather than java's built in because the
		// order in which ties are broken is important to the algorithm and
		// must match the decompressor (puff) (java's breaks ties arbitrarily)
		PQ<HuffTree<Character>> pq = new PQC<HuffTree<Character>>();
		for (char ch : fT.keySet())
			pq.add(new HuffTreeC(null, null, ch, fT.get(ch)));

		// build the full hufftree
		while (pq.size() > 1)
			pq.add(new HuffTreeC(pq.remove(), pq.remove()));

		HuffTree bigTree = pq.remove();

		// map characters to binary codes
		Map<Character, ShortenedBinary> binCodes = new HashMap<>();
		walkTreeFormulateBinCodes(bigTree, binCodes);

		// write output headers
		BinaryOut bO = io.openBinaryOutputFile();
		bO.write(SIGNATURE); 
		bO.write(fT.size());
		l("fT.size(): " + fT.size());

		for (char ch : fT.keySet()) {
			l("writing " + ch + " as " + fT.get(ch));
			bO.write(ch);
			bO.write(fT.get(ch));
		}
		
		// write the encoded data
		try {
			inputFile = io.openInputFile(args[0]);
			while ((c = inputFile.read()) != -1) {
				ShortenedBinary binCode = binCodes.get((char) c);
				l("writing " + (char) c + " as " + binCode);
				bO.write(binCode.getValue(), binCode.getLength());
			}
			inputFile.close();
		} catch (IOException e) { System.out.println("error reading input"); }
		bO.close();
	}
	public static void l(Object s) { System.out.println(s); }
}
