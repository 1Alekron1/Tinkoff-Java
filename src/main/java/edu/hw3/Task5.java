package edu.hw3;

import java.util.Arrays;
import java.util.Comparator;

public class Task5 {
    public static String[] parseContacts(String[] names, String order) {
        if (names == null || names.length == 0) {
            return new String[0];
        }

        Comparator<String> comparator = getComparator(order);
        Arrays.sort(names, comparator);
        return names;
    }

    private static Comparator<String> getComparator(String order) {
        return (name1, name2) -> {
            String[] split1 = name1.split(" ");
            String[] split2 = name2.split(" ");
            String lastName1 = (split1.length > 1) ? split1[1] : split1[0];
            String lastName2 = (split2.length > 1) ? split2[1] : split2[0];

            if (order.equals("ASC")) {
                return lastName1.compareTo(lastName2);
            } else {
                return lastName2.compareTo(lastName1);
            }
        };
    }

    private Task5() {

    }
}
