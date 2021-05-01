# Assignment #4
## Goal
By overriding the compareTo() method, we must compare and sort a set of cards based on the following hierarchy (such that *iff* the suits are the same, then the ranks matter):
- diamonds < clubs < hearts < spades
- 2 < 3 < ... < 9 < 10 < Jack < King < Queen < Ace
- note that a joker has SUIT.NONE

The solution I created solved the ranking of the cards and their 'compareTo' values all-in-one. By ordering the suits and ranks in an array, I was able to compare two cards to each other by testing if their position in the array was greater or lower than the other.

- - -
Testing files can be found [here](https://github.com/karldamus/java-assignments/tree/main/assignment04/testing).

- - -
```
Grade: 91.4%
```
