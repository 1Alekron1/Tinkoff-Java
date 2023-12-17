package edu.hw10.Task1;

public class Example {
    public static final int SPECIAL_VALUE = 42;
    private int intValue;
    @NotNull
    private String stringValue;
    @Min(1)
    @Max(10)
    private int rangedIntValue;

    public Example() {
    }

    public static Example create() {
        var ex = new Example();
        ex.setInt(SPECIAL_VALUE);
        return ex;
    }

    @Override
    public String toString() {
        return "Example{"
            + "intValue=" + intValue
            + ", stringValue='" + stringValue
            + '\'' + ", rangedIntValue=" + rangedIntValue
            + '}';
    }

    public int getIntValue() {
        return rangedIntValue;
    }

    public int getBasicIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setInt(int value) {
        intValue = value;
    }
}
