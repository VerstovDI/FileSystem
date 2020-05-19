package Monitor.program;

import Functions.AddInfo.AddInfoCommand;
import Functions.CreateFS.CreateFSCommand;
import Functions.CreateFS.CreateFSParameters;
import Functions.CreateFile.CreateFileCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.DownloadFS.DownloadFSCommand;
import Functions.ParameterReaders.ReadParameters;
import Functions.SaveFS.SaveFSCommand;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IParameterReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {
        System.out.println("Вас приветствует команда С17-501 блаблабла");
        System.out.print("1 - Загрузить файловую систему из файла\n2 - Создать новую файловую систему\nВаш выбор: ");
        int choice = 0;
        boolean check = true;
        String mistake = "";
        Scanner sc = new Scanner(System.in);
        while (choice < 1 || choice > 2) {
            if (!check && mistake.length() > 0) {
                System.out.print("Некорректный ввод " + mistake + ", пожалуйста, повторите: ");
            }
            if (!check && mistake.length() == 0) {
                System.out.print("Некорректный ввод " + choice + ", пожалуйста, повторите: ");
            }
            mistake = "";
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException nfe) {
                choice = 0;
                mistake = sc.next();
            }
            check = false;
        }
        FileSystem fs = new FileSystem(0, "", "", 0, 0);
        if (choice == 1) {
            DownloadFSCommand downloadFS = new DownloadFSCommand();
            IParameterReader ipr = new ReadParameters();
            downloadFS.Execute(fs, ipr, () -> {
            });
            System.out.println("Файловая система успешно загружена!");
        } else {
            CreateFSCommand newFS = new CreateFSCommand();
            IParameterReader newFSPar = new CreateFSParameters(0, "", "", 0, 0);
            newFS.Execute(fs, newFSPar, () -> {
            });
            System.out.println("Файловая система успешно создана!");
        }

        while (true) {
            System.out.print(fs.ownerName + "> ");
            String command = sc.next();

            if (command.equals("exit")) {
                System.out.print("Вы хотите сохранить текущую файловую систему?(д/н): ");
                String yn;
                while (true) {
                    yn = sc.next();
                    if (yn.equals("y") || yn.equals("д")) {
                        SaveFSCommand sfc = new SaveFSCommand();
                        IParameterReader ipr = new ReadParameters();
                        sfc.Execute(fs, ipr, () -> {
                        });
                        System.out.println("Файловая система успешно сохранена!");
                        break;
                    }

                    if (yn.equals("n") || yn.equals("н")) {
                        break;
                    }
                    System.out.print("Некорректный ввод, пожалуйста, повторите: ");
                }
                System.out.println("Досвидания!");
                break;
            }

            if (command.equals("help")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String helperPath = System.getProperty("user.dir") + "/src/main/java/Monitor/program/help.txt";
                File f = new File(helperPath);
                BufferedReader fin = new BufferedReader(new FileReader(f));
                String line;
                while ((line = fin.readLine()) != null) System.out.println(line);
                check = true;
            }

            if (command.equals("file")) {
                CreateFileCommand cfc = new CreateFileCommand();
                IParameterReader ipr = new ReadParameters();
                cfc.Execute(fs, ipr, () -> {
                });
                System.out.println("Файл успешно создан!");
                check = true;
            }

            if (command.equals("delete")) {
                DeleteFileCommand dfc = new DeleteFileCommand();
                IParameterReader ipr = new ReadParameters();
                dfc.Execute(fs, ipr, () -> {
                });
                System.out.println("Файл успешно удалён!");
                check = true;
            }

            if (command.equals("save")) {
                SaveFSCommand sfc = new SaveFSCommand();
                IParameterReader ipr = new ReadParameters();
                sfc.Execute(fs, ipr, () -> {
                });
                System.out.println("Файловая система успешно сохранена!");
                check = true;
            }

            if (command.equals("add")) {
                AddInfoCommand aic = new AddInfoCommand();
                IParameterReader ipr = new ReadParameters();
                aic.Execute(fs, ipr, () -> {
                });
                System.out.println("Информация в файл добавлена!");
                check = true;
            }

            if (!check) {
                System.out.println("Не найдена команда " + command + ". Пожалуйста, повторите ввод.\nВся информация о командах доступна по команде help.");
            }

            check = false;
        }
    }
}