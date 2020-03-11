package com.fxpro.task;

import org.junit.Test;

import static org.junit.Assert.*;


public class AppTest {

    private App app = new App();

    private static final int LANDSCAPE_LIMIT = 32000;


    @Test
    public void calculateWaterAmount() {
        int[] input1 = {5, 2, 3, 4, 5, 4, 0, 3, 1};
        assertEquals(9, app.calculateWaterAmount(input1));

        /*        * *
         *   _*_*_*_*_
         *
         * */
        int[] input2 = {0, 1, 0, 1, 0, 2, 0, 2, 0};
        assertEquals(4, app.calculateWaterAmount(input2));

        /*
         *       *
         *       **
         *      _**
         *
         * */
        int[] input3 = {0, 3, 2};
        assertEquals(0, app.calculateWaterAmount(input3));

    }

    @Test(expected = RuntimeException.class)
    public void calculateWaterAmount_throwsException_whenInputExceedsLimit() {
        int[] input = new int[LANDSCAPE_LIMIT + 1];

        app.calculateWaterAmount(input);
    }

    @Test(expected = RuntimeException.class)
    public void calculateWaterAmount_throwsException_whenLandscapeHeightExceedsLimit() {
        int[] input = {1, 2, 3, 4, LANDSCAPE_LIMIT + 1, 1, 2};

        app.calculateWaterAmount(input);
    }

    @Test
    public void validateHeightLimit() {
        app.validateHeightLimit(5);
    }

    @Test(expected = RuntimeException.class)
    public void validateHeightLimit_throwsException_whenNegativeInput() {
        app.validateHeightLimit(-5);
    }

    @Test(expected = RuntimeException.class)
    public void validateHeightLimit_throwsException_whenInputExceedsLimit() {
        app.validateHeightLimit(LANDSCAPE_LIMIT + 1);
    }

    @Test
    public void validateLengthLimit() {
        int[] input = new int[5];
        app.validateLengthLimit(input);
    }

    @Test(expected = RuntimeException.class)
    public void validateLengthLimit_throwsException_whenInputExceedsLimit() {
        int[] input = new int[LANDSCAPE_LIMIT + 1];
        app.validateLengthLimit(input);
    }


}
