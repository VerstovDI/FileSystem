package Structure.FileSystemStructure;

// Интерфейс для чтения вводимых параметров при операциях с ФС
public interface IParameterReader {
    IParameterReader ParameterReader ();    // Считыватель параметров

    String readFileName(String message);  // Считывает имя файла

    int readFileSize(String message);  // Считывает размер файла

    int readFSSize(String message);  // Считывает число сегментов ФС

    int readSizeForFiles(String message); // Считывает размер под файлы в ФС
}
