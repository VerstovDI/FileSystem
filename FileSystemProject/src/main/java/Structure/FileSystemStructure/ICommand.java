package Structure.FileSystemStructure;

// Интерфейс для выполнения команд пользователя по работе с ФС
public interface ICommand {
    void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message);    // Метод "Выполнить команду"
    void ReadParameters(IParameterReader parameterReader);                              // Считать параметры для метода
}
