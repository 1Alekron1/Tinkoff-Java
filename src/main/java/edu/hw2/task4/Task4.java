package edu.hw2.task4;

public class Task4 {
    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        var caller = stackTrace[2];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        return new CallingInfo(className, methodName);
    }

    private Task4() {
    }
}
