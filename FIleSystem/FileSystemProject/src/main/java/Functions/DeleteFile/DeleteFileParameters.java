package Functions.DeleteFile;

import Structure.FileSystemStructure.IParameterReader;

import java.util.Scanner;

public class DeleteFileParameters implements IParameterReader {
    public String fileName;
    @Override
    public IParameterReader ParameterReader() {
        Scanner in = new Scanner(System.in);
        String fileName;
        do {
            System.out.format("Введите имя удаляемого файла: ");
            fileName = in.nextLine();
        } while (!inputCheck(fileName));

        this.fileName = fileName;
        return this;

    }

    private boolean inputCheck(String fileName) {
        return fileName.length() <= 63 && fileName.length() > 0;
    }
}
