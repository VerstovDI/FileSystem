package Structure.Main;

import Functions.CreateFile.CreateFileCommand;
import Functions.CreateFile.CreateFileParameters;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IMessageWriter;

public class tester {

    public static void main(String[] args) {
        // Отладка создания ФС
        FileSystem newFileSystem = new FileSystem(4,"hi","hi",2,3);
        // Отладка создания файла
        CreateFileCommand fileCommand = new CreateFileCommand();
        CreateFileParameters fileParameters = new CreateFileParameters();
        IMessageWriter imw = new IMessageWriter() {
            @Override
            public void write() {
                System.out.println("заглушка");
            }
        };
        fileCommand.Execute(newFileSystem, fileParameters, imw);
        //createFileCommand.Execute(newFileSystem, createFileParams.ParameterReader());
    }
}
