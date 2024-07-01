package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> roster = new ArrayList<>(List.of(

                new Student("Fred", 68),
                new Student("Jim", 52),
                new Student("Sheila", 93),
                new Student("Bill", 66),
                new Student("Andy", 73),
                new Student("Fred", 83),
                new Student("Mary", 83)
//                new Student(null, 44),
//                new Student(null, 63)
        ));

        System.out.println("Original order");
        roster.forEach(System.out::println);
        System.out.println("Grade ordee");
        roster.sort(Comparator.comparingInt(s -> s.getPrecent()));
        roster.forEach(System.out::println);
        System.out.println("Name ordering");
        roster.sort(Comparator.comparing(s -> s.getName()));
        roster.forEach(System.out::println);
        System.out.println("Name, then grade descending, order");
        roster.sort(Comparator.comparing((Student s) -> s.getName())
                .thenComparing(s -> s.getPrecent(), Comparator.reverseOrder()));
        roster.forEach(System.out::println);

        System.out.println("Exam exampl");
        roster.sort(Comparator.comparing((Student s) -> s.getName(), Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER))
                .thenComparingInt(s -> s.getPrecent()));
        roster.forEach(System.out::println);

        var la = List.of(1, 2, 3);
        var lb = List.copyOf(la);
        var ia1 = new int[]{};
        var ia2 = Arrays.copyOf(ia1, 0);

        System.out.println(la == lb);
        System.out.println(ia1 == ia2);
    }
}