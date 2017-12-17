package hu.unideb.inf.firstvan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberParser {

    private static final Pattern numberPattern = Pattern.compile("\\d+");

    public static int getIntNumberFromString(String text) {
        Matcher matcher = numberPattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group().trim());
        }

        return 0;
    }
}
