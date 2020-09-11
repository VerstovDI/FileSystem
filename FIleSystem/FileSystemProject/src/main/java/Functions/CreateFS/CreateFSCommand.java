package Functions.CreateFS;

import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

public class CreateFSCommand implements ICommand {
    int size;
    public String systemName;           // Имя ФС
    public String ownerName;            // Владелец ФС
    public int tomId;                   // ID тома
    public int version;                 // Версия ФС
    public int fileSystemSize;          // Максимально допустимый размер ФС (под файлы, сегменты не учитываются)

    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        ReadParameters(parameter);
        FileSystem buf = new FileSystem(size, systemName, ownerName, tomId, version, fileSystemSize);
        fs.copy(buf);
        message.write("Файловая система была успешно создана!");
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        this.size = parameterReader.readFSSize("Введите число сегментов ФС (1-31): ");
        this.fileSystemSize = parameterReader.readSizeForFiles("Введите максимальный размер дискового пространства ФС: ");
        this.systemName = parameterReader.readFileName("Введите название файловой системы: ");
        this.ownerName = parameterReader.readFileName("Введите имя владельца файловой системы: ");
        this.tomId = parameterReader.readNumbers("Введите номер тома: ");
        this.version = parameterReader.readNumbers("Введите версию ФС: ");
    }
}