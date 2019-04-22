/**
 * 
 */
package Module11_Lab3;

/**
 * @author jph
 *
 */
public class HuffmanNode {
   int frequency;             // long?
   String symbol;             // A-Z, multi-alpha
   
//   int selfIndex            // i
   int parentIndex;           // floor(i/2)
   int leftChildIndex;        // 2*i
   int rightChildIndex;       // 2*1 + 1
   boolean leftChild;         // indicates left or right child
   
   public HuffmanNode() {
      frequency = -1;             // long?
      symbol = "";             // A-Z, multi-alpha
      
//      int selfIndex = 0
      parentIndex = Integer.MIN_VALUE;
      leftChildIndex = Integer.MIN_VALUE;
      rightChildIndex = Integer.MIN_VALUE;
   }

   public int addNode() {
      return 0;
   }
   
   
   // ***** ACCESSOR METHODS ***** //
   /**
    * @return the frequency
    */
   public int getFrequency() {
      return this.frequency;
   }

   /**
    * @param frequency the frequency to set
    */
   public void setFrequency(int frequency) {
      this.frequency = frequency;
   }

   /**
    * @return the symbol
    */
   public String getSymbol() {
      return this.symbol;
   }

   /**
    * @param symbol the symbol to set
    */
   public void setSymbol(String symbol) {
      this.symbol = symbol;
   }

   /**
    * @return the parentIndex
    */
   public int getParentIndex() {
      return this.parentIndex;
   }

   /**
    * @param parentIndex the parentIndex to set
    */
   public void setParentIndex(int parentIndex) {
      this.parentIndex = parentIndex;
   }

   /**
    * @return the leftChildIndex
    */
   public int getLeftChildIndex() {
      return this.leftChildIndex;
   }

   /**
    * @param leftChildIndex the leftChildIndex to set
    */
   public void setLeftChildIndex(int leftChildIndex) {
      this.leftChildIndex = leftChildIndex;
   }

   /**
    * @return the rightChildIndex
    */
   public int getRightChildIndex() {
      return this.rightChildIndex;
   }

   /**
    * @param rightChildIndex the rightChildIndex to set
    */
   public void setRightChildIndex(int rightChildIndex) {
      this.rightChildIndex = rightChildIndex;
   }
   
   
   /**
    * @return the leftChild
    */
   public boolean isLeftChild() {
      return leftChild;
   }

   /**
    * @param leftChild the leftChild to set
    */
   public void setLeftChild(boolean leftChild) {
      this.leftChild = leftChild;
   }

   public boolean isLeafNode() {
      // both children are null
      return
      (
         (this.leftChildIndex == Integer.MIN_VALUE) && 
         (this.rightChildIndex == Integer.MIN_VALUE)
      );
   }
   
   public boolean isRootNode() {
      // parent is null
      return (this.parentIndex == Integer.MIN_VALUE);
   }
   
}
