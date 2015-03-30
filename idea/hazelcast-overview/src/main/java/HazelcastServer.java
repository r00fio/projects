import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.TreeMap;
/**
 * Created by bebe on 3/6/15.
 * Equals vs compare on string
 */
public class HazelcastServer {

    public static void main(String[] args) {
        Config config = new Config();
//        NetworkConfig networkConfig = new NetworkConfig();
//        networkConfig.setPort(5703);
//        config.setNetworkConfig(networkConfig);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        IMap<Integer, String> customers = hazelcastInstance.getMap("customers");
        customers.put(1, "John Doe");
        customers.put(2, "Ben Grimm");
        customers.put(3, "Yury Grybkov");
        customers.put(4, "Nicolay Grybkov");

    }
}
