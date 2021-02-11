/*****************************
 * Find.java Author: Karl Damus ©2021, All Rights Reserved
 *****************************/

public class Find {

    /** this main function is for TESTING PURPOSES ONLY */
    public static void main(String[] args) {
        int[] array = new int[] {8,6,7,5,3,0,9};
        int[] sequence = new int[] {5};

        locateSequence(sequence, array);
    }
    
    public static int locateSequence(int[] sequence, int[] array){
        int returnValue = -1; // set returnValue to -1 in advance, it only changes if a sequence is found.
        int tempSequenceIndex = 0;

        /** loop through @param int[] array */
        for (int arrayIndex=0; arrayIndex<array.length; arrayIndex+=1) {
            /** loop through @param int[] sequence */
            int sequenceIndex = 0;
            while ((sequenceIndex<sequence.length) & ((arrayIndex + sequenceIndex) != array.length)) {
                if (sequence[sequenceIndex] == array[arrayIndex + sequenceIndex]) {
                    if (sequenceIndex == 0) {
                        tempSequenceIndex = arrayIndex;
                    }
                    if (sequenceIndex == sequence.length - 1) {
                        returnValue = tempSequenceIndex;
                    }
                } else {
                    break;
                }
                sequenceIndex+=1;
            }
        }
        // System.out.println(returnValue);
        return returnValue;
    }
}