package Functions.DeleteFile;

import Functions.CreateFile.CreateFileParameters;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

public class DeleteFileCommand implements ICommand {
    DeleteFileParameters params;
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        String fileName =this.params.fileName;
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        IParameterReader paramReader = parameterReader.ParameterReader();
        DeleteFileParameters fileParams = (DeleteFileParameters) paramReader;
        this.params = fileParams;
    }
}
