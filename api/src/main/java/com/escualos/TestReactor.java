package com.escualos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class TestReactor {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i%2 == 0)
                .map(i -> Integer.toString(i))
                .subscribe(s -> {
                    System.out.println("FLUX: " + s);
                });

        Mono.just(1)
                .map(i -> Integer.toString(i))
                .subscribe(s -> {
                    System.out.println("MONO: " + s);
                });

        Arrays.stream(new Integer[] {1})
                .map(i -> Integer.toString(i))
                .forEach(s -> {
                    System.out.println("STREAM: " + s);
                });


    }

}
