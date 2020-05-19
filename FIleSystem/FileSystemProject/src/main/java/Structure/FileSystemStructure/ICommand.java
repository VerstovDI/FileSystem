package Structure.FileSystemStructure;

// Интерфейс для выполнения команд пользователя по работе с ФС (вызов соответствующих функций)
public interface ICommand {
    void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message);    // Метод "Выполнить команду"
    void ReadParameters(IParameterReader parameterReader);                              // Метод "Считать параметры для метода, который выполняется через Execute"
}
