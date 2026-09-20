package Algorithms;

import Algorithms.metrics.Metrics;

public class QuickSelect {
    public static int select(int[] nums, int k) {
        return select(nums, k, new Metrics());
    }

    public static int select(int[] nums, int k, Metrics metrics) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        if (k < 0 || k >= nums.length) {
            throw new IllegalArgumentException("k must be between 0 and nums.length - 1");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int[] equalRange = ThreeWayPartition.partition(nums, start, end, metrics);

            if (k < equalRange[0]) {
                end = equalRange[0] - 1;
            } else if (k > equalRange[1]) {
                start = equalRange[1] + 1;
            } else {
                return nums[k];
            }
        }

        throw new IllegalStateException("Selection failed");
    }
}
