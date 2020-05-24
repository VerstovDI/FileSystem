package Functions.Fragmentation;

import Structure.FileSystemStructure.FileSystem;
import Structure.FileSystemStructure.ICommand;
import Structure.FileSystemStructure.IMessageWriter;
import Structure.FileSystemStructure.IParameterReader;

public class FragmentationCommand implements ICommand {
    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        if (fs.seg.isEmpty()) {//пустая ли ФС

        } else {
           int full=0;
           int empty=0;
           int maxempty=0;
           //size = (fs.seg.get(fs.seg.size()-1).head-head);
           for(int j=0;j<fs.seg.size();j++)
            for (int i = 0; i < fs.seg.get(j).info.size(); i++) {
                if(fs.seg.get(j).info.get(i).getTypeNote()==1)
                    full +=fs.seg.get(j).info.get(i).getSize();
                else {
                    if(fs.seg.get(j).info.get(i).getSize()>maxempty)
                        maxempty=fs.seg.get(j).info.get(i).getSize();
                    empty += fs.seg.get(j).info.get(i).getSize();
                }
            }

           if(maxempty< FileSystem.fileSystemSize -full-empty)
               maxempty= FileSystem.fileSystemSize -full-empty;
            empty += FileSystem.fileSystemSize -full-empty;
            double frag=((double)empty/(double) FileSystem.fileSystemSize)*100;
            // empty=emptySpace(fs);
            message.write("Empty space size ="+ empty +"\n"+"Fragmentation ="+(int)frag+"%"+"\n"+"Max place for file = "+ maxempty);
        }
    }


    @Override
    public void ReadParameters(IParameterReader parameterReader) {

    }


    public int emptySpace(FileSystem fs) {
        int full = 0;
        int empty = 0;
        for (int j = 0; j < fs.seg.size(); j++)
            for (int i = 0; i < fs.seg.get(j).info.size(); i++) {
                if (fs.seg.get(j).info.get(i).getTypeNote() == 1)
                    full += fs.seg.get(j).info.get(i).getSize();
                else {
                    empty += fs.seg.get(j).info.get(i).getSize();
                }
            }

        empty += FileSystem.fileSystemSize - full - empty;
        return empty;
    }
}
