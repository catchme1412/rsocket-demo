package com.rsocket.poc;

import reactor.core.publisher.Flux;

import java.net.UnknownHostException;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        RSocketServer rSocketServer = new RSocketServer();
        RequestPublisher requestPublisher = new RequestPublisher("server");
        Thread.sleep(50000);
        rSocketServer.dispose();
        requestPublisher.complete();
    }


}
