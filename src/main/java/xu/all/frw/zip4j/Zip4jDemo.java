package xu.all.frw.zip4j;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.junit.jupiter.api.Test;
import xu.tools.toolsio.FileTool;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Zip4jDemo {

    private static final String PATH = "src/main/java/xu/all/frw/zip4j/";
    private static final String DIR_PATH = PATH + "testDir/";
    private static final String ZIP_PATH = PATH + "testZip.zip";

    /**
     * @Description: 创建zip
     */
    @Test
    public void createZip() throws Exception {
        createFileAndDir();
        //参数设置
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters.setPassword("123456");
        //创建zip
        ZipFile zipFile = new ZipFile(ZIP_PATH);
        File file = new File(DIR_PATH);
        if (file.isFile()) {
            zipFile.addFile(file, parameters);
        } else if (file.isDirectory()) {
            zipFile.addFolder(file, parameters);
        }
        FileTool.deleteFolder(new File(DIR_PATH));
    }

    /**
     * @Description: 解压zip
     */
    @Test
    public void unZip() {
        try {
            ZipFile zipFile = new ZipFile(ZIP_PATH);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword("123456");
            }
            zipFile.extractAll(PATH);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 删除zip和文件夹
     */
    @Test
    public void remove() throws Exception {
        if (Files.exists(Paths.get(DIR_PATH))) {
            FileTool.deleteFolder(new File(DIR_PATH));
        }
        if (Files.exists(Paths.get(ZIP_PATH))) {
            Files.delete(Paths.get(ZIP_PATH));
        }
    }

    /**
     * @Description: 创建测试用的文件夹及文件
     */
    private void createFileAndDir() {
        try {
            Files.createDirectory(Paths.get(DIR_PATH));
            FileTool.createAndWriteFile(DIR_PATH + "testFile01.txt", "hello你好1");
            FileTool.createAndWriteFile(DIR_PATH + "testFile02.txt", "hello你好2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
