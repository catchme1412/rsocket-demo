package com.rsocket.poc;

import com.rsocket.Constants;
import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomRSocketImpl extends AbstractRSocket {

    private  RequestPublisher requestPublisher;

    public CustomRSocketImpl() {
        requestPublisher = new RequestPublisher("server");
    }

    /**
     * Handle request for bidirectional channel
     *
     * @param payloads Stream of payloads delivered from the client
     * @return
     */
    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        Flux.from(payloads)
                .subscribe(requestPublisher::processPayload);
        Flux<Payload> channel = Flux.from(requestPublisher);
        return channel;
    }
}
