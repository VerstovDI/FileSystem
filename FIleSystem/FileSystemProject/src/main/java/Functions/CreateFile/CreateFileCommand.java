package Functions.CreateFile;

import Functions.Fragmentation.FragmentationCommand;
import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.*;
import org.javatuples.Triplet;

import java.util.ArrayList;


public class CreateFileCommand implements ICommand {
    DataInfo dataInfoOfCreatingFile;  // Ссылка на новый DataInfo создаваемого файла

    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        if (createFile(fs, this.dataInfoOfCreatingFile)) {
            message.write("Файл успешно создан!");
        } else {
            message.write("Файл не удалось создать!");
        }
    }

    @Override
    public void ReadParameters(IParameterReader p) {
        this.dataInfoOfCreatingFile = new DataInfo();
        this.dataInfoOfCreatingFile.setDate(ReadParameters.getCurrentDate());
        this.dataInfoOfCreatingFile.setNameFile(p.readFileName("Введите имя создаваемого файла: "));
        this.dataInfoOfCreatingFile.setSize(p.readFileSize("Введите размер создаваемого файла: "));
        this.dataInfoOfCreatingFile.setTypeNote(1);
    }

    // Функция создания файла. Сначала пытаемся создать файл в пустых промежутках, затем - в конце, иначе - false.
    private boolean createFile(FileSystem fs, DataInfo newFileInfo) {
        if (FragmentationCommand.emptySpace(fs) < newFileInfo.getSize()) {
            return false;
        } else {
            if (fs.seg.isEmpty()) {    // Если ФС пустая - добавляем первый сегмент, возвращаем искомые позиции
                addSegment(fs, Segment.segmentSize * Segment.segmentsLimit + 1);
                fs.seg.get(0).info.add(0, newFileInfo);
                return true;
            } else {
                if (createFileTillTheEnd(fs, newFileInfo)) {
                    return true;
                } else {
                    return createFileInTheEnd(fs, newFileInfo);
                }
            }
        }
    }

    // Функция создания файла в области ДО последнего непустого DataInfo включительно
    private boolean createFileTillTheEnd(FileSystem fs, DataInfo newFileInfo) {
        int requiredSize = newFileInfo.getSize();  // Требуемый для создания размер
        int continuosFreeSpace = 0;  // Хранит пустое место подряд идущих DataInfo в рамках одного и того же сегмента
        ArrayList<Triplet<Integer, Integer, Integer>> r = systemFreeSpaceInfo(fs); // Массив кортежей с инфой о пустом месте
        if (r.size() == 0) {
            return false;
        }
        int firstEmptyDataInfo = 0;  // Хранит индекс первого пустого DataInfo в цепочке подряд идущих пустых DataInfo в пределах одного сегмента
        int previousSegmentNumber = -1;  // Предыдущий сегмент
        int numberOfEmptyDatas = 0;  // Число подряд идущих пустых DataInfo в текущем сегменте
        int tmp;
        for (Triplet<Integer, Integer, Integer> triplet : r) {
            if (triplet.getValue2() == requiredSize) {
                fs.seg.get(triplet.getValue0()).info.add(triplet.getValue1(), newFileInfo);
                return true;
            } else {
                if (triplet.getValue0() == previousSegmentNumber) {
                    continuosFreeSpace += triplet.getValue2();
                    numberOfEmptyDatas++;
                    if (requiredSize < continuosFreeSpace) {
                        int diff = continuosFreeSpace - requiredSize;
                        tmp = fs.seg.get(triplet.getValue0()).info.get(firstEmptyDataInfo).getSize();
                        if (requiredSize < tmp) {
                            diff = tmp - requiredSize;
                            int nextDataInfoSize = fs.seg.get(triplet.getValue0()).info.get(firstEmptyDataInfo + 1).getSize();
                            fs.seg.get(triplet.getValue0()).info.get(firstEmptyDataInfo + 1).setSize(nextDataInfoSize + diff);
                        } else {
                            fs.seg.get(triplet.getValue0()).info.get(firstEmptyDataInfo + 1).setSize(diff);
                            for (int i = firstEmptyDataInfo + 2; i < numberOfEmptyDatas; i++) {
                                fs.seg.get(triplet.getValue0()).info.get(firstEmptyDataInfo + 1).setSize(0);
                            }
                        }
                        fs.seg.get(triplet.getValue0()).info.set(firstEmptyDataInfo, newFileInfo);
                        return true;
                    }
                } else {
                    numberOfEmptyDatas = 1;
                    firstEmptyDataInfo = triplet.getValue1();
                    continuosFreeSpace = triplet.getValue2();
                }
            }
            previousSegmentNumber = triplet.getValue0();
        }
        return false;
    }

    // Функция создания файла в конце, после последнего инициализированного DataInfo
    private boolean createFileInTheEnd(FileSystem fs, DataInfo newDataInfo) {
        int[] newFilePosition = new int[2];
        newFilePosition[0] = -1;

        Segment currSegment = fs.seg.get(Segment.currentSegment);
        if (currSegment.info.size() < 5) {      // Текущий сегмент не заполнен до конца?
            newFilePosition = new int[]{Segment.currentSegment, currSegment.info.size()};
        } else {
            if (Segment.currentSegment < fs.cntOfSegments) {
                addSegment(fs,currSegment.head + segmentFilesSize(currSegment));
                newFilePosition = new int[]{Segment.currentSegment, 0};
            } else {
                System.out.println("Все сегменты заняты.");
            }
        }
        if (newFilePosition[0] != -1) {
            fs.seg.get(newFilePosition[0]).info.add(newFilePosition[1], newDataInfo);
            return true;
        } else {
            return false;
        }
    }

    // Функция нахождения пустого места во всей инициализированной области системы
    private ArrayList<Triplet<Integer, Integer, Integer>> systemFreeSpaceInfo(FileSystem fs) {
        ArrayList<Triplet<Integer, Integer, Integer>> freeSpaceInfoArray = new ArrayList<>();
        for (int i = 0; i< fs.seg.size(); i++) {
            Segment segment = fs.seg.get(i);
            for (int j = 0; j < segment.info.size(); j++) {
                DataInfo dataInfo = segment.info.get(j);
                if (dataInfo.getTypeNote() == 0) {
                    freeSpaceInfoArray.add(new Triplet<>(i, j, dataInfo.getSize()));
                }
            }
        }
        return freeSpaceInfoArray;
    }

    // Функция подсчёта размера всех файлов сегмента
    private int segmentFilesSize(Segment segment) {
        int size = 0;
        for (DataInfo di : segment.info) {
            size += di.getSize();
        }
        return size;
    }

    // Функция добавления сегмента
    private void addSegment(FileSystem fs, int head) {
        fs.seg.add(new Segment(head));
        if (Segment.counter == 0) {
            Segment.currentSegment = 0;
        } else {
            Segment.currentSegment++;
        }
        Segment.counter++;
    }
}
