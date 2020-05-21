package Structure.Main;

import Functions.CreateFile.CreateFileCommand;
import Functions.Defragmentation.DefragmentationCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.FileSystem;

import java.io.IOException;

public class testerDownloadSaveFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Отладка создания ФС
        FileSystem newFileSystem = new FileSystem(4, "MyFileSystem", "Igor", 2, 3);

        // Отладка создания файла (создание файла в пустой ФС)
        CreateFileCommand fileCommand = new CreateFileCommand();
       // IMessageWriter imw = new IMessageWriter() {
      //      @Override
       //     public void write() {
      //          System.out.println("заглушка");
       //     }
      //  };
        ReadParameters fileCreationParameters = new ReadParameters();
       // fileCommand.Execute(newFileSystem, fileCreationParameters, imw);



        // Отладка создания файла (создание файла в непустой ФС, вставка в непустой сегмент)
        for (int i = 0; i < 11; i++) {
<<<<<<< HEAD
      //      fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }

        //Сохранение
        // SaveFSCommand saveFSCommand= new SaveFSCommand();
        // saveFSCommand.Execute(newFileSystem,fileCreationParameters,imw);
=======
            fileCommand.Execute(newFileSystem, fileCreationParameters, imw);
        }

        //Сохранение
       // SaveFSCommand saveFSCommand= new SaveFSCommand();
       // saveFSCommand.Execute(newFileSystem,fileCreationParameters,imw);
>>>>>>> 327bf19168ea33a7b6b79d3aac8c00cdea7b303c

        //  удалениe файла
        DeleteFileCommand delFileCommand = new DeleteFileCommand();
        ReadParameters delFileParameters = new ReadParameters();
        for (int i = 0; i < 4; i++) {
<<<<<<< HEAD
      //      delFileCommand.Execute(newFileSystem, delFileParameters, imw);
=======
            delFileCommand.Execute(newFileSystem, delFileParameters, imw);
>>>>>>> 327bf19168ea33a7b6b79d3aac8c00cdea7b303c
        }
      //  delFileCommand.Execute(newFileSystem, delFileParameters, imw);

        //Загрузка
<<<<<<< HEAD
        //  FileSystem afterload = new FileSystem(4, "MyFileSystem", "Nasvai", 2, 3);
        //  DownloadFSCommand downloadFSCommand = new DownloadFSCommand();
        //  downloadFSCommand.Execute(afterload,fileCreationParameters,imw);
        DefragmentationCommand defrag = new DefragmentationCommand();
        ReadParameters defragParameters = new ReadParameters();

      //  defrag.Execute(newFileSystem,defragParameters,imw);
=======
      //  FileSystem afterload = new FileSystem(4, "MyFileSystem", "Nasvai", 2, 3);
      //  DownloadFSCommand downloadFSCommand = new DownloadFSCommand();
      //  downloadFSCommand.Execute(afterload,fileCreationParameters,imw);
        DefragmentationCommand defrag = new DefragmentationCommand();
        ReadParameters defragParameters = new ReadParameters();

        defrag.Execute(newFileSystem,defragParameters,imw);
>>>>>>> 327bf19168ea33a7b6b79d3aac8c00cdea7b303c
        //delFileCommand.Execute(newFileSystem, delFileParameters, imw);
    }
}
