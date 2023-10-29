package edu.hw3;

import java.util.ArrayList;

public class Task2 {

    public static final char OPEN_BRACKET = '(';
    public static final char CLOSE_BRACKET = ')';

    public static String[] clusterize(String str) {
        var list = new ArrayList<String>();
        var counter = 0;
        var builder = new StringBuilder();
        for (Character c : str.toCharArray()) {
            builder.append(c);
            if (c.equals(OPEN_BRACKET)) {
                counter++;
            } else if (c.equals(CLOSE_BRACKET)) {
                counter--;
            }
            if (counter == 0) {
                list.add(builder.toString());
                builder.setLength(0);
            }
        }
        return list.toArray(new String[0]);
    }

    private Task2() {

    }
}
