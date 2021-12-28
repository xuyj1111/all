package xu.all.frw.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GuavaDemo {

    @Test
    public void JoinerDemo() {
        List<String> list1 = Arrays.asList("aa", "bb", "cc");
        System.out.println(Joiner.on("-").join(list1));

        List<String> list2 = Arrays.asList("aa", "bb", "cc", "dd", null, "ff");
        System.out.println(Joiner.on("-").skipNulls().join(list2));
        System.out.println(Joiner.on("-").useForNull("nullValue").join(list2));

        System.out.println(Joiner.on("-").skipNulls().appendTo(new StringBuilder("abcdef:"), list2));

        Map map = ImmutableMap.of("k1", "v1", "k2", "v2");
        System.out.println(Joiner.on("-").withKeyValueSeparator("=").join(map));
    }

    @Test
    public void SplitterDemo() {
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

    @Test
    public void StringsDemo() {
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
