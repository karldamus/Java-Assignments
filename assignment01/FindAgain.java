/*****************************
 * FindAgain.java Author: Karl Damus ©2021, All Rights Reserved
 *****************************/

public class FindAgain {

    /** this main function is for TESTING PURPOSES ONLY */
    public static void main(String[] args) {
        int[] array = new int[] {1,2,3,1,2,4,1};
        int[] target1 = new int[] {2};

        printArray(locateAllSequenceLocations(target1, array)); // print array with use of custom helper function -- see EOF
    }

    /** 
     * this function takes two args
     * 1) int[] target -> is the pattern we are looking for
     * 2) int[] array -> is the where we are looking for the pattern
     */
    public static int[] locateAllSequenceLocations(int[] target, int[] array) {
        int[] returnArray = new int[1];
        int tempSequenceIndex = 0;
        int numberOfSequences = 0;
        
        /** loop through @param int[] array */
        for (int arrayIndex=0; arrayIndex<array.length; arrayIndex+=1) {
            /** loop through @param int[] sequence*/
            int sequenceIndex = 0;
            while ((sequenceIndex<target.length) & ((arrayIndex + sequenceIndex) != array.length)) {
                // if current number found in sequence
                if (target[sequenceIndex] == array[arrayIndex + sequenceIndex]) {
                    // start of sequence found -> set tempSequenceIndex
                    if (sequenceIndex == 0) {
                        tempSequenceIndex = arrayIndex;
                    }
                    // end of sequence found -> appendToNewArray() => helper function (line 59-73)!
                    if (sequenceIndex == target.length - 1) {
                        numberOfSequences += 1;
                        returnArray = appendToNewArray(numberOfSequences, returnArray, tempSequenceIndex);
                    }
                }
                else {
                    break;
                }
                sequenceIndex+=1;
            }
        }
        return returnArray; // end of locateAllSequenceLocations()
    }

    /** 
     * this function takes three args
     * 1) int numOfSequencesToDate -> replaces index 0 in arrToStay -- (becomes newLocalArray)
     * 2) int[] arrToStay -> index 0 in arrToStay becomes index 1 in newLocalArray
     * 3) int iToAdd -> appended to newLocalArray[]
    */
    public static int[] appendToNewArray(int numOfSequencesToDate, int[] arrToStay, int iToAdd) {
        int lengthOfNewLocalArray = arrToStay.length + 1;
        int[] newLocalArray = new int[lengthOfNewLocalArray];

        // set new number of sequences
        newLocalArray[0] = numOfSequencesToDate;
        // set rest of previous array
        for (int i=1; i<newLocalArray.length - 1; i+=1) {
            newLocalArray[i] = arrToStay[i];
        }
        // set newest index position
        newLocalArray[newLocalArray.length - 1] = iToAdd;
        
        return newLocalArray; // end of appendToNewArray()
    }

    /** this function is for TESTING PURPOSES ONLY 
     * located below to de-clutter main function
     */
    public static int[] printArray(int[] returnArray) {
        System.out.print("[");
        for (int i=0; i<returnArray.length; i+=1) {
            if (i != returnArray.length - 1) {
                System.out.print(returnArray[i] + ",");
            } else {
                System.out.print(returnArray[i] + "]");
            }
        }
        System.out.println();      

        return returnArray;
    }
}


