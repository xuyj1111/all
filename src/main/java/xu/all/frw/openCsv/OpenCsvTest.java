package xu.all.frw.openCsv;

import com.opencsv.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenCsvTest {

    private static final byte[] UTF8BOOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
    private static final String PATH = "src/main/java/xu/all/frw/openCsv/";

    /**
     * @Description: 创建csv文件
     * UTF8BOOM标识为utf8编码，防止中文乱码
     */
    @Test
    public void createCsv() throws IOException {
        FileOutputStream os = new FileOutputStream(PATH + "testCsv.csv");
        os.write(UTF8BOOM);
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(os));
        String[] heads = new String[]{"名字", "年龄"};
        csvWriter.writeNext(heads);
        String[] data = new String[]{"张三", "20"};
        csvWriter.writeNext(data);
        csvWriter.flush();
    }

    /**
     * @Description: 读取csv文件
     */
    @Test
    public void readCsv() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(PATH + "testCsv.csv")));
        List<String[]> strings = reader.readAll();
        strings.stream().forEach(strArr -> {
            for (String str : strArr) {
                System.out.print(str);
            }
            System.out.println();
        });
    }

    /**
     * @Description: 读取csv文件，通过建造者模式
     */
    @Test
    public void readCsvNew() throws IOException {
        InputStreamReader is = new InputStreamReader(new FileInputStream(PATH + "testCsv.csv"), "utf-8");
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader reader = new CSVReaderBuilder(is).withCSVParser(csvParser).build();
        List<String[]> strings = reader.readAll();
        strings.stream().forEach(strArr -> {
            for (String str : strArr) {
                System.out.print(str);
            }
            System.out.println();
        });
    }

    /**
     * @Description: 删除csv文件
     */
    @Test
    public void remove() throws IOException {
        if (Files.exists(Paths.get(PATH + "testCsv.csv"))) {
            Files.delete(Paths.get(PATH + "testCsv.csv"));
        }
    }
}
