package Structure.Main;

import Functions.CreateFile.CreateFileCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.DownloadFS.DownloadFSCommand;
import Functions.ParameterReaders.ReadParameters;
import Functions.SaveFS.SaveFSCommand;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IMessageWriter;

import java.io.IOException;

public class testerDownloadSaveFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Отладка создания ФС
        FileSystem newFileSystem = new FileSystem(4, "MyFileSystem", "Igor", 2, 3);

        // Отладка создания файла (создание файла в пустой ФС)
        CreateFileCommand fileCommand = new CreateFileCommand();
        IMessageWriter imw = new IMessageWriter() {
            String message1 ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message1);
            }
        };
        ReadParameters fileCreationParameters = new ReadParameters();
        fileCommand.Execute(newFileSystem, fileCreationParameters, imw);

        // Отладка создания файла (создание файла в непустой ФС, вставка в непустой сегмент)
        for (int i = 0; i < 5; i++) {
            fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }


        //  удалениe файла
        IMessageWriter imw1 = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        DeleteFileCommand delFileCommand = new DeleteFileCommand();
        ReadParameters delFileParameters = new ReadParameters();
        for (int i = 0; i < 3; i++) {
            delFileCommand.Execute(newFileSystem, delFileParameters, imw1);
        }
        //Сохранение
        SaveFSCommand saveFSCommand= new SaveFSCommand();
        saveFSCommand.Execute(newFileSystem,fileCreationParameters,imw);


        //Загрузка
        FileSystem afterload = new FileSystem(4, "MyFileSystem", "Nasvai", 2, 3);
        DownloadFSCommand downloadFSCommand = new DownloadFSCommand();
        downloadFSCommand.Execute(afterload,fileCreationParameters,imw);

        delFileCommand.Execute(newFileSystem, delFileParameters, imw1);
    }
}
