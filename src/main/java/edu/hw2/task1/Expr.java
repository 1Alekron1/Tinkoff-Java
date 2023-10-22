package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double constant) implements Expr {

        @Override
        public double evaluate() {
            return constant;
        }
    }

    public record Negate(Expr value) implements Expr {

        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr valueToPower, Expr power) implements Expr {

        public Exponent(Expr valueToPower, double power) {
            this(valueToPower, new Constant(power));
        }

        public Exponent(double valueToPower, Expr power) {
            this(new Constant(valueToPower), power);
        }

        public Exponent(double valueToPower, double power) {
            this(new Constant(valueToPower), new Constant(power));
        }

        @Override
        public double evaluate() {
            return Math.pow(valueToPower.evaluate(), power.evaluate());
        }
    }

    public record Addition(Expr firstTerm, Expr secondTerm) implements Expr {
        public Addition(Expr firstTerm, double secondTerm) {
            this(firstTerm, new Constant(secondTerm));
        }

        public Addition(double firstTerm, Expr secondTerm) {
            this(new Constant(firstTerm), secondTerm);
        }

        public Addition(double firstTerm, double secondTerm) {
            this(new Constant(firstTerm), new Constant(secondTerm));
        }

        @Override
        public double evaluate() {
            return firstTerm.evaluate() + secondTerm.evaluate();
        }
    }

    public record Multiplication(Expr firstMultiplier, Expr secondMultiplier) implements Expr {

        public Multiplication(Expr firstMultiplier, double secondMultiplier) {
            this(firstMultiplier, new Constant(secondMultiplier));
        }

        public Multiplication(double firstMultiplier, Expr secondMultiplier) {
            this(new Constant(firstMultiplier), secondMultiplier);
        }

        public Multiplication(double firstMultiplier, double secondMultiplier) {
            this(new Constant(firstMultiplier), new Constant(secondMultiplier));
        }

        @Override
        public double evaluate() {
            return firstMultiplier.evaluate() * secondMultiplier.evaluate();
        }
    }
}
