package edu.hw5;

public class Task4 {
    private Task4() {
    }

    public static boolean containsSpecialCharacter(String password) {
        String regex = ".*[~!@#$%^&*|].*";
        return password.matches(regex);
    }
}
