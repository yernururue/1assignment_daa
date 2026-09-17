package Algorithms;

public class MergeInsertion {
    public static void algorithm(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        if (nums.length <= 15) {
            insertionSort(nums);
            return;
        }

        int middle = nums.length / 2;
        int[] leftNums = new int[middle];
        int[] rightNums = new int[nums.length - middle];

        for (int i = 0; i < middle; i++) {
            leftNums[i] = nums[i];
        }
        for (int i = middle; i < nums.length; i++) {
            rightNums[i - middle] = nums[i];
        }

        algorithm(leftNums);
        algorithm(rightNums);
        merge(leftNums, rightNums, nums);
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    public static void merge(int[] leftNums, int[] rightNums, int[] nums) {
        int i = 0, l = 0, r = 0;

        while (l < leftNums.length && r < rightNums.length) {
            if (leftNums[l] < rightNums[r]) {
                nums[i++] = leftNums[l++];
            } else {
                nums[i++] = rightNums[r++];
            }
        }

        while (l < leftNums.length) {
            nums[i++] = leftNums[l++];
        }

        while (r < rightNums.length) {
            nums[i++] = rightNums[r++];
        }
    }
}