package com.rsocket.poc;

import com.rsocket.Constants;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import reactor.core.publisher.Flux;

public class ChannelClient {

    private RSocket socket;

    public void start() {
        System.out.println("init client");
        this.socket = RSocketFactory.connect()
                .transport(TcpClientTransport.create("192.168.138.121", Constants.TCP_PORT))
                .start()
                .block();
    }

    public void startChannel() {
        RequestPublisher requestPublisher = new RequestPublisher("client");
        System.out.println("start channel");
        socket.requestChannel(Flux.from(requestPublisher))
                .doOnNext(requestPublisher::processPayload)
                .blockLast();
    }

    public void dispose() {
        this.socket.dispose();
    }
}
