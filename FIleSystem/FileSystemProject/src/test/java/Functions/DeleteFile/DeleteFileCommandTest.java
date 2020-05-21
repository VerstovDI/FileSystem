package Functions.DeleteFile;

import Functions.ParameterReaders.ReadParameters;
import Structure.FileSystemStructure.IMessageWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFileCommandTest {

    @Test
    void execute() {
    }

    @Test
    void readParameters() {
        IMessageWriter imw1 = new IMessageWriter() {
            @Override
            public void write(String message) {
                System.out.println(message);
            }
        };
        DeleteFileCommand delFileCommand = new DeleteFileCommand();
        ReadParameters delFileParameters = new ReadParameters();
        //delFileCommand.ReadParameters(delFileParameters);
    }
}