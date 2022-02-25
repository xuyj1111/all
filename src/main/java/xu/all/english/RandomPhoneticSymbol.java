package xu.all.english;

import java.io.IOException;
import java.util.Random;

public class RandomPhoneticSymbol {
    private static String[] symbols = new String[]{
            "/ɑ:/", "/ɔ:/", "/ɜ:/", "/i:/", "/u:/",
            "/ʌ/", "/ɒ/", "/ə/", "/ɪ/", "/ʊ/", "/e/", "/æ/",
            "/eɪ/", "/aɪ/", "/ɔɪ/", "/ɪə/", "/eə/", "/ʊə/", "/əʊ/", "/aʊ/",
            "/p/", "/t/", "/k/", "/f/", "/θ/", "/s/", "/∫/", "/ts/", "/t∫/", "/tr/",
            "/b/", "/d/", "/g/", "/v/", "/ð/", "/z/", "/ʒ/", "/dz/", "/dʒ/", "/dr/",
            "/m/", "/n/", "/ŋ/",
            "/h/", "/l/", "/r/",
            "/j/", "/w/"};

    public static void main(String[] args) throws IOException {
        while (true) {
            int index = new Random().nextInt(symbols.length);
            System.out.println(symbols[index]);
            System.in.read();
        }
    }
}
