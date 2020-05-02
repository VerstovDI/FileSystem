package Structure.FileSystemStructure;

import java.util.ArrayList;

public class Segment {
    public static final int segmentsLimit = 31;
    public static int counter;         // Счетчик занятых сегментов
    public int head;                   // Ячейка в таблице, с которой начинаются файлы сегмента
    public ArrayList<DataInfo> info;   // Массив записей о файле
    public int capacity;               // Число сегментов, отведенных под каталог
    public int currentSegment;         // Номер текущего сегмента

    Segment(int head, int capacity, int currentSegment){
        this.head = head;
        this.capacity = capacity;
        this.currentSegment = currentSegment;
        ArrayList<DataInfo> buf = new ArrayList<DataInfo>();
    }
}
