package xu.all.jdk;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoClass {

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
    public void patternDemo() {
        Pattern p = Pattern.compile("runoo+b");
        Matcher matcher = p.matcher("runoooooob");
        System.out.println(matcher.matches());

        System.out.println(Pattern.matches("runoo+b", "runoooooob"));
    }

}
