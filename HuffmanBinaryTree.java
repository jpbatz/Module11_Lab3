/**
 * 
 */
package Module11_Lab3;

/**
 * @author jph
 *
 */
public class HuffmanBinaryTree {
   
   HuffmanNode [] HBTree;
   int first;
   int last;
   
   public HuffmanBinaryTree() {
      HBTree = new HuffmanNode[MAX_ALPHA_SIZE];
      HBTree[0] = null;
      first = 0;
      last = 0;
   }
   
   // print Huffman Tree nodes in pre-order
   // print all tree nodes (sorted by highest to lowest frequency)
   // top - down by sibling levels
   //    left child - right child
   //
   // Ex. The tree in preorder is: XYZ: 6, X: 3, YX: 3, Y: 1, Z: 2
   public void printTree() {
      
      System.out.print("The tree in preorder is ");
      
      return;
   }
   
   // print tree leaf nodes to get codes
   // top - down
   //    left subtree leaves (0) - right subtree leaves (1)
   public void printCode() {

      // Ex. The code is X = 0; Y = 10; Z = 11;
      
      System.out.print("The code is ");
      
      // for each non-empty node:
      //    start at index 0
      //    top down:
      //       left subtree:
      //          traverse parent -> final leftChild leaf
      //          traverse parent -> final rightChild leaf
      //       right subtree:
      //          traverse parent -> final leftChild leaf
      //          traverse parent -> final rightChild leaf
      // System.out.print(HBTree[i].symbol + " = " + HBTree[i].frequency + "; ");
      return;
   }
   
   
   
   // *** PRIVATE ATTRIBUTES ***** //
   
   // this lab: A to Z and null bit string:
   private static final int MAX_ALPHA_SIZE = 27;
   
   // future: alphanumeric, symbols, etc.:
   // private static final int MAX_ALPHA_SIZE = 256;
}
