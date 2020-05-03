package Structure.FileSystemStructure;

public class DataInfo {
    public int[] date;          // Дата создания файла [число, месяц, год]
    public String nameFile;     // Имя файла String, 1 < length() <= 63 char.
    public int size;            // Размер файла, > 1, Байт
    //public int typeNote;      // Тип записи [временно убрано]
    //public int typeFile;      // Тип файла [временно убрано]

    public DataInfo(int[] date, String fileName, int fileSize) {//, int typeNote, int typeFile) {
        this.date = date;
        this.nameFile = fileName;
        this.size = fileSize;
        //this.typeNote = typeNote;
        //this.typeFile = typeFile;
    }
}
