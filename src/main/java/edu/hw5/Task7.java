package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private Task7() {
    }

        public static boolean checkThirdSymbol(String input) {
            String regex = "^.{2}0.*$";
            return Pattern.matches(regex, input);
        }

        public static boolean checkStartAndEnd(String input) {
            String regex = "^(.).*\\1$";
            return Pattern.matches(regex, input);
        }

        public static boolean checkLength(String input) {
            String regex = "^.{1,3}$";
            return Pattern.matches(regex, input);
        }
}
