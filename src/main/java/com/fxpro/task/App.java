package com.fxpro.task;


import java.util.Arrays;

public class App {

    private static final int LANDSCAPE_LIMIT = 32000;

    public static void main(String[] args) {
        App app = new App();

        if (args.length == 0) {
            System.out.println("Running with mock inputs...");
            app.runMock();
        } else {
            int[] landscape = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
            System.out.println("Water amount: " + app.calculateWaterAmount(landscape));
        }
    }

    /**
     * Calculates total water amount between pits.
     *
     * @param landscape array representation of pits
     * @return total water amount
     * @throws RuntimeException when given landscape sizes are exceeds the max limit
     */
    long calculateWaterAmount(int[] landscape) {

        validateLengthLimit(landscape);

        //because we start to iterate from 1st index and stop before last. So we have to validate bounds as well.
        validateHeightLimit(landscape[0]);
        validateHeightLimit(landscape[landscape.length - 1]);

        long waterAmount = 0L;


        for (int i = 1; i < landscape.length - 1; i++) {

            validateHeightLimit(landscape[i]);

            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, landscape[j]);
            }

            int rightMax = 0;
            for (int j = i + 1; j < landscape.length; j++) {
                rightMax = Math.max(rightMax, landscape[j]);
            }

            int currentWater = Math.min(leftMax, rightMax) - landscape[i];
            if (currentWater < 0) continue;

            waterAmount = waterAmount + currentWater;
        }

        return waterAmount;
    }

    void validateHeightLimit(int height) {
        if (height > LANDSCAPE_LIMIT) {
            throw new RuntimeException(
                    String.format("Landscape height limit exceeds. Max: %d, Actual: %d", LANDSCAPE_LIMIT, height));
        } else if (height < 0) {
            throw new RuntimeException("Landscape height is negative: " + height);
        }
    }

    void validateLengthLimit(int[] landscape) {
        if (landscape.length > LANDSCAPE_LIMIT) {
            throw new RuntimeException(
                    String.format("Landscape length limit exceeds. Max: %d, Actual: %d", LANDSCAPE_LIMIT, landscape.length));
        }
    }

    private void runMock() {
        int[][] inputs = {
                {5, 2, 3, 4, 5, 4, 0, 3, 1},
                {8, 5, 4, 5, 1, 1, 6, 1, 5, 1, 4},
                {8, 1, 4, 1, 4, 1, 5, 3, 1}
        };

        for (int[] input : inputs) {
            long result = calculateWaterAmount(input);
            System.out.println(String.format("Water amount for input %s --> %d", Arrays.toString(input), result));
        }


    }
}
