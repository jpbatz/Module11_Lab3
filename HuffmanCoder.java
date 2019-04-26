/**
 *  * @author Joanne Hayashi
 */
package Module11_Lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HuffmanCoder {

   public HuffmanCoder() {
      // I. Create a Huffman Encoded Binary Tree
      // HuffmanBinaryTree hbt = new HuffmanBinaryTree();

      // II. Create a tree structure and store the frequency table
      // III. Print the resulting tree nodes in pre-order traversal (node,
      // left, right)
      // Ex. The tree in preorder is: XYZ:6, X:3, YZ:3, Y:1, Z:2
      // IV. Print the resulting encoding generated by the tree (the leaves)
      // Ex. The code is: X=0, Y=10, Z=11
      //
      // encode or decode input files to output file
      //
      // Rules:
      // 1.Numeric left to right
      // 2.Tie Breakers:
      // a) Simple, then
      // b) Alphabetical
      // 3.No/Ignore punctuation
   }

   /**
    * @param args
    */
   public static void main(String[] args) {

      BufferedReader freqTable;
      BufferedReader clearTextData;
      BufferedReader encodedTextData;
      BufferedWriter results;

      int numSyms = 0;   // frequency table size (# of symbols)
      char coderMode;    // d: decode input file, e: encode input file

      String [] symbols = new String[MAXSYMBOLS];
      String [] frequencies = new String[MAXSYMBOLS];

      String clearTextString = "";
      String encodedString = "";

      // Sample string
      String sampleString = "Hello World";
      String binarySample = "1101101000010001111100011111101000000101100";

      HuffmanCoder hc = new HuffmanCoder();
      HuffmanBinaryTree hbtree = new HuffmanBinaryTree();

      System.out.println("Huffman Encoder and Decoder\n");
      //  Check for command line arguments.
      if (args.length != 4) {
          System.out.println("Usage:\n\tjava HuffmanCoder"
                               + " <e=encode | d=decode>"
                               + " <freq_table_filename>"
                               + " <input_filename to encode or decode>"
                               + " <output_filename>");
          System.exit(1);
      }

      coderMode = args[0].toUpperCase().toCharArray()[0];
      if ((coderMode != 'D') && (coderMode != 'E')){
         System.out.println("Invalid Mode: Expecting 'D' or 'E'");
         System.exit(1);
      }
      
      freqTable = hc.openInputHandler(args[1], "Frequency Table file: ");
      results = hc.openOutputHandler(args[3], " Output Results file: ");
      System.out.println();

      // load symbols and frequencies from frequency table input file
      numSyms = hc.loadFreqTableArray(freqTable, symbols, frequencies);
      hc.closeInputFileHandler(freqTable);
      hc.printFreqTable(symbols, frequencies, numSyms);

      // build the min heap binary tree
      hbtree.buildMinHeapBinaryTree(symbols, frequencies, numSyms);
      hbtree.printMinHeap();   // before

      // build the Huffman encoded tree
      hbtree.buildHuffmanEncodedTree();
      
      hbtree.printMinHeap();   // after
      
      // traverse encoded tree and print sym-freq nodes
      // hbtree.printEncodedTree();

      // traverse encoded tree and print cdoes
      // hbtree.printCode();
      
      // decode or encode input files
      // write results to output
      // disregard case, spaces, numbers, and symbols
      switch(coderMode) {
         case 'E': {
            System.out.println("Mode: Encode Text");
            clearTextData = hc.openInputHandler(args[2], 
                  "Clear Text Input filename: ");
            clearTextString = hc.readFile(clearTextData);
            System.out.println(clearTextString + "\n");
            hc.closeInputFileHandler(clearTextData);
   
            // encode each character in the string into binary code
            
            break;
         }
         case 'D': {
            System.out.println("Mode: Decode Text");
            encodedTextData = hc.openInputHandler(args[2], 
                  "Encoded Input filename: ");
            encodedString = hc.readFile(encodedTextData);
            System.out.println(encodedString + "\n");
            hc.closeInputFileHandler(encodedTextData);
   
            // decode the binary string into symbols
            
            break;
         }
         default: {
            System.out.println("Invalid Mode: Expecting 'D' or 'E'");
            System.exit(1);
         }
      }

      hc.closeOutputFileHandler(results);
      
   } // end main()


   public String encodeText() {
      // TODO for every character in the clear text file, traverse the
      // encoded tree to create and return the coded string.
      return "";
   }


   public String decodeText() {
      // TODO for codes in the encoded file, traverse the encoded tree
      // to create and return the corresponding decoded string
      return "";
   }


   // ***** FILE AND FILE HANDLER ROUTINES *****
   // open input file handlers
   private BufferedReader openInputHandler(String argument, String message) {
      BufferedReader handler = null;

      try {
         handler = new BufferedReader(new FileReader(argument));
         System.out.println(message + argument);
         System.out.println();
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(3);
      }
      return handler;
   }

   // open input file handlers
   private BufferedWriter openOutputHandler(String argument, String message) {
      BufferedWriter handler = null;

      try {
         handler = new BufferedWriter(new FileWriter(argument));
         System.out.println(message + argument);
         System.out.println();
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(4);
      }
      return handler;
   }

   private void closeInputFileHandler(BufferedReader br) {
      // close input file handler
      try {
         br.close();
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(5);
      }
      return;
   }

   private void closeOutputFileHandler(BufferedWriter br) {
      // close output file handler
      try {
         br.close();
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(5);
      }
      return;
   }

   private String readFile(BufferedReader br) {

      String line = "";
      String fileText = "";

      try {
         while (((line = br.readLine()) != null)) {
            fileText = fileText + line + "\n";
         }
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(2);
      }

      return fileText;
   }

   // loads symbol table (with frequencies) into their respective arrays
   // returns the number of symbols from the input file
   private int loadFreqTableArray(BufferedReader br, String syms[],
         String freqs[]) {
      
      int index = 1;
      String line = "";
      int symCount = 0;

      try {
         // reads each "symbol - frequency" pair exactly formatted, per line
         // TODO should accept any format of character followed by number
         while (((line = br.readLine()) != null)) {
            syms[index] = line.substring(0, 1); // expecting single character
            freqs[index] = line.substring(4); // expecting multi-digit number
            symCount++;    // number of symbols in the frequency table
            index++;       // corresponding indexes for syms and freqs arrays
         }
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(2);
      }
      return symCount;
   }

   // prints symbols and frequencies
   private void printFreqTable(String syms[], String freqs[], int size) {
      
      System.out.println("[HuffmanCoder - printFreqTable()]\n" 
                        + "Symbol - Frequency pairs: " + size + "\n");
      
      for (int i = 1; i <= size; i++) {
         System.out.println(syms[i] + " " + freqs[i]);
      }
      System.out.println();
   }

   /**
    * Write a string to the output stream.
    * 
    * @param text
    *           The text to write.
    * @param output
    *           The output stream to write the text to. Ref: Projec0.java
    */
   private void writeResults(String text, BufferedWriter output) {
      try {
         output.write(text, 0, text.length());
         output.newLine();
      } catch (IOException ioe) {
         System.err.println(ioe.toString());
         System.exit(6);
      }
      return;
   }

   // ***** CONSTANTS ***** //
   private static final int MIN        = 1;
   private static final int MAXSYMBOLS = 27; // includes null bit string
}
