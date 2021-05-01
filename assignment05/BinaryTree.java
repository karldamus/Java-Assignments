import java.io.File;
import java.util.Scanner;

public class BinaryTree {
	protected Node root = null;
    protected int  size = 0;

	/**
	 * Create a new BinaryTree with size 0
	 */
	public BinaryTree(){
		size = 0;
	}

	/**
	 * create a new BinaryTree with a root and size 1
	 * @param s the value of the root node
	 * 
	 * @see Node#Node(String)
	 */
	public BinaryTree(String s){
		root = new Node(s);
		size = 1;
	}

	public int getSize(){ return this.size; } 	// get size
	public Node getRoot(){ return this.root; }	// get root

	/**
	 * Find a String value (target) in a binary tree
	 * @param target the target we are looking for
	 * @return true or false depending on if target is found
	 * 
	 * @see #traverse(Node, String)
	 */
	public boolean contains(String target){
		// three base cases
		if (target == null) { return false; }
		if (this.root == null) { return false; }
		if (target.equals(this.root.data)) { return true; }

		// recursive call
		return traverse(this.root, target);
	}

	/**
	 * Traverse through a binary tree, starting at Node node;
	 * 		locate String target;
	 * 		helper method for contains.
	 * @param node the node to check, and it's corresponding 
	 * 				left and right sub-nodes
	 * @param target the string we are comparing to node.data
	 * @return true or false depending on if target is found
	 * 
	 * @see #contains(String)
	 */
	private boolean traverse(Node node, String target) {
		// check if current node == target
		if (node.getData().equals(target)) { 
			return true; 
		}
		// traverse left sub-tree
		if ((node.left != null && traverse(node.left, target))) {
			return true;
		}

		// traverse right sub-tree
		if (node.right != null && traverse(node.right, target)) {
			return true;
		}

		return false; // all nodes have been traversed, target not found
	}

	/**
	 * Check to see if a binary tree is a valid binary search tree (bst)
	 * @return true or false depending on if it is a bst or not
	 * 
	 * @see #recursivelyCheckIfIsBST(Node, String, String)
	 */
	public boolean isBST() {
        return recursivelyCheckIfIsBST(root, null, null); // null min and max to start (no min or max on the root)
    }

    private boolean recursivelyCheckIfIsBST(Node node, String min, String max) {
        if (node == null) { return true; } // simple base case

		// if node < min
        if ( (min != null)  &&  (node.getData().compareTo(min) <= 0) ) {
			return false;
		}
		// if node > max
        if ( (max != null)  &&  (node.getData().compareTo(max) >= 0) ) {
			return false;
		}

		// recursive return call
		// for left, set max to current node.data
		// for right, set min to current node.data
        return ( (recursivelyCheckIfIsBST(node.getLeft(), min, node.getData()))  &&  (recursivelyCheckIfIsBST(node.getRight(), node.getData(), max)) );
    }

	/**
	 * Loads a BST from a file and creates a new BST object with all the values as nodes
	 * @param fname the name of the file to create
	 */
	public void loadFromFile(String fname){
		BinaryTree bt = new BinaryTree();
		try{
			Scanner file = new Scanner( new File(fname) );
			while( file.hasNextLine()){
				bt.add(file.nextLine().strip());
			}
		}catch(Exception e){
			System.out.println("Something went wrong!!");
		}
		this.root = bt.root;
		this.size = bt.size;
	}

	public void add(String s){
		addRandom(s);
	}
	
	/* add a node in a random place in the tree. */
	private void addRandom(String s){
		if(root == null && size == 0){
			root = new Node(s);
		}else{
		  Node tmp = root;
		  boolean left = Math.random() < 0.5; 
		  Node child = left ? tmp.getLeft() : tmp.getRight();
		  while(child != null){
			tmp = child;
			left = Math.random() < 0.5;
			child = left ? tmp.getLeft() : tmp.getRight();
		  }
		  // assert: child == null
		  // yea! we have a place to add s
		  if(left){
		  	tmp.setLeft(new Node(s));
		  }else{
			  tmp.setRight(new Node(s));
		  }
		}
		size += 1;
	}

	/** Computes the height of the binary tree
	  *
		* The height is the length of the longest path from
		* the root of the tree to any other node.
		*
		* @return the height of the tree
		*/
	public final int height(){
	  if( root == null ){ return -1; }
	  if( size == 1){ return 0; }
	  return heightRecursive(root);
	}
	protected final static int heightRecursive(Node root){
		if( root == null ){
			return -1;
		}
		int leftHeight = heightRecursive(root.getLeft());
		int rightHeight = heightRecursive(root.getRight());
		if( leftHeight < rightHeight){
			return 1 + rightHeight;
		}else{
			return 1 + leftHeight;
		}
	}


	public static void main(String[] args){
		BinaryTree t = new BinaryTree("cat");
		System.out.println("height = " + t.height() + ",  size = " + t.getSize());
		t.add("dog");
		t.add("eel");
		t.add("cow");
		t.add("rat");
		System.out.println("height = " + t.height() + ",  size = " + t.getSize());
		System.out.println(t);

		System.out.println("height = " + t.height() + ",  size = " + t.getSize());
		t.loadFromFile("sample.txt");
		System.out.println(t);
	}


	@Override
	public String toString() {
		return PrintBinaryTree.toString(this);
	}


}
