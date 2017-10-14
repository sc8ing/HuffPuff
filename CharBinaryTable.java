import java.util.*;
import java.io.*;

public class CharBinaryTable implements SymbolTable<Character, ShortenedBinary> {
 private Map<Character, ShortenedBinary> fT;

 public CharBinaryTable(HuffTree hT) {
   fT = new HashMap<Character, ShortenedBinary>();
   
   initialize(hT, new ShortenedBinary(0, 0));
 }
 
 //recursively traverse tree to create symbol table
 private void initialize(HuffTree hT, ShortenedBinary sB) {
   boolean hasChild = false;
   if (hT.getLeft() != null) { //has left child
     hasChild = true;
     initialize(hT.getLeft(), sB.addZero());
   }
   if (hT.getRight() != null) { //has right child
     hasChild = true;
     initialize(hT.getRight(), sB.addOne());
   }
   if (!hasChild) {
     put(hT.getSymbol(), sB); //add to symbol table
   }
 }
 
 public void put(Character k, ShortenedBinary v) { fT.put(k, v); }
 public ShortenedBinary get(Character k) { return fT.get(k); }
 public ShortenedBinary remove(Character k) { return fT.remove(k); }
 public int size() { return fT.size(); }
 public boolean isEmpty() { return fT.isEmpty(); }
 public Set<Character> keySet() { return fT.keySet(); }
}
