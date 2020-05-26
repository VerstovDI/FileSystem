
package Functions.AddInfo;

import Functions.FindPlace.FindPlace;
import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.*;

import static Functions.FindPlace.FindPlace.findFile;

public class AddInfoCommand implements ICommand {

    private DataInfo dataInfoOfAddInfo;


    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        FindPlace filePlace = findFile(fs, dataInfoOfAddInfo.getNameFile());
        if (filePlace != null) {
            Segment segment = fs.seg.get(filePlace.getNumberOfSegment());
            int fileSize = segment.info.get(filePlace.getNumberOfRecord()).getSize();
            if (this.dataInfoOfAddInfo.getSize() > fileSize) {
                message.write("Размер записи превышает размер файла");
            }
            else {
                message.write("Информация в файл добавлена");
            }
        } else {
            message.write("Такого файла не существует!");
        }
    }

    @Override
    public void ReadParameters(IParameterReader parameter) {
        this.dataInfoOfAddInfo = new DataInfo();
        this.dataInfoOfAddInfo.setNameFile(((ReadParameters) parameter).readFileName("Введите имя файла для добавления информации: "));
        this.dataInfoOfAddInfo.setSize(((ReadParameters) parameter).readFileSize("Введите размер записи:  "));
    }

}

