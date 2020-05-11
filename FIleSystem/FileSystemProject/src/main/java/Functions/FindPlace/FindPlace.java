package Functions.FindPlace;

import Structure.FileSystemStructure.DataInfo;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.Segment;

public class FindPlace {
    private int numberOfSegment;
    private int numberOfRecord;

    private FindPlace(int numberSegment, int numberRecord) {
        this.numberOfRecord = numberRecord;
        this.numberOfSegment = numberSegment;
    }

    public int getNumberOfSegment() {
        return this.numberOfSegment;
    }

    public int getNumberOfRecord() {
        return this.numberOfRecord;
    }

    //функция поиска файла по имени. возвращает место файла, или null. используется в DeleteFile
    public static FindPlace findFile(FileSystem fs, String fileName) {
        for (int i = 0; i < fs.seg.size(); i++) {
            Segment segment = fs.seg.get(i);
            for (int j = 0; j < segment.info.size(); j++) {
                DataInfo dataInfo = segment.info.get(j);
                if (dataInfo != null && dataInfo.getNameFile().equals(fileName) && dataInfo.getTypeNote() != 0) {
                    return new FindPlace(i, j);
                }
            }
        }
        return null;
    }

    // Функция поиска места. Сначала её нужно доработать и разобраться с инициализацией пустых сегментов массива.
    /*private static int[] findPlace(FileSystem fs, DataInfo newFileInfo) {
        int[] newFilePosition = new int[2];

        if (fs.seg.isEmpty()) {    // Если ФС пустая - добавляем первый сегмент, возвращаем искомые позиции
            addSegment(fs, Segment.segmentSize * Segment.segmentsLimit + 1);
            newFilePosition = new int[] { Segment.currentSegment, 0 };
            return newFilePosition;
        } else {
            Segment currSegment = fs.seg.get(Segment.currentSegment);
            if (currSegment.info.size() < 5) {      // Текущий сегмент не заполнен до конца?
                newFilePosition = new int[]{Segment.currentSegment, currSegment.info.size()};
            } else {
                if (Segment.currentSegment < fs.cntOfSegments) {
                    addSegment(fs,currSegment.head + segmentFilesSize(currSegment));
                    newFilePosition = new int[]{Segment.currentSegment, 0};
                    return newFilePosition;
                } else {
                    System.out.println("Все сегменты заняты. Мои соболезнования.");
                }
            }
        }
        return newFilePosition;
    }*/

    // Функция из AddInfoCommand. Надо совместить то, что выше с этим.
    /*private AddInfoCommand.FilePlace findFile(FileSystem fs, DataInfo fileInfo) {
        for (int i = 0; i < fs.seg.size() ; i++) {
            Segment segment = fs.seg.get(i);
            for (int j = 0; j < segment.info.size() ;j++) {
                DataInfo dataInfo = segment.info.get(j);
                if (dataInfo != null && dataInfo.getNameFile().equals(params.fileInfo.getNameFile())) {
                    return new AddInfoCommand.FilePlace(i,j);
                }
            }
        }
        return null;
    }*/
}
