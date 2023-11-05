package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalTest {

    @Test
    @DisplayName("Сортировка животных по росту (задача 1)")
    void testTask1() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false));

        List<Animal> sortedAnimals = Requests.task1(animals);

        assertThat(sortedAnimals).containsExactly(
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true)
        );
    }

    @Test
    @DisplayName("Сортировка животных по весу и выбор k первых (задача 2)")
    void testTask2() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false));

        List<Animal> topKAnimals = Requests.task2(animals, 2);

        assertThat(topKAnimals).containsExactly(
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false)
        );
    }

    @Test
    @DisplayName("Подсчет животных каждого вида (задача 3)")
    void testTask3() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 1, false));

        Map<Animal.Type, Integer> typeCount = Requests.task3(animals);

        assertThat(typeCount).containsOnly(
            entry(Animal.Type.CAT, 1),
            entry(Animal.Type.DOG, 1),
            entry(Animal.Type.BIRD, 1),
            entry(Animal.Type.FISH, 1)
        );
    }

    @Test
    @DisplayName("Поиск животного с самым длинным именем (задача 4)")
    void testTask4() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Elephant", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false));

        Animal longestNameAnimal = Requests.task4(animals);

        assertThat(longestNameAnimal).isEqualTo(new Animal("Elephant", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
    }

    @Test
    @DisplayName("Определение, каких животных больше: самцов или самок (задача 5)")
    void testTask5() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 1, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 1, false));

        Animal.Sex moreSex = Requests.task5(animals);

        assertThat(moreSex).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Поиск самого тяжелого животного каждого вида (задача 6)")
    void testTask6() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 3, 35, 6, true));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 40, 7, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 5, 50, 8, false));

        Map<Animal.Type, Animal> heaviestAnimals = Requests.task6(animals);

        assertThat(heaviestAnimals).containsOnly(
            entry(Animal.Type.CAT, new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 3, 35, 6, true)),
            entry(Animal.Type.DOG, new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 40, 7, true)),
            entry(Animal.Type.BIRD, new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 5, 50, 8, false))
        );
    }

    @Test
    @DisplayName("Поиск K-го по старшинству животного (задача 7)")
    void testTask7() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 5, 10, 1, false));

        Animal kthOldest = Requests.task7(animals, 3);

        assertThat(kthOldest).isEqualTo(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
    }

    @Test
    @DisplayName("Поиск самого тяжелого животного среди животных ниже k см (задача 8)")
    void testTask8() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 6, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 2, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 5, 10, 1, false));

        Optional<Animal> heaviestBelowHeight = Requests.task8(animals, 20);

        assertThat(heaviestBelowHeight).isPresent();
        assertThat(heaviestBelowHeight.get()).isEqualTo(new Animal(
            "Bird",
            Animal.Type.BIRD,
            Animal.Sex.F,
            1,
            10,
            2,
            false
        ));
    }

    @Test
    @DisplayName("Сумма лап у животных в списке (задача 9)")
    void testTask9() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 2, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 5, 10, 0, false));

        int totalPaws = Requests.task9(animals);

        assertThat(totalPaws).isEqualTo(10);
    }

    @Test
    @DisplayName("Список животных с возрастом, не совпадающим с количеством лап (задача 10)")
    void testTask10() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 2, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 0, 10, 0, false));

        List<Animal> mismatchedAgeAndPaws = Requests.task10(animals);

        assertThat(mismatchedAgeAndPaws).containsExactly(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true)
        );
    }

    @Test
    @DisplayName("Список животных, которые могут укусить и рост которых превышает 100 см (задача 11)")
    void testTask11() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 90, 4, false));
        animals.add(new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 3, 110, 4, true));
        animals.add(new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 1, 120, 2, false));
        animals.add(new Animal("Fish1", Animal.Type.FISH, Animal.Sex.F, 5, 80, 1, true));

        List<Animal> potentialBiters = Requests.task11(animals);

        assertThat(potentialBiters).containsExactly(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 3, 110, 4, true)
        );
    }

    @Test
    @DisplayName("Список животных, у которых вес превышает рост (задача 12)")
    void testTask12() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 2, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 5, 10, 0, false));

        int weightGreaterThanHeightCount = Requests.task12(animals);

        assertThat(weightGreaterThanHeightCount).isEqualTo(0);
    }

    @Test
    @DisplayName("Список животных, имена которых состоят из более чем двух слов (задача 13)")
    void testTask13() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true));
        animals.add(new Animal("Beautiful Red Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 2, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 5, 10, 0, false));

        List<Animal> multiWordNames = Requests.task13(animals);

        assertThat(multiWordNames).containsExactly(
            new Animal("Beautiful Red Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 2, false)
        );
    }

    @Test
    @DisplayName("Проверка наличия собаки с ростом более k см (задача 14)")
    void testTask14() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 2, false));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 5, 10, 0, false));

        boolean hasBigDog = Requests.task14(animals, 30);

        assertThat(hasBigDog).isTrue();
    }

    @Test
    @DisplayName("Сумма веса животных каждого вида от k до l лет (задача 15)")
    void testTask15() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 3, 35, 4, true));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 40, 7, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 5, 50, 8, false));

        Map<Animal.Type, Integer> weightSum = Requests.task15(animals, 2, 4);

        assertThat(weightSum).containsOnly(
            entry(Animal.Type.CAT, 4)
        );
    }

    @Test
    @DisplayName("Список животных, отсортированных по виду, полу и имени (задача 16)")
    void testTask16() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 3, 35, 4, true));
        animals.add(new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 4, 40, 7, true));
        animals.add(new Animal("Bird1", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 8, false));

        List<Animal> sortedAnimals = Requests.task16(animals);

        assertThat(sortedAnimals).containsExactly(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 4, 40, 7, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 3, 35, 4, true),
            new Animal("Bird1", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 8, false)
        );
    }

    @Test
    @DisplayName("Сравнение частоты укусов пауков и собак (задача 17)")
    void testTask17() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 2, 30, 4, true));
        animals.add(new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 3, 35, 4, true));
        animals.add(new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 4, 40, 7, false));
        animals.add(new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 5, 50, 8, true));

        boolean spidersBiteMoreThanDogs = Requests.task17(animals);

        assertThat(spidersBiteMoreThanDogs).isTrue();
    }

    @Test
    @DisplayName("Поиск самой тяжелой рыбки среди двух списков (задача 18)")
    void testTask18() {
        List<Animal> fishList1 = new ArrayList<>();
        fishList1.add(new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 15, 3, true));
        fishList1.add(new Animal("Fish2", Animal.Type.FISH, Animal.Sex.M, 2, 20, 2, true));

        List<Animal> fishList2 = new ArrayList<>();
        fishList2.add(new Animal("Fish3", Animal.Type.FISH, Animal.Sex.F, 3, 25, 4, true));
        fishList2.add(new Animal("Fish4", Animal.Type.FISH, Animal.Sex.F, 4, 30, 5, true));

        Animal heaviestFish = Requests.task18(fishList1, fishList2);

        assertThat(heaviestFish).isEqualTo(new Animal("Fish4", Animal.Type.FISH, Animal.Sex.F, 4, 30, 5, true));
    }

    @Test
    @DisplayName("Поиск животных с ошибками и перечисление ошибок (задача 19)")
    void testTask19() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, -3, 35, 4, true));
        animals.add(new Animal("", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 8, false));
        animals.add(new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 4, 40, 2, false));
        animals.add(new Animal("Mr. Dog", Animal.Type.DOG, Animal.Sex.M, 11, 60, 9, true));

        Map<String, Set<String>> errors = Requests.task19(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                .map(ValidationError::getMessage)
                .collect(Collectors.toSet())
            ));

        assertThat(errors).containsOnly(
            entry("Dog1", Set.of(
                "Age must be non-negative."
            )),
            entry("", Set.of("Name is required.")),
            entry("Mr. Dog", Set.of("Unusual combination of name and age."))
        );
    }

    @Test
    @DisplayName("Поиск животных с ошибками и объединение ошибок в строку (задача 20)")
    void testTask20() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 4, false));
        animals.add(new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 3, 35, 4, true));
        animals.add(new Animal("Bird1", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 8, false));
        animals.add(new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 4, 40, -2, false));

        Map<String, String> errorStrings = Requests.task20(animals);

        assertThat(errorStrings).containsOnly(
            entry("Fish1", "Weight must be non-negative.")
        );
    }
}

