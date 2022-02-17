package xu.all.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 与OkHttpDemo对应
 * @Author: xuyujun
 * @Date: 2022/1/14
 */
@Slf4j
@RestController
@RequestMapping("/okHttp")
public class OkHttpController {

    @GetMapping("/helloWorld")
    public String helloWorld(@RequestParam("isFail") Boolean isFail) throws Exception {
        if (isFail) {
            throw new Exception("我失败了");
        }
        return "helloWorld";
    }

    /*
        call.enqueue异步方法
        call.execute同步方法
     */
    @PostMapping("/send")
    public void send(@RequestParam("isFail") Boolean isFail) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = "http://localhost:8090/okHttp/helloWorld";
        HashMap<String, String> params = Maps.newHashMap();
        params.put("isFail", isFail.toString());
        Request request = new Request.Builder()
                .get()
                .url(parse(url,params))
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("WARNING WARNING WARNING");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });

//        new Thread(() -> {
//            try {
//                Response execute = call.execute();
//                System.out.println(execute.body().toString());
//            } catch (IOException e) {
//                log.error("系统异常", e);
//            }
//        }).start();
    }

    private static String parse(String url, Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        params.forEach((k, v) -> {
            try {
                builder.append(k).append("=").append(URLEncoder.encode(v == null ? "" : v, "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                log.error("系统异常", e);
            }
        });
        String parse = builder.toString();
        return parse.substring(0, parse.length() - 1);
    }
}
