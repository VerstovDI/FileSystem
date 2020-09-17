package Monitor.monitor;

import Structure.FileSystemStructure.FileSystem;

import java.net.URISyntaxException;
import java.util.function.Consumer;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    static final Monitor monitor = new Monitor(new FileSystem(0, "",
            "", 0, 0, 100));

    public static int intInput(){
        int rez;
        String mistake = "";
        Scanner sc = new Scanner(System.in);
        do {
            try {
                rez = sc.nextInt();
                break;
            } catch (InputMismatchException nfe) {
                mistake = sc.nextLine();
            }
            System.out.print("Некорректный ввод " + mistake +
                    ", ожидается целое число, пожалуйста, повторите: ");
        } while(true);
        return rez;
    }

    private static void doWithQuestionAboutSave(Consumer<Monitor> func){
        Scanner sc = new Scanner(System.in);
        System.out.print("Вы хотите сохранить текущую файловую систему?(д/н): ");
        String yn;
        while (true) {
            yn = sc.next();
            if (yn.equals("y") || yn.equals("д")) {
                monitor.save();
                func.accept(monitor);
                break;
            }

            if (yn.equals("n") || yn.equals("н")) {
                func.accept(monitor);
                break;
            }
            System.out.print("Некорректный ввод, пожалуйста, повторите: ");
        }
    }

    public static void init(){
        System.out.println("Вас приветствует команда С17-501");
        System.out.print("1 - Загрузить файловую систему из файла\n" +
                "2 - Создать новую файловую систему\nВаш выбор: ");
        int choice = 0;
        boolean check = true;
        while (choice < 1 || choice > 2) {
            if (!check) {
                System.out.print("Некорректный ввод " + choice + ", пожалуйста, повторите: ");
            }
            choice = intInput();
            check = false;
        }

        if (choice == 1) {
            monitor.download();
        } else {
            monitor.create();
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        init();
        while (true) {
            System.out.print(monitor.fs.ownerName + "> ");
            String command = sc.nextLine();

            if (command.trim().equals("exit")) {
                doWithQuestionAboutSave((monitor) -> {});
                break;
            }

            if (command.trim().equals("create")) {
                doWithQuestionAboutSave(Monitor::create);
                check = true;
            }

            if (command.trim().equals("download")) {
                doWithQuestionAboutSave(Monitor::download);
                check = true;
            }

            if (command.trim().equals("help")) {
                monitor.help();
                check = true;
            }

            if (command.trim().equals("create file")) {
                monitor.file();
                check = true;
            }

            if (command.trim().equals("delete")) {
                monitor.delete();
                check = true;
            }

            if (command.trim().equals("save")) {
                monitor.save();
                check = true;
            }

            if (command.trim().equals("add")) {
                monitor.add();
                check = true;
            }

            if (command.trim().equals("title")) {
                monitor.title();
                check = true;
            }

            if (command.trim().equals("title in order")) {
                monitor.title_in_order();
                check = true;
            }

            if (command.trim().equals("defragmentation")) {
                monitor.defragmentation();
                check = true;
            }

            if (command.trim().equals("fragmentation")) {
                monitor.fragmentation();
                check = true;
            }

            if (command.trim().equals("info")){
                System.out.println(monitor.fs);
                check = true;
            }

            if (!check) {
                System.out.println("Не найдена команда " + command + ". Пожалуйста, повторите ввод.\n" +
                        "Вся информация о командах доступна по команде help.");
            }

            check = false;
        }
    }
}