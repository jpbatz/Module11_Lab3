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

   public HuffmanBinaryTree() {
      HBTree = new HuffmanNode[MAX_ALPHA_SIZE];
      HBTree[0] = null;
      firstNodeIndex = 0;
      lastNodeIndex = 0;
      numOfNodes = 0;
   }


   public void buildMinHeapBinaryTree(String [] syms, String [] freqs, int size) {
      
      int currNodeIndex = 0;
      
      System.out.println("Size = " + size);
      
      this.HBTree[currNodeIndex] = null;     // null bit string at index = 0
      
      HuffmanNode newNode;

      // add first (root) node at index 1
      currNodeIndex++;
      this.firstNodeIndex = currNodeIndex;   // should remain as 1
      this.lastNodeIndex = size;
      
      // build binary tree, top down
      // this.printFreqTableHeader();
      
      for (currNodeIndex=1; currNodeIndex<=size; currNodeIndex++) {
         newNode = this.makeNewNode(size, syms[currNodeIndex], 
                                    freqs[currNodeIndex], currNodeIndex);
         this.HBTree[currNodeIndex] = newNode;
         // this.printNode(newNode);
         // this.lastNodeIndex = currNodeIndex;
         
         // adjust min priority heap down
         this.adjustMinHeapDown(currNodeIndex);
      }
      return;
   }


   public HuffmanNode makeNewNode(int size, String sym, String freq, int index) {
      
      HuffmanNode temp = new HuffmanNode();
      temp.setSymbol(sym);
      if (freq != null);
         temp.setFrequency(Integer.parseInt(freq));
      temp.setSelfIndex(index);
      if (index != 0)   // root does not have parent, everyone else does
         temp.setParentIndex((int)(index/2));
      if (index <= size/2) {
//         temp.leftChildIndex = index*2;
//         temp.rightChildIndex = index*2 + 1;
         
         // last inner node
         if ((index == size/2) && (size % 2 == 0)) {
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


   private void adjustMinHeapDown(int currIndex) {
      
      int parentIndex;
      int index;
      
      if (this.HBTree[currIndex].isRootNode()) {
         // at root, do nothing
         // System.out.println("buildMinHeapBinaryTree(): ROOT");
      } else {   // calling node not root
         index = currIndex;
         // System.out.println("buildMinHeapBinaryTree(): Node " + index);

         // percolate down (toward root)
         while (this.HBTree[index].isRootNode() == false) {
            parentIndex = this.HBTree[index].getParentIndex();
            if (this.HBTree[index].getFrequency() 
                  < this.HBTree[parentIndex].getFrequency()) {
               // System.out.println("buildMinHeapBinaryTree(): PercolateDown() " + index);
               this.swapNodes(index, parentIndex);
               index = (int) (index/2);
            } else {
               break;
            }
         }
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
      HuffmanNode [] superNodeArray = new HuffmanNode[50];   // convert to stack
      int snArrIndex = 0;   // super node array index
      
//      System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]");
      System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: Last Node Index = " + this.getLastNodeIndex());
      // until tree empty
         // one node first:
      
         // remove root minNode1 (min freq)
         System.out.println("ROOT NODE: " + this.HBTree[ROOT_INDEX].getSymbol() + " " 
                                          + this.HBTree[ROOT_INDEX].getFrequency() + " " + 
                            "LAST NODE: " + this.HBTree[this.lastNodeIndex].getSymbol() + " " 
                                          + this.HBTree[lastNodeIndex].getFrequency());
         minNode1 = this.deleteRoot();
         // push to temp stack
         superNodeArray[snArrIndex] = minNode1;
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: superNodeAray " + superNodeArray[snArrIndex].getSymbol() + " " + superNodeArray[snArrIndex].getFrequency());
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: Last Node Index = " + this.getLastNodeIndex());
         
         // adjust min heap/percolate up
         this.adjustMinHeapUp(ROOT_INDEX);
         
         
         // remove root minNode2 (min freq)
         System.out.println("ROOT NODE: " + this.HBTree[ROOT_INDEX].getSymbol() + " " 
                                          + this.HBTree[ROOT_INDEX].getFrequency() + " " + 
                            "LAST NODE: " + this.HBTree[this.lastNodeIndex].getSymbol() + " " 
                                          + this.HBTree[lastNodeIndex].getFrequency());
         minNode2 = this.deleteRoot();
         // push to temp stack
         snArrIndex++;
         superNodeArray[snArrIndex] = minNode2;
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: superNodeAray " + superNodeArray[snArrIndex].getSymbol() + " " + superNodeArray[snArrIndex].getFrequency());
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: Last Node Index = " + this.getLastNodeIndex());
         
         // adjust min heap/percolate up
         this.adjustMinHeapUp(ROOT_INDEX);
         
         // combine super node = minNode1 and minNode2
         System.out.println("ROOT NODE: " + this.HBTree[ROOT_INDEX].getSymbol() + " " 
                                          + this.HBTree[ROOT_INDEX].getFrequency() + " " + 
                            "LAST NODE: " + this.HBTree[this.lastNodeIndex].getSymbol() + " " 
                                        + this.HBTree[lastNodeIndex].getFrequency());
         superNode = this.makeSuperNode(minNode1, minNode2);
         
         // push to temp stack
         snArrIndex++;
         superNodeArray[snArrIndex] = superNode;
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: superNodeAray " + superNodeArray[snArrIndex].getSymbol() + " " + superNodeArray[snArrIndex].getFrequency());
         System.out.println("[HuffmanBinaryTree - buildHuffmanEncodedTree()]: Last Node Index = " + this.getLastNodeIndex());
         
//         // re-insert super node into tree
//         //    adjust min heap/percolate up by freq
//               this.adjustMinHeapUp(ROOT_INDEX);
//         //       if tie, break with:
//         //          if same length
//         //             alpha first (left)
//         //          otherwise,
//         //             simple node (shorter symbol first or left)
//      // } empty tree
//      // encode tree (resets tree)
   }

   // largest (last) value at root
   private void adjustMinHeapUp(int index) {

//      int parentIndex;
      int currIndex = Integer.MIN_VALUE;
      int leftChildIndex = Integer.MIN_VALUE;
      int rightChildIndex = Integer.MIN_VALUE;
      int currFreq = 0;
      int leftChildFreq = 0;
      int rightChildFreq = 0;
//      int nextIndex = 0;
      boolean leftChildPresent = false;
      boolean rightChildPresent = false;
      HuffmanNode currNode = null;

      System.out.println("[HuffmanBinaryTree - adjustMinHeapUp()]");

      if ((this.HBTree[index] != null)) {   // or this.HBTree[index].getSelfIndex() != Integer.MIN_VALUE or > 1
         currNode = this.HBTree[index];

         if (currNode.isLeafNode()) {
            // at root or leaf, do nothing
            // System.out.println("buildMinHeapBinaryTree(): ROOT");
            System.out.println("[HuffmanBinaryTree - adjustMinHeapUp()]: ROOT or LEAF NODE - No Min Heap Adjust Up Required");
         } else {   // process non-root node(s)
            currIndex = index;
            currFreq = currNode.getFrequency();
            // System.out.println("buildMinHeapBinaryTree(): Node " + currIndex);
   
            // percolate up (toward leaves)
//            while ((currNode.isLeafNode() == false) && 
//                   (currNode.getLeftChildIndex() != Integer.MIN_VALUE) &&
//                   (currNode.getRightChildIndex() != Integer.MIN_VALUE)) {
            while ((currNode.isLeafNode() == false)) {
   //            parentIndex = this.HBTree[currIndex].getParentIndex();
               
               leftChildPresent = false;
               rightChildPresent = false;
               
//               currNode = this.HBTree[currIndex];
               
               // extract child indexes and frequencies
               if ((this.HBTree[currIndex].getLeftChildIndex() > 1) &&
                   (this.HBTree[currIndex].getLeftChildIndex() <= this.lastNodeIndex)) {
                  leftChildPresent = true;
                  leftChildIndex = this.HBTree[currIndex].getLeftChildIndex();
                  System.out.println("leftChildIndex Before = " + leftChildIndex);
                  leftChildIndex = currIndex*2;
                  System.out.println("leftChildIndex After = " + leftChildIndex);
                  leftChildFreq = this.HBTree[leftChildIndex].getFrequency();
               } else {
                  System.out.println("Invalid leftChildIndex - no left child for node at index: " + leftChildIndex);
                  break;
               }
                  
               if ((this.HBTree[currIndex].getRightChildIndex() <= this.lastNodeIndex) && 
                   (this.HBTree[currIndex].getRightChildIndex() > 1)) {
                  rightChildPresent = true;
                  rightChildIndex = this.HBTree[currIndex].getRightChildIndex();
                  System.out.println("rightChildIndex Before = " + rightChildIndex);
                  rightChildIndex = currIndex*2 + 1;
                  System.out.println("rightChildIndex After = " + rightChildIndex);
                  rightChildFreq = this.HBTree[rightChildIndex].getFrequency();
               } else {
                  System.out.println("Invalid rightChildIndex - no right child for node at index: " + rightChildIndex);
                  break;
               }

               
               // both children present, compare their frequencies
               if (rightChildPresent && leftChildPresent) {
                  // System.out.println("buildMinHeapBinaryTree(): PercolateUp() " + index);

//                  if (leftChildFreq < rightChildFreq) {
                  if ((leftChildFreq < rightChildFreq) || 
                     ((!rightChildPresent) && (leftChildIndex > 1))){
                     if (currFreq > leftChildFreq) {
                        this.swapNodes(currIndex, leftChildIndex);
                        currIndex = (currIndex*2);
                     }
                  } else if ((rightChildFreq < leftChildFreq) ||
                            ((!leftChildPresent) && (rightChildIndex > 1))){
                     if (currFreq > rightChildFreq) {
                        this.swapNodes(currIndex, rightChildIndex);
                        currIndex = (int) (currIndex*2 + 1);
                     }
                  } else if (rightChildFreq == leftChildFreq){
                     if ((currFreq > leftChildFreq) && (currFreq > rightChildFreq)) {
                        // simpler - should have previously been assigned to the left
                        // alpha -  - should have previously been assigned to the left

                        // this.swapNodes(currIndex, leftChildIndex);
                        // currIndex = (int) (currIndex*2 + 1);
                        
                        // this.swapNodes(currIndex, rightChildIndex);   // or
                        // currIndex = (currIndex*2);                    // or
                     }
                  }
                  
//                  // assign next current node
//                  if (this.HBTree[nextIndex] != null) {
//                     currNode = this.HBTree[currIndex];
//                  }
   
               // only left child present
//               } else if ((!rightChildPresent) && (leftChildIndex > 1)) {
//   
//               // only right child
//               } else if ((!leftChildPresent) && (rightChildIndex > 1)){
//                  
               } else if ((!leftChildPresent) && (!rightChildPresent)){
                  // no children
                  break;
               }
               
               // assign next current node
               if (this.HBTree[currIndex] != null) {
                  currNode = this.HBTree[currIndex];
               }
               
            }   // end while loop
         }
      } else {
         // node is null, stop processing
         System.exit(13);
      }
      
   }

   private HuffmanNode makeSuperNode(HuffmanNode node1, HuffmanNode node2) {
      HuffmanNode comboNode;
      String sumSyms = "";
      int sumFreqs;

      sumSyms = node1.getSymbol() + node2.getSymbol();
      sumFreqs = node1.getFrequency() + node2.getFrequency();
      comboNode = new HuffmanNode(sumSyms, sumFreqs);
      return comboNode;
   }


   private void encodeTree() {
      this.resetTree();
      // insert items in super node stack into tree
         // assign left/right pointers
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


//   private HuffmanNode getParentNode(int index) {
//      int parentIndex;
//      parentIndex = this.HBTree[index].getParentIndex();
//      return this.HBTree[parentIndex];
//   }


   private HuffmanNode deleteRoot() {
   
      HuffmanNode hnode = new HuffmanNode(this.HBTree[ROOT_INDEX]);
      String lastNodeSym = "";
      int lastNodeFreq = 0;
      int parentIndex;
      
      System.out.println("[HuffmanBinaryTree - deleteRoot()]");
      if (this.lastNodeIndex > ROOT_INDEX) {
         lastNodeSym = this.HBTree[this.lastNodeIndex].getSymbol();
         lastNodeFreq = this.HBTree[this.lastNodeIndex].getFrequency();
      }
      //copy last node to root
      this.HBTree[ROOT_INDEX].setSymbol(lastNodeSym);
      this.HBTree[ROOT_INDEX].setFrequency(lastNodeFreq);

      // remove the last node's parent's child pointer
      parentIndex = this.HBTree[this.getLastNodeIndex()].getParentIndex();
      if(this.HBTree[this.lastNodeIndex].isLeftChild()) {
         this.HBTree[parentIndex].setLeftChildIndex(Integer.MIN_VALUE);
      } else {
         this.HBTree[parentIndex].setRightChildIndex(Integer.MIN_VALUE);;
      }

      // reset last node so percolate up can complete properly
      this.resetNode(HBTree[this.lastNodeIndex]);
      // adjust last node pointer
      this.lastNodeIndex--;
      System.out.println("[HuffmanBinaryTree - deleteRoot()] LastNodeIndex = " + this.getLastNodeIndex());
      return hnode;
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


   public void resetNode(HuffmanNode hnode) {
      hnode.setSymbol("");
      hnode.setFrequency(0);
      hnode.setSelfIndex(0);
      hnode.setParentIndex(Integer.MIN_VALUE);
      hnode.setLeftChildIndex(Integer.MIN_VALUE);
      hnode.setRightChildIndex(Integer.MIN_VALUE);
   }


   // clear all fields in all nodes of linked array, 
   // first=last=null bit string at 0
   private void resetTree() {
      // implement this
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
      for(int i=1; i<=this.lastNodeIndex; i++) {
         System.out.println("[" + this.HBTree[i].getSelfIndex() + "] " + 
            this.HBTree[i].getSymbol() + " " + this.HBTree[i].getFrequency()
            + " " + this.HBTree[i].getParentIndex()
            + " " + this.HBTree[i].getLeftChildIndex()
            + " " + this.HBTree[i].getRightChildIndex()
            + " " + this.HBTree[i].getLeftChild());
      }
   }


   // print Huffman Tree nodes in pre-order
   // print all tree nodes (sorted by highest to lowest frequency)
   // top - down by sibling levels
   //    left child - right child
   //
   // Ex. The tree in preorder is: XYZ: 6, X: 3, YX: 3, Y: 1, Z: 2
   public void printTree() {
      
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

   // null bit string
   private static final int ROOT_INDEX = 1;
   // this lab: A to Z and null bit string:
   private static final int MAX_ALPHA_SIZE = 27;
   
   // future: alphanumeric, symbols, etc.:
   // private static final int MAX_ALPHA_SIZE = 256;
}
