package Functions.ParameterReaders;

import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.IParameterReader;

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
        } while (!checkFileName(fileSize));
        return fileSize;
    }

    // Проверка корректности имени файла
    private boolean checkFileName(String fileName) {
        return fileName.length() <= DataInfo.fileNameLengthLimit && fileName.length() > 0;
    }

    // Проверка корректности размера файла
    private boolean checkFileName(int fileSize) {
        return fileSize > 0 && fileSize < Integer.MAX_VALUE / 10;
    }
}
