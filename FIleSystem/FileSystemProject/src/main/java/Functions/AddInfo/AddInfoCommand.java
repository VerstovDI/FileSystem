package Functions.AddInfo;

import Structure.FileSystemStructure.*;

public class AddInfoCommand implements ICommand {

    AddInfoParameters params;

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
        DataInfo fileInfo = this.params.fileInfo;
        FilePlace fp = findFile(fs, fileInfo);
        if (fp != null) {
            System.out.println("Можно добавлять инфу в файл");
        }
        else {
            System.out.println("Нет файла");
        }
    }

    @Override
    public void ReadParameters(IParameterReader p) {
        IParameterReader paramReader = p.ParameterReader();
        AddInfoParameters fileParams = (AddInfoParameters) paramReader;
        this.params = fileParams;
    }

    private FilePlace findFile(FileSystem fs,DataInfo fileInfo) {
        for (int i = 0; i < fs.seg.size() ; i++) {
            Segment segment = fs.seg.get(i);
            for (int j = 0; j < segment.info.size() ;j++) {
                DataInfo dataInfo = segment.info.get(j);
                if (dataInfo != null && dataInfo.nameFile.equals(params.fileInfo.nameFile)) {
                    return new FilePlace(i,j);
                }
            }
        }
        return null;
    }
}
