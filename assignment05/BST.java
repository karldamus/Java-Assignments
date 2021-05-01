import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BST extends BinaryTree {

	/**
	 * Create a new BST object. Purposely empty.
	 */
	public BST(){

	}

	@Override
	/**
	 * Find the value target in a Binary Search Tree
	 * 		doesn't use recursion!
	 */
	public boolean contains(String target) {
		Node tmp = this.root;

		// for some reason tmp.getData() was getting called on "null" tmp objects. 
		// an extra null check has been added to each if statement to counter this
		while (tmp != null) {
			if ((tmp != null) && tmp.getData().equals(target)) { 
				return true; // found target
			}
			if ((tmp != null) && target.compareTo(tmp.getData()) < 0) {
				tmp = tmp.getLeft(); // set tmp to tmp.left
			}
			if ((tmp != null) && target.compareTo(tmp.getData()) > 0) {
				tmp = tmp.getRight(); // set tmp to tmp.right
			}
		}

		return false; // all nodes searched, target not found
	}

	@Override
	/**
	 * Add a new node with value target to a Binary Search Tree
	 */
	public void add(String target) {
		if (this.getRoot() == null) {
			this.root = new Node(target);
			return;
		}

		addAsChild(new Node(target), this.getRoot());
	}	

	/**
	 * Helper method for {@link #add(String)}
	 * @param node the node to add
	 * @param root the root, will change using recursion
	 * 
	 * @see #add(String)
	 */
	public void addAsChild(Node nodeToAdd, Node tmpNode) {
		// if nodeToAdd data < tmpNode data -> check leftside
		if (nodeToAdd.getData().compareTo(tmpNode.getData()) < 0) {

			if (tmpNode.getLeft() == null) { // tmpNode has NO left node:
				tmpNode.setLeft(nodeToAdd); // set tmpNode's left node
			} else {
				// tmpNode already has a left node, continue searching on left side
				addAsChild(nodeToAdd, tmpNode.getLeft());
			}

		}
		// if nodeToAdd data > tmpNode data -> check rightside
		if (nodeToAdd.getData().compareTo(tmpNode.getData()) > 0) {

			if (tmpNode.getRight() == null) { // tmpNode has NO right node:
				tmpNode.setRight(nodeToAdd); // set tmpNode's right node
			} else {
				// tmpNode already has a right node, continue searching on right side
				addAsChild(nodeToAdd, tmpNode.getRight());
			}

		}
	}

	/**
	 * Given an existing BinarySearchTree, not of optimal height
	 * 		make a new BinarySearchTree, OF optimal height
	 * @return a balanced Binary Search Tree 
	 * 
	 * @see #listFromBST(BST)
	 * @see #addNodesToList(List, Node)
	 * @see #balancedBSTFromList(List)
	 * @see #createMinimalBST(List, int, int)
	 */
	public BST makeBalanced() {
		List<String> listFromBST = listFromBST(this, false); 	// list of all values from current tree
		java.util.Collections.sort(listFromBST); 		// sort the list alphabetically

		return balancedBSTFromList(listFromBST);
	}

	/**
	 * ===============================
	 * HELPER METHODS FOR MAKEBALANCED
	 * ===============================
	 */

	 /**
	  * Given a BinarySearchTree, produce a List<String> of all the nodes 
	  * @param bst the binary search tree
	  * @param preorder (bool): if true, make a list using a pre-order algorithm, 
	  		if false, make a list using an in-order traversal
	  		... this method is also used by {@link #saveToFile(String)}, which requres a pre-order
	   		traversal. {@link #makeBalanced() does not require a pre-order traversal. }
	  * @return the List<String> of nodes in bst
	  *
	  * @see #addNodesToList(List, Node, boolean)
	  * @see #saveToFile(String)
	  */
	private List<String> listFromBST(BST bst, boolean preorder) {
		List<String> returnList = new ArrayList<>();
        addNodesToList(returnList, bst.getRoot(), preorder); 

		return returnList;
	}

	/**
	 * Create a balanced BST given a list
	 * @param listFromBST the list of nodes
	 * @return a BST with minimal height
	 * 
	 * @see #createMinimalBST(List, int, int)
	 */
	private BST balancedBSTFromList(List<String> listFromBST) {
		BST returnBST = new BST();
		returnBST.root = createMinimalBST(listFromBST, 0, listFromBST.size() - 1);
		
		return returnBST;
	}

	/**
	 * Helper method for {@link #balancedBSTFromList(List)}
	 * @param list the list of nodes values
	 * @param start the start index
	 * @param end the end index
	 * @return tmp: root node => {@link #balancedBSTFromList(List)}
	 */
	private Node createMinimalBST(List<String> list, int start, int end) {
		// base case
		if (start > end) { return null; }

		int mid = (start + end) / 2; // find middle of the list

		Node tmp = new Node(list.get(mid));

		// 2 recursive calls for setting left and right nodes of tmp
		tmp.setLeft(createMinimalBST(list, start, mid - 1));
		tmp.setRight(createMinimalBST(list, mid + 1, end));
		
		return tmp;
	}

	private void addNodesToList(List<String> listOfNodes, Node node, boolean preorder) {
		if (node == null){ return; }

		if (preorder) {
			// pre-order traversal of the binary tree
			listOfNodes.add(node.getData());
			addNodesToList(listOfNodes, node.getLeft(), true);
			addNodesToList(listOfNodes, node.getRight(), true);
		} else {
			// in-order traversal of the binary tree
			addNodesToList(listOfNodes, node.getLeft(), false);
			listOfNodes.add(node.getData());
			addNodesToList(listOfNodes, node.getRight(), false);
		}
    }

	/** ------------- END OF HELPER METHODS FOR MB() ------------- */

	public boolean saveToFile(String fname) {
		FileWriter fw;
		try {
			fw = new FileWriter(fname);
			PrintWriter pw = new PrintWriter(fw);
			
			List<String> listFromBST = listFromBST(this, true); 
			for (String s : listFromBST) {
				pw.println(s);
			}

			pw.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void loadFromFile(String fname){
		BinaryTree bst = new BST();
		try{
		  Scanner file = new Scanner( new File(fname) );
		  while( file.hasNextLine() ){
			bst.add(file.nextLine().strip());
		  }
		}catch(Exception e){
		  System.out.println("Something went wrong!!");
		}
		this.root = bst.root;
		this.size = bst.size;
	}

	/**
	 * MAIN METHOD FOR TESTING LESS GOOOOO
	 * 			- dababy
	 */
	public static void main(String[] args) {
		BST b = new BST();
		b.loadFromFile("sample.txt");
		System.out.println("1st load: ");
		System.out.println(b);

		b.saveToFile("new.txt");
		System.out.println("saved");

		b.loadFromFile("new.txt");
		System.out.println("2nd load: ");
		System.out.println(b);

	}

}
