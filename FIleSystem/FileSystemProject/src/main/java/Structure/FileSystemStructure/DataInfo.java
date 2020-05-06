package Structure.FileSystemStructure;

public class DataInfo {
    public int[] date;          // Дата создания файла [число, месяц, год]
    public String nameFile;     // Имя файла String, 1 < length() <= 63 char.
    public int size;            // Размер файла, > 1, Байт
    public int typeNote;       // Тип записи [временно стоит 1 - если существует,0- файл удален]
    //public int typeFile;      // Тип файла [временно убрано]

    public DataInfo(int[] date, String fileName, int fileSize) {//, int typeNote, int typeFile) {
        this.date = date;
        this.nameFile = fileName;
        this.size = fileSize;
        this.typeNote = 1; // ИСПРАВИТЬ установелно по ДЕФОЛТУ, потом исправить.[ 0 - файл  удален ]
        //this.typeFile = typeFile;
    }
}
