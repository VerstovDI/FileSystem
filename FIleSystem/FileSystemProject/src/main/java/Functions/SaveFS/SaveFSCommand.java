package Functions.SaveFS;

import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFSCommand implements ICommand {
    String fileName;
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        ReadParameters(parameter);
        try {
            this.Save(fs, fileName);
        }
        catch (IOException ex){
            System.out.println(ex);
        }
        message.write("file system successfully saved");
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        this.fileName=(((ReadParameters) parameterReader).readFileName("Введите имя файла для сохранения информации: "));
    }

    public  void Save(FileSystem fileSystem,String fileName) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("E:\\" + fileName + ".txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(fileSystem);
        for(int i=0;i<fileSystem.seg.size();i++){
            for(int j=0;j<fileSystem.seg.get(i).info.size();j++){
                o.writeObject(fileSystem.seg.get(i).info.get(j));
            }
        }

        o.close();
        f.close();
    }




}
