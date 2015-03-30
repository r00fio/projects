import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Created by bebe on 3/8/15.
 */
public class MyHazelcastClient {
    public static void main(String[] args) {
        ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();
        clientNetworkConfig.addAddress("localhost:5701");
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
        IMap<Integer, String> customers = hazelcastInstance.getMap("customers");
        customers.forEach((k, v) -> {
            System.out.println(k + ", " + v);
        });

    }
}
