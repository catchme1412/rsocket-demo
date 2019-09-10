package com.rsocket;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class DuplexClient {

  public static void main(String[] args) {
    RSocketFactory.receive()
        .acceptor(
            (setup, reactiveSocket) -> {
              reactiveSocket
                  .requestStream(DefaultPayload.create("Hello-Bidi"))
                  .map(Payload::getDataUtf8)
                  .log()
                  .subscribe();

              return Mono.just(new AbstractRSocket() {});
            })
        .transport(TcpServerTransport.create("localhost", 7000))
        .start()
        .subscribe();

    RSocket socket =
        RSocketFactory.connect()
            .acceptor(
                rSocket ->
                    new AbstractRSocket() {
                      @Override
                      public Flux<Payload> requestStream(Payload payload) {
                        return Flux.interval(Duration.ofSeconds(1))
                            .map(aLong -> DefaultPayload.create("Bi-di Response => " + aLong));
                      }
                    })
            .transport(TcpClientTransport.create("localhost", 7000))
            .start()
            .block();

    socket.onClose().block();
  }
}