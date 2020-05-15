package Functions.CreateFS;

import Structure.FileSystemStructure.IParameterReader;

import java.util.Scanner;

public class CreateFSParameters implements IParameterReader {

    int size;
    String systemName;
    String ownerName;
    int tomId;
    int version;

    @Override
    public IParameterReader ParameterReader() {
        int size = 0;
        Scanner in = new Scanner(System.in);
        boolean err = true;
        while (err) {
            System.out.println("Введите размер файловой системы (1-31)");
            size = in.nextInt();
            if (size < 0 || size > 31) {
                System.out.println("Введите корректное значение");
            } else
                err = false;
        }

        System.out.println("Введите название файловой системы");
        String systemName = in.nextLine();

        System.out.println("Введите имя владельца файловой системы");
        String ownerName = in.nextLine();

        System.out.println("Введите номер тома");
        int tomId = in.nextInt();

        System.out.println("Введите версию");
        int version = in.nextInt();

        return new CreateFSParameters(size, systemName, ownerName, tomId, version);

    }

    public CreateFSParameters(int size, String systemName, String ownerName, int tomId, int version) {
        this.systemName = systemName;
        this.ownerName = ownerName;
        this.tomId = tomId;
        this.version = version;
        this.size = size;
    }

}




