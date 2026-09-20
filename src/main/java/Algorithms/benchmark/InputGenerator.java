package Algorithms.benchmark;

import java.util.Random;

public class InputGenerator {
    private static final long DEFAULT_SEED = 42L;

    private final Random random;

    public InputGenerator() {
        this(DEFAULT_SEED);
    }

    public InputGenerator(long seed) {
        random = new Random(seed);
    }

    public int[] generateRandom(int size) {
        int[] values = new int[requireValidSize(size)];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt();
        }
        return values;
    }

    public int[] generateSorted(int size) {
        int[] values = new int[requireValidSize(size)];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        return values;
    }

    public int[] generateDuplicates(int size) {
        int[] values = new int[requireValidSize(size)];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(10);
        }
        return values;
    }

    private int requireValidSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative");
        }
        return size;
    }
}
