package Structure.FileSystemStructure;

import java.io.PrintWriter;
import java.util.ArrayList;

public class FileSystem {
    public static PrintWriter out;
    public String systemName;           // Имя ФС
    public String ownerName;            // Владелец ФС
    public int tomId;                   // ID тома
    public int version;                 // Версия ФС
    public ArrayList<Segment> seg;      // Массив сегментов ФС. Maximum = 31 сегмент.
    public int cntOfSegments;           // Желаемое число сегментов в каталоге (< Maximum).

   public FileSystem(int size, String systemName, String ownerName, int tomId, int version){ //    0<=size<=31
        this.systemName = systemName;
        this.ownerName = ownerName;
        this.tomId = tomId;
        this.version = version;
        this.cntOfSegments = size;
        ArrayList<Segment> buf = new ArrayList<Segment>();
        buf.ensureCapacity(size);
        seg = buf;
    }

    FileSystem(){ }
}
