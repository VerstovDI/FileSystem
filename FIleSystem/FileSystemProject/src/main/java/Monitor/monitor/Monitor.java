package Monitor.monitor;

import Functions.AddInfo.AddInfoCommand;
import Functions.CreateFS.CreateFSCommand;
import Functions.CreateFile.CreateFileCommand;
import Functions.Defragmentation.DefragmentationCommand;
import Functions.DeleteFile.DeleteFileCommand;
import Functions.DownloadFS.DownloadFSCommand;
import Functions.Fragmentation.FragmentationCommand;
import Functions.ParameterReaders.ReadParameters;
import Functions.SaveFS.SaveFSCommand;
import Functions.Title.TitleCommand;
import Functions.TitleInOrder.TitleInOrderCommand;
import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IParameterReader;
import java.io.*;

public class Monitor {

    FileSystem fs;
    ICommand ic;
    IParameterReader ipr = new ReadParameters();

    Monitor(FileSystem fs){
        this.fs = fs;
    }

    public void download(){
        if (!(ic instanceof DownloadFSCommand)) {
            ic = new DownloadFSCommand();
        }
        fs = new FileSystem(0, "",
                "", 0, 0, 100);
        ic.Execute(fs, ipr, System.out::println);
    }

    public void create(){
        if (!(ic instanceof CreateFSCommand)) {
            ic = new CreateFSCommand();
        }
        fs = new FileSystem(0, "",
                "", 0, 0, 100);
        ic.Execute(fs, ipr, System.out::println);
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
        if (!(ic instanceof CreateFileCommand)) {
            ic = new CreateFileCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }

    public void delete(){
        if (!(ic instanceof DeleteFileCommand)) {
            ic = new DeleteFileCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }

    public void save(){
        if (!(ic instanceof SaveFSCommand)) {
            ic = new SaveFSCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }

    public void add(){
        if (!(ic instanceof AddInfoCommand)) {
            ic = new AddInfoCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }

    public void defragmentation(){
        if (!(ic instanceof DefragmentationCommand)) {
            ic = new DefragmentationCommand();
        }
        /*fs = new FileSystem(1, "",
                "", 0, 0, 100);*/
        ic.Execute(fs, ipr, System.out::println);
    }

    public void fragmentation(){
        if (!(ic instanceof FragmentationCommand)) {
            ic = new FragmentationCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }

    public void title(){
        if (!(ic instanceof TitleCommand)) {
            ic = new TitleCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }

    public void title_in_order(){
        if (!(ic instanceof TitleInOrderCommand)) {
            ic = new TitleInOrderCommand();
        }
        ic.Execute(fs, ipr, System.out::println);
    }
}
