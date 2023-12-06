package com.example;

import java.util.Scanner;

import com.example.commands.ChildDirectoryCommand;
import com.example.commands.CommandsInvoker;
import com.example.commands.CreateNewDirectoryCommand;
import com.example.commands.DeleteSubDirectoriesCommand;
import com.example.commands.ExistCommand;
import com.example.commands.FindCommand;
import com.example.commands.GetChildsCommand;
import com.example.commands.GetDirectoryNameCommand;
import com.example.commands.GetParantName;
import com.example.commands.GetSubDirectoriesCommand;
import com.example.commands.GoToChildCommand;
import com.example.commands.GoToParentDirectory;
import com.example.commands.ICommand;


public class App {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        WorkingDirectory wd = WorkingDirectory.getInstance("src\\test\\resources\\testFolder");

        while (running) {
            clearConsole();
            System.out.println("Выберите одну из команд:");
            System.out.println("1. ChildDirectoryCommand");
            System.out.println("2. CreateNewDirectoryCommand");
            System.out.println("3. DeleteSubDirectoriesCommand");
            System.out.println("4. ExistCommand");
            System.out.println("5. FindCommand");
            System.out.println("6. GetChildsCommand");
            System.out.println("7. GetDirectoryNameCommand");
            System.out.println("8. GetParantName");
            System.out.println("9. GetSubDirectoriesCommand");
            System.out.println("10. GoToChildCommand");
            System.out.println("11. GoToParentDirectory");
            System.out.println("12. GetDirectoryName");

            System.out.print("Введите номер команды (или '0' для выхода): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем лишний перевод строки

            ICommand command = null;
            switch (choice) {
                case 1: {
                    String input = readStringInput(scanner, "Введите имя предполагаемого ребенка");
                    command = new ChildDirectoryCommand(input);
                    break;
                }
                case 2: {
                    String input = readStringInput(scanner, "Введите название новой директории");
                    command = new CreateNewDirectoryCommand(input);
                    break;
                }
                case 3:
                    command = new DeleteSubDirectoriesCommand();
                    break;
                case 4: {
                    String input = readStringInput(scanner, "Введите имя файла или директории");
                    command = new ExistCommand(input);
                    break;
                }
                case 5: {
                    String input = readStringInput(scanner, "Введите расширение файла, который будет найден");
                    command = new FindCommand(input);
                    break;
                }
                case 6:
                    command = new GetChildsCommand();
                    break;
                case 7:
                    command = new GetDirectoryNameCommand();
                    break;
                case 8:
                    command = new GetParantName();
                    break;
                case 9:
                    command = new GetSubDirectoriesCommand();
                    break;
                case 10: {
                    String input = readStringInput(scanner, "Введите название поддиректории");
                    command = new GoToChildCommand(input);
                    break;
                }
                case 11:
                    command = new GoToParentDirectory();
                    break;
                case 12:
                    command = (WorkingDirectory w) -> {System.out.println(w.getDirectoryName());};
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный номер команды.");
            }
            if(running)
                command.apply(wd);
            if (running) {
                waitForEnter();
            }
        }
        scanner.close();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void waitForEnter() {
        System.out.println("Нажмите Enter, чтобы продолжить...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readStringInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
