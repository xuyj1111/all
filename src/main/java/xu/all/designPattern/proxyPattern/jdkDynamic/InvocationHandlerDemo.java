package xu.all.designPattern.proxyPattern.jdkDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerDemo implements InvocationHandler {

    private Object subject;

    public InvocationHandlerDemo(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method");
        System.out.println("Call Method: " + method);

        Object obj = method.invoke(subject, args);

        System.out.println("After method");
        System.out.println();
        return obj;
    }
}
