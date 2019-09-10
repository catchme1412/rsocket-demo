package com.rsocket;

import com.rsocket.Constants;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

public class ReqResClient {

    private final RSocket socket;
 
    public ReqResClient() {
        this.socket = RSocketFactory.connect()
          .transport(TcpClientTransport.create("localhost", Constants.TCP_PORT))
          .start()
          .block();
    }
 
    public String callBlocking(String string) {
        return socket
          .requestResponse(DefaultPayload.create(string))
          .map(Payload::getDataUtf8)
          .block();
    }
 
    public void dispose() {
        this.socket.dispose();
    }
}