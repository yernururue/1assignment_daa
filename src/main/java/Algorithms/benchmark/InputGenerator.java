package Algorithms.benchmark;

import java.util.Random;

public class InputGenerator {
    private final Random random;

    public InputGenerator() {
        random = new Random();
    }

    public InputGenerator(long seed) {
        random = new Random(seed);
    }

    public int[] generateRandom(int size) {
        // TODO: создать массив случайных чисел
        return new int[size];
    }

    public int[] generateSorted(int size) {
        // TODO: создать отсортированный массив
        return new int[size];
    }

    public int[] generateDuplicates(int size) {
        // TODO: создать массив со значениями от 0 до 9
        return new int[size];
    }
}
