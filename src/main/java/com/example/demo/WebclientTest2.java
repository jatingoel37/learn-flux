package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebclientTest2 {

    public static void main(String[] args) throws Exception {
        WebClient client =  WebClient.create("https://wwwegenciacom.int-maui.sb.karmalab.net");
        Mono<String> result = client.get()
                .uri("/approval-service/base/version").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(s -> System.out.println("Received in ------------------------------------->" + Thread.currentThread().getName()))
                .doOnError(t-> System.out.println("----ERROR!!!!!!!"));

        //result.subscribe(s -> System.out.println("Received in -->" + Thread.currentThread().getName()));
        System.out.println("------------------------------------------------------------");
        System.out.println(result);
        result.subscribe(s -> System.out.println("Received in ------------------------------------->" + Thread.currentThread().getName()));
        System.out.println("==========DONE===========");


        //Thread.sleep(10000L);
    }
}
