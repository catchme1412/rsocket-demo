package com.rsocket.poc;

import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;


public class RequestPublisher implements Publisher<Payload> {

    private List<Subscriber<? super Payload>> subscribers;

    private String name;

    public RequestPublisher(String name) {
        this.name = name;
        subscribers = new ArrayList<>();
        System.out.println("RequestPublisher initialized...");
    }

    @Override
    public void subscribe(Subscriber<? super Payload> subscriber) {
        System.out.println("Subscription request received from " + subscriber);
        subscribers.add(subscriber);
        for (int i = 0; i < 10; i++) {
            subscriber.onNext(DefaultPayload.create("test" + name + i));
        }

    }

    public void processPayload(Payload payload) {
        System.out.println("Paylod received at " + name);
//        subscribers.get(0).onNext(payload);
    }

    public void complete() {
        subscribers.get(0).onComplete();
    }
}
