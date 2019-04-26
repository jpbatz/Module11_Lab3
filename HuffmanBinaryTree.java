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
   int firstNodeIndex;
   int lastNodeIndex;
   int numOfNodes;
   boolean treeIsNull;

   public HuffmanBinaryTree() {
      HBTree = new HuffmanNode[MAX_ALPHA_SIZE];
      this.treeIsNull = true;
      this.firstNodeIndex = 0;
      this.lastNodeIndex = 0;
      this.numOfNodes = 0;
      this.treeIsNull = false;
      
      HBTree[0] = null;
   }


   public int buildMinHeapBinaryTree(String [] syms, String [] freqs, int size) {
      
      int currNodeIndex = NULL_BIT_INDEX;
      HuffmanNode newNode;     // stores Huffman node and super node objects
      
      this.setNumOfNodes(size);
      this.HBTree[currNodeIndex] = null;     // null bit string at index = 0

      // add first (root) node at index 1
      currNodeIndex++;
      this.firstNodeIndex = ROOT_INDEX;      // should always remain as 1
      this.lastNodeIndex = size;
      this.treeIsNull = false;

      // build binary tree, top down, with symbol-frequency arrays
      // corresponding indexes
      for (currNodeIndex=1; currNodeIndex<=size; currNodeIndex++) {
         newNode = this.makeNewNode(size, syms[currNodeIndex], 
                                    freqs[currNodeIndex], currNodeIndex);
         this.HBTree[currNodeIndex] = newNode;
         this.setLastNodeIndex(currNodeIndex);
         // percolate min priority heap down
         this.percolateDown(currNodeIndex);
      }
      System.out.println("[HufmanBinaryTree - buildMinHeapBinaryTree()]:\n" + 
                         "Last Node Index: " + (currNodeIndex-1) + "\n");
      return (currNodeIndex-1);
   }


   private HuffmanNode makeNewNode(int size, String sym, String freq, int index) {
      
      HuffmanNode temp = new HuffmanNode();
      temp.setSymbol(sym);
      if (freq != null);
         temp.setFrequency(Integer.parseInt(freq));
      temp.setSelfIndex(index);
      if (index != NULL_BIT_INDEX)   // root does not have parent, everyone else does
         temp.setParentIndex((int)(index/2));
      if (index <= (size/2)) {   // inner node

         // last inner node, last index is even (left child node)
         if ((index == (size/2)) && ((size % 2) == 0)) {
            temp.leftChildIndex = index*2;   // assign only left child index
         } else {  // assign left and right child indexes
            temp.leftChildIndex = index*2;
            temp.rightChildIndex = index*2 + 1;
         }

         // is current node a left child?
         if (temp.isLeftChild())
            temp.leftChild = true;
         else
            temp.leftChild = false;
      }
      return temp;
   }

   // nodes added as a leaf, percolate up toward root
   // array perspective is downward (toward root index 1)
   private void percolateDown(int index) {
      
      int parentIndex;
      int currIndex;
      int currFreq;
      int parentFreq;
      boolean swapped = true;
      HuffmanNode currNode;
      
      System.out.println("[HuffmanBinaryTree - percolateDown()] ENTER");
      
      if (this.HBTree[index].isRootNode()) {
         // at root, done percolating, do nothing
          System.out.println("[HuffmanBinaryTree - percolateDown()] ROOT - Done Percolating Down");
      } else {   // node not root
         currIndex = index;
         currNode = this.HBTree[currIndex];
         
         System.out.println("[HuffmanBinaryTree - percolateDown()] Node Index: " + currIndex);

         // percolate down (toward root)
         while ((!currNode.isRootNode()) && (swapped)) {
            parentIndex = currNode.getParentIndex();
            currFreq = currNode.getFrequency();
            parentFreq = this.HBTree[parentIndex].getFrequency();
            swapped = false;
            
            if (currFreq < parentFreq) {
                System.out.println("[HuffmanBinaryTree - percolateDown()] swap nodes: " + currIndex + " " + parentIndex);
               this.swapNodes(currIndex, parentIndex);
               swapped = true;
//               currIndex = (int) (currIndex/2);
               currIndex = parentIndex;
               currNode = this.HBTree[currIndex];
            } else {
               System.out.println();
               break;
            }
         }
         System.out.println();
      }
   }


   // build encoded binary tree
   // - remove 2 min nodes from top
   // - combine into single node
   // - insert combined node at bottom
   public void buildHuffmanEncodedTree() {

      HuffmanNode minNode1;
      HuffmanNode minNode2;
      HuffmanNode superNode;   // combined minNode1 and minNode2
      HuffmanNode [] superNodeArray = new HuffmanNode[100];   // convert to stack
      int snArrIndex = 0;   // super node array index
      
      System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()] ENTER");
      System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
                       + "Last Node Index = " + this.getLastNodeIndex());
      // until tree empty
      while(this.treeIsNull == false) {
         // one node first:
         minNode1 = null;  // reset temp node
         minNode2 = null;  // reset temp node
         
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
               + "NODE_1 = " + this.HBTree[ROOT_INDEX]);
         
         // remove root minNode1 (min freq)
         this.printRootLastNode();

         minNode1 = this.deleteRoot();
         // push to temp stack
         System.out.println("Super Node Array Index = " + snArrIndex);
         superNodeArray[++snArrIndex] = minNode1;
         System.out.println(
               "[HuffmanBinaryTree - buildHuffmanEncodedTree()]: superNodeAray "
               + superNodeArray[snArrIndex].getSymbol() + " " 
               + superNodeArray[snArrIndex].getFrequency());
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
               + "Last Node Index = " + this.getLastNodeIndex());
         
         // adjust min heap/percolate up
         this.percolateUp(ROOT_INDEX);

         if (this.getLastNodeIndex() > 0) {
            
            System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
                  + "NODE_2 = " + this.HBTree[ROOT_INDEX]);
            
            // remove root minNode2 (min freq)
            this.printRootLastNode();

            minNode2 = this.deleteRoot();
            // push to temp stack
            System.out.println(snArrIndex);
            superNodeArray[++snArrIndex] = minNode2;
            System.out.println(
                  "[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
                  + "superNodeAray " + superNodeArray[snArrIndex].getSymbol() 
                  + " " + superNodeArray[snArrIndex].getFrequency());
            System.out.println(
                  "[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
                  + "Last Node Index = " + this.getLastNodeIndex());
            
            // adjust min heap/percolate up
            this.percolateUp(ROOT_INDEX);
         }
         
         // combine super node = minNode1 and minNode2
         this.printRootLastNode();

         superNode = this.makeSuperNode(minNode1, minNode2);
         
         // push to temp stack
         System.out.println(snArrIndex);
         superNodeArray[++snArrIndex] = superNode;
         System.out.println(
               "[HuffmanBinaryTree - buildHuffmanEncodedTree()]: superNodeAray " 
               + superNodeArray[snArrIndex].getSymbol() + " " 
               + superNodeArray[snArrIndex].getFrequency());
         System.out.println(
               "[HuffmanBinaryTree - buildHuffmanEncodedTree()]: "
               + "Last Node Index = " + this.getLastNodeIndex());
         
         // re-insert super node into tree at lastNodeIndex + 1
         this.appendNode(superNode);
         
         //    adjust min heap/percolate down by freq
         this.percolateDown(this.getLastNodeIndex());
         
      }   // end while
      
      //empty tree
      System.out.println("SuperNodeArray: Length = " + snArrIndex);
      for(int i=1; i<snArrIndex; i++) {
         System.out.println("SuperNodeArray: " + "[" + i + "] " + superNodeArray[i].getFrequency() + " "
               + superNodeArray[i].getSymbol());
      }
      
      this.resetTree();
      // encode the tree (resets tree)
      this.encodeTree(superNodeArray);
      
   }

   // called after delete node, and last node copied to root
   // nodes swapped into root position, percolate downward (toward leaves)
   // array perspective is upward (higher indexes)
   private void percolateUp(int index) {

      int currIndex = Integer.MIN_VALUE;
      int leftChildIndex = Integer.MIN_VALUE;
      int rightChildIndex = Integer.MIN_VALUE;
      int currFreq = 0;
      int leftChildFreq = 0;
      int rightChildFreq = 0;
      boolean leftChildPresent = false;
      boolean rightChildPresent = false;
      boolean swapped = true;   // stop percolating when swapped or leaf
      HuffmanNode currNode = null;

      System.out.println("[HuffmanBinaryTree - percolateUp()]: ENTER");

      if (!this.treeIsNull) {   // or this.HBTree[index].getSelfIndex() != Integer.MIN_VALUE or > 1
         currNode = this.HBTree[index];   // should be leaf node

         if (currNode.isLeafNode()) {
            // leaf node, done, do nothing
            System.out.println("[HuffmanBinaryTree - percolateUp()]: "
                  + "LEAF - No Min Heap Adjust Up Required for Index " + index);
         } else {   // process non-leaf node(s)
            System.out.println("[HuffmanBinaryTree - percolateUp()]: "
                  + "NODE - Min Heap Adjust Up Required for Index " + index);

//            currIndex = index;
            
            // percolate up (toward leaves) if one or two children exist
            // and still swapping
            while ((currNode.isLeafNode() == false) || (swapped == true)) {
               
               currIndex = currNode.getSelfIndex();
               currFreq = currNode.getFrequency();
               
               // initialize/update loop vars
               swapped = false;
               leftChildPresent = false;
               rightChildPresent = false;
               leftChildIndex = currNode.getLeftChildIndex();
               rightChildIndex = currNode.getRightChildIndex();
               
               System.out.println("[HuffmanBinaryTree - PercolateUp()]: *** currIndex *** = " + currIndex);
               
               // determine presence of left child
               // extract left child frequency
               if ((leftChildIndex > ROOT_INDEX) &&
                   (leftChildIndex <= this.lastNodeIndex)) {
                  leftChildPresent = true;
                  System.out.println("[HuffmanBinaryTree - percolateUp()] set leftChildIndex = " + leftChildIndex);
                  leftChildFreq = this.HBTree[leftChildIndex].getFrequency();
               } else {
                  System.out.println(
                     "Invalid leftChildIndex - no left child for node at index: " 
                       + currIndex);
               }

               // determine presence of right child
               // extract right child frequency
               if ((rightChildIndex > ROOT_INDEX) && 
                   (rightChildIndex <= this.lastNodeIndex)) {
                  rightChildPresent = true;
                  System.out.println("HuffmanBinaryTree - percolateUp()] set rightChildIndex = " + rightChildIndex);
                  rightChildFreq = this.HBTree[rightChildIndex].getFrequency();
               } else {
                  System.out.println(
                        "Invalid rightChildIndex - no right child for node at index: " 
                       + currIndex);
               }
               
               if ((rightChildPresent == false) && (leftChildPresent == false)) {
                  // curr node is a leaf, and has no children
                  break;
               }
               
               // both children present, compare their frequencies
               if (rightChildPresent && leftChildPresent) {
                  
                  System.out.println("HuffmanBinaryTree - percolateUp()] Compare Both Children for node index = " + currIndex);
                  
                  // percolate left if freq is less
                  if ((leftChildFreq < rightChildFreq) || 
                        (leftChildFreq == rightChildFreq)) {
                     
                     if (currFreq > leftChildFreq) {
                        this.swapNodes(currIndex, leftChildIndex);
                        swapped = true;
                     }
                     currIndex = (currIndex*2);
                     
                  // percolate right if freq less
//                  } else if ((rightChildFreq < leftChildFreq) ||
//                   ((!leftChildPresent) && (rightChild Index > ROOT_INDEX))){
                  } else if ((rightChildFreq < leftChildFreq) && 
                            ((rightChildIndex > ROOT_INDEX) && 
                             (rightChildIndex <= this.lastNodeIndex))) {

                     if (currFreq > rightChildFreq) {
                        this.swapNodes(currIndex, rightChildIndex);
                        swapped = true;
                     }
                     currIndex = (int) (currIndex*2 + 1);
                  }

               // only left child present, swap left child
               } else if (!rightChildPresent) {
                  
                  System.out.println("HuffmanBinaryTree - percolateUp()] Swap LEFT child (no right child) for node index = " + currIndex);
                  System.out.println("HuffmanBinaryTree - percolateUp()] LeftFreq currFreq: " + leftChildFreq + " " + currFreq);
                  
                  if (leftChildFreq < currFreq) {
                     System.out.println("HuffmanBinaryTree - percolateUp()] LeftFreq currFreq: " + leftChildFreq + " " + currFreq);
                     this.swapNodes(currIndex, leftChildIndex);
                     swapped = true;
                  }
                  currIndex = currIndex * 2;
               
               // only right child present, swap right child
               } else if (!leftChildPresent) {
                  
                  System.out.println("HuffmanBinaryTree - percolateUp()] Swap RIGHT child (no left child) for node index = " + currIndex);
                  
                  if (rightChildFreq < currFreq) {
                     this.swapNodes(currIndex, rightChildIndex);
                     swapped = true;
                  }
                  currIndex = (int) (currIndex*2 + 1);
               } 
               
               // assign next current node
               
               System.out.println("HuffmanBinaryTree - percolateUp()] Assign next currNode for node index = " + currIndex);
               
               if (this.HBTree[currIndex] != null) {
                  currNode = this.HBTree[currIndex];
               }
               
            }   // end while loop
         }
      } else {
         // node is null, stop processing
         // System.exit(13);
      }
   }


   private HuffmanNode makeSuperNode(HuffmanNode node1, HuffmanNode node2) {

      HuffmanNode comboNode;
      String sumSyms = "";
      int sumFreqs;

      if ((node1 != null) && (node2 != null)) {
         sumSyms = node1.getSymbol() + node2.getSymbol();
         sumFreqs = node1.getFrequency() + node2.getFrequency();
         comboNode = new HuffmanNode(sumSyms, sumFreqs);
      } else if (node1 == null) {
         comboNode = node2;
      } else if (node2 == null) {
         comboNode = node1;
      } else {   // both nodes null
         comboNode = null;
      }
      return comboNode;
   }


   private void encodeTree(HuffmanNode [] hnArray) {
      System.out.println("[HuffmanBinaryTree - encodeTree()]");
      // insert items in super node stack into tree
      // assign left/right nodes and pointers
      // address ties with frequency:
      //    simpler - fewer characters assigned to the left
      // address ties with simplicity:
      //    alpha - lower alpha assigned to the left
   }


   // ***** ACCESSOR METHODS ***** //


   /**
    * @return the numOfNodes
    */
   public int getNumOfNodes() {
      return numOfNodes;
   }


   /**
    * @return the lastNodeIndex
    */
   public int getLastNodeIndex() {
      return lastNodeIndex;
   }


   /**
    * @param lastNodeIndex the lastNodeIndex to set
    */
   public void setLastNodeIndex(int lastNodeIndex) {
      this.lastNodeIndex = lastNodeIndex;
   }


   /**
    * @param numOfNodes the numOfNodes to set
    */
   public void setNumOfNodes(int numOfNodes) {
      this.numOfNodes = numOfNodes;
   }


   private void appendNode(HuffmanNode hnode) {
      int newIndex;
      HuffmanNode newNode;
      
      System.out.println("[HuffmanBinaryTree - appendNode()] ENTER");
      System.out.println(
            "[HuffmanBinaryTree - appendNode()]: ***** Before LastNodeIndex = " 
            + this.getLastNodeIndex());
      newIndex = this.getLastNodeIndex() + 1;
      this.HBTree[newIndex] = hnode;
      newNode = this.HBTree[newIndex];
      // this.printNode(newNode);
      System.out.println(newNode.toString());
//      newNode.setSelfIndex(this.getLastNodeIndex() + 1);
      newNode.setSelfIndex(newIndex);
      newNode.setParentIndex(this.calcParentIndex(newIndex));
      newNode.setLeftChild(newNode.isLeftChild());
      // this.printNode(newNode);
      System.out.println(newNode.toString());
      this.setLastNodeIndex(newIndex);
      System.out.println(
            "[HuffmanBinaryTree - appendNode()]: ***** After LastNodeIndex = "
            + this.getLastNodeIndex());
      return;
   }


   // returns deleted root node and last node becomes the new root node
   // if last node in tree, returns root node
   // if root null, returns null
   private HuffmanNode deleteRoot() {
   
      HuffmanNode deletedNode = null;
      HuffmanNode firstNode = null;
      HuffmanNode lastNode = null;
      HuffmanNode parentNode = null;
//      String lastNodeSym = "";
//      int lastNodeFreq = 0;
      int lastIndex;
      int parentIndex;
      
      System.out.println("[HuffmanBinaryTree - deleteRoot()] ENTER");
      lastIndex = this.getLastNodeIndex();
      
      if (lastIndex >= ROOT_INDEX) {
         
         deletedNode = new HuffmanNode(this.HBTree[ROOT_INDEX]);
         firstNode = this.HBTree[ROOT_INDEX];
//         lastNode = this.HBTree[lastIndex = this.getLastNodeIndex()];
         lastNode = this.HBTree[lastIndex];
         
//         if (this.getLastNodeIndex() > ROOT_INDEX) {
         if (lastIndex > ROOT_INDEX) {
            //copy last node to root
            firstNode.setSymbol(lastNode.getSymbol());
            firstNode.setFrequency(lastNode.getFrequency());
   
            // remove the last node's parent's child pointer
            parentIndex = lastNode.getParentIndex();
            parentNode = this.HBTree[parentIndex];
            
            if(lastNode.isLeftChild()) {
               parentNode.setLeftChildIndex(Integer.MIN_VALUE);
            } else {
               parentNode.setRightChildIndex(Integer.MIN_VALUE);;
            }
         }
         
         // update the last node's pointer
         // if (this.getLastNodeIndex() >= ROOT_INDEX) {
         if (lastIndex == ROOT_INDEX) { // one item
            System.out
                  .println("[HuffmanBinaryTree - deleteRoot()] LAST SYMBOL: "
                        + this.HBTree[this.getLastNodeIndex()].getSymbol());
         }
         
         // reset last node so percolate up can complete properly
         // lastNode.resetNode();
         // lastNode = null;
         // this.HBTree[lastIndex] = null;

         // adjust last node pointer
         this.setLastNodeIndex(lastIndex - 1);

      }
      
      // removed last node, so tree is empty
      if (this.HBTree[ROOT_INDEX].getSymbol().length() == this.getNumOfNodes()) {
         System.out.println("[HuffmanBinaryTree - deleteRoot()]: "
                          + "Setting Tree to Null");
         this.treeIsNull = true;
      }
      System.out.println("[HuffmanBinaryTree - deleteRoot()]: Returning Node "
                        + deletedNode.getSelfIndex() + " " 
                        + deletedNode.getSymbol() + " " 
                        + deletedNode.getFrequency());
      return deletedNode;
   }


   private void swapNodes(int index1, int index2) {
      
      String sym1;
      int freq1;  
      String sym2;
      int freq2;  
      
      sym1 = this.HBTree[index1].getSymbol();
      freq1 = this.HBTree[index1].getFrequency();
      sym2 = this.HBTree[index2].getSymbol();
      freq2 = this.HBTree[index2].getFrequency();
      this.HBTree[index2].setSymbol(sym1);
      this.HBTree[index2].setFrequency(freq1);
      this.HBTree[index1].setSymbol(sym2);
      this.HBTree[index1].setFrequency(freq2);
      return;
   }


   // clear all fields in all nodes of linked array, 
   // first=last=null bit string at 0
   private void resetTree() {
      System.out.println("[HuffmanBinaryTree - resetTree()] Number of nodes = "
                        + this.getLastNodeIndex());
   }


   private void printFreqTableHeader() {
      System.out.println("Symbol Table Node: " + 
                         "Symbol Frequency " +
                         "SelfIndex " +
                         "ParentIndex " +
                         "LeftChildIndex " +
                         "RightChildIndex " +
                         "LeafNode");
   }


   private int calcParentIndex(int index) {
      return (int) (index/2);
   }


   private int calcLeftChildIndex(int index) {
      return (int) (index*2);
   }


   private int calcRightChildIndex(int index) {
      return (int) (index*2 + 1);
   }


   private void printNode(HuffmanNode hnode) {
      System.out.println("Symbol Table Node: " 
            + hnode.getSymbol() + " " 
            + hnode.getFrequency() + " " 
            + hnode.getSelfIndex() + " " 
            + hnode.getParentIndex() + " " 
            + hnode.getLeftChildIndex() + " " 
            + hnode.getRightChildIndex() + " " 
            + hnode.getLeftChild());
   }

   // print each element in the min heap in order by index
   public void printMinHeap() {
      for(int i=1; i<=this.getLastNodeIndex(); i++) {
         System.out.println("[" + this.HBTree[i].getSelfIndex() + "] " + 
            this.HBTree[i].getSymbol() + " " + this.HBTree[i].getFrequency()
            + " " + this.HBTree[i].getParentIndex()
            + " " + this.HBTree[i].getLeftChildIndex()
            + " " + this.HBTree[i].getRightChildIndex()
            + " " + this.HBTree[i].getLeftChild());
      }
      System.out.println();
   }


   private void printRootLastNode() {
      System.out.print("ROOT NODE: " + this.HBTree[ROOT_INDEX].getSymbol() 
            + " " + this.HBTree[ROOT_INDEX].getFrequency());
      if (this.lastNodeIndex != 0) {
         System.out.print(" / LAST NODE: " 
               + this.HBTree[this.lastNodeIndex].getSymbol() + " " 
               + this.HBTree[lastNodeIndex].getFrequency());
      } else {
         System.out.print(" / LAST NODE: -- --");
      }
      
      System.out.println(" / Last Node INDEX: " + this.getLastNodeIndex());
   }
   
   // print Huffman Tree nodes in pre-order
   // print all tree nodes (sorted by highest to lowest frequency)
   // top - down by sibling levels
   //    left child - right child
   //
   // Ex. The tree in preorder is: XYZ: 6, X: 3, YX: 3, Y: 1, Z: 2
   public void printEncodedTree() {
      
      System.out.print("The tree in preorder is ");
      //   implement this
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

   // null bit string index
   private int NULL_BIT_INDEX = 0;
   
   // root node index
   private static final int ROOT_INDEX = 1;
   
   // this lab: A to Z and null bit string:
   private static final int MAX_ALPHA_SIZE = 27;
   
   // future: alphanumeric, symbols, etc.:
   // private static final int MAX_ALPHA_SIZE = 256;
}
