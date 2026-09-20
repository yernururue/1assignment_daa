package Algorithms;

import Algorithms.metrics.Metrics;

public class QuickSort {
    public static void algorithm(int[] nums) {
        algorithm(nums, new Metrics());
    }

    public static void algorithm(int[] nums, Metrics metrics) {
        quicksort(nums, 0, nums.length - 1, metrics, 1);
    }

    private static void quicksort(int[] nums, int start, int end, Metrics metrics, int depth) {
        while (start < end) {
            metrics.updateMaxDepth(depth);
            int[] equalRange = ThreeWayPartition.partition(nums, start, end, metrics);

            int leftEnd = equalRange[0] - 1;
            int rightStart = equalRange[1] + 1;
            int leftSize = leftEnd - start + 1;
            int rightSize = end - rightStart + 1;

            if (leftSize < rightSize) {
                quicksort(nums, start, leftEnd, metrics, depth + 1);
                start = rightStart;
            } else {
                quicksort(nums, rightStart, end, metrics, depth + 1);
                end = leftEnd;
            }
        }
    }
}
