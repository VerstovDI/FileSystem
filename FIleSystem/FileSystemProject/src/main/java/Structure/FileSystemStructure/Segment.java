package Structure.FileSystemStructure;

import java.io.Serializable;
import java.util.ArrayList;

public class Segment implements Serializable {
    public static final int segmentSize = 1024;         // Размер сегмента (в байтах) по ТЗ
    public static final int segmentsLimit = 31;         // Ограничение на максимальное число сегментов в ФС
    public static final int dataInfoCountLimit = 5;     // Максимальное число записей о файла в сегменте
    public static int counter;                          // Счетчик занятых сегментов
    /* Сделал поле counter static, т.к. какой смысл хранить в каждом сегменте его?
    А к статику можно обращаться извне, не создавая экзмепляра. Удобно для многих операций
    */
    public static int currentSegment;                   // Номер текущего сегмента
    // Сделал поле currentSegment static, потому что всегда нужно знать, какой сейчас сегмент, и динамически менять это
    public int head;                                    // Ячейка в таблице, с которой начинаются файлы сегмента
    public ArrayList<DataInfo> info;                    // Массив записей о файле. В каждом сегменте таких записей - dataInfoCountLimit
    public static int capacity;                         // Число сегментов, отведенных под каталог

    public Segment(int head) {
        this.head = head;
        this.info = new ArrayList<DataInfo>(5);
    }

    public boolean isEmptySegment() {
        return this.info.isEmpty();
    }
    @Override
    public String  toString () {
        return "segmentSize:"+ segmentSize+
                "\nsegmentsLimit:"+segmentsLimit+
                "\ndataInfoCountLimit:"+dataInfoCountLimit+
                "\ncounter:"+counter+
                "\ncurrentSegment:"+currentSegment+
                "\nhead:"+head+
                "\ninfo:"+info.toString()+
                "\ncapacity:"+capacity;
    }
}
