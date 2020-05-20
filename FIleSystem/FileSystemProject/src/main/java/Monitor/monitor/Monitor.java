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
        downloadFS.Execute(fs, ipr, () -> {
        });
    }

    public void create(){
        CreateFSCommand newFS = new CreateFSCommand();
        IParameterReader newFSPar = new CreateFSParameters(0,
                "", "", 0, 0);
        newFS.Execute(fs, newFSPar, () -> {
        });
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
        cfc.Execute(fs, ipr, () -> {
        });
    }

    public void delete(){
        DeleteFileCommand dfc = new DeleteFileCommand();
        IParameterReader ipr = new ReadParameters();
        dfc.Execute(fs, ipr, () -> {
        });
    }

    public void save(){
        SaveFSCommand sfc = new SaveFSCommand();
        IParameterReader ipr = new ReadParameters();
        sfc.Execute(fs, ipr, () -> {
        });
    }

    public void add(){
        AddInfoCommand aic = new AddInfoCommand();
        IParameterReader ipr = new ReadParameters();
        aic.Execute(fs, ipr, () -> {
        });
    }
}
