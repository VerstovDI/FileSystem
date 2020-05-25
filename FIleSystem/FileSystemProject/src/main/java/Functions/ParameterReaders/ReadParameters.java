package Functions.ParameterReaders;

import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IParameterReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReadParameters implements IParameterReader {

    @Override
    public IParameterReader ParameterReader() {
        return null;
    }

    // Считываем имя файла.
    // message - это текст запроса, который выведется на консоль
    // Функция, выполняющая проверку имени файла на длину. Возвращает введённое имя в случае успеха.
    @Override
    public String readFileName(String message) {
        Scanner in = new Scanner(System.in);
        String fileName;
        do {
            System.out.format(message);
            fileName = in.nextLine();
        } while (!checkFileName(fileName));
        return fileName;
    }

    // Функция, выполняющая проверку размера файла. Вовзвращает введённый размер в случае успеха.
    @Override
    public int readFileSize(String message) {
        Scanner in = new Scanner(System.in);
        int fileSize = -1;
        String errMessageNotANumber = "Введено не число!";
        String errMessageIncorrectNumber = "Введённое число не удовлетворяет условиям на размер файла!";
        boolean checkFileSizeFunctionFlag = false;  // Флаг для корректного вывода сообщения об ошибке в случае, когда checkFileSize вернёт false
        do {
            if (checkFileSizeFunctionFlag)
            {
                System.out.println(errMessageIncorrectNumber);
            }
            System.out.format(message);
            checkFileSizeFunctionFlag = true;
            try {
                fileSize = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                checkFileSizeFunctionFlag = false;
                System.out.println(errMessageNotANumber);
            }
        } while (!checkFileSize(fileSize));
        return fileSize;
    }

    // Функция получения текущей даты (дата создания файла) в формате [dd, mm, yyyy] - массив int
    // перенести в интерфейс
    public static int[] getCurrentDate() {
        int[] creationDate = new int[3];
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("d.M.y");
        String dateString = formatForDateNow.format(dateNow);
        String[] parsedDateStrings = dateString.split("\\.");
        for (int i = 0; i < parsedDateStrings.length; i++) {
            creationDate[i] = Integer.parseInt(parsedDateStrings[i]);
        }
        return creationDate;
    }

    @Override
    public int readFSSize(String message){
        Scanner sc = new Scanner(System.in);
        int size;
        do {
            System.out.format(message);
            while (!sc.hasNextInt()) {
                System.out.println("Введено не число! Повторите ввод, пожалуйста!");
                sc.next();
            }
            size = sc.nextInt();
        } while (size < 1 || size > 31) ;
        return size;
    }

    @Override
    public int readSizeForFiles(String message) {
        Scanner sc = new Scanner(System.in);
        int size;
        do {
            System.out.format(message);
            while (!sc.hasNextInt()) {
                System.out.println("Введено не число! Повторите ввод, пожалуйста!");
                sc.next();
            }
            size = sc.nextInt();
        } while (size <= 0 || size > Integer.MAX_VALUE/10);
        return size;
    }

    // Проверка корректности имени файла
    private boolean checkFileName(String fileName) {
        return fileName.length() <= DataInfo.fileNameLengthLimit && fileName.length() > 0;
    }

    // Проверка корректности размера файла
    private boolean checkFileSize(int fileSize) {
        return fileSize > 0 && fileSize < FileSystem.fileSystemSize;
    }

}
