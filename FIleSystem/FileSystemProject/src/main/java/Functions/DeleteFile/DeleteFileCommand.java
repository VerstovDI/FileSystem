package Functions.DeleteFile;

import Structure.FileSystemStructure.*;

public class DeleteFileCommand implements ICommand {
    DeleteFileParameters params;

<<<<<<< HEAD

    // вспомогательный класс для нахождения места вставки. Потом вынесем.

=======
    // вспомогательный класс для нахождения места вставки. Потом вынесем.
>>>>>>> 8bc2ae2c5d6723ad241f7fbf1261780512f06bf5
    class FilePlace {
        private int numberSegment;
        private int numberRecord;

        FilePlace(int numberSegment, int numberRecord) {
            this.numberRecord = numberRecord;
            this.numberSegment = numberSegment;
        }
    }

    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        this.ReadParameters(parameter);
        FilePlace filePlace = findFile(fs, this.params.fileName);
        if (filePlace != null) {
            System.out.println(" удаляем файл  " + params.fileName);
            Segment segment = fs.seg.get(filePlace.numberSegment);
            DataInfo dataInfo = segment.info.get(filePlace.numberRecord);
            dataInfo.typeNote = 0;
            rewriteSizeDown(filePlace.numberRecord, segment, dataInfo);
            rewriteSizeUp(filePlace.numberRecord, segment);
        } else {
            System.out.println("Нет такого файла");
        }
        message.write();
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        IParameterReader paramReader = parameterReader.ParameterReader();
        DeleteFileParameters fileParams = (DeleteFileParameters) paramReader;
        this.params = fileParams;
    }

<<<<<<< HEAD

    // по сути дефрагментация всех файлов далее по сегменту.
    //Начиная от текущего местоположения currentDataInfo, иду вниз пока не конец сегмента или пока не встречу неудаленный файл
    // если след файл удален и его размер !=0, делаю его размер =0. А отнятый размер добавляю к размеру currentDataInfo

=======
    // по сути дефрагментация всех файлов далее по сегменту.
    //Начиная от текущего местоположения currentDataInfo, иду вниз пока не конец сегмента или пока не встречу неудаленный файл
    // если след файл удален и его размер !=0, делаю его размер =0. А отнятый размер добавляю к размеру currentDataInfo
>>>>>>> 8bc2ae2c5d6723ad241f7fbf1261780512f06bf5
    private void rewriteSizeDown(int numberRecord, Segment segment, DataInfo currentDataInfo) {
        DataInfo tmpDataInfo = null;
        for (int i = numberRecord + 1; i < segment.info.size(); i++) {
            tmpDataInfo = segment.info.get(i);
            if (tmpDataInfo.typeNote == 0 && tmpDataInfo.size != 0) {
                currentDataInfo.size += tmpDataInfo.size;
                tmpDataInfo.size = 0;
            } else break;
        }
    }

<<<<<<< HEAD

    // по сути дефрагментация всех файлов далее вверх по сегменту.
=======
    //  по сути дефрагментация всех файлов далее вверх по сегменту.
>>>>>>> 8bc2ae2c5d6723ad241f7fbf1261780512f06bf5
    //Начиная от текущего местоположения , иду вверх с помощью двух указателей firstPointerDataInfo и secondPointerDataInfo
    //	пока не конец сегмента или пока не встречу неудаленный файл.
    //firstPointerDataInfo за ним  secondPointerDataInfo
    // В переменную size  записываю размер стартового удаленного файла secondPointerDataInfo
    // Если след файл firstPointerDataInfo удален и его размер !=0 , добавляю размер firstPointerDataInfo в size, а размер второго указателя =0
    // Как только останавливаюсь, записываю значение переменной size в файл остановки.
    //В итоге весь размер подряд идущего пустого пространства и далее вверх записан в самом первом удаленном файле.
<<<<<<< HEAD

=======
>>>>>>> 8bc2ae2c5d6723ad241f7fbf1261780512f06bf5
    private void rewriteSizeUp(int numberRecord, Segment segment) {
        if (numberRecord != 0) {
            DataInfo firstPointerDataInfo = null;
            DataInfo secondPointerDataInfo = segment.info.get(numberRecord);
            int size = secondPointerDataInfo.size;
            for (int i = numberRecord - 1; i > -1; i--) {
                firstPointerDataInfo = segment.info.get(i);
                if (firstPointerDataInfo.typeNote == 0) {
                    size += firstPointerDataInfo.size;
                    secondPointerDataInfo.size = 0;
                    if (i == 0) {
                        firstPointerDataInfo.size = size;
                    }
                    secondPointerDataInfo = firstPointerDataInfo;
                } else {
                    secondPointerDataInfo.size = size;
                    break;
                }
            }
        }
    }


    private FilePlace findFile(FileSystem fs, String fileName) {
        for (int i = 0; i < fs.seg.size(); i++) {
            Segment segment = fs.seg.get(i);
            for (int j = 0; j < segment.info.size(); j++) {
                DataInfo dataInfo = segment.info.get(j);
                if (dataInfo != null && dataInfo.nameFile.equals(fileName)) {
                    return new FilePlace(i, j);
                }
            }
        }
        return null;
    }
}
