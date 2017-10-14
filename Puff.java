import java.util.*;
import java.io.*;

public class Puff {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Syntax: java Puff [zipfilename]");
			return;
		}

		FileIO io = new FileIOC();
		BinaryIn file = io.openBinaryInputFile(args[0]);

		// check if the file was compressed with Huff
		short fileSignature = file.readShort();
		short huffsSignature = 0xBC0;
		if (fileSignature != huffsSignature) {
			System.out.println("File not compressed with Huff.");
			return;
		}

		// get the length of the frequency table
		int stLength = file.readInt();
		
		// get the frequency table
		PQ<HuffTree> pq = new PQC();
		for (int i=0; i<stLength; i++) {
			pq.add(new HuffTreeC(null, null, file.readChar(), file.readInt()));
		}

		// make the hufftree from the frequency table
		while (pq.size() > 1) {
			HuffTree merge = new HuffTreeC(pq.remove(), pq.remove());
			pq.add(merge);
		}
		HuffTree bigTree = pq.remove();

		// prepare the uncompressed output file
		FileWriter out = io.openOutputFile();
		
		// read bits till we get to a leaf node on the tree and write
		// out the corresponding character for the binary code
		try {
			while (!file.isEmpty()) {
				HuffTree tree = bigTree;
				while (tree.getLeft() != null || tree.getRight() != null) { //while node is not a leaf
					if (!file.isEmpty())
						tree = (file.readBoolean() ? tree.getRight() : tree.getLeft());
					else break;
				}
				out.write(tree.getSymbol());
			}
			out.close();
		} catch (IOException e) {
			System.out.println("IOException with output file. " + e);
		}
	}
}
