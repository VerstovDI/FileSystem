package Functions.CreateFile;

import Structure.FileSystemStructure.*;

import java.util.Date;

public class CreateFileCommand implements ICommand {
    CreateFileParameters params;

   /* public CreateFileCommand(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }*/


    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        DataInfo fileInfo = this.params.fileInfo;
        createFile(fs, fileInfo);
    }


    @Override
    public void ReadParameters(IParameterReader p) {
        IParameterReader paramReader = p.ParameterReader();
        CreateFileParameters fileParams = (CreateFileParameters) paramReader;
        this.params = fileParams;
    }

    private void createFile(FileSystem fs, DataInfo fileInfo) {
        int[] filePositions = findPlace(fs, fileInfo);  // Ищем сегмент и место в нём для вставки инф. о файле (DataInfo)
        fs.seg.get(filePositions[0]).info.add(filePositions[1], fileInfo);    // Вставляем DataInfo о новом созданном файле в найденную позицию
        System.out.println("Успех!");  // для отладки
    }


    /* Возвращает свободную позицию для вставки в файла в формате [ № сегмента, № первой свободной записи в сегменте]
    Пока договорились вставлять ТОЛЬКО В КОНЕЦ до победного, пока считаем, что дальше идёт только пустое место
    Считаем, что файлы вставляем подряд. Находим последний занятый сегмент, вставляем в последнюю свободную dataInfo сегмента, если она есть.
    Иначе - пишем в новый сегмент, считая его местоположение.*/
    private int[] findPlace(FileSystem fs, DataInfo newFileInfo) {
        int[] newFilePosition = new int[2];

        if (fs.seg.isEmpty()) {    // Если ФС пустая - добавляем первый сегмент, возвращаем искомые позиции
            fs.seg.add(new Segment(Segment.segmentSize * Segment.segmentsLimit + 1));
            Segment.currentSegment = 0;
            Segment.counter++;
            newFilePosition = new int[] { Segment.currentSegment, 0 };
            return newFilePosition;
        } else {
            Segment currSegment = fs.seg.get(Segment.currentSegment);
            if (currSegment.info.size() < 5) {      // Текущий сегмент не заполнен до конца?
                newFilePosition = new int[]{Segment.currentSegment, currSegment.info.size()};
            } else {
                if (Segment.currentSegment < fs.cntOfSegments) {
                    fs.seg.add(new Segment(currSegment.head + segmentFilesSize(currSegment)));
                    Segment.currentSegment++;
                    Segment.counter++;
                    newFilePosition = new int[]{Segment.currentSegment, 0};
                    return newFilePosition;
                } else {
                    System.out.println("Все сегменты заняты. Мои соболезнования.");
                }
            }
        }
        return newFilePosition;
    }

    // Функция подсчёта размера всех файлов сегмента
    private int segmentFilesSize(Segment segm) {
        int sz = 0;
        for (DataInfo di : segm.info) {
            sz += di.size;
        }
        return sz;
    }

    // Функция добавления сегмента
    private void addSegment(FileSystem fs) {
    }
}
