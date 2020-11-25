package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;

//@SpringBootApplication
//@EnableWebFlux
public class DemoApplication {

	public static void main(String[] args) throws Exception{
		//SpringApplication.run(DemoApplication.class, args);


		Flux.range(1, 3)
				.flatMap(DemoApplication::fetch, 4)
				.collect(Result::new, Result::add)
				.subscribe(i -> System.out.println(i + "---->" + Thread.currentThread().getName()));


		System.out.println("Done!!!");
	}



	private static Mono<Integer> fetch(int value) {
		System.out.println("fetch -->" + Thread.currentThread().getName());
		return Mono.fromCallable(() -> block(value)) // (1)
				.subscribeOn(Schedulers.parallel());            // (2)
	}

	private static int block(int value) {
		System.out.println("Running in -->" + Thread.currentThread().getName());
		return value;
	}


	public static class Result {
		static void  add(Result a, int b) {
			System.out.println("Adder called -->" + Thread.currentThread().getName());
		}
	}

}
