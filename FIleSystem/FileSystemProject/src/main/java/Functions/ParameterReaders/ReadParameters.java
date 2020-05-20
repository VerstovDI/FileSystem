package Functions.ParameterReaders;

import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.IParameterReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReadParameters implements IParameterReader {

    @Override
    public IParameterReader ParameterReader() {
        return null;
    }

    //@Override
    // Считываем имя файлы.
    // message - это текст запроса, который выведется на консоль
    // Функция, выполняющая проверку имени файла на длину. Вовзвращает введённое имя в случае успеха.
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
    public int readFileSize(String message) {
        Scanner in = new Scanner(System.in);
        int fileSize;
        do {
            System.out.format(message);
            fileSize = in.nextInt();
        } while (!checkFileSize(fileSize));
        return fileSize;
    }

    /*
    Считывание информации для добавления в файл. Поскольку самих файлов в ФС нет, то сохранять текст бессмысленно
     */
    public  String readInfo (String message,int fileSize) {
        StringBuilder info = new StringBuilder();
        Scanner in = new Scanner(System.in);
        System.out.format(message);
        do {
            info.append( in.nextLine());
        } while (checkInfoSize(info,fileSize));
        return info.toString().substring(0,fileSize);
    }

    // Функция получения текущей даты (дата создания файла) в формате [dd, mm, yyyy] - массив int
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


    public int readFSSize(String message){
        int size = 0;
        Scanner in = new Scanner(System.in);
        boolean err = true;
        while (err) {
            System.out.println(message);
            size = in.nextInt();
            if (size > 0 && size < 32) {
                err = false;

        }
    }
        return size;
    }
    // Проверка корректности имени файла
    private boolean checkFileName(String fileName) {
        return fileName.length() <= DataInfo.fileNameLengthLimit && fileName.length() > 0;
    }

    // Проверка корректности размера файла
    private boolean checkFileSize(int fileSize) {
        return fileSize > 0 && fileSize < Integer.MAX_VALUE / 10;
    }

    //Проверка корректности ввода информации для записи
    private  boolean checkInfoSize (StringBuilder info, int fileSize) {
        return info.length() < fileSize;
    }
}
