package Functions.DownloadFS;

import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

public class DownloadFSCommand implements ICommand {
    DownloadFSParameters par;
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {


    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
    IParameterReader p =parameterReader.ParameterReader();
        DownloadFSParameters par;
        par =(DownloadFSParameters) p;
        this.par=par;
    }
}
