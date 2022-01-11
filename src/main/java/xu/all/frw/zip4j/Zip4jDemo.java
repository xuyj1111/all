package xu.all.frw.zip4j;

import org.junit.jupiter.api.Test;
import xu.tools.toolsio.FileTool;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Zip4jDemo {

    private static final String path = "src/main/java/xu/all/frw/zip4j/testDir/";

    private void createFileAndDir() {
        try {
            Files.createDirectory(Paths.get(path));
            FileTool.createAndWriteFile(path + "testFile01.txt", "hello你好1");
            FileTool.createAndWriteFile(path + "testFile02.txt", "hello你好2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void remove() {
        try {
            FileTool.deleteFolder(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createZip() {
//        createFileAndDir();
        remove();
    }
}
