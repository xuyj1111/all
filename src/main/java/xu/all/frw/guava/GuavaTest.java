package xu.all.frw.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GuavaTest {

    /**
     * @Description: Joiner.on(String separator)
     * .join(Iterable<?> parts)：传入集合或数组，返回以指定分隔符将集合或数组内值组合成一个字符串
     * .skipNulls()：跳过集合或数组中的null值
     * .useForNull(String nullText)：将集合或数组中的null值替换成指定字符串
     * .appendTo(StringBuilder builder, Iterable<?> parts)：将builder的字符串与parts join后的字符串组合
     * .withKeyValueSeparator(String keyValueSeparator)：设置KV的连接符，返回MapJoiner，即之后再调用join则可传入Map类型对象
     */
    @Test
    public void joinerTest() {
        List<String> list1 = Arrays.asList("aa", "bb", "cc");
        System.out.println(Joiner.on("-").join(list1));

        List<String> list2 = Arrays.asList("aa", "bb", "cc", "dd", null, "ff");
        System.out.println(Joiner.on("-").skipNulls().join(list2));
        System.out.println(Joiner.on("-").useForNull("nullValue").join(list2));

        System.out.println(Joiner.on("-").skipNulls().appendTo(new StringBuilder("abcdef:"), list2));

        Map<String, String> map = ImmutableMap.of("k1", "v1", "k2", "v2");
        System.out.println(Joiner.on("-").withKeyValueSeparator("=").join(map));
    }

    /**
     * @Description: Splitter.on(String separator)、Splitter.fixedLength(int length)
     * Splitter.on：规定分隔符
     * Splitter.fixedLength：规定分割长度
     * —————
     * .split(CharSequence sequence)：分割字符串
     * .trimResults()：删除字符串头尾空格
     * .omitEmptyStrings()：忽略空字符串，空格不忽略
     * .withKeyValueSeparator(String separator)：规定KV的连接符，返回MapSplitter，之后再调用split则会返回map对象
     */
    @Test
    public void splitterTest() {
        String s1 = " ,b,c,,";
        System.out.println(Splitter.on(",").split(s1));
        System.out.println(Splitter.on(",").trimResults().split(s1));
        System.out.println(Splitter.on(",").omitEmptyStrings().split(s1));
        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split(s1));

        String s2 = "12345678";
        System.out.println(Splitter.fixedLength(2).split(s2));

        String s3 = "1=2,3=4";
        System.out.println(Splitter.on(",").withKeyValueSeparator("=").split(s3));
    }

    /**
     * @Description: Strings
     * .nullToEmpty(String string)：若入参为null，则返回空字符串
     * .emptyToNull(String string)：若入参为空字符串，则返回null
     * .isNullOrEmpty(String string)：若入参为null或者空字符串，返回true，否false
     * .padStart(String string, int minLength, char padChar)：规定字符串的最小长度，若小于最小长度，开头补上指定字符
     * .padEnd(String string, int minLength, char padChar)：规定字符串的最小长度，若小于最小长度，结尾补上指定字符
     * .commonPrefix(CharSequence a, CharSequence b)：返回两个字符串相同的开头字符串，若无返回空字符串
     * .commonSuffix(CharSequence a, CharSequence b)：返回两个字符串相同的结尾字符串，若无返回空字符串
     */
    @Test
    public void stringsTest() {
        String s1 = null;
        System.out.println("1" + Strings.nullToEmpty(s1) + "2");
        String s2 = "";
        System.out.println(Strings.emptyToNull(s2));
        System.out.println(Strings.isNullOrEmpty(s2));

        String s3 = "12345";
        System.out.println(Strings.padStart(s3, 7, 'S'));
        System.out.println(Strings.padEnd(s3, 7, 'E'));

        String s4 = "abc18fduja";
        String s5 = "abcsfafjja";
        System.out.println(Strings.commonPrefix(s4, s5));
        System.out.println(Strings.commonSuffix(s4, s5));
    }
}
