package xu.all.jdk;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

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

}
