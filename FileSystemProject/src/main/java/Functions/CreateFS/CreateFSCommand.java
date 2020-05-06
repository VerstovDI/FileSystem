package Functions.CreateFS;

import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

public class CreateFSCommand implements ICommand {

   CreateFSParameters par;// уберем, после замены возвращаемого значения ReadParameters

    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        ReadParameters(parameter);
        FileSystem buf = new FileSystem(par.size, par.systemName, par.ownerName, par.tomId, par.version);
        fs = buf;
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) { // кидаем сюда экземпляр класса CreateFSParameters
        //стоит убрать void если можно
        //хотя вообще лучше его убрать, но надо поговорить с Алексеенко
        IParameterReader p=parameterReader.ParameterReader();
        CreateFSParameters par;
        par =(CreateFSParameters) p;
        this.par=par;
    }
}
