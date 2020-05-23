package Functions.Title;

import Structure.FileSystemStructure.*;

public class TitleCommand implements ICommand {
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        StringBuilder title = new StringBuilder();
        for (Segment segment : fs.seg) {
            if (!segment.isEmptySegment()) {
                for (DataInfo dataInfo : segment.info) {
                    if (dataInfo.getTypeNote() != 0) {               //если файл существует
                        title.append(dataInfo.getNameFile() + "\n");
                    }
                }
            }
        }
        message.write(title.toString());
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        //нет параметров
    }
}
