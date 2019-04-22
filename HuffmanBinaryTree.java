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
   int numOfNodes;
   
   public HuffmanBinaryTree() {
      HBTree = new HuffmanNode[MAX_ALPHA_SIZE];
      HBTree[0] = null;
      first = 0;
      last = 0;
      numOfNodes = 0;
   }
   
   public void buildHuffmanBinaryTree(String [] syms, String [] freqs, int size) {
      
      int currNodeIndex = 0;
      int firstNodeIndex = 0;
      int lastNodeIndex = 0;
      
      System.out.println("Size = " + size);
      
      this.HBTree[currNodeIndex] = null;   // null bit string at index = 0
      
      HuffmanNode newNode;

      // add first (root) node at index 1
      currNodeIndex++;
      firstNodeIndex = currNodeIndex;
      lastNodeIndex = currNodeIndex;
      
      // build binary tree, top down
      System.out.println("Symbol Table Node: Symbol Frequency SelfIndex ParentIndex LeftChildIndex RightChildIndex LeafNode");
      for (currNodeIndex=1; currNodeIndex<=size; currNodeIndex++) {
         newNode = this.makeNewNode(size, syms[currNodeIndex], freqs[currNodeIndex], currNodeIndex);
         this.HBTree[currNodeIndex] = newNode;
         this.printNode(newNode);
         lastNodeIndex = currNodeIndex;
      }
      
      // percolate bottom up
      
      return;
   }
   
   public HuffmanNode makeNewNode(int size, String sym, String freq, int index) {
      
      HuffmanNode temp = new HuffmanNode();
      temp.setSymbol(sym);
      if (freq != null);
         temp.setFrequency(Integer.parseInt(freq));
      temp.setSelfIndex(index);
      if (index != 0)   // root does not have parent
         temp.setParentIndex((int)(index/2));
      if (index <= size/2) {
         temp.leftChildIndex = index*2;
         temp.rightChildIndex = index*2 + 1;
         if (index % 2 == 0)
            temp.leftChild = true;
         else
            temp.leftChild = false;
      }
      return temp;
   }
   
   
   // ***** ACCESSOR METHODS ***** //
   
   /**
    * @return the numOfNodes
    */
   public int getNumOfNodes() {
      return numOfNodes;
   }

   /**
    * @param numOfNodes the numOfNodes to set
    */
   public void setNumOfNodes(int numOfNodes) {
      this.numOfNodes = numOfNodes;
   }

   
   public void printNode(HuffmanNode hnode) {
      System.out.println("Symbol Table Node: " 
            + hnode.getSymbol() + " " 
            + hnode.getFrequency() + " " 
            + hnode.getSelfIndex() + " " 
            + hnode.getParentIndex() + " " 
            + hnode.getLeftChildIndex() + " " 
            + hnode.getRightChildIndex() + " " 
            + hnode.isLeftChild());
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
