package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.students.Applicant;
import com.example.students.Subject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        FCPMS faculty = new FCPMS();
        Scanner scanner = new Scanner(System.in);

        boolean addMoreUsers = true;

        while (addMoreUsers) {
            String fullName = readStringInput(scanner, "Введите ФИО: ");
            int numOfSubjects = Integer.parseInt(readStringInput(scanner, "Введите количество предметов: "));
            scanner.nextLine(); // Считываем лишний перевод строки

            List<Subject> lSubj = new ArrayList<>();
            for (int i = 0; i < numOfSubjects; i++) {
                String name = readStringInput(scanner, "Введите название предмета " + (i + 1) + ": ");
                int grade = Integer.parseInt(readStringInput(scanner, "Введите бал " + (i + 1) + ": "));
                lSubj.add(new Subject(name, grade));
            }

            Applicant applicant = Applicant.builder()
                    .name(fullName)
                    .id(fullName.hashCode())
                    .subjects(lSubj)
                    .faculty(faculty)
                    .build();

            System.out.println("\nХотите добавить еще одного абитуриента? (да/нет): ");
            String response = scanner.nextLine().toLowerCase();
            addMoreUsers = response.equals("да");
        }
        scanner.close();
        faculty.notifyApplicantAboutAdmission();
    }

    private static String readStringInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
