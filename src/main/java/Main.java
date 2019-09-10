import com.rsocket.ChannelClient;
import com.rsocket.ReqResClient;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        channel();
        reqResonse();
    }

    private static void channel() throws InterruptedException {
        Server server = new Server();
        ChannelClient channelClient = new ChannelClient();
        channelClient.playGame();
        Thread.sleep(5000);
        channelClient.dispose();
        server.dispose();
    }

    private static void reqResonse() throws InterruptedException {
        Server server = new Server();
        ReqResClient client = new ReqResClient();
        String string = "Hello RSocket";

        String response = client.callBlocking(string);
        System.out.println(response);

        Thread.sleep(5000);
        client.dispose();
        server.dispose();
    }
}
