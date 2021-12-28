package xu.all.javaBase.multithreading;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Integer call() {
        int value = 0;
        for (int i = 0; i < 100; i++) {
            value += i;
        }
        return value;
    }
}
