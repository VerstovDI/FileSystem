package Functions.TitleInOrder;

import Structure.FileSystemStructure.*;

import java.util.ArrayList;
import java.util.Comparator;

public class TitleInOrderCommand implements ICommand {
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        StringBuilder title = new StringBuilder();
        ArrayList<String> titleInOrderArray = new ArrayList<>();
        for (Segment segment : fs.seg) {
            if (!segment.isEmptySegment()) {
                for (DataInfo dataInfo : segment.info) {
                    if (dataInfo.getTypeNote() != 0) {               //если файл существует
                        titleInOrderArray.add(dataInfo.getNameFile());
                    }
                }
            }
        }
        titleInOrderArray.sort(Comparator.naturalOrder());                 // сортировка
        for (String str: titleInOrderArray) {
            title.append(str + "\n");
        }
        message.write(title.toString());
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {

    }
}
