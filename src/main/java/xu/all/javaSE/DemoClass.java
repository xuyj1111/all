package xu.all.javaSE;

import org.junit.jupiter.api.Test;

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

}
