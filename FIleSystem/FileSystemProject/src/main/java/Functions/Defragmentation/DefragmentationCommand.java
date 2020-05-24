package Functions.Defragmentation;

import Structure.FileSystemStructure.*;

public class DefragmentationCommand implements ICommand {


    @Override
    public void Execute(FileSystem fs, IParameterReader parameter, IMessageWriter message) {
        if (fs.seg.isEmpty()) {//пустая ли ФС

        } else {
            for (int i = 0; i < fs.seg.size(); i++) { sort(fs.seg.get(i)); }
            for(int i = 0; i < fs.seg.size()-1; i++){//проходим по каждому сегменту
                int emp;int allempty;
                while( (emp =getNextEmpty(fs.seg.get(i)))!=-1){//пока есть пустые места в сегменте
                    boolean allEmpty=false;
                    boolean swapComplete=false;
                    for(int j=i+1; j<fs.seg.size();j++) {//отправляемся в слеющие сегменты
                        int notEmp;
                        if ((notEmp = getNextNotEmpty(fs.seg.get(j))) != -1){//необходимо, чтобы пропускать полностью пустые сегменты и переходить к следующим
                            externalSwap(fs.seg.get(i), fs.seg.get(j), emp, notEmp);
                            swapComplete=true;
                            break;
                        }else {
                            allEmpty = true;
                        }
                    }
                    if(!swapComplete&&allEmpty)//т.е. если в нашем сегменте еще пустые места есть, но все остльные сегменты уже пустые
                        break;
                }
            }

            for (int i = 0; i < fs.seg.size(); i++) { sort(fs.seg.get(i)); }
            clear(fs);
            repair(fs);


        message.write("Defragmentation complete");

        }
    }

    @Override
    public void ReadParameters(IParameterReader parameterReader) {

    }


    private void swap(Segment segment,int i,int j){
        DataInfo buf = segment.info.get(i);
        segment.info.set(i,segment.info.get(j));
        segment.info.set(j,buf);
    }

    private void sort(Segment segment) {
        for (int i = 0; i < segment.info.size(); i++)
            for (int j = 0; j < segment.info.size(); j++)
                if (segment.info.get(i).getTypeNote() > segment.info.get(j).getTypeNote())
                    swap(segment, i, j);
    }

    private int getNextEmpty(Segment s){
        for(int i=0;i<s.info.size();i++)
            if(s.info.get(i).getTypeNote()==0)
                return i;
            return -1;
    }
    private int getNextNotEmpty(Segment s){
        for(int i=0;i<s.info.size();i++)
            if(s.info.get(i).getTypeNote()==1)
                return i;
        return -1;
    }

    private void externalSwap(Segment a, Segment b, int i, int j){
        DataInfo buf = a.info.get(i);
        a.info.set(i,b.info.get(j));
        b.info.set(j,buf);
    }

    private boolean SegFullEmpty (Segment a){
        boolean fl=true;
        for (int i = 0; i < a.info.size(); i++) {
            if(a.info.get(i).getTypeNote()==1)
                fl = false;
        }
        return fl;
    }

    private void clear(FileSystem fs){
        for(int i=0;i<fs.seg.size();i++)//удаляем пустые сегменты
            if(SegFullEmpty(fs.seg.get(i))){
                fs.seg.remove(i);
            }
        int last=fs.seg.size()-1;
        for(int i=0;i<fs.seg.get(last).info.size();i++) {//удаяем все пустые datainfo  в последнем сегменте
            if (fs.seg.get(last).info.get(i).getTypeNote() == 0) {
                fs.seg.get(last).info.remove(i);
                i --;
             }

        }
    }

    private void repair(FileSystem fs){
        int size=0;
        Segment.counter = fs.seg.size();
        for(int i=0;i<fs.seg.size();i++){
            fs.seg.get(i).head=size;
            for(int j=0;j< fs.seg.get(i).info.size();j++)
                size+=fs.seg.get(i).info.get(j).getSize();
        }
    }




}
