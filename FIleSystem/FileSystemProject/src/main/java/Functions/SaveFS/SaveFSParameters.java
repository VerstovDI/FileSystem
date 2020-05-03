package Functions.SaveFS;

import Structure.FileSystemStructure.IParameterReader;

import java.util.Scanner;

public class SaveFSParameters implements IParameterReader {
    String filename;
    @Override
    public IParameterReader ParameterReader() {
            System.out.println("Введите имя файла");
            Scanner in = new Scanner(System.in);
            filename = in.nextLine();


        return new SaveFSParameters(filename);
    }


    SaveFSParameters (String filename){
        this.filename   = filename;
    }
}
