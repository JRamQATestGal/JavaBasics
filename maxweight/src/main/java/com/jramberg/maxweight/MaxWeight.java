package com.jramberg.maxweight;

public class MaxWeight {

    /*
     *  Author:   Joanna Ramberg
     *  Position: QA Engineer
     *  This program takes an array of N elements.  All elements are all equal except for one, which is heavier
     *  than the rest.  Return the position of the heavy ball.
     */

    public static int findHeavyBall(int start, int end, int[] ballArray) {

        int sumOfSideA = 0;
        int sumOfSideB = 0;
        int index = -1;
        int length = end - start + 1;
        int heavyBall = -1;

        /* Empty Array */
        if (ballArray.length == 0)
            return -1;

        /* Array of 1 */
        if (length == 1) {
            return 0;
        }

        /* Array of 2.  Return the heavier of the two balls */
        if (length == 2) {
            if (ballArray[start] < ballArray[end]) {
                return end;
            }
            return start;
        }
        /* Array of an even size.  Find the midpoint and compare the sum of the weight for both sides.  Then
         * take the heavier subset of the two sides and go thru this process again */
        if (length % 2 == 0) {

            index = length / 2 + start;
            for (int i = start; i < index; i++) {
                sumOfSideA += ballArray[i];
            }
            for (int i = index; i <= end; i++) {
                sumOfSideB += ballArray[i];
            }

            /* Take the subset with the heavier ball and goes thru the process again. */
            if (sumOfSideA > sumOfSideB) {
                heavyBall = findHeavyBall(start, index - 1, ballArray);
            } else {
                heavyBall = findHeavyBall(index, end, ballArray);
            }
        }
        /* Array of an odd size.  Find the middle of the array and compare the sum of the weight for both sides.
         * Compare the two sides of the array (minus the middle index).  If sum of both sides are the same, the middle of
         * the array has the heavy ball.  Otherwise, take the heavier of the two sides and go thru this entire 
         * process again.  The search is faster if the ball's position is in the middle of the odd sized array. */

        else {
            index = length / 2 + start;
            for (int i = start; i < index; i++) {
                sumOfSideA += ballArray[i];
            }
            for (int i = index + 1; i <= end; i++) {
                sumOfSideB += ballArray[i];
            }

            // Middle position has the heavy ball
            if (sumOfSideA == sumOfSideB) {
                return index;
            }

            // Have to take the subset with the heavier ball and goes thru the process again.
            if (sumOfSideA > sumOfSideB) {
                heavyBall = findHeavyBall(start, index - 1, ballArray);
            } else {
                heavyBall = findHeavyBall(index, end, ballArray);
            }
        }
        return heavyBall;
    }

}
