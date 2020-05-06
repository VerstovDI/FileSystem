package Functions.DownloadFS;

import Structure.FileSystemStructure.IParameterReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DownloadFSParameters implements IParameterReader {
    String filename;
    File file;

    @Override
    public IParameterReader ParameterReader() {
        boolean err = true;
        while (err) {
            System.out.println("Введите имя файла");
            Scanner in = new Scanner(System.in);
            filename = in.nextLine();
            try {
                File file = new File(filename + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                    err = false;
                }
            } catch (IOException e) {
                System.out.println("ERROR" + e);
            }

        }

        return new DownloadFSParameters(file,filename);
    }

    DownloadFSParameters(File file, String filename) {
        this.file = file;
        this.filename=filename;
    }
}
