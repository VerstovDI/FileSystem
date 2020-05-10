package Functions.FindPlace;

public class FindPlace {
    private int numberOfSegment;
    private int numberOfRecord;

    private FindPlace(int numberSegment, int numberRecord) {
        this.numberOfRecord = numberRecord;
        this.numberOfSegment = numberSegment;
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
}
