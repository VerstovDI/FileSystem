package Structure.Main;

import Functions.AddInfo.AddInfoCommand;
import Functions.CreateFile.CreateFileCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.ParameterReaders.ReadParameters;
import Functions.Title.TitleCommand;
import Functions.TitleInOrder.TitleInOrderCommand;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IMessageWriter;


public class tester {

    public static void main(String[] args) {
        // Отладка создания ФС
        FileSystem newFileSystem = new FileSystem(4, "MyFileSystem", "Igor", 2, 3,100);

        // Отладка создания файла (создание файла в пустой ФС)
        CreateFileCommand fileCommand = new CreateFileCommand();
        IMessageWriter imw = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        ReadParameters fileCreationParameters = new ReadParameters();
        fileCommand.Execute(newFileSystem, fileCreationParameters, imw);

        // Отладка создания файла (создание файла в непустой ФС, вставка в непустой сегмент)
        for (int i = 0; i < 4; i++) {
            fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }

        // Отладка удаления файла
        IMessageWriter imw1 = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        DeleteFileCommand delFileCommand = new DeleteFileCommand();
        ReadParameters delFileParameters = new ReadParameters();
        for (int i = 0; i < 2; i++) {
            delFileCommand.Execute(newFileSystem, delFileParameters, imw1);
        }
        delFileCommand.Execute(newFileSystem, delFileParameters, imw1);

        // Отладка создания файла (текущий сегмент полон, создаём новый, добавляем в него, прерасчитываем head)
        fileCommand.Execute(newFileSystem, fileCreationParameters, imw);

        //  Отладка создания файла (один из сегментов полон, второй полон частично)
        for (int i = 0; i < 2; i++) {
            fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }

        // Отладка вывода оглавления как оно есть
        IMessageWriter imw2 = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };

        TitleCommand titleCommand = new TitleCommand();
        ReadParameters titleParameters = new ReadParameters();
        titleCommand.Execute(newFileSystem,titleParameters,imw2 );

        // Отладка добавления информации в файл
        IMessageWriter imw3 = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };

        AddInfoCommand addInfoCommand = new AddInfoCommand();
        ReadParameters addInfoParameters = new ReadParameters();
        addInfoCommand.Execute(newFileSystem, addInfoParameters, imw3);

        // Отладка вывода оглавления в алфавитном порядке
        IMessageWriter imw4 = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };

        TitleInOrderCommand titleOrderCommand = new TitleInOrderCommand();
        ReadParameters titleOrderParameters = new ReadParameters();
        titleOrderCommand.Execute(newFileSystem,titleOrderParameters,imw4 );
    }

}