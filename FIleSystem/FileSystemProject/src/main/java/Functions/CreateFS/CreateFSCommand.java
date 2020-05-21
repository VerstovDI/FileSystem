package Functions.CreateFS;

import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.*;

public class CreateFSCommand implements ICommand {

    int size;
    public String systemName;           // Имя ФС
    public String ownerName;            // Владелец ФС
    public int tomId;                   // ID тома
    public int version;                 // Версия ФС

    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        ReadParameters(parameter);
        FileSystem buf = new FileSystem(size, systemName, ownerName, tomId, version);
        fs = buf;
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        this.size=((ReadParameters)parameterReader).readFSSize("Введите размер файловой системы (1-31)");
        this.systemName=((ReadParameters)parameterReader).readFileName("Введите название файловой системы");
        this.ownerName=((ReadParameters)parameterReader).readFileName("Введите имя владельца файловой системы");
        this.tomId=((ReadParameters)parameterReader).readFileSize("Введите номер тома");
        this.version=((ReadParameters)parameterReader).readFileSize("Введите версию");
    }
}