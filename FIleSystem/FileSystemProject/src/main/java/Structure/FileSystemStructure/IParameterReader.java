package Structure.FileSystemStructure;

public interface IParameterReader {
    IParameterReader ParameterReader ();    // Считыватель параметров

    // Добавим public String readFilename(String message) ;
    public String readFileName(String message);

    public int readFileSize(String message);

    public  String readInfo (String message,int fileSize);

    public int readFSSize(String message);


}
