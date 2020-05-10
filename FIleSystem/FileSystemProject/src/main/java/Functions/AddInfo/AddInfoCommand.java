
package Functions.AddInfo;

import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.*;

public class AddInfoCommand implements ICommand {

    private DataInfo dataInfoOfAddInfo;

    class FilePlace {
       private int numberSegment;
       private int numberRecord;

       FilePlace (int numberSegment, int numberRecord) {
           this.numberRecord = numberRecord;
           this.numberSegment = numberSegment;
       }
    }
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        FilePlace filePlace = findFile(fs, dataInfoOfAddInfo);
        if (filePlace != null) {
            System.out.println("Добавляем инфу ");
        } else {
            System.out.println("Такого файла не существует!");
        }
        message.write();
    }

    @Override
    public void ReadParameters(IParameterReader parameter) {
        this.dataInfoOfAddInfo = new DataInfo();
        this.dataInfoOfAddInfo.setNameFile(((ReadParameters) parameter).readFileName("Введите имя файла для добавления информации: "));
        this.dataInfoOfAddInfo.setSize(((ReadParameters) parameter).readFileSize("Введите размер записи:  "));
        ((ReadParameters) parameter).readInfo("Введите информацию для добавления:  ",this.dataInfoOfAddInfo.getSize());
    }

    private FilePlace findFile(FileSystem fs,DataInfo fileName) {
        for (int i = 0; i < fs.seg.size(); i++) {
            Segment segment = fs.seg.get(i);
            for (int j = 0; j < segment.info.size(); j++) {
                DataInfo dataInfo = segment.info.get(j);
                if (dataInfo != null && dataInfo.getNameFile().equals(fileName.getNameFile())) {
                    return new FilePlace(i, j);
                }
            }
        }
        return null;
    }
}
