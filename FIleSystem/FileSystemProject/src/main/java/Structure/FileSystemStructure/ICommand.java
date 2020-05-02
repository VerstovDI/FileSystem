package Structure.FileSystemStructure;

public interface ICommand {
    void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message);
    void ReadParameters(IParameterReader parameterReader);
}
