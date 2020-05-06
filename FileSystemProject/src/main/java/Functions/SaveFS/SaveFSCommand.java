package Functions.SaveFS;

import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

public class SaveFSCommand implements ICommand {
    SaveFSParameters par;
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {

    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        IParameterReader p =parameterReader.ParameterReader();
       SaveFSParameters par;
        par =(SaveFSParameters) p;
        this.par=par;
    }
}
