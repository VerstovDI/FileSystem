package Structure.Main;


import Functions.AddInfo.AddInfoCommand;
import Functions.AddInfo.AddInfoParameters;
import Functions.CreateFile.CreateFileCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IMessageWriter;


public class tester {

    public static void main(String[] args) {
        // Отладка создания ФС
        FileSystem newFileSystem = new FileSystem(4, "MyFileSystem", "Igor", 2, 3);

        // Отладка создания файла (создание файла в пустой ФС)
        CreateFileCommand fileCommand = new CreateFileCommand();
        IMessageWriter imw = new IMessageWriter() {
            @Override
            public void write() {
                System.out.println("заглушка");
            }
        };
        ReadParameters fileCreationParameters = new ReadParameters();
        fileCommand.Execute(newFileSystem, fileCreationParameters, imw);

        // Отладка создания файла (создание файла в непустой ФС, вставка в непустой сегмент)
        for (int i = 0; i < 4; i++) {
            fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }

        // Отладка удаления файла
        DeleteFileCommand delFileCommand = new DeleteFileCommand();
        ReadParameters delFileParameters = new ReadParameters();
        for (int i = 0; i < 4; i++) {
            delFileCommand.Execute(newFileSystem, delFileParameters, imw);
        }
        delFileCommand.Execute(newFileSystem, delFileParameters, imw);

        // Отладка создания файла (текущий сегмент полон, создаём новый, добавляем в него, прерасчитываем head)
        fileCommand.Execute(newFileSystem, fileCreationParameters, imw);

        //  Отладка создания файла (один из сегментов полон, второй полон частично)
        for (int i = 0; i < 2; i++) {
            fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }

        // Отладка добавление информации в файл
        AddInfoCommand addInfoCommand = new AddInfoCommand();
        AddInfoParameters addInfoParameters = new AddInfoParameters();
        // Выход по Ctrl+D в конце
        addInfoCommand.Execute(newFileSystem, addInfoParameters, imw);
    }

}
