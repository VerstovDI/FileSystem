package Structure.FileSystemStructure;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class FileSystem implements Serializable {
    public static PrintWriter out;
    public String systemName;           // Имя ФС
    public String ownerName;            // Владелец ФС
    public int tomId;                   // ID тома
    public int version;                 // Версия ФС
    public ArrayList<Segment> seg;      // Массив сегментов ФС. В системе может быть не более Segment.segmentsLimit (31) сегмента.
    public int cntOfSegments;           // Желаемое число сегментов в каталоге (оно обязано быть <= Segment.segmentsLimit).

    public FileSystem(int size, String systemName, String ownerName, int tomId, int version){
        this.systemName = systemName;
        this.ownerName = ownerName;
        this.tomId = tomId;
        this.version = version;
        this.cntOfSegments = size;
        ArrayList<Segment> buf = new ArrayList<Segment>();
        buf.ensureCapacity(size);
        seg = buf;
    }
    @Override
    public String  toString () {
        return "systemName:"+ systemName+
                "\nownerName:"+ownerName+
                "\ntomId:"+tomId+
                "\nversion:"+version+
                // "\nseg:"+seg.toString()+
                "\ncntOfSegments:"+cntOfSegments;
    }

    public void copy(FileSystem fsbuf){
        this.systemName = fsbuf.systemName;
        this.ownerName =fsbuf. ownerName;
        this.tomId = fsbuf.tomId;
        this.version = fsbuf.version;
        this.cntOfSegments = fsbuf.cntOfSegments;
        this.seg = fsbuf.seg;
    }
}
