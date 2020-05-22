package Functions.DeleteFile;

import Functions.FindPlace.FindPlace;
import Structure.FileSystemStructure.*;

import static Functions.FindPlace.FindPlace.findFile;

public class DeleteFileCommand implements ICommand {
    private String fileName;


    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        FindPlace filePlace = findFile(fs, fileName);
        if (filePlace != null) {
            message.write("файл "+ fileName+ " найден, удаляем  " );
            Segment segment = fs.seg.get(filePlace.getNumberOfSegment());
            DataInfo dataInfo = segment.info.get(filePlace.getNumberOfRecord());
            dataInfo.setTypeNote(0);
            rewriteSizeDown(filePlace.getNumberOfRecord(), segment, dataInfo);
            rewriteSizeUp(filePlace.getNumberOfRecord(), segment);
        } else {
            message.write("Такого файла не существует!");
        }

    }

    @Override
    public void ReadParameters(IParameterReader parameter) {
        this.fileName =  parameter.readFileName("Введите имя удаляемого файла:  ");
    }


    /* По сути дефрагментация всех файлов далее по сегменту.
    Начиная от текущего местоположения currentDataInfo, иду вниз пока не конец сегмента или пока не встречу не удаленный файл.
    Если следующий файл удален и его размер !=0,то делаю его размер =0. А отнятый размер добавляю к размеру currentDataInfo.
    */

    private void rewriteSizeDown(int numberRecord, Segment segment, DataInfo currentDataInfo) {
        DataInfo tmpDataInfo = null;
        for (int i = numberRecord + 1; i < segment.info.size(); i++) {
            tmpDataInfo = segment.info.get(i);
            if (tmpDataInfo.getTypeNote() == 0 && tmpDataInfo.getSize() != 0) {
                currentDataInfo.addSize(tmpDataInfo.getSize());
                tmpDataInfo.setSize(0);
            } else break;
        }
    }


    /* По сути дефрагментация всех файлов далее вверх по сегменту.
    Начиная от текущего местоположения, иду вверх с помощью двух указателей firstPointerDataInfo и secondPointerDataInfo,
    пока не конец сегмента или пока не встречу не удаленный файл.
    firstPointerDataInfo, за ним secondPointerDataInfo.
    В переменную size записываю размер стартового удаленного файла secondPointerDataInfo.
    Если следующий файл firstPointerDataInfo удален и его размер !=0, то добавляю размер firstPointerDataInfo в size, а размер второго указателя становится =0.
    Как только останавливаюсь, записываю значение переменной size в файл остановки.
    В итоге весь размер подряд идущего пустого пространства и далее вверх записан в самом первом удаленном файле.
    */

    private void rewriteSizeUp(int numberRecord, Segment segment) {
        if (numberRecord != 0) {
            DataInfo firstPointerDataInfo = null;
            DataInfo secondPointerDataInfo = segment.info.get(numberRecord);
            int size = secondPointerDataInfo.getSize();
            for (int i = numberRecord - 1; i > -1; i--) {
                firstPointerDataInfo = segment.info.get(i);
                if (firstPointerDataInfo.getTypeNote() == 0) {
                    size += firstPointerDataInfo.getSize();
                    secondPointerDataInfo.setSize(0);
                    if (i == 0) {
                        firstPointerDataInfo.setSize(size);
                    }
                    secondPointerDataInfo = firstPointerDataInfo;
                } else {
                    secondPointerDataInfo.setSize(size);
                    break;
                }
            }
        }
    }

}
