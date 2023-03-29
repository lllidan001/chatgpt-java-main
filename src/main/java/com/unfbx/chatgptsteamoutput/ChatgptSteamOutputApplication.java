package com.unfbx.chatgptsteamoutput;

import com.unfbx.chatgpt.OpenAiStreamClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * 描述：ChatgptSteamOutputApplication
 *
 * @author https:www.unfbx.com
 * @date 2023-02-28
 */
@SpringBootApplication
public class ChatgptSteamOutputApplication {

    @Value("${chatgpt.apiKey}")
    private String apiKey;
    @Value("${chatgpt.apiHost}")
    private String apiHost;

    public static void main(String[] args) {
        SpringApplication.run(ChatgptSteamOutputApplication.class, args);
        System.out.println("启动成功！");
    }


    @Bean
    public OpenAiStreamClient openAiStreamClient() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        return OpenAiStreamClient.builder().apiHost(apiHost).apiKey(apiKey).proxy(proxy).build();
    }

}
