# Assignment #5
## Methods to Implement:
**Binary Tree class:**
```java
public boolean contains(String s)
// precondition: s is string
// postcondition: returns true if s in the this tree, false otherwise
// constraint: this method MUST use recursion
public boolean isBST()
// note: an empty tree is a valid BST
// postcondition: returns true if this tree is a valid binary search tree, false otherwise
// notes: A BST object might NOT satisfy the binary search tree property.
// This method will not use instanceof. It should determine if the
// values and structure of this tree satisfy the binary search tree property.
```

**BST class**
```java
@Override
public boolean contains(String s)
// precondition: s is string
// postcondition: returns true if s in the this tree, false otherwise
// efficiency: (i) this method must be efficient
// (ii) do NOT use recursion for this method
@Override
public boolean add(String s)
// precondition: (i) s is a string
// postcondition: (i) s is added to this tree, if it is not already
// in the tree, and tree remains a valid BST
// (ii) s is added as a leaf in the tree (if added)
// (iii) returns true if s is added to the tree, false otherwise
public BST makeBalanced()
// postcondition: returns a new binary search tree that
// (i) has the same data (strings) as this tree
// (ii) has minimal height
// (iii) is a valid binary search tree
public boolean saveToFile(String fname)
// purpose: saves the current tree in text format in the current directory in a file fname
// precondition: fname is a string (name of file to save tree)
// postcondition: (i) returns true if successful (saved to file), false otherwise
// (ii) loading the saved file with loadFromFile will exactly reconstruct this tree
```

- - -
Testing files can be found [here](https://github.com/karldamus/java-assignments/tree/main/assignment05/testing).

- - -
```
Grade: 100%
```
