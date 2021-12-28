package xu.all.frw.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import xu.all.frw.easyexcel.interceptor.CommentWriteHandler;
import xu.all.frw.easyexcel.interceptor.CustomCellWriteHandler;
import xu.all.frw.easyexcel.interceptor.CustomSheetWriteHandler;
import xu.all.frw.easyexcel.pojo.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: 参考API：https://alibaba-easyexcel.github.io/index.html
 * *
 * ExcelWriter：excel写类
 * WriteSheet：sheet类
 * WriteTable：table类
 * *
 * @Author: xuyujun
 * @Date: 2021/7/6
 */
public class EasyExcelWrite {
    //根据自己路径修改
    final String filePath = "C:\\Users\\edz\\Desktop\\";

    /**
     * @Description: 构建数据
     */
    public static List<DemoData> buildData() {
        ArrayList<DemoData> dataArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setString("标题" + i);
            demoData.setDate(new Date());
            demoData.setDoubleData(3.14);
            dataArrayList.add(demoData);
        }
        return dataArrayList;
    }

    /**
     * @Description: 最简单的写
     */
    @Test
    public void simpleWrite() {
        String fileName = filePath + "simpleWrite.xlsx";
        //第一种写法，流会自动关闭
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(buildData());
        //第二种写法，流需手动关闭
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(buildData(), writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * @Description: 根据参数导出指定列
     */
    @Test
    public void excloudeOrIncloudeWrite() {
        String fileName = filePath + "excloudeOrIncloudeWrite.xlsx";
        HashSet set = new HashSet<String>();
        set.add("doubleData");
        //排除列
//        EasyExcel.write(fileName, Deta.class).excludeColumnFiledNames(set).sheet("模板").doWrite(buildData());
        //指定列
        EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(set).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 指定写入的列的位置
     */
    @Test
    public void indexWrite() {
        String fileName = filePath + "indexWrite.xlsx";
        //写入class字段指定index
        EasyExcel.write(fileName, IndexData.class).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 记录重复写入
     */
    @Test
    public void repeatedWrite() throws ClassNotFoundException {
        String fileName = filePath + "repeatedWrite.xlsx";
        //第一种写法，同一个模板
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet wrieSheet = EasyExcel.writerSheet("模板").build();
//        for (int i = 0; i < 2; i++) {
//            excelWriter.write(buildData(), wrieSheet);
//        }
//        excelWriter.finish();
        //第二种写法，不同模板，同一个对象
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        for (int i = 0; i < 2; i++) {
        //模板名变了才会多个sheet
//            WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + 1).build();
//            excelWriter.write(buildData(), writeSheet);
//        }
//        excelWriter.finish();
        //第三种写法，不同模板，不同对象
        String[] className = new String[]{"DemoData", "IndexData"};
        String prefix = "xu.all.frw.easyexcel.pojo.";
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        for (int i = 0; i < 2; i++) {
            //模板名变了才会多个sheet
            WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(Class.forName(prefix + className[i])).build();
            excelWriter.write(buildData(), writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * @Description: 格式转换
     */
    @Test
    public void converterWrite() {
        String fileName = filePath + "converterWrite.xlsx";
        EasyExcel.write(fileName, ConverterData.class).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 图片导入excel
     * 五种方式导入
     * File
     * InputStream
     * String（需要转换为image）
     * ByteArray
     * Url
     */
    @Test
    public void imageWrite() {
        String fileName = filePath + "imageWrite.xlsx";
        //本地图片
        String imagePath = "C:\\Users\\edz\\Pictures\\Camera Roll\\0f720273.jpg";

        ArrayList<ImageData> list = new ArrayList<>();
        ImageData imageData = new ImageData();
        list.add(imageData);

        InputStream inputStream = null;
        try {
            imageData.setFile(new File(imagePath));
            imageData.setInputStream(FileUtils.openInputStream(new File(imagePath)));
            //转换器转为image
            imageData.setString(imagePath);
            imageData.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
            imageData.setUrl(new URL("https://raw.githubusercontent.com/alibaba/easyexcel/master/src/test/resources/converter/img.jpg"));
            EasyExcel.write(fileName, ImageData.class).sheet().doWrite(list);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 根据模板写入
     * 先执行simpleWrite创建excel
     * 根据simpleWrite作为模板创建excel
     */
    @Test
    public void templateWrite() {
        //simpleWrite的路径
        String templateName = filePath + "simpleWrite.xlsx";
        String fileName = filePath + "templateWrite.xlsx";
        EasyExcel.write(fileName, DemoData.class).withTemplate(templateName).sheet().doWrite(buildData());
    }

    /**
     * @Description: 列宽，行高
     */
    @Test
    public void widthAndHeightWrite() {
        String fileName = filePath + "widthAndHeightWrite2.xlsx";
        EasyExcel.write(fileName, WidthAndHeightData.class).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 自定义样式
     * WriteCellStyle：样式类
     * WriteFont：设置字体类
     * horizontalCellStyleStrategy：策略类，构造函数参数（头样式，内容样式）
     */
    @Test
    public void styleWrite() {
        String fileName = filePath + "styleWrite.xlsx";
        //头样式
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        headWriteCellStyle.setWriteFont(headWriteFont);
        //内容样式
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short) 20);
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 合并单元格
     * LoopMergeStrategy：循环合并类（上下合并）
     */
    @Test
    public void mergeWrite() {
        String fileName = filePath + "mergeWrite.xlsx";
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(loopMergeStrategy).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 使用table写入
     */
    @Test
    public void tableWrite() {
        String fileName = filePath + "tableWrite.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        //sheet设置不需要头
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
        //table设置需要头
        WriteTable table01 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
        WriteTable table02 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();
        excelWriter.write(buildData(), writeSheet, table01);
        excelWriter.write(buildData(), writeSheet, table02);
        excelWriter.finish();
    }

    /**
     * @Description: 动态头
     * 调用head方法，顶替@ExcelProperty
     */
    @Test
    public void dynamicHeadWrite() {
        String fileName = filePath + "dynamicHeadWrite.xlsx";
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(buildData());
    }

    private List<List<String>> head() {
        ArrayList<List<String>> list = new ArrayList<>();
        ArrayList<String> head0 = new ArrayList<>();
        head0.add("字符串" + System.currentTimeMillis());
        ArrayList<String> head1 = new ArrayList<>();
        head1.add("数字" + System.currentTimeMillis());
        ArrayList<String> head2 = new ArrayList<>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    /**
     * @Description: 自动列宽（不太精准）
     */
    @Test
    public void longestMatchColumnWidthWrite() {
        String fileName = filePath + "longestMatchColumnWidthWrite.xlsx";
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 自定义拦截器（超链接 + 下拉框）
     */
    @Test
    public void customHandlerWrite() {
        String fileName = filePath + "customHandlerWrite.xlsx";
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(new CustomSheetWriteHandler())
                .registerWriteHandler(new CustomCellWriteHandler()).sheet("模板").doWrite(buildData());
    }

    /**
     * @Description: 自定义拦截器（插入批注）
     */
    @Test
    public void commentWrite() {
        String fileName = filePath + "commentWrite.xlsx";
        //这里要注意inMemory 要设置为true，才能支持批注
        EasyExcel.write(fileName, DemoData.class).inMemory(Boolean.TRUE)
                .registerWriteHandler(new CommentWriteHandler()).sheet("模板").doWrite(buildData());
    }
}
