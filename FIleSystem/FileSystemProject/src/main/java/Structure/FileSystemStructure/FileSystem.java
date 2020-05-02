package Structure.FileSystemStructure;

import java.io.PrintWriter;
import java.util.ArrayList;

public class FileSystem {
    public static PrintWriter out;
    public String systemName;
    public String ownerName;
    public int tomId;
    public int version;
    public ArrayList<Segment> seg;

   public FileSystem(int size, String systemName, String ownerName, int tomId, int version){ //    0<=size<=31
        this.systemName = systemName;
        this.ownerName = ownerName;
        this.tomId = tomId;
        this.version = version;
        ArrayList<Segment> buf = new ArrayList<Segment>();
        buf.ensureCapacity(size);
        seg = buf;
    }

    FileSystem(){ }



}

