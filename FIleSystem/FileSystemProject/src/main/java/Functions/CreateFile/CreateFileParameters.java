package Functions.CreateFile;

import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.IParameterReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateFileParameters implements IParameterReader {
    /*// Для задания уникальных имён файлов: (newFile[id], т.е newFile1, newFile2 и т.д.)
    private static int fileID = 0;
    private static final String defaultFileName = "newFile";
    // Константа для задания размера файла по умолчанию - 512 байт
    private static final int defaultFileSize = 512;
    String fileName;    // Имя создаваемого файла
    int fileSize;       // Размер создаваемого файла*/
    DataInfo fileInfo;    // Информация о файле

    /*CreateFileParameters() {
        this.fileName = defaultFileName.concat(Integer.toString(fileID++));
        this.fileSize = defaultFileSize;
    }

    CreateFileParameters(String fileName) {
        this.fileName = fileName;
        this.fileSize = defaultFileSize;
    }

    CreateFileParameters(int fileSize) {
        this.fileName = defaultFileName.concat(Integer.toString(fileID++));
        this.fileSize = fileSize;
    }

    CreateFileParameters(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }*/

    /*public CreateFileParameters(DataInfo fileInfo) {
        this.fileInfo = fileInfo;
    }*/

    @Override
    public IParameterReader ParameterReader() {
        Scanner in = new Scanner(System.in);
        String fileName;    // Имя создаваемого файла
        int fileSize;       // Размер создаваемого файла
        boolean fl = false;
        do {
            if (fl) {
                System.out.println("Incorrect input. Repeat, please: ");
            }
            System.out.format("Enter file's name: ");
            fileName = in.nextLine();
            System.out.format("Enter file's size (in bytes): ");
            fileSize = in.nextInt();
        } while (!(fl = inputCheck(fileName, fileSize)));
        this.fileInfo = new DataInfo(getCurrentDate(), fileName, fileSize);
        return this;
    }

    // Функция получения текущей даты (дата создания файла) в формате [dd, mm, yyyy] - массив int
    private int[] getCurrentDate() {
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

    // Функция проверки корректности введённых данных
    private boolean inputCheck(String fileName, int fileSize) {
        return fileName.length() <= DataInfo.fileNameLengthLimit
                && fileName.length() > 0
                && fileSize > 0;
    }
}
