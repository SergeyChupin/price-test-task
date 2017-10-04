package ru.csi.util;

public class IdGenerator {

    private static int value = 1000;

    public static int generateId() {
        return value++;
    }
}
