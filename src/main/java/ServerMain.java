import com.rsocket.Constants;
import com.rsocket.DataPublisher;
import com.rsocket.GameController;
import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        Thread.sleep(60000);
        server.dispose();
    }
}