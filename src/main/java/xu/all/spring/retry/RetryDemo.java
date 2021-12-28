package xu.all.spring.retry;

import org.junit.jupiter.api.Test;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @Description: Spring Retry demo
 * @Author: xuyujun
 * @Date: 2021/9/23
 */
public class RetryDemo {

    private RetryTemplate retryTemplate = new RetryTemplate();

    private int num = 0;

    /**
     * @Description: 简单重试策略和指数退避策略
     */
    @Test
    public void SimpleRetryAndBackOff() throws Exception {
        //简单重试策略
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        //重试5次
        policy.setMaxAttempts(5);
        retryTemplate.setRetryPolicy(policy);

        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        //初试重试的间隔时间，即第一次重试
        exponentialBackOffPolicy.setInitialInterval(1000L);
        //每次重试的间隔倍数，1s→2s→4s...
        //exponentialBackOffPolicy.setMultiplier(2);
        //每次重试的间隔时间，毫秒
        exponentialBackOffPolicy.setMaxInterval(2000L);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        RetryCallback<String, Exception> retryCallback = context -> {
            try {
                System.out.println(System.currentTimeMillis());
                num++;
                if (num < 3) {
                    throw new Exception();
                }
                return null;
            } catch (Exception e) {
                throw new Exception();
            }
        };
        RecoveryCallback<String> recoveryCallback = context -> {
            //重试结束才会进，若业务正常不报错 则不进
            System.out.println("Retry the end");
            return null;
        };
        retryTemplate.execute(retryCallback, recoveryCallback);
    }
}
