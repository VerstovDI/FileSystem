package Structure.FileSystemStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataInfoTest {

    @Test
    @DisplayName("Test for getDate function")
    void getDate() {
        DataInfo di1 = new DataInfo(new int[] { 14, 6, 2020}, "fileName1", 100);
        int[] expected = new int[] {14, 6, 2020};
        int[] actual = di1.getDate();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for setDate function")
    void setDate() {
        DataInfo di = new DataInfo(new int[]{15, 8, 2020}, "fileName2", 45);
        int[] expected = new int[]{14, 6, 2020};
        di.setDate(expected);
        Assertions.assertEquals(expected, di.getDate());
    }

    @Test
    @DisplayName("Test for getNameFile function")
    void getNameFile() {
        DataInfo di1 = new DataInfo(new int[]{15, 8, 2020}, "fileName3", 45);
        DataInfo di2 = new DataInfo(new int[]{15, 8, 2020}, "s", 45);
        DataInfo di3 = new DataInfo(new int[]{15, 8, 2020}, "phps.txt", 45);
        Assertions.assertEquals("fileName3", di1.getNameFile());
        Assertions.assertEquals("s", di2.getNameFile());
        Assertions.assertEquals("phps.txt", di3.getNameFile());
    }

    @Test
    void setNameFile() {
    }

    @Test
    void getSize() {
    }

    @Test
    void setSize() {
    }

    @Test
    void addSize() {
    }

    @Test
    void getTypeNote() {
    }

    @Test
    void setTypeNote() {
    }
}