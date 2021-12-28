package xu.all.frw.okHttp.controller;

import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/okHttp")
public class OkHttpController {

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    /*
        call.enqueue异步方法
        call.execute同步方法
     */
    @RequestMapping("/send")
    public void send() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody body = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .post(body)
                .url("http://localhost:8090/okHttp/helloWorld")
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
//                e.printStackTrace();
//            }
//        }).start();
    }
}
