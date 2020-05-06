package Structure.Main;

import Functions.AddInfo.AddInfoCommand;
import Functions.AddInfo.AddInfoParameters;
import Functions.CreateFile.CreateFileCommand;
import Functions.CreateFile.CreateFileParameters;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.DeleteFile.DeleteFileParameters;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IMessageWriter;

public class tester {

    public static void main(String[] args) {
        // Отладка создания ФС
        FileSystem newFileSystem = new FileSystem(4, "hi", "hi", 2, 3);
        // Отладка создания файла (создание файла в пустой ФС)
        CreateFileCommand fileCommand = new CreateFileCommand();
        CreateFileParameters fileParameters = new CreateFileParameters();
        IMessageWriter imw = new IMessageWriter() {
            @Override
            public void write() {
                System.out.println("заглушка");
            }
        };
        fileCommand.Execute(newFileSystem, fileParameters, imw);
        // Отладка создания файла (создание файла в непустой ФС, вставка в непустой сегмент)
        for (int i = 0; i < 4; i++) {
            fileCommand.Execute(newFileSystem, fileParameters, imw);
        }


        // отладка удаления файла
        DeleteFileCommand delFileCommand = new DeleteFileCommand();
        DeleteFileParameters delFileParameters = new DeleteFileParameters();
        for (int i = 0; i < 4; i++) {
            delFileCommand.Execute(newFileSystem, delFileParameters, imw);
        }

        // Отладка создания файла (текущий сегмент полон, создаём новый, добавляем в него, прерасчитываем head)

        fileCommand.Execute(newFileSystem, fileParameters, imw);
        // Отладка создания файла (один из сегментов полон, второй полон частично)
        for (int i = 0; i < 2; i++) {
            fileCommand.Execute(newFileSystem, fileParameters, imw);
        }
        // Отладка добавление информации в файл
        AddInfoCommand addInfoCommand = new AddInfoCommand();
        AddInfoParameters addInfoParameters = new AddInfoParameters();
        addInfoCommand.Execute(newFileSystem, addInfoParameters, imw);
    }
}