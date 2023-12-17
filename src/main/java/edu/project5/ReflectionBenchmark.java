package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {

    public static final int TIME_VALUE = 5;
    public static final String NAME = "name";

    public static void startBenchmark() throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(TIME_VALUE))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(TIME_VALUE))
            .build();

        new Runner(options).run();
    }

    private Student student;
    private GetNameFunction lambdaFunction;
    private MethodHandles.Lookup lookup = MethodHandles.lookup();

    @Setup
    public void setup() {
        student = new Student("Alexander", "Bordunov");

        try {
            MethodType methodType = MethodType.methodType(String.class, Student.class);
            MethodHandle methodHandle = lookup.findVirtual(Student.class, NAME, MethodType.methodType(String.class));

            CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(GetNameFunction.class),
                methodType,
                methodHandle,
                methodType
            );

            lambdaFunction = (GetNameFunction) callSite.dynamicInvoker().invokeExact();
        } catch (Throwable e) {
            throw new RuntimeException("Error creating LambdaMetafactory", e);
        }
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws Throwable {
        Method method = Student.class.getMethod(NAME);
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        MethodHandle methodHandle = lookup.findVirtual(Student.class, NAME, MethodType.methodType(String.class));
        String name = (String) methodHandle.invokeExact(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaFunction.apply(student);
        bh.consume(name);
    }
}
