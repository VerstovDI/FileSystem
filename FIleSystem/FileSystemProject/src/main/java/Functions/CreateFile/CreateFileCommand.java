package Functions.CreateFile;

import Structure.FileSystemStructure.*;

import java.util.Date;

public class CreateFileCommand implements ICommand {
    CreateFileParameters params;

   /* public CreateFileCommand(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }*/


    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        DataInfo fileInfo = this.params.fileInfo;
        createFile(fs, fileInfo);
    }


    @Override
    public void ReadParameters(IParameterReader p) {
        IParameterReader paramReader = p.ParameterReader();
        CreateFileParameters fileParams = (CreateFileParameters) paramReader;
        this.params = fileParams;
    }

    private void createFile(FileSystem fs, DataInfo fileInfo) {
        findPlace(fileInfo.size); // найти место
        // ВСТАВИТЬ инфу
    }


    // Если свободное место найдено, оно возвращается. Если нет, возвращается -1;
    private int findPlace(int requiredSize) {
        if (Segment.counter < Segment.segmentsLimit) {
            // ИЩЕМ МЕСТО ДЛЯ ФАЙЛА
        }
        return -1;
    }

}
