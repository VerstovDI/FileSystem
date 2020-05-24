package Functions.DownloadFS;

import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class DownloadFSCommand implements ICommand {
    String fileName;
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        ReadParameters(parameter);
        try {
            FileSystem fsbuf = null;
            fsbuf=this.Download(fileName);
            fs.copy(fsbuf);
        }
        catch (ClassNotFoundException ex){
            System.out.println(ex);
        }
        catch (IOException exio){
            System.out.println(exio);
        }
        message.write("file system successfully download");
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {
        this.fileName=(((ReadParameters) parameterReader).readFileName("Введите имя файла для загрузки информации: "));
    }



    public  FileSystem Download( String filename) throws IOException, ClassNotFoundException {

        FileInputStream fi = new FileInputStream(new File(filename+".txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        FileSystem fs = (FileSystem) oi.readObject();//считали параметры FS

        /*
        CreateFileCommand createFileCommand=new CreateFileCommand();
        String line=null;
        BufferedReader br = new BufferedReader(new FileReader(filename+".txt"));
        while ((line = br.readLine()) != null) {
            createFileCommand.createFile(fs,(DataInfo)oi.readObject());
        }
        */
        oi.close();
        fi.close();
        return fs;
    }
}
