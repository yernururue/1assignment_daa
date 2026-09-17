package Algorithms;

public class MergeSort {
    public static void mergeSort(int[] nums) {
        int length = nums.length;

        if (length <= 1) {
            return;
        }

        int middle = length/2;
        int[] leftNums = new int[middle];
        int[] rightNums = new int[length - middle];


        //copying elements from original array
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (i<middle) {
                leftNums[i] = nums[i];
            } else {
                rightNums[j] = nums[i];
                j++;
            }
        }
        mergeSort(leftNums);
        mergeSort(rightNums);
        merge(leftNums, rightNums, nums);
    }

    public static void merge(int[] leftNums, int[] rightNums, int[] nums) {

        int leftSize = nums.length/2;
        int rightSize = nums.length - leftSize;
        int i = 0;
        int l = 0;
        int r = 0;

        while(l<leftSize && r<rightSize) {
            if (leftNums[l] < rightNums[r]) {
                nums[i] = leftNums[l];
                i++;
                l++;
            } else {
                nums[i] = rightNums[r];
                i++;
                r++;
            }
        }

        while (l<leftSize) {
            nums[i] = leftNums[l];
            i++;
            l++;
        }

        while (r < rightSize) {
            nums[i] = rightNums[r];
            i++;
            r++;
        }
    }
}

