package com.colruytgroup.streams.utils;

import java.util.stream.LongStream;

public class TestingUtils {

    private TestingUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static long timeMethodExecution(Runnable runnable, int runXTimes) {
        if (runXTimes < 1) {
            throw new IllegalArgumentException("runXTimes must be greater than 0");
        }
        long start = System.currentTimeMillis();
        LongStream.range(0, runXTimes).forEach(i -> runnable.run());
        long end = System.currentTimeMillis();
        return end - start;
    }
}
