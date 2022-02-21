package xu.all;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestClazz {

    @Test
    public void testMethod() throws IOException {
        String date = "2022-02-21 15:00:00";
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(date);
        System.out.println(dateTime.toString());
    }
}
