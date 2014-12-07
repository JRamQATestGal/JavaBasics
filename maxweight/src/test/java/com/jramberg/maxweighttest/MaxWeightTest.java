package com.jramberg.maxweighttest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jramberg.maxweight.MaxWeight;

/*
 * This test case assumest hat there is always an array of weights of balls. The array may be empty. It also assumes
 * there is always ONE ball that is heavier than the other balls. The program does not test for the case that all balls
 * being equal.
 */

public class MaxWeightTest {

    /*
     * Test case for if the array size is an odd size array and the heavy ball is towards the beginning of the array list.
     */
    @Test
    public void testMaxWeightOddLeft() throws Exception {

        int[] ballArray = {
                4,
                4,
                4,
                5,
                4,
                4,
                4,
                4,
                4 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(3, position);
    }

    /*
     * Test case for if the array size is an odd size array and the heavy ball is at the middle of the array list.
     */
    @Test
    public void testMaxWeightOddMiddle() throws Exception {

        int[] ballArray = {
                4,
                4,
                4,
                4,
                5,
                4,
                4,
                4,
                4 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(4, position);
    }

    /*
     * Test case for if the array size is an odd size array and the heavy ball is towards the end of the array list.
     */
    @Test
    public void testMaxWeightOddRight() throws Exception {

        int[] ballArray = {
                4,
                4,
                4,
                4,
                4,
                4,
                5,
                4,
                4 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(6, position);
    }

    /*
     * Test case for if the array size is an odd size array and the heavy ball is towards the end of the array list.
     * This particular case eventually checks for and returns the middle of a subset and saving some time in returning
     * the position.
     */
    @Test
    public void testMaxWeightOddRightMiddle() throws Exception {

        int[] ballArray = {
                4,
                4,
                4,
                4,
                4,
                4,
                4,
                4,
                5,
                4,
                4,
                4,
                4 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(8, position);
    }

    /*
     * Test case for if the array size is an even size array and the heavy ball is towards the beginning of the array list.
     */
    @Test
    public void testMaxWeightEvenLeft() throws Exception {

        int[] ballArray = {
                4,
                5,
                4,
                4,
                4,
                4,
                4,
                4 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(1, position);
    }

    /*
     * Test case for if the array size is an even size array and the heavy ball is towards the end of the array list.
     */
    @Test
    public void testMaxWeightEvenRight() throws Exception {

        int[] ballArray = {
                4,
                4,
                4,
                4,
                4,
                5,
                4,
                4 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(5, position);
    }

    /*
     * Test case for one array.  Should be "0" for the position and show the weight of the one ball.
     */
    @Test
    public void testMaxOneBall() throws Exception {

        int[] ballArray = { 5 };

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);
        System.out.println("The position of the heavy ball is: " + position + ", and the weight of the ball is: "
                + ballArray[position]);
        Assert.assertEquals(0, position);
    }

    /*
     * Test case for empty array.
     */
    @Test
    public void testMaxEmptyArray() throws Exception {

        int[] ballArray = {};

        int position = MaxWeight.findHeavyBall(0, ballArray.length - 1, ballArray);

        if (position != -1)
            Assert.fail("FAIL: The array is NOT empty!!!");
        else
            System.out.println("The array is empty!");
        Assert.assertEquals(-1, position);
    }
}
