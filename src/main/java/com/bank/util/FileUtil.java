package com.bank.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(";"));
    }
}
