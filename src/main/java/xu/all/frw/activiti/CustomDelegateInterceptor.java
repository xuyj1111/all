package xu.all.frw.activiti;

import org.activiti.engine.impl.delegate.invocation.DelegateInvocation;
import org.activiti.engine.impl.interceptor.DelegateInterceptor;

public class CustomDelegateInterceptor implements DelegateInterceptor {


    public CustomDelegateInterceptor() {
    }

    @Override
    public void handleInvocation(DelegateInvocation invocation) {
        System.out.println("CustomDelegateInterceptor...");
        invocation.proceed();
    }
}
