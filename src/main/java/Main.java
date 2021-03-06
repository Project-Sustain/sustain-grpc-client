import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sustain.DirectRequest;
import org.sustain.DirectResponse;
import org.sustain.SustainGrpc;

import java.util.Iterator;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] programArgs) {

        for(int i = 0; i < 1000; i++ ){
            ManagedChannel channel = ManagedChannelBuilder.forTarget("10.98.54.124:50051").usePlaintext().build();
            SustainGrpc.SustainBlockingStub sustainBlockingStub = SustainGrpc.newBlockingStub(channel);
            DirectRequest directRequest = DirectRequest.newBuilder()
                    .setCollection("test_collection")
                    .setQuery("test_query")
                    .build();

            Iterator<DirectResponse> responseIterator = sustainBlockingStub.echoQuery(directRequest);
            while (responseIterator.hasNext()) {
                DirectResponse response = responseIterator.next();
                log.info(response.getData());
            }
        }
    }
}
