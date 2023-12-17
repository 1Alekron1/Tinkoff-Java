package edu.hw10.Task2;

public interface Calculator {
    @Cache(persist = true)
    int calculate(int number);
}

