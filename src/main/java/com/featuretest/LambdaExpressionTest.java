package com.featuretest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by T_DanteZ1 on 25/01/18.
 */

public class LambdaExpressionTest {

    interface CheckPerson {
        boolean test(Person p);
    }

    static class Person {

        enum Sex {
            MALE, FEMALE
        }

        String name;
        int age;
        Sex gender;
        String emailAddress;
        Date birthday;

        public int getAge() {
            return age;
        }

        public void printPerson() {
            System.out.println("Name: " + name);
        }

        public Sex getGender() {
            return gender;
        }

        public String getEmailAddress() {return emailAddress;};

        public static int compareByAge(Person a, Person b) {
            return a.birthday.compareTo(b.birthday);
        }
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    public static void processPersonsWithFunction(List<Person> roster, Predicate<Person> tester, Function<Person, String> mapper, Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String string = mapper.apply(p);
                block.accept(string);
            }
        }
    }

    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public void test() {
        List<Person> list = new ArrayList<>();
        CheckPerson check = (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25;
        printPersons(list, check);
        Predicate<Person> checkPredictable = (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25;
        printPersonsWithPredicate(list, checkPredictable);

        processPersonsWithFunction(
                list,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        processElements(
                list,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        list.stream().filter(p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25).map(p ->  {
                    String string = p.getGender().toString();
                    return p.getEmailAddress() + string;
                }).forEach(string -> System.out.println(string));

        Person[] array = list.toArray(new Person[list.size()]);
        Arrays.sort(array, (Person a, Person b) -> {
            return a.birthday.compareTo(b.birthday);
        });
        Arrays.sort(array, (a, b) -> Person.compareByAge(a, b));
        Arrays.sort(array, Person::compareByAge);

        String[] stringArray = { "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        Comparator.comparing(Person::getAge).reversed().thenComparing(Comparator.comparing(Person::getGender));

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));
        listOfIntegers.stream().reduce(Integer::sum);

    }

}
