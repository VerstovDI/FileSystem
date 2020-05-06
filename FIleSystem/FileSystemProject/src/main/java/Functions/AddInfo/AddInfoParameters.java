package Functions.AddInfo;

import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.IParameterReader;

import java.util.Scanner;

public class AddInfoParameters implements IParameterReader {
    DataInfo fileInfo;
    @Override
    public IParameterReader ParameterReader() {
        Scanner in = new Scanner(System.in);
        String fileName;
        String text;
        int bufferSize;
        do {
            System.out.format("Введите имя файла для записи: ");
            fileName = in.nextLine();
            System.out.format("Введите размер записи: ");
            bufferSize = in.nextInt();
            System.out.format("Введите информацию: ");
            text = readBuffer(in);
        } while (!inputCheck(fileName, bufferSize,text));
        in.close();
        fileInfo = new DataInfo(new int [0],fileName,bufferSize); //нужно для DataInfo добавить еще один подходящий конструктор
        return this;
    }

    private boolean inputCheck(String fileName, int fileSize,String text) {
        return fileName.length() <= DataInfo.fileNameLengthLimit
                && fileName.length() > 0
                && fileSize > 0
                && text.length() < fileSize;
    }

    private String readBuffer(Scanner in) {
        StringBuilder string = new StringBuilder();
        while (in.hasNextLine()) { // конец ввода ctrl + D
            String addString = in.nextLine();
            string.append(addString);
        }
        return string.toString();
    }
}
