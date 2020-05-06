package Functions.CreateFile;

import Functions.CreateFS.CreateFSParameters;
import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.IParameterReader;

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
        String fileName;
        int fileSize;
        do {
            System.out.format("Введите имя создаваемого файла: ");
            fileName = in.nextLine();
            System.out.format("Введите размер создаваемого файла: ");
            fileSize = in.nextInt();
        } while (!inputCheck(fileName, fileSize));
        DataInfo fileInfo = createDataInfo(fileName, fileSize);
        this.fileInfo = fileInfo;
        return this;
    }

    private DataInfo createDataInfo(String fileName, int fileSize) {
        int[] creationDate = new int[3];
        // ЗАГЛУШКА
        creationDate[0] = 2;
        creationDate[1] = 5;
        creationDate[2] = 2020;
        //
        return new DataInfo(creationDate, fileName, fileSize);
    }

    private boolean inputCheck(String fileName, int fileSize) {
        return fileName.length() <= 63 && fileName.length() > 0 && fileSize > 0;
    }
}
