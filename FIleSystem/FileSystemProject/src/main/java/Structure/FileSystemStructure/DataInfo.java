package Structure.FileSystemStructure;

public class DataInfo {
    public int[] date;
    public String nameFile;
    public int size;
    //public int typeNote; // временно убрано
    //public int typeFile;  // временно убрано

    public DataInfo(int[] date, String fileName, int fileSize) {//, int typeNote, int typeFile) {
        this.date = date;
        this.nameFile = fileName;
        this.size = fileSize;
        //this.typeNote = typeNote;
        //this.typeFile = typeFile;
    }
}
