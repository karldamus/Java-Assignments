import java.util.Arrays;

public class SimpleTester{

    public static byte[][] game = new byte[][]{
        {0,0,0,0,0,0,9,0,0},
        {0,0,0,0,0,0,8,0,0},
        {0,0,0,0,0,0,6,0,0},
        {1,2,3,4,5,6,7,9,8},
        {0,0,0,0,0,0,5,0,0},
        {0,0,0,0,0,0,4,0,0},
        {1,2,3,0,0,0,3,0,0},
        {7,8,9,0,0,0,2,0,0},
        {4,5,6,0,0,0,1,0,0}};
        
  

    public static boolean testFind(){
        int[] array = {8,6,7,5,3,0,9};
        int[][] targets = { {7,5,3}, {9}, {3,0,9,1} };
        int[] expected = {2, 6, -1};
        boolean pass = true;
        
        System.out.println("Simple Tester for Find.locateSequence(target, array)" );
        System.out.println( "array=" + Arrays.toString(array));
        for(int i=0; i<targets.length; i+=1){
            int[] target = targets[i];
            System.out.print( "--target=" + Arrays.toString(target));
            int actual = Find.locateSequence(target, array);
            System.out.print( " expected=" + expected[i]);
            System.out.print( " actual=" + actual);
            System.out.println(  "  [" + (expected[i] == actual ? "pass" : "fail") + "]");
            if(expected[i] != actual){
                pass = false;
            }
        }
        return pass;
    }

    public static boolean testFindAgain(){
        int[] array = {1,2,3,1,2,4,1};
        int[][] targets = { {1}, {1,2}, {1,2,3}, {5} };
        int[][] expected = {{3,0,3,6}, {2,0,3}, {1,0}, {0}};
        boolean pass = true;
        
        System.out.println("Simple Tester for FindAgain.locateAllSequenceLocations(target, array)" );
        System.out.println( "array=" + Arrays.toString(array));
        for(int i=0; i<targets.length; i+=1){
            int[] target = targets[i];
            System.out.print( "--target=" + Arrays.toString(target));
            int[] actual = FindAgain.locateAllSequenceLocations(target, array);
            System.out.print( " expected=" + Arrays.toString(expected[i]));
            System.out.print( " actual=" + Arrays.toString(actual));
            System.out.println(  "  [" + (Arrays.equals(expected[i], actual) ? "pass" : "fail") + "]");
            if(Arrays.equals(expected[i], actual)){
                pass = false;
            }
        }
        return pass;


    }

    public static boolean testSudoku(){
        boolean pass = true;
        System.out.println("SudokuChecker\n-------------" );
 

        System.out.println("checkRow(row, grid)");
        boolean expectedValid=true, expectedInvalid=false;
        boolean actualValid = SudokuChecker.checkRow(3,game);
        boolean actualInvalid = SudokuChecker.checkRow(1,game);        
        System.out.print("  SudokuChecker.checkRow(3,game)");
        System.out.print(" expected=" + expectedValid);
        System.out.print(" actual=" + actualValid);
        System.out.println("  [" + (expectedValid == actualValid ? "pass" : "fail") + "]");
        if( expectedValid != actualValid){ pass = false;}
        System.out.print("  SudokuChecker.checkRow(1,game)");
        System.out.print(" expected=" + expectedInvalid);
        System.out.print(" actual=" + actualInvalid);
        System.out.println("  [" + (expectedInvalid == actualInvalid ? "pass" : "fail") + "]");
        if( expectedInvalid != actualInvalid){ pass = false;}

        System.out.println("checkColumn(row, grid)");
        actualValid = SudokuChecker.checkColumn(6,game);
        actualInvalid = SudokuChecker.checkColumn(1,game);        
        System.out.print("  SudokuChecker.checkColumn(6,game)");
        System.out.print(" expected=" + expectedValid);
        System.out.print(" actual=" + actualValid);
        System.out.println("  [" + (expectedValid == actualValid ? "pass" : "fail") + "]");
        if( expectedValid != actualValid){ pass = false;}
        System.out.print("  SudokuChecker.checkColumn(1,game)");
        System.out.print(" expected=" + expectedInvalid);
        System.out.print(" actual=" + actualInvalid);
        System.out.println("  [" + (expectedInvalid == actualInvalid ? "pass" : "fail") + "]");
        if( expectedInvalid != actualInvalid){ pass = false;}


        System.out.println("checkSubregion(row, grid)");
        actualValid = SudokuChecker.checkSubregion(6,game);
        actualInvalid = SudokuChecker.checkSubregion(1,game);        
        System.out.print("  SudokuChecker.checkSubregion(6,game)");
        System.out.print(" expected=" + expectedValid);
        System.out.print(" actual=" + actualValid);
        System.out.println("  [" + (expectedValid == actualValid ? "pass" : "fail") + "]");
        if( expectedValid != actualValid){ pass = false;}
        System.out.print("  SudokuChecker.checkSubregion(1,game)");
        System.out.print(" expected=" + expectedInvalid);
        System.out.print(" actual=" + actualInvalid);
        System.out.println("  [" + (expectedInvalid == actualInvalid ? "pass" : "fail") + "]");
        if( expectedInvalid != actualInvalid){ pass = false;}


        return pass;
    }

    public static void main(String[] args){
        testFind();
        System.out.println();
        testFindAgain();
        System.out.println();
        testSudoku();       
    }
}