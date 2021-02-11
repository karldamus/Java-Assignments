
/*****************************
 * SudokuChecker.java
 * Author: Karl Damus 
 * ©2021, All Rights Reserved
*****************************/

/** a small note: 
 * i am aware that checkrow and checkcolumn have practically duplicate for loops. 
 * i am unsure if a helper function would be allowed in this question because of the wording in the assignment specification
 * if i was to make a helper function...it would simply take in either row or column, run through the row or column, and return false or nothing.
 * i would have also made it work with the checkSubregion function by also including value parameters
 * also...i apologize in advance for the monstrosity that is the 'nested' for loop in checkSubregion()
 */

public class SudokuChecker {

	/** this main function is for TESTING PURPOSES ONLY */
	public static void main(String[] args) {
		// System.out.print("example1 | expected output is true  | actual output is ");
		// System.out.println(check(example1));
		// System.out.print("example2 | expected output is false | actual output is ");
		// System.out.println(check(example2));
    }

  	/** checks if row 'row' is OK in the grid */	
	public static boolean checkRow(int row, byte[][] grid) {
      	for (int i=0; i<=8; i+=1) {
			// System.out.print(grid[row][i]);
			for (int j=0; j<=8; j+=1) {
				// edge case
				if ((grid[row][j] < 1) || (grid[row][j] > 9)) {
					return false;
				}
				if (i != j) {
					if (grid[row][i] == grid[row][j]) {
						return false;
			}}}
		}
		return true;
    }

  	/** checks if column 'col' is OK in the grid */
    public static boolean checkColumn(int col, byte[][] grid) {
		for (int i=0; i<=8; i+=1) {
			// System.out.print(grid[row][i]);
			for (int j=0; j<=8; j+=1) {
				// edge case
				if ((grid[j][col] < 1) || (grid[j][col] > 9)) {
					return false;
				}
				if (i != j) {
					if (grid[i][col] == grid[j][col]) {
						return false;
			}}}
		} 
		return true;
    }

  	/** checks if the subregion 'region' is OK in the grid */
    public static boolean checkSubregion(int region, byte[][] grid) {
    	int subregionColumn = 0;
		int subregionRow = 0;

		// switch statement based on 0-8 left-right grid pattern
		// subregionColumns and Rows refer to starting INDEX positions
		switch(region) {
			case 0: subregionColumn = 0;
			case 1: subregionRow = 0; 
				break;
			case 2: subregionColumn = 6; subregionRow = 0; 
				break;
			case 3: subregionColumn = 0;
			case 4: subregionRow = 3;
				break;
			case 5: subregionColumn = 6; subregionRow = 3;
				break;
			case 6: subregionColumn = 0;
			case 7: subregionRow = 6;
				break;
			case 8: subregionColumn = 6; subregionRow = 6;
		}
		// provide subregionColumn for numbers not covered by switch statement --> (1,4,7)
		if ((region == 1) || (region == 4) || (region == 7)) {
			subregionColumn = 3;
		}

		// honestly, i am so sorry for this. i didn't want to do this.
		// outer loop
		for (int i=subregionRow; i<(subregionRow + 3); i+=1) {
			for (int j=subregionColumn; j<(subregionColumn + 3); j+=1) {
				// inner loop
				for (int k=subregionRow; k<(subregionRow + 3); k+=1) {
					for (int l=subregionColumn; l<(subregionColumn + 3); l+=1) {
						// edge case
						if ((grid[i][j] < 1) || (grid[i][j] > 9)) {
							return false;
						}
						// conditional
						if (!(j == l) || !(i == k)) {
							if (grid[i][j] == grid[k][l]) {
								return false; 
			}}}}} // closes all INNER for loops
		} // end of first-level for loop
		return true;
	}

    public static boolean check(byte[][] grid) {
		// check the subregions
		for(int subregion=0; subregion<9; subregion+=1) {  
			if (!checkSubregion(subregion, grid)) {
				return false;
			}
		}

		// check the rows
		for(int row=0; row<9; row+=1) {                    
			if (!checkRow(row, grid)) {
				return false;
			}
		}

		// check the rows
		for(int col=0; col<9; col+=1) {                    
			if (!checkColumn(col, grid)) {
				return false;
			}
		}
		
		// if we get this far, then we conclude:
		// the grid must be valid
		return true;
	}

	/** sample valid game */
    public static byte[][] example1 = new byte[][] {
      {5,3,4,6,7,8,9,1,2},
      {6,7,2,1,9,5,3,4,8},
      {1,9,8,3,4,2,5,6,7},
      {8,5,9,7,6,1,4,2,3},
      {4,2,6,8,5,3,7,9,1},
      {7,1,3,9,2,4,8,5,6},
      {9,6,1,5,3,7,2,8,4},
      {2,8,7,4,1,9,6,3,5},
	  {3,4,5,2,8,6,1,7,9}
	};

    /** sample invalid games */
    public static byte[][] example2 = new byte[][] {
      {5,3,4,6,7,8,9,1,2},
      {6,7,2,1,9,5,3,4,8},
      {1,9,8,3,4,2,5,6,7},
      {8,5,9,7,6,1,4,2,3},
      {4,2,6,8,5,3,7,9,1},
      {7,1,3,9,2,4,8,5,6},
      {9,6,1,5,3,7,2,8,3},
      {2,8,7,4,1,9,6,2,6},
	  {3,4,5,2,8,6,1,8,8}
	};

	public static byte[][] example3 = new byte[][] {
		{5,5,4,6,7,8,9,1,2},
		{6,7,2,1,9,5,3,4,8},
		{1,9,8,3,4,2,5,6,7},
		{8,5,9,7,6,1,4,2,3},
		{4,2,6,8,5,3,7,9,1},
		{7,1,3,9,2,4,8,5,6},
		{9,6,1,5,3,7,2,8,4},
		{2,8,7,4,1,9,6,3,5},
		{3,4,5,2,8,6,1,7,9}
	};
	public static byte[][] game = new byte[][]{
        {0,0,0,0,0,0,9,0,0},
        {0,0,0,0,0,0,8,0,0},
        {0,0,0,0,0,0,6,0,0},
        {1,2,3,4,5,6,7,9,8},
        {0,0,0,0,0,0,5,0,0},
        {0,0,0,0,0,0,4,0,0},
        {1,2,3,0,0,0,3,0,0},
        {7,8,9,0,0,0,2,0,0},
		{4,5,6,0,0,0,1,0,0}
	};

}