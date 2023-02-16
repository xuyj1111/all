package xu.all.frw.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * @Description:
 * @Author: xuyujun
 * @Date: 2021/7/29
 */
public class StringUtilsTest {

    /**
     * @Description: 参考API：https://www.javadoc.io/doc/org.apache.commons/commons-lang3/latest/index.html
     */
    @Test
    public void isUtils() {
        //只有null和空双引号返回true（不屏蔽空格）
        StringUtils.isEmpty("");
        //相反
        StringUtils.isNotEmpty("abcd");
        //多个
        StringUtils.isAllEmpty(null, "");
        //任意一个
        StringUtils.isAnyEmpty("abcd", "");
        //多个+相反
        StringUtils.isNoneEmpty("abcd", "efg");

        //null、""、" "空字符串、制表符 换行符等返回true
        StringUtils.isBlank("  ");
        //相反
        StringUtils.isNotBlank("abcd");
        //多个
        StringUtils.isAllBlank("", "  ");
        //任意一个
        StringUtils.isAnyBlank("abcd", "  ");
        //多个+相反
        StringUtils.isNoneBlank("abcd", "efg");

        //字符串只有数字组成，返回true
        StringUtils.isNumeric("123456");
        //字符串允许数字和空格，返回true
        StringUtils.isNumericSpace("123 456");

        //全部小写
        StringUtils.isAllLowerCase("abcdefg");
        //全部大写
        StringUtils.isAllUpperCase("ABCDEFG");

        //字符串只有字母组成，返回true
        StringUtils.isAlpha("abcd");
        //不屏蔽空格
        StringUtils.isAlphaSpace("ab cd");
        //不屏蔽数字
        StringUtils.isAlphanumeric("ab1cd");
        //不屏蔽空格和数字
        StringUtils.isAlphanumericSpace("ab1 cd");

        //字符串包含大写和小写字符，返回true
        StringUtils.isMixedCase("ab CD");
        //只有空双引号和空格，返回true
        StringUtils.isWhitespace("  ");
        //ASCII码都返回true
        StringUtils.isAsciiPrintable("\u007e");

    }
}
