package com.rsocket;

import com.rsocket.ChannelClient;
import com.sun.security.ntlm.Client;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        ChannelClient channelClient = new ChannelClient();
        channelClient.playGame();
        Thread.sleep(5000);
        channelClient.dispose();
    }
}
