package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public String getClassName() {
        return this.className;
    }

    public String getMethodName() {
        return this.methodName;
    }
}
