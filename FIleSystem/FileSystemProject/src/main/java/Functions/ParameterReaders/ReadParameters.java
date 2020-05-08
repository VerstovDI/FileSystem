package Functions.ParameterReaders;

import Structure.FileSystemStructure.IParameterReader;

import java.util.Scanner;

public class ReadParameters implements IParameterReader {

    @Override
    public IParameterReader ParameterReader() {
        return null;
    }

    //@Override
    //Считываем имя файлы.
    // message текст запроса,который выведется на консоль
    //Выполняется проверка имени на длину
    public String readFilename(String message) {
        Scanner in = new Scanner(System.in);
        String fileName;
        do {
            System.out.format(message);
            fileName = in.nextLine();
        } while (!checkFileName(fileName));

        return fileName;
    }

    // Проверка корректности имени
    private boolean checkFileName(String fileName) {
        return fileName.length() <= 63 && fileName.length() > 0;
    }
}
