package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class AnimalValidator {

    public static final int MAX_LENGHT = 50;
    public static final int MIN_AGE = 10;

    private AnimalValidator() {
    }

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name() == null || animal.name().isEmpty()) {
            errors.add(new ValidationError("Name is required."));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("Age must be non-negative."));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("Weight must be non-negative."));
        }

        if (animal.height() < 0) {
            errors.add(new ValidationError("Height must be non-negative."));
        }

        if (animal.name() != null && animal.name().length() > MAX_LENGHT) {
            errors.add(new ValidationError("Name is too long."));
        }

        if (animal.age() > MIN_AGE && animal.name() != null && animal.name().startsWith("Mr.")) {
            errors.add(new ValidationError("Unusual combination of name and age."));
        }

        return errors;
    }
}
