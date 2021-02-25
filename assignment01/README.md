# Assignment #1
### Sequence Finders and Sudoku Checker
- - -
#### Find.java 
For a given target sequence (non-empty array of integers), 
the method searches the input array (of integers) to find an occur- rence of the target sequence if it is present. 
If the sequence is present, the method returns the array index position of where it starts in the array. 
If the sequence is not present, the method returns -1.

#### FindAgain.java
The method returns an array of integers with size at least 1. 
The first value in the output array is number of occurrences of the target in the array. 
This number may be zero or greater. If the number of occurrences is n, 
then the next n values in the output array are the n starting positions of these n occurrences 
(in the order that they appear from left to right; that is, the positions are ordered from smallest to largest).

#### SudokuChecker.java
```java
public static boolean checkRow(int row, byte[][] grid)
public static boolean checkColumn(int column, byte[][] grid)
public static boolean checkSubregion(int region, byte[][] grid)
```

These methods are used in the provided check method that takes a 2-dimensional (9×9) array of numbers and determines if 
it represents a valid (solved) sudoku grid of numbers.
- - -
```
Grade: 100%
```
