package cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by air on 30/07/15.
 */
public class Memorizer<K, V> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Memorizer<Integer, String> memorizer = new Memorizer<>();
        String execute = memorizer.execute(3, new ExpensiveComputation());
        for (int i = 0; i < 20; i++) {
            final int a = i;
            new Thread(() -> {
                try {
                    memorizer.execute(a, new ExpensiveComputation());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    private final Map<K, FutureTask<V>> cache = new ConcurrentHashMap<>();// Put Future immediately instead of waiting
    // ExpensiveComputation finished

    public V execute(K key, Computable<K, V> computable) throws ExecutionException, InterruptedException {
        FutureTask<V> vFuture = cache.get(key);
        if (vFuture == null) {
            vFuture = new FutureTask(() -> computable.compute(key));
            cache.put(key, vFuture);
            vFuture.run();
        }
        V result = null;
        try {
            result = vFuture.get();
        } catch (CancellationException ex) {
            cache.remove(key);
        }
        return result;
    }

    static class ExpensiveComputation implements Computable<Integer, String> {

        @Override
        public String compute(Integer key) {
            System.out.println("Begin computation");
            return "Computed";
        }
    }
}

interface Computable<K, V> {
    V compute(K key);
}

