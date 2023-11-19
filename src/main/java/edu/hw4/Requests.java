package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Requests {

    public static final int MAX_HEIGHT = 100;

    private Requests() {
    }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> task2(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    public static Animal task4(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElseThrow();
    }

    public static Animal.Sex task5(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting())).entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow();
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(
            Animal::type,
            animal -> animal,
            BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
        ));
    }

    public static Animal task7(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).skip(k - 1).findFirst()
            .orElseThrow();
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k)
            .sorted(Comparator.comparingInt(Animal::weight).reversed()).findFirst();
    }

    public static Integer task9(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.paws() != animal.age()).toList();
    }

    public static List<Animal> task11(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > MAX_HEIGHT).toList();
    }

    public static Integer task12(List<Animal> animals) {
        return (int) animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 1).toList();
    }

    public static Boolean task14(List<Animal> animals, int k) {
        return animals.stream().anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int k, int i) {
        return animals.stream().filter(animal -> animal.age() > k && animal.age() < i)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> animal.weight())));
    }

    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    public static Boolean task17(List<Animal> animals) {
        long dogBitesCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .filter(Animal::bites)
            .count();
        long spiderBitesCount = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .filter(Animal::bites)
            .count();

        try {
            return spiderBitesCount / animals.stream()
                .filter(animal -> animal.type() == Animal.Type.SPIDER).count() > dogBitesCount / animals.stream()
                .filter(animal -> animal.type() == Animal.Type.DOG).count();
        } catch (Exception e) {
            return false;
        }

    }

    public static Animal task18(List<Animal> animals1, List<Animal> animals2) {
        return Stream.concat(animals1.stream(), animals2.stream()).filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !AnimalValidator.validateAnimal(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidator::validateAnimal,
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                }
            ));
    }

    public static Map<String, String> task20(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !AnimalValidator.validateAnimal(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                animal -> getFieldErrors(animal),
                (existing, replacement) -> existing
            ));
    }

    public static String getFieldErrors(Animal animal) {
        return AnimalValidator.validateAnimal(animal).stream()
            .map(ValidationError::getMessage)
            .collect(Collectors.joining(" "));
    }
}
