package Algorithms;

import Algorithms.metrics.Metrics;

import java.util.concurrent.ThreadLocalRandom;

public class ThreeWayPartition {
    public static int[] partition(int[] nums, int start, int end, Metrics metrics) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(start, end + 1);
        int pivot = nums[pivotIndex];

        int less = start;
        int current = start;
        int greater = end;

        while (current <= greater) {
            metrics.incrementComparisons();
            if (nums[current] < pivot) {
                swap(nums, less, current);
                less++;
                current++;
            } else {
                metrics.incrementComparisons();
                if (nums[current] > pivot) {
                    swap(nums, current, greater);
                    greater--;
                } else {
                    current++;
                }
            }
        }

        return new int[]{less, greater};
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
