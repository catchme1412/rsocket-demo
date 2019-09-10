package com.rsocket.poc;

public class ClientMain {
    public static void main(String[] args) {
        ChannelClient channelClient = new ChannelClient();
        channelClient.start();
        channelClient.startChannel();
    }
}
