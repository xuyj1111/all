package xu.all.frw.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import xu.all.frw.easyexcel.converter.CustomStringStringConverter;
import lombok.Data;

import java.util.Date;

@Data
public class ConverterData {

    @ExcelProperty(value = "字符串标题", converter = CustomStringStringConverter.class)
    private String string;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("日期标题")
    private Date date;

    @NumberFormat("#.##%")
    @ExcelProperty(value = "数字标题")
    private Double doubleData;

}
