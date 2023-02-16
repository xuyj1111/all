package xu.all.test;

import org.junit.jupiter.api.Test;
import xu.all.annotation.SimpleAnno;
import xu.all.dto.TestAnnoDTO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {

    /**
     * @Description: byte数组和string相互转换
     */
    @Test
    public void byteStringConvert() {
        String str = "hello world";
        byte[] bytes = str.getBytes();
        System.out.println(new String(bytes));
    }

    /**
     * @Description: 校验网址
     */
    @Test
    public void verifyURL() {
        String url = "http://www.hahahaha.com";
        try {
            new URL(url);
            System.out.println("verify success");
        } catch (MalformedURLException e) {
            System.out.println("verify error");
        }
    }

    /**
     * @Description: Pattern的使用
     */
    @Test
    public void testPattern() {
        Pattern p = Pattern.compile("runoo+b");
        Matcher matcher = p.matcher("runoooooob");
        System.out.println(matcher.matches());

        System.out.println(Pattern.matches("runoo+b", "runoooooob"));
    }

    /**
     * @Description: 自定义注解
     */
    @Test
    public void testAnnotation() {
        TestAnnoDTO testAnnoDTO = new TestAnnoDTO();
        Class<? extends TestAnnoDTO> aClass = testAnnoDTO.getClass();
        SimpleAnno annotation = aClass.getAnnotation(SimpleAnno.class);
        if (Objects.isNull(annotation)) {
            System.out.println("该类没有使用 [SimpleAnno] 注解");
        } else {
            testAnnoDTO.setName(annotation.name());
            testAnnoDTO.setAge(annotation.age());
            System.out.println(testAnnoDTO);
        }
    }
}
