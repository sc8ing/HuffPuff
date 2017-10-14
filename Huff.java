import java.io.*;
import java.util.*;

public class Huff {
 public static final boolean DEBUG = true;

 public static void main(String[] args) {
  if (args.length != 1) {
   System.out.println("Syntax: java Huff [textfilename]");
   return;
  }
// get the file
  FileIO io = new FileIOC();
  FileReader file = io.openInputFile(args[0]);

// build the frequency table
  SymbolTable fT = new CharFreqTable(file);
  Character[] charsInFile = (Character[]) fT.keySet().toArray(new Character[0]);
  // apparently some systems sort the keysets automatically, but some don't
  Arrays.sort(charsInFile);
  
// make a priority queue
  // just a note, why does fT.get() return Object? shouldn't it be Integer?
  // Integer i = (Integer) fT.get('a');
  PQ<HuffTree> pq = new PQC<HuffTree>();
  for (Character c : charsInFile) {
   pq.add(new HuffTreeC(null, null, c, (Integer) fT.get(c)));
  }

// build the huffman tree
  while (pq.size() > 1) {
   HuffTree merge = new HuffTreeC(pq.remove(), pq.remove());
   pq.add(merge);
  }
  HuffTree bigTree = pq.remove();
  l(bigTree);

// create symbol table of shortened binary values
  CharBinaryTable symbolTable = new CharBinaryTable(bigTree);
  
// write output
  BinaryOut bO = io.openBinaryOutputFile();
  short signature = 0x0BC0;
  bO.write(signature); //write unique BC algorithm signature
  int n = fT.size();
  bO.write(n); //write size of symbol table
  for (int i = 0; i < charsInFile.length; i++) {
    char c = charsInFile[i]; //it must be a char, not a Character
    bO.write(c); //write key
    int frequency = (Integer)fT.get(c);
    bO.write(frequency); //write frequency of that key
  }
  //read char from file one at a time
  file = io.openInputFile(args[0]);
  int ch;
  char c;
  try {
  while ((ch = file.read()) != -1) {
    c = (char) ch;
    ShortenedBinary sB = symbolTable.get(c);
    bO.write(sB.getValue(), sB.getLength()); //write next char
   }
  } catch (IOException e) {
   System.out.println("IOException initializing symbol table. " + e);
  }
  bO.close();
 }
  public static void l(Object s) { System.out.println(s); }
}
