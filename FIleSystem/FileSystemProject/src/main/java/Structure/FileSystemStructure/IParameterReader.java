package Structure.FileSystemStructure;

public interface IParameterReader {
    IParameterReader ParameterReader ();    // Считыватель параметров

    public String readFileName(String message);  // Считывает имя файла

    public int readFileSize(String message);  // Считывает размер файла

    public  String readInfo (String message,int fileSize);

    public int readFSSize(String message);
}
