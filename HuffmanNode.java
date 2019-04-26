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
   
   int selfIndex;             // i
   int parentIndex;           // floor(i/2)
   int leftChildIndex;        // 2*i
   int rightChildIndex;       // 2*1 + 1
   boolean leftChild;         // indicates left or right child
   
   // generic constructor
   public HuffmanNode() {
      super();
      symbol = "";            // A-Z, multi-alpha
      frequency = -1;         // long?
      
      selfIndex = 0;
      parentIndex = Integer.MIN_VALUE;
      leftChildIndex = Integer.MIN_VALUE;
      rightChildIndex = Integer.MIN_VALUE;
      leftChild = false;
   }
   
   // constructor for copying node
   public HuffmanNode(HuffmanNode hnode) {
      symbol = hnode.getSymbol();
      frequency = hnode.getFrequency();
      selfIndex = 0;
      parentIndex = Integer.MIN_VALUE;
      leftChildIndex = Integer.MIN_VALUE;
      rightChildIndex = Integer.MIN_VALUE;
      leftChild = false;
   }
   
   // constructor for creating super nodes
   public HuffmanNode(String sym, int freq) {
      this.symbol = sym;
      this.frequency = freq;
      selfIndex = 0;
      parentIndex = Integer.MIN_VALUE;
      leftChildIndex = Integer.MIN_VALUE;
      rightChildIndex = Integer.MIN_VALUE;
      leftChild = false;
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
    * @return the selfIndex
    */
   public int getSelfIndex() {
      return selfIndex;
   }

   /**
    * @param selfIndex the selfIndex to set
    */
   public void setSelfIndex(int selfIndex) {
      this.selfIndex = selfIndex;
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
    * @param leftChild the leftChild to set
    */
   public boolean getLeftChild() {
      return this.leftChild;
   }
   
   
   /**
    * @param leftChild the leftChild to set
    */
   public void setLeftChild(boolean leftChild) {
      this.leftChild = leftChild;
   }


   /**
    * @return the leftChild
    */
   public boolean isLeftChild() {
      return (this.selfIndex % 2 == 0);
   }


   // a leaf has no children, both children are null
   public boolean isLeafNode() {
      
      boolean result;
      
      if ((this.leftChildIndex == Integer.MIN_VALUE)
            && (this.rightChildIndex == Integer.MIN_VALUE)) {
         result = true;
      } else {
         result = false;
      }
      
      System.out.println("[HuffmanNode - isLeafNode()]:" 
            + " Self Index = " + this.getSelfIndex() 
            + " / leftChildIndex = " + this.getLeftChildIndex() 
            + " / rightChildIndex = " + this.getRightChildIndex() 
            + " / isLeaf = " + result);
      
      return result;
   }
   
   
   public boolean isRootNode() {
      // root has no parent and is at index 1
      return (this.getSelfIndex() == 1);
   }

   public String sortSymbol(String str) {
      // TODO sort and return the letters in the string argument
      
      char [] charArray = str.toUpperCase().toCharArray();
      // sort the elements of the array
      for (char c : charArray) {
         System.out.println(c);
         //test...
      }
      return "";
   }
   


   public void resetNode() {
      this.setSymbol("");
      this.setFrequency(0);
      this.setSelfIndex(0);
      this.setParentIndex(Integer.MIN_VALUE);
      this.setLeftChildIndex(Integer.MIN_VALUE);
      this.setRightChildIndex(Integer.MIN_VALUE);
      this.setLeftChild(false);
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return "HuffmanNode [symbol=" + symbol 
            + " | frequency=" + frequency 
            + " | selfIndex=" + selfIndex 
            + " | parentIndex=" + parentIndex
            + " | leftChildIndex=" + leftChildIndex 
            + " | rightChildIndex=" + rightChildIndex 
            + " | leftChild=" + leftChild + "]";
   }
   
}
