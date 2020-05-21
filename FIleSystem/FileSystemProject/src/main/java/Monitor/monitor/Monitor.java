package Monitor.monitor;

import Functions.AddInfo.AddInfoCommand;
import Functions.CreateFS.CreateFSCommand;
import Functions.CreateFS.CreateFSParameters;
import Functions.CreateFile.CreateFileCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.DownloadFS.DownloadFSCommand;
import Functions.ParameterReaders.ReadParameters;
import Functions.SaveFS.SaveFSCommand;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Monitor {

    FileSystem fs;

    Monitor(FileSystem fs){
        this.fs = fs;
    }

    public void download(){
        DownloadFSCommand downloadFS = new DownloadFSCommand();
        IParameterReader ipr = new ReadParameters();
        IMessageWriter imw = new IMessageWriter() {
            String message ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        downloadFS.Execute(fs, ipr, imw);
    }

    public void create(){
        CreateFSCommand newFS = new CreateFSCommand();
        IParameterReader newFSPar = new CreateFSParameters(0,
                "", "", 0, 0);
        IMessageWriter imw = new IMessageWriter() {
            String message ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        newFS.Execute(fs, newFSPar, imw);
    }

    public void help() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String helperPath = System.getProperty("user.dir") + "/src/main/java/Monitor/monitor/help.txt";
        File f = new File(helperPath);
        BufferedReader fin = new BufferedReader(new FileReader(f));
        String line;
        while ((line = fin.readLine()) != null) System.out.println(line);
    }

    public void file(){
        CreateFileCommand cfc = new CreateFileCommand();
        IParameterReader ipr = new ReadParameters();
        IMessageWriter imw = new IMessageWriter() {
            String message ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        cfc.Execute(fs, ipr, imw);
    }

    public void delete(){
        DeleteFileCommand dfc = new DeleteFileCommand();
        IParameterReader ipr = new ReadParameters();
        IMessageWriter imw = new IMessageWriter() {
            String message ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        dfc.Execute(fs, ipr, imw);
    }

    public void save(){
        SaveFSCommand sfc = new SaveFSCommand();
        IParameterReader ipr = new ReadParameters();
        IMessageWriter imw = new IMessageWriter() {
            String message ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        sfc.Execute(fs, ipr, imw);
    }

    public void add(){
        AddInfoCommand aic = new AddInfoCommand();
        IParameterReader ipr = new ReadParameters();
        IMessageWriter imw = new IMessageWriter() {
            String message ="заглушка";
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        aic.Execute(fs, ipr, imw);
    }
}
