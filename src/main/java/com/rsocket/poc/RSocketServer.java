package com.rsocket.poc;

import com.rsocket.Constants;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RSocketServer {
    private final Disposable server;

    public RSocketServer() throws UnknownHostException {
        String systemIp = InetAddress.getLocalHost().getHostAddress();
        this.server = RSocketFactory.receive()
                .acceptor((setupPayload, reactiveSocket) -> Mono.just(new CustomRSocketImpl()))
                .transport(TcpServerTransport.create(systemIp, Constants.TCP_PORT))
                .start()
                .doOnNext(x -> System.out.println("Server started"))
                .subscribe();
    }

    public void dispose() {
        this.server.dispose();
    }
}
