package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        buildLines(5).forEach(System.out::println);
    }

    static java.util.List<String> buildLines(int count) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        for (int i = 1; i <= count; i++) {
            lines.add("i = " + i);
        }
        return lines;
    }
}