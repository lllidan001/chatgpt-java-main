package com.unfbx.chatgptsteamoutput;

import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import com.unfbx.chatgpt.OpenAiClient;

@SpringBootTest
class ChatgptSteamOutputApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        //配置api keys
        //代理可以为null
       Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        //Proxy proxy = null;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey("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")
                .connectTimeout(50)
                .writeTimeout(50)
                .readTimeout(50)
                .interceptor(Arrays.asList(httpLoggingInterceptor))
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                //.apiHost("http://www.baidu.com/")
                .build();
        CompletionResponse completions = openAiClient.completions("我想申请转专业，从计算机专业转到会计学专业，帮我完成一份两百字左右的申请书");
        Arrays.stream(completions.getChoices()).forEach(System.out::println);
    }

}
